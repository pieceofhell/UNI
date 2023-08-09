#include <math.h>
#include <stdio.h>

int ex1() {
  int i, n = 1;
  for (i = n; i <= 10; ++i) {
    printf("%d ", i);
  }
  return 0;
}

int ex2() {
  int i, n = 10;
  printf("\n\n");
  for (i = n; i >= 1; --i) {
    printf("%d ", i);
  }
  return 0;
}

int ex3() {
  int i, n = 1, num;
  printf("Digite um número x qualquer. ");
  scanf("%d", &num);
  for (i = n; i <= num; i += 2) {
    printf("%d ", i);
  }
  return 0;
}

int ex4() {
  int i, n = 1, num, min, fatorial = 1;
  printf("Digite um número x qualquer. ");
  scanf("%d", &num);
  if (num < 0) {
    printf("Não há fatorial de números negativos.");
  } else {
    for (i = n; i <= num; ++i) {
      fatorial *= i;
    }
    printf("\n\nFatorial de %d é: %d ", num, fatorial);
  }
  return 0;
}

int ex5(int n) {
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

int ex6() {
  int i, n = 20, soma = 0;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma = pow(-1, i + 1) * pow(i, 2);
    printf("%d ", soma);
  }
  return 0;
}

int exx() {
  int i, n, result, soma;
  printf("\n\nDigite um número x qualquer. ");
  scanf("%d", &n);
  for (i = n; i <= n; i++)
    ;
  soma = soma + pow(i, 2);
  printf("%d ", soma + i);
  return 0;
}

int main() {
  // ex1();
  // ex2();
  // ex3();
  // ex4();
  // ex5(21);
  ex6();
  return 0;
}
