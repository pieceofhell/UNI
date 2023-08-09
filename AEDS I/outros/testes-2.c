// Fibonacci Series using Space Optimized Method
#include <stdio.h>
int fib(int n) {
  int a = 0, b = 1, c, i;
  if (n == 0)
    return a;
  for (i = 2; i <= n; i++) {
    c = a + b;
    a = b;
    b = c;
  }
  return b;
}

int main() {
  int n;
  printf("Insira a posição desejada da série de Fibonacci. ");
  scanf("%d", &n);
  if (n <= 2) {
    printf("Posição inválida. Insira um número maior que 2.");
  } else {
    printf("\n %d\n", fib(n));
  }

  getchar();
  return 0;
}
