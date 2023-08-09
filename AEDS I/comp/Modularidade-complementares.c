#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

float *aleatorio(int i) {
  float max = 100, min = -100;

  if (min > max) {
    printf("Min value is greater than max value\n");
    return 0;
  }

  srand(time(0));

  printf("10 Random Numbers between %.f and %.f => ", min, max);
  float *var_x, *x;
  for (i = 0; i < 3; i++) {
    var_x[i] = min + ((float)rand() / (RAND_MAX / (max - min)));
    printf("%.f ", x[i]);
  }

  return var_x;
}

float f1(int n, double *x) {

  float soma = 0;
  for (int i = 0; i < n; i++) {
    soma = soma + pow(x[i], 2.0);
  }
  return soma;
}

float f2(int n, double *x) {

  float soma = 0;
  for (int i = 0; i < n - 1; i++) {
    soma = soma + (100 * pow(pow(x[i], 2.0) - x[i + 1], 2.0) + pow((1 - x[i]), 2.0));
  }
  return soma;
}

float f3(int n, double *x) {

  float soma = 0;
  for (int i = 0; i < n; i++) {
    soma = soma + x[i];
  }
  return soma;
}

int main(void) {
  int func;
  printf("Insira a função que você deseja ser executada. ");
  scanf("%d", &func);
  if (func == 1) {
    int n = 3;
    double fx;

    double x[3] = {2.0, -4.0, 10.0};
    fx = f1(n, x);
    printf("A função é: %.2lf\n", fx);
    return 0;
  }

  if (func == 2) {
    int n = 3;
    double fx;

    double x[3] = {2.0, -4.0, 10.0};
    fx = f2(n, x);
    printf("A função é: %.2lf\n", fx);
    return 0;
  }

  if (func == 3) {
    int n = 3;
    double fx;

    double x[3] = {2.0, -4.0, 10.0};
    fx = f3(n, x);
    printf("A função é: %.lf\n", fx);
    return 0;
  }

  //  aleatorio();
  return 0;
}