#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define xi *(x + i + 1)

double *aleatorio(int n, int lim1, int lim2) {
  srand(time(NULL));
  double *arr = malloc(n + 1);
  *arr = n;
  for (int i = 0; i < n; i++) {
    *(arr + i + 1) = (double) (rand() % abs(lim1 - lim2)) + ((lim1 < lim2) ? lim1 : lim2);
  }
  return arr;
}

void print(double *arr) {
  for (int i = 0; i < *arr; i++) {
    printf("%.2f, ", *(arr + i + 1));
  }
}

double func1(double *x) {
  double resp = 0;
  for (int i = 0; i < *x; i++) {
    resp += pow(xi, 2);
  }
  return resp;
}

double func2(double *x) {
  double resp = 0;
  for (int i = 0; i < *x-1; i++) {
    resp += 100 * (pow(xi, 2) - (xi + 1)) + pow(1 - xi, 2);
  }
  return resp;
}

int func3(double *x) {
  int resp = 0;
  for (int i = 0; i < *x; i++) {
    resp += (int)xi;
  }
  printf("\n%d", resp);
  return resp;
}

double func4(double *x) {
  double resp = 0;
  for (int i = 0; i < *x; i++) {
    resp += 1 / sqrt(2 * 3.141) * pow(2.71, 0.5 * xi);
  }
  return resp;
}

long double func5(double *x) {
  long double resp = 0.002;
  int list[5] = {-32, -16, 0, 16, 32};
  int aij = 0;
  double bottom;
  for (int j = 0; j < 25; j++) {
    bottom = j;
    for(int i = 0; i<*x; i++){
      if(i==0){
        aij = list[j%5];
      }else{
        aij = list[j/5];
      }
      bottom+=pow(xi - aij, 6);
    }
    resp += 1/bottom;
  }
  return resp;
}

double func6(double *x) {
  double resp;
  double x1;
  double x2;
  for (int i = 0; i < *x; i++) {
    if(i==0){
      x1 = xi;
    }else{
      x2 = xi;
    }
  }
  resp = pow(pow(x1, 2.0) + pow(x2, 2), 0.25) * pow(sin(50*pow(pow(x1, 2) + pow(x2, 2),0.1)),2) + 1.0;
  return resp;
}

double func7(double *x){
  double resp;
  double x1;
  double x2;
  for (int i = 0; i < *x; i++) {
    if(i==0){
      x1 = xi;
    }else{
      x2 = xi;
    }
  }

  resp = (pow(x1,2) + pow(x2,2))/2 - cos(20*3.141*x1)*cos(20*3.141*x2)+2;
  return resp;
}

double func8(double *x) {
  double resp = 10*(*x);
  for (int i = 0; i < *x; i++) {
    resp += pow(xi,2) - 10*cos(2*3.141*xi);
  }
  return resp;
}

int main(void) {
  int funcOpt;
  double* x;
  printf("Tabela de funções:\n\nFunção 1: sphere(quadratic) [1]\nFunção 2: Rosenbrock        [2]\nFunção 3: Degrau - De Jong  [3]\nFunção 4: Gauss - De Jong   [4]\nFunção 5: Shekel's Foxholes [5]\nFunção 6: Schaffer          [6]\nFunção 7: Sem Nome          [7]\nFunção 8: Rastrigin         [8]\n\nFunção desejada: ");
  scanf("%d", &funcOpt);
  switch(funcOpt){
    case 1:
      x = aleatorio(3, -100, 100);
      print(x);
      printf("\n\n%.2f", func1(x));
      break;
    case 2:
      x = aleatorio(2, -2048, 2048);
      print(x);
      for (int i = 0; i < *x; i++) {
        xi /= 1000;
      }
      printf("\n\n%.2f", func2(x));
      break;
    case 3:
      x = aleatorio(5, -512, 512);
      print(x);
      for (int i = 0; i < *x; i++) {
        xi /= 100;
      }
      printf("\n\n%d", func3(x));
      break;
    case 4:
      x = aleatorio(30, -128, 128);
      print(x);
      for (int i = 0; i < *x; i++) {
        xi /= 100;
      }
      printf("\n\n%.2f", func4(x));
      break;
    case 5:
      x = aleatorio(2, -65536, 65536);
      print(x);
      for (int i = 0; i < *x; i++) {
        xi /= 1000;
      }
      printf("\n\n%Lf", func5(x));
      break;
    case 6:
      x = aleatorio(2, -100, 100);
      print(x);
      printf("\n\n%.2f", func6(x));
      break;
    case 7:
      x = aleatorio(2, -10, 10);
      print(x);
      printf("\n\n%.2f", func7(x));
      break;
    case 8:
      x = aleatorio(20, -512, 512);
      for (int i = 0; i < *x; i++) {
        xi /= 100;
      }
      print(x);
      printf("\n\n%.2f", func8(x));
      break;
  }
  return 0; 
}