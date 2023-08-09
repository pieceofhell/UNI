#include <stdio.h>
#include <math.h>

int x, y;
float soma, divisao;

int sum(void) {
  printf("Insira os valores de x e y. \n");
  scanf("%d%d", &x, &y);
soma = x + y;
  return 0;
}

int main(void) {
  sum();
  printf("A média entre %d e %d é de: %.2f \n", x, y, soma/2);
  return 0;
}