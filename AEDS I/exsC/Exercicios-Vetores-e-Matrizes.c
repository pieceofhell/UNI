#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define xi *(A + i + 1)

double *aleatorio(int n) {
  srand(time(NULL));
  double *arr = malloc(n + 1 * sizeof(int));
  *arr = n;
  for (int i = 0; i < n; i++) {
    *(arr + i + 1) = (double)(rand() % abs(-1 - 5)) + ((0 <= 5) ? 0 : 5);
  }
  return arr;
}

void print(double *arr) {
  for (int i = 0; i < *arr; i++) {
    printf("%.f, ", *(arr + i + 1));
  }
}

float soma(double *A) {
  double resp = 0;
  for (int i = 0; i < *A; i++) {
    resp += xi;
  }
  return resp;
}

float find(double *A) {
  int x;
  printf("\n\nInsira o valor de x. ");
  scanf("%d", &x);
  for (int i = 0; i < *A; i++) {
    if (x == xi) {
      printf("\nPosição do elemento: [%d]\n\n", i + 1);
    } else if (i == (*A - 1)) {
      printf("Não há elementos igual a x contidos no vetor.");
    }
  }
  return 0;
}

float big(double *A) {
  int maior = 0, j;
  for (int i = 0; i < *A; i++) {
    j = xi + 2;
    if (j < xi) {
      xi = maior;
      printf("\nPosição do maior elemento: [%d]\n\n", maior);
    }
  }
  return 0;
}

int main(void) {
  int ex, n;
  double *A;
  scanf("%d", &ex);
  switch (ex) {
  case 1:
    printf("\nQuestão 1:\n\n");
    A = aleatorio(100);
    print(A);
    printf("\n\n%.2f", soma(A));
    break;
  case 2:
    printf("\nQuestão 2:\n\n");
    A = aleatorio(100);
    print(A);
    printf("\n\n%.2f", find(A));
    break;
  case 3:
    printf("\nQuestão 3:\n\n");
    printf("\nInsira o valor da quantidade de números na sequência numérica. ");
    scanf("%d", &n);
    A = aleatorio(n);
    print(A);
    printf("\n\n%.2f", big(A));
    break;
  }
  return 0;
}