#include <stdio.h>

int ex1() {
  int n, num, i, j, pos = 0, neg = 0, zeros = 0;
  printf("Insira o tamanho da lista de números. ");
  scanf("%d", &n);
  float arr[n];
  printf("Insira os números da lista.\n");
  for (i = 0; i < n; i++) {
    printf("Número [%d]: ", i + 1);
    scanf("%d", &num);
    arr[i] = num;
  }
  for (i = 0; i < n; i++) {
    printf("\n%.2f", arr[i]);
    if (arr[i] > 0) {
      pos++;
    } else if (arr[i] == 0) {
      zeros++;
    } else {
      neg++;
    }
  }
  printf("\n\nNúmeros positivos: %d\nNúmeros negativos: %d\nZeros: %d", pos,
         neg, zeros);
  return 0;
}

int ex2() {
  int n, num, i, j, pos = 0, neg = 0, zeros = 0, T = 1, posT, negT, zeroT;
  printf("Insira o tamanho da lista de números. ");
  scanf("%d", &n);
  float arr[n];
  printf("Insira os números da lista.\n");
  for (i = 0; i < n; i++) {
    printf("Número [%d]: ", i + 1);
    scanf("%d", &num);
    arr[i] = num;
  }
  for (i = 0; i < n; i++) {
    printf("\n%.2f", arr[i]);
    if (arr[i] > 0) {
      pos++;
    } else if (arr[i] == 0) {
      zeros++;
    } else {
      neg++;
    }
  }
  
  posT = (pos * 100) / n;
  negT = (neg * 100) / n;
  zeroT = (zeros * 100) / n;
  printf("\n\nPorcentagem de números positivos: [%d]\nPorcentagem de números "
         "negativos: [%d]\nPorcentagem de zeros: [%d]",
         posT, negT, zeroT);
  return 0;
}

int main(void) {
  printf("Hello World\n");
  // ex1();
  ex2();
  return 0;
}