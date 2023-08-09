#include <stdio.h>
  
int main(void)
{
  int x;
  int resultado;
  printf("Insira qualquer numero x: ");
  scanf("%d", &x);
  
  resultado = x % 2;
  if (resultado == 0) {
    printf("O número é par. ");
  } else {
    printf("O número é ímpar. "); 
  }
    printf("Número lido: %d", resultado);
  
    return 0;
  }