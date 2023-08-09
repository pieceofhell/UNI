#include <stdio.h>

int main(void) {
  float x, y, z;
  printf("3 - Insira os valores de x e y.\n");
  scanf(" %f%f", &x, &y);
  z = x - y;
  if (z == 0) {
    printf("\nA operacao diferenca entre x e y resulta em zero.");
  } else {
    printf("\nA operacao diferenca entre x e y nao resulta em zero.");
  }

return 0;
}