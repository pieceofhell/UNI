#include <stdio.h>
#include <stdlib.h>
#include <omp.h> // Para OpenMP

int main() {
  int width = 40000000;
  int size = width * sizeof(double);
  double *a = (double*) malloc (size);

  // Inicialização paralela (apenas para ser justo)
  #pragma omp parallel for
  for(int i = 0; i < width; i++) 
    a[i] = i;

  double sum = 0.0;

  // Medição de tempo
  double start_time = omp_get_wtime();

  // Redução paralela com OpenMP
  #pragma omp parallel for reduction(+:sum)
  for(int i = 0; i < width; i++) {
    sum += a[i];
  }

  double end_time = omp_get_wtime();
  double time_spent = (end_time - start_time); // em segundos

  printf("\n--- Versão OpenMP (CPU) ---\n");
  printf("Sum = %f\n", sum);
  printf("Tempo de Cálculo: %f ms\n", time_spent * 1000.0);

  free(a);
  return 0;
}