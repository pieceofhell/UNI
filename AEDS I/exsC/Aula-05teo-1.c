#include <math.h>
#include <stdio.h>
#define PI 3.1415

int main() {
  double raio, diam, volume, area;
  printf("Digite o diametro do esfera.\n");
  scanf("%lf", &diam);
  raio = diam / 2;
  volume = (4 * PI * pow(raio, 3)) / 3;
  area = PI * 4 * pow(raio, 2);
  printf("\nVolume da esfera: %.2f\n", volume);
  printf("Area da superficie esferica: %.2f\n\n", area);
  return 0;
}