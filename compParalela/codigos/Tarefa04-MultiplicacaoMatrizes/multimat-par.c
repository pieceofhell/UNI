/*
Tarefa 04 - Multiplicação de Matrizes paralelo com OpenMP
Autor: Henrique Oliveira

Compilação: gcc multimat.c -o multimat -fopenmp -lm
Execução: time ./multimat

Extra - Controle do número de threads no Windows:
Insira no terminal:

  $env:OMP_NUM_THREADS=1; ./multimat.exe     -> força 1 thread
  $env:OMP_NUM_THREADS=4; ./multimat.exe     -> força 4 threads
  Remove-Item Env:OMP_NUM_THREADS         -> reseta para padrão

A partir do código desenvolvido, foram feitas 3 execuções com número de threads disponíveis diferentes, e seus resultados estão anotados a seguir:

RESULTADO DA EXECUÇÃO 1 (width = 1000):

Paralelo:
real    0m0.725s
user    0m7.998s
sys     0m0.060s
Sequencial:
real    0m2.733s
user    0m2.711s
sys     0m0.020s

RESULTADO DA EXECUÇÃO 2 (width = 1500):

Paralelo:
real    0m3.569s
user    0m40.677s
sys     0m0.249s
Sequencial:
real    0m11.675s
user    0m11.584s
sys     0m0.088s

RESULTADO DA EXECUÇÃO 3 (width = 2000):

Paralelo:
real    0m10.435s
user    2m2.175s
sys     0m0.214s
Sequencial:
real    0m59.538s
user    0m59.339s
sys     0m0.197s
*/

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>   // necessário para OpenMP

void mm(double* a, double* b, double* c, int width) 
{
  // paraleliza os dois primeiros loops (i e j)
  #pragma omp parallel for collapse(2)
  for (int i = 0; i < width; i++) {
    for (int j = 0; j < width; j++) {
      double sum = 0;
      for (int k = 0; k < width; k++) {
        double x = a[i * width + k];
        double y = b[k * width + j];
        sum += x * y;
      }
      c[i * width + j] = sum;
    }
  }
}

int main()
{
  int width = 2000;
  double *a = (double*) malloc (width * width * sizeof(double));
  double *b = (double*) malloc (width * width * sizeof(double));
  double *c = (double*) malloc (width * width * sizeof(double));

  for(int i = 0; i < width; i++) {
    for(int j = 0; j < width; j++) {
      a[i*width+j] = i;
      b[i*width+j] = j;
      c[i*width+j] = 0;
    }
  }

  mm(a,b,c,width);

  //  for(int i = 0; i < width; i++) {
  //  for(int j = 0; j < width; j++) {
  //    printf("\n c[%d][%d] = %f",i,j,c[i*width+j]);
  //  }
  // }
}
