#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int testing() {
  int n, m, pog, arr[n][m];
  printf("Insira o tamanho de colunas e de linhas da matriz: ");
  scanf("%d%d", &n, &m);

  printf("Insira os números para serem designados à array: ");
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      scanf("%d", &arr[i][j]);
    }
  }

  for (int i = 0; i < n - 1; i++) {
    for (int j = 0; j < m - 1; j++) {
      printf("%d", arr[i][j]);
    }
  }
  return 0;
}

int main(void) {
  FILE *fptr;
  fptr = fopen("nums.txt", "r");
  int arr[6], valueOld, valueNew, value;
  char nome[50];

  fscanf(fptr, "%d", &valueOld);

  printf("Valor gasto mês passado: %d \n", valueOld);

  printf("\nQual foi o valor gasto nesse mês com energia? ");
  scanf("%d", &valueNew);

  value = valueNew - valueOld;

  fclose(fptr);
  
  fscanf(fptr, "\n%s", &nome[50]);

  printf(
      "\nO valor gasto com energia na conta de:\n\n%s nesse mês foi de: %dkWh",
      &nome[50], value);

  printf("\n\nGravação em arquivo");

  fptr = fopen("leituraNova.txt", "w");
  fprintf(fptr, "O novo valor a ser pago nesse mês por %s é de %dkWh",
          &nome[50], value);
  fclose(fptr);
}