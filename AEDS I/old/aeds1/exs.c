#include <stdio.h>
#include <math.h>
#define PI 3.14


int main()
{
  int raio;
  int altura;
  float volume;
  printf("Digite o raio do cilindro.\n");
  scanf("%d", &raio);
  printf("Digite a altura do cilindro.\n");
  scanf("%d", &altura);
  volume = PI*raio*raio*altura;
  printf("Volume do cilindro: %.2f\n.", volume);
  return 0;
}
