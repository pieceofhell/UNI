#include <math.h>
#include <stdio.h>
#define PI 3.14

int main() {
  float raio;
  float altura;
  float volume;
  printf("Digite o raio do cilindro.\n");
  scanf("%f", &raio);
  printf("Digite a altura do cilindro.\n");
  scanf("%f", &altura);
  volume = pow(raio, 2) * PI * altura;
  printf("O volume do cilindro é %.2f\n.", volume);
  return 0;
}