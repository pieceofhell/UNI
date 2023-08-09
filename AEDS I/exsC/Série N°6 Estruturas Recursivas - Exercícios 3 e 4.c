#include <math.h>
#include <stdio.h>

int ex1_1a() {
  int i = 2, r, p = 1, n, soma = 0;
  printf("\n\nDigite o valor de N: ");
  scanf("%i", &n);
  
  for (i = 2; i >= n - 1 || p == 0; ++i) {
    r = n % i;
    if (r == 0) {
      p = 0;
    }
  }
  printf("teste!!%d", i);
  return 0;
}
int main() {
  ex1_1a();
  return 0;
}