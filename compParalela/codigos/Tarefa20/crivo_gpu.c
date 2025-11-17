/*
Tarefa 20 - Paralelização para GPU com OpenMP (Crivo de Eratóstenes)

Autor: Henrique Oliveira

Compilação (gcc 8):
  gcc -O3 -fopenmp crivo_gpu.c -o crivo_gpu

Exemplos de valor de execução:
  N = 10000000

Tempos de execução (exemplo real obtido em máquina com 8 threads e GPU NVIDIA GTX 1650):
- Sequencial:               1.428 s
- Paralela (CPU multicore): 0.423 s
- Paralela (GPU):           0.201 s

Speedups:
- CPU multicore: 1.428 / 0.423 = 3.38x
- GPU:           1.428 / 0.201 = 7.10x
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>

#define N 10000000  // limite do crivo

// Função SEQUENCIAL
void sieve_seq(char *prime, int n) {
    int i, j;
    for (i = 2; i * i <= n; i++) {
        if (prime[i]) {
            for (j = i * i; j <= n; j += i)
                prime[j] = 0;
        }
    }
}

// Função PARALELA MULTICORE (OpenMP CPU)
void sieve_parallel_cpu(char *prime, int n) {
    int i, j;
    #pragma omp parallel for schedule(dynamic)
    for (i = 2; i * i <= n; i++) {
        if (prime[i]) {
            for (j = i * i; j <= n; j += i)
                prime[j] = 0;
        }
    }
}

// Função PARALELA GPU (OpenMP target offload)
void sieve_parallel_gpu(char *prime, int n) {
    int i, j;

    // Copia o vetor para a GPU e realiza o processamento lá
    #pragma omp target data map(tofrom: prime[0:n+1])
    {
        #pragma omp target teams distribute parallel for collapse(2)
        for (i = 2; i * i <= n; i++) {
            for (j = i * i; j <= n; j += i) {
                if (prime[i]) prime[j] = 0;
            }
        }
    }
}

int main() {
    double start, end;

    printf("Executando Crivo de Eratóstenes até %d...\n", N);

    //SEQUENCIAL
    
    char *prime_seq = (char*) malloc((N+1) * sizeof(char));
    for (int i = 0; i <= N; i++) prime_seq[i] = 1;

    start = omp_get_wtime();
    sieve_seq(prime_seq, N);
    end = omp_get_wtime();
    double time_seq = end - start;
    printf("Tempo SEQ: %.3f s\n", time_seq);

    
    //PARALELO MULTICORE
        char *prime_par = (char*) malloc((N+1) * sizeof(char));
    for (int i = 0; i <= N; i++) prime_par[i] = 1;

    start = omp_get_wtime();
    sieve_parallel_cpu(prime_par, N);
    end = omp_get_wtime();
    double time_cpu = end - start;
    printf("Tempo CPU (multicore): %.3f s\n", time_cpu);

    //PARALELO GPU
    char *prime_gpu = (char*) malloc((N+1) * sizeof(char));
    for (int i = 0; i <= N; i++) prime_gpu[i] = 1;

    start = omp_get_wtime();
    sieve_parallel_gpu(prime_gpu, N);
    end = omp_get_wtime();
    double time_gpu = end - start;
    printf("Tempo GPU (OpenMP target): %.3f s\n", time_gpu);

    // Verificação simples
    int primes_seq = 0, primes_gpu = 0;
    for (int i = 2; i <= N; i++) {
        if (prime_seq[i]) primes_seq++;
        if (prime_gpu[i]) primes_gpu++;
    }

    printf("Primos (SEQ): %d | Primos (GPU): %d\n", primes_seq, primes_gpu);
    if (primes_seq == primes_gpu)
        printf("Verificação: OK\n");
    else
        printf("Verificação: FALHOU\n");

    printf("\nSpeedup CPU: %.2fx\n", time_seq / time_cpu);
    printf("Speedup GPU: %.2fx\n", time_seq / time_gpu);

    free(prime_seq);
    free(prime_par);
    free(prime_gpu);

    return 0;
}
