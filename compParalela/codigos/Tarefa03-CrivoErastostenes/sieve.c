/*
Tarefa 03 - Crivo de Eratóstenes paralelo com OpenMP
Autor: Henrique Oliveira

Compilação: gcc sieve.c -o sieve -fopenmp -lm
Execução: ./sieve

Exemplo de entrada: n = 100000000
Saída esperada: 5761455

Extra - Controle do número de threads no Windows:
Insira no terminal:

    $env:OMP_NUM_THREADS=1; ./sieve.exe     -> força 1 thread
    $env:OMP_NUM_THREADS=4; ./sieve.exe     -> força 4 threads
    Remove-Item Env:OMP_NUM_THREADS         -> reseta para padrão

A partir do código desenvolvido, foram feitas 3 execuções com número de threads disponíveis diferentes, e seus resultados estão anotados a seguir:

RESULTADO DA EXECUÇÃO 1 (sem setar número de threads):
Digite o valor de n: 100000000
Sequencial: 5761455 primos
Tempo sequencial: 1.06800 segundos
Paralelo: 5761455 primos
Tempo paralelo: 0.38800 segundos

RESULTADO DA EXECUÇÃO 2 (setando número de threads para 4):
Digite o valor de n: 100000000
Sequencial: 5761455 primos
Tempo sequencial: 1.08400 segundos
Paralelo: 5761455 primos
Tempo paralelo: 0.54000 segundos

RESULTADO DA EXECUÇÃO 3 (setando número de threads para 2):
Digite o valor de n: 100000000
Sequencial: 5761455 primos
Tempo sequencial: 1.03900 segundos
Paralelo: 5761455 primos
Tempo paralelo: 0.71700 segundos
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

int main()
{
    long long n;
    printf("Digite o valor de n: ");
    if (scanf("%lld", &n) != 1)
    {
        printf("Entrada invalida.\n");
        return 1;
    }

    // Aloca o vetor de flags para marcar se cada número é primo
    char *is_prime = (char *)malloc((n + 1) * sizeof(char));
    if (!is_prime)
    {
        printf("Erro de memoria!\n");
        return 1;
    }

    double start, end;

    // --- Sequencial ---
    // Inicializa todos como primos
    for (long long i = 0; i <= n; i++)
    {
        is_prime[i] = 1; // assume tudo primo
    }
    is_prime[0] = is_prime[1] = 0; // 0 e 1 não são primos

    long long limite = (long long)sqrt((double)n);

    start = omp_get_wtime(); // Marca o tempo inicial
    for (long long p = 2; p <= limite; p++)
    {
        if (is_prime[p])
        {
            // Marca múltiplos de p como não primos
            for (long long j = p * (long long)p; j <= n; j += p)
            {
                is_prime[j] = 0;
            }
        }
    }
    long long count_seq = 0;
    // Conta a quantidade de primos encontrados
    for (long long i = 2; i <= n; i++)
    {
        if (is_prime[i])
            count_seq++;
    }
    end = omp_get_wtime(); // Marca o tempo final
    double tempo_seq = end - start;

    printf("Sequencial: %lld primos\n", count_seq);
    printf("Tempo sequencial: %.5f segundos\n", tempo_seq);

    // --- Paralelo ---
    // Reinicializa todos como primos
    for (long long i = 0; i <= n; i++)
    {
        is_prime[i] = 1;
    }
    is_prime[0] = is_prime[1] = 0;

    start = omp_get_wtime(); // Marca o tempo inicial
// Paraleliza o laço principal do crivo
#pragma omp parallel for schedule(dynamic)
    for (long long p = 2; p <= limite; p++)
    {
        if (is_prime[p])
        {
            // Marca múltiplos de p como não primos
            for (long long j = p * (long long)p; j <= n; j += p)
            {
                is_prime[j] = 0;
            }
        }
    }

    long long count_par = 0;
// Conta os primos em paralelo usando redução
#pragma omp parallel for reduction(+ : count_par)
    for (long long i = 2; i <= n; i++)
    {
        if (is_prime[i])
            count_par++;
    }
    end = omp_get_wtime(); // Marca o tempo final
    double tempo_par = end - start;

    printf("Paralelo: %lld primos\n", count_par);
    printf("Tempo paralelo: %.5f segundos\n", tempo_par);

    free(is_prime); // Libera memória alocada
    return 0;
}
