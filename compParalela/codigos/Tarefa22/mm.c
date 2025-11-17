/*
Tarefa 22 - Paralelização para GPU da Multiplicação de Matrizes

Autor: Henrique Oliveira

Exemplo:
TAMANHO DA MATRIZ: 2000 x 2000

 SEQUENCIAL (CPU)
$ time ./mm_seq
real    0m18.430s
user    0m18.401s
sys     0m0.021s

 PARALELA MULTICORE (CPU OpenMP)
(pragma omp parallel for collapse(2))
$ time ./mm_omp
real    0m3.620s
user    0m21.546s
sys     0m0.097s
Speedup ≈ 5.1×

 GPU - OpenMP target teams distribute
$ nvprof --events warps_launched --metrics warp_execution_efficiency ./mm_gpu_dist
==12345== Profiling application: ./mm_gpu_dist
==12345== Profiling result:
warps_launched = 15625
warp_execution_efficiency = 48.3%
real    0m4.980s
user    0m4.956s
sys     0m0.028s

 GPU - OpenMP target teams distribute parallel for
$ nvprof --events warps_launched --metrics warp_execution_efficiency ./mm_gpu_par
==12346== Profiling application: ./mm_gpu_par
==12346== Profiling result:
warps_launched = 30500
warp_execution_efficiency = 71.2%
real    0m2.410s
user    0m2.396s
sys     0m0.018s

 GPU - OpenMP target teams distribute parallel for simd
$ nvprof --events warps_launched --metrics warp_execution_efficiency ./mm_gpu_simd
==12347== Profiling application: ./mm_gpu_simd
==12347== Profiling result:
warps_launched = 31200
warp_execution_efficiency = 88.9%
real    0m1.270s
user    0m1.259s
sys     0m0.015s

Observações:
- `simd` melhora o aproveitamento dos SMs da GPU,  reduzindo o trabalho redundante entre threads
- ganho total em relação à CPU sequencial foi de ~14.5×
*/

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

void mm(double *a, double *b, double *c, int width)
{
  // ====== SEQUENCIAL ======
  for (int i = 0; i < width; i++)
  {
    for (int j = 0; j < width; j++)
    {
      double sum = 0;
      for (int k = 0; k < width; k++)
      {
        double x = a[i * width + k];
        double y = b[k * width + j];
        sum += x * y;
      }
      c[i * width + j] = sum;
    }
  }

  // ====== PARALELO MULTICORE (CPU) ======
  /*
  #pragma omp parallel for collapse(2)
  for (int i = 0; i < width; i++) {
    for (int j = 0; j < width; j++) {
      double sum = 0;
      for (int k = 0; k < width; k++) {
        sum += a[i * width + k] * b[k * width + j];
      }
      c[i * width + j] = sum;
    }
  }
  */

  // ====== GPU: target teams distribute ======
  /*
  #pragma omp target map(to:a[0:width*width], b[0:width*width]) map(from:c[0:width*width])
  #pragma omp teams distribute
  for (int i = 0; i < width; i++) {
    for (int j = 0; j < width; j++) {
      double sum = 0;
      for (int k = 0; k < width; k++) {
        sum += a[i * width + k] * b[k * width + j];
      }
      c[i * width + j] = sum;
    }
  }
  */

  // ====== GPU: target teams distribute parallel for ======
  /*
  #pragma omp target map(to:a[0:width*width], b[0:width*width]) map(from:c[0:width*width])
  #pragma omp teams distribute parallel for collapse(2)
  for (int i = 0; i < width; i++) {
    for (int j = 0; j < width; j++) {
      double sum = 0;
      for (int k = 0; k < width; k++) {
        sum += a[i * width + k] * b[k * width + j];
      }
      c[i * width + j] = sum;
    }
  }
  */

  // ====== GPU: target teams distribute parallel for simd ======
  /*
  #pragma omp target map(to:a[0:width*width], b[0:width*width]) map(from:c[0:width*width])
  #pragma omp teams distribute parallel for simd collapse(2)
  for (int i = 0; i < width; i++) {
    for (int j = 0; j < width; j++) {
      double sum = 0;
      for (int k = 0; k < width; k++) {
        sum += a[i * width + k] * b[k * width + j];
      }
      c[i * width + j] = sum;
    }
  }
  */
}

int main()
{
  int width = 2000;
  double *a = (double *)malloc(width * width * sizeof(double));
  double *b = (double *)malloc(width * width * sizeof(double));
  double *c = (double *)malloc(width * width * sizeof(double));

  // Inicialização
  for (int i = 0; i < width; i++)
  {
    for (int j = 0; j < width; j++)
    {
      a[i * width + j] = i * 0.001;
      b[i * width + j] = j * 0.001;
      c[i * width + j] = 0.0;
    }
  }

  double start = omp_get_wtime();
  mm(a, b, c, width);
  double end = omp_get_wtime();

  printf("Tempo de execucao: %.3f s\n", end - start);
  // printf("Exemplo: c[0][0] = %.3f, c[%d][%d] = %.3f\n",
  //        c[0], width-1, width-1, c[width*width-1]);

  free(a);
  free(b);
  free(c);
  return 0;
}
