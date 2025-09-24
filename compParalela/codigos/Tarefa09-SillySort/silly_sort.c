/*
Tarefa 09 - Silly Sort Paralelo

Autor: Henrique Oliveira

Compilação: gcc silly_sort.c -o silly_sort -fopenmp
Execução: ./silly_sort

Exemplo:

vetor entrada [3 7 2 1]
vetor auxiliar [2 3 1 0] // o elemento 3 deveria estar na posiçao 2, o elemento 7 deveria estar na posição 3, ...
vetor saída [1 2 3 7]

A partir do código desenvolvido, foram feitas 3 execuções com número de threads disponíveis diferentes, e seus resultados estão anotados a seguir:

RESULTADO DA EXECUÇÃO 1 (#pragma omp parallel for private(j) num_threads(2) schedule(static)):
test passed
Tempo: 0.937 segundos

real    0m0.942s
user    0m1.808s
sys     0m0.004s


RESULTADO DA EXECUÇÃO 2 (#pragma omp parallel for private(j) num_threads(2) schedule(dynamic)):
test passed
Tempo: 1.281 segundos

real    0m1.285s
user    0m2.564s
sys     0m0.000s


MELHOR EXECUÇÃO (menor tempo)! RESULTADO DA EXECUÇÃO 3 (#pragma omp parallel for private(j) num_threads(2) schedule(guided)):
test passed
Tempo: 0.921 segundos

real    0m0.924s
user    0m1.783s
sys     0m0.000s


RESULTADO DA EXECUÇÃO 3 (sequencial):
test passed

real    0m1.738s
user    0m1.736s
sys     0m0.000s

*/

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main() 
{
   int i, j, n = 30000; 

   // Allocate input, output and position arrays
   int *in  = (int*) calloc(n, sizeof(int));
   int *pos = (int*) calloc(n, sizeof(int));   
   int *out = (int*) calloc(n, sizeof(int));   

   // Initialize input array in the reverse order
   for(i=0; i < n; i++)
      in[i] = n-i;  

   double start = omp_get_wtime();

   // Silly sort paralelizado
   #pragma omp parallel for private(j) num_threads(2) schedule(static)
   for(i=0; i < n; i++) {
      for(j=0; j < n; j++) {
         if(in[i] > in[j]) 
            pos[i]++;
      }
   }

   // Move elements to final position
   #pragma omp parallel for num_threads(2) schedule(static)
   for(i=0; i < n; i++) 
      out[pos[i]] = in[i];
   
   double end = omp_get_wtime();

   // Check if answer is correct
   for(i=0; i < n; i++)
      if(i+1 != out[i]) 
      {
         printf("test failed\n");
         exit(0);
      }

   printf("test passed\n");
   printf("Tempo: %.3f segundos\n", end - start);

   free(in);
   free(pos);
   free(out);

   return 0;
}
