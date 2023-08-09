#include <stdio.h>

#define LINHAS 3
#define COLUNAS 4

void somarLinhas(int matriz[][COLUNAS], int vetorSoma[]) {
  for (int i = 0; i < LINHAS; i++) {
    int soma = 0;
    for (int j = 0; j < COLUNAS; j++) {
      soma += matriz[i][j];
    }
    vetorSoma[i] = soma;
  }
}

int main() {
  int matriz[LINHAS][COLUNAS] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
  int vetorSoma[LINHAS];

  somarLinhas(matriz, vetorSoma);

  printf("Somas das linhas:\n");
  for (int i = 0; i < LINHAS; i++) {
    printf("Linha %d: %d\n", i + 1, vetorSoma[i]);
  }

  return 0;
}