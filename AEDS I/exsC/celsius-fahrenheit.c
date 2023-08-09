#include <stdio.h>

int main(void) {
  float celsius;
  float fahrenheit;
  printf("Insira a temperatura em Celsius.\n");
  scanf("%f", &celsius);
  fahrenheit = 1.8 * celsius + 32;
  printf("\nTemperatura em Fahrenheit: %.6f", fahrenheit);
  return 0;
}