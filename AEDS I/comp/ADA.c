#include <stdio.h>

int misterio(int i) {
  switch (i) {
  case 0:
    return i;
  case 1:
    i *= i;
    break;
  case 2:
    i *= 2;
    break;
  case 3:
    i *= 3;
    break;
  default:
    i *= 4;
  }
  return misterio(i % 4);
}

int main(void) {
  int x1 = 4, x2 = 6, x3 = 9, x4 = 0;
  int i = 1;

  if (!((x3 % 3) == 0)) {
    printf("Olá Mundo!");
  }

  printf("%d\n", misterio(3));
  
  return 0;
}