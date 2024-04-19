#include <stdio.h>
int main() {
  short a;
  long b;
  long long c;
  long double d;

  printf("tamanho da variavel short = %d bytes\n", sizeof(a));
  printf("tamanho da variavel long = %d bytes\n", sizeof(b));
  printf("tamanho da variavel long long = %d bytes\n", sizeof(c));
  printf("tamanho da variavel long double= %d bytes\n", sizeof(d));
  return 0;
}
