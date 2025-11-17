#include <stdio.h>
#include <stdlib.h>
#include <time.h> // Para time.h

int main() {
  int width = 40000000;
  int size = width * sizeof(double);
  double *a = (double*) malloc (size);
  
  for(int i = 0; i < width; i++) 
    a[i] = i;

  double sum = 0.0;
  
  // Medição de tempo
  struct timespec start, end;
  clock_gettime(CLOCK_MONOTONIC, &start);

  for(int i = 0; i < width; i++) {
    sum += a[i];
  }

  clock_gettime(CLOCK_MONOTONIC, &end);
  double time_spent = (end.tv_sec - start.tv_sec) +
                      (end.tv_nsec - start.tv_nsec) / 1e9; // em segundos

  printf("\n--- Versão Sequencial (CPU) ---\n");
  printf("Sum = %f\n", sum);
  printf("Tempo de Cálculo: %f ms\n", time_spent * 1000.0);
  
  free(a);
  return 0;
}