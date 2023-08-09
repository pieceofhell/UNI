#include <math.h>
#include <stdio.h>

int main(void) {
  int a = 2, b = 3, c = 4;
  float x, y, A, B, C;
  printf("a - %d\n", a > b);
  printf("b - %d\n\n", c == c);
  printf("c - Digite o valor de x.\n");
  scanf("%f", &x);
  printf("Digite o valor de y.\n");
  scanf("\n%f", &y);
  printf("\n%b", x > y);
  printf("\n%b", x < y);
  printf("\n%d", x == y);

  printf("\n\nd - Digite o valor de x.\n\n");
  scanf("\n%f", &x);
  printf("Digite o valor de y.\n");
  scanf("\n%f", &y);
  printf("Digite o valor de A.\n");
  scanf("\n%f", &A);
  printf("\n%d", pow(x, 2) - pow(y, 3) > pow(x, A + 4));

  printf("\n\ne - Digite o valor de A.\n");
  scanf("\n%f", &A);
  printf("Digite o valor de B.\n");
  scanf("\n%f", &B);
  printf("Digite o valor de C.\n");
  scanf("\n%f", &C);

  printf("\n%f", -B + sqrt((pow(B, 2) - 4 * A * C)) / 2 * A);
  return 0;
}