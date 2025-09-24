/*
Tarefa 07 - Crivo de Erastótenes com o padrão REDUCE

Autor: Henrique Oliveira

Compilação: gcc sieve.c -o sieve -fopenmp -lm
Execução: ./sieve

Exemplo de entrada: n = 100000000
Saída esperada: 5761455

A partir do código desenvolvido, foram feitas 3 execuções com número de threads disponíveis diferentes, e seus resultados estão anotados a seguir:

RESULTADO DA EXECUÇÃO 1 (static):
Quantidade de primos até 100000000: 5761455
Tempo: 1.087 segundos

real    0m1.092s
user    0m10.955s
sys     0m0.203s

RESULTADO DA EXECUÇÃO 2 (dynamic):
Quantidade de primos até 100000000: 5761455
Tempo: 5.973 segundos

real    0m5.977s
user    1m9.892s
sys     0m0.226s


RESULTADO DA EXECUÇÃO 3 (guided):
Quantidade de primos até 100000000: 5761455
Tempo: 1.295 segundos

real    0m1.299s
user    0m13.665s
sys     0m0.229s

RESULTADO DA EXECUÇÃO 3 (sequencial):
5761455

real    0m1.203s
user    0m1.117s
sys     0m0.084s
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <omp.h>

#define N 100000000 // limite superior do crivo
#define MODO 0
// 0 = static
// 1 = dynamic
// 2 = guided

int sieveOfEratosthenes(int n, int schedule_type)
{
    int primes = 0;
    char *prime = (char *)malloc((n + 1) * sizeof(char)); // 0 = não primo, 1 = primo
    int sqrt_n = sqrt(n);

    memset(prime, 1, (n + 1) * sizeof(char));

    // Crivo: marcar múltiplos
    for (int p = 2; p <= sqrt_n; p++)
    {
        if (prime[p] == 1)
        {
            if (schedule_type == 0)
            {
#pragma omp parallel for schedule(static)
                for (int i = p * 2; i <= n; i += p)
                    prime[i] = 0;
            }
            else if (schedule_type == 1)
            {
#pragma omp parallel for schedule(dynamic)
                for (int i = p * 2; i <= n; i += p)
                    prime[i] = 0;
            }
            else if (schedule_type == 2)
            {
#pragma omp parallel for schedule(guided)
                for (int i = p * 2; i <= n; i += p)
                    prime[i] = 0;
            }
        }
    }

// Contagem paralela com reduction
#pragma omp parallel for reduction(+ : primes) schedule(static)
    for (int p = 2; p <= n; p++)
    {
        if (prime[p] == 1)
            primes++;
    }

    free(prime);
    return primes;
}

int main()
{
    double start = omp_get_wtime();
    int total_primes;

    total_primes = sieveOfEratosthenes(N, MODO);

    double end = omp_get_wtime();

    printf("Quantidade de primos até %d: %d\n", N, total_primes);
    printf("Tempo: %.3f segundos\n", end - start);

    return 0;
}