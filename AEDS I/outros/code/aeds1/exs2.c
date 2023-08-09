#include <stdio.h>
#include <math.h>
#define PI 3.14


int main()
{
  int dias;
  float imposto;
  float volume;
  printf("Digite o numero de dias trabalhados pelo encanador.\n");
  scanf("%d", &dias);
  volume = dias*20;
  imposto = volume/100*8;
  printf("Valor total: %.2f\n.", volume - imposto);
  return 0;
}
