#include <stdio.h>
int n, m;
mediaMatriz(int arr[n][m], int vetorMedia[n]);

int main(void) {
  int n, m, arr[n][m], vetorMedia[n];
  printf("Insira a quantidade de linhas e colunas da matriz:\n");
  scanf("%d%d", &n, &m);
  mediaMatriz(arr[n][m], vetorMedia[n]);
  return 0;
}

void mediaMatriz(int arr[n][m], int vetorMedia[n]) {
  int soma, media;
  for (int i = 0; i < n; i++) {
    soma = 0;
    for (int j = 0; j < m; j++) {
      soma += arr[i][j];
      media = soma / m;
    }
    vetorMedia[i] = media;
  }
  for (int i = 0; i < n; i++) {
    printf("Média da linha %d: %d\n", i + 1, vetorMedia[i]);
  }
}
