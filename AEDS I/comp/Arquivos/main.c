#include <math.h>
#include <stdio.h>
#include <stdlib.h>

int main() {
  FILE *fptr, *fptr_out;
  double arr[8][8];
  double nums;

  printf("Programa de teste para manipulação de arquivos\n\n");

  fptr = fopen("tx.txt", "w");

  if (fptr == NULL) {
    printf("Erro ao abrir o arquivo!");
    exit(1);
  }

  double n1 = 1, n2 = 1, n3, i, num = 2;

  /* PRIMEIRO FPRINTF; IMPRIME 1 E 1 (ITERAÇÕES DE FIBONACCI 2 E 3) */
  fprintf(fptr, "%.2lf\n%.2lf\n", n1, n2);

  for (i = 2; i < 64; ++i) {
    n3 = n1 + n2;
    /* SEGUNDO FPRINTF; IMPRIME O RESTO DAS ITERAÇÕES DE FIBONACCI ATÉ 64 */
    fprintf(fptr, "%.2lf\n", n3);
    n1 = n2;
    n2 = n3;
  }

  fclose(fptr);

  /* LEITURA DE VALORES DO ARQUIVO E PREENCHIMENTO DA ARRAY */
  fptr = fopen("tx.txt", "r");

  if (fptr == NULL) {
    printf("Erro ao abrir o arquivo!");
    exit(1);
  }

  for (int i = 0; i < 8; ++i) {
    for (int j = 0; j < 8; j++) {
      fscanf(fptr, "%lf", &nums);
      arr[i][j] = nums;
    }
  }

  fclose(fptr);

  /* IMPRESSÃO DOS NÚMEROS DA ARRAY EM fiboArr */
  fptr_out = fopen("fiboArr.txt", "w");

  if (fptr_out == NULL) {
    printf("Erro ao criar o arquivo!");
    exit(1);
  }

  for (int i = 0; i < 8; ++i) {
    for (int j = 0; j < 8; j++) {
      fprintf(fptr_out, "%.2lf\n", arr[i][j]);
    }
  }

  fclose(fptr_out);

  /* IMPRESSÃO DOS NÚMEROS PARES DA ARRAY EM fiboPares */
  fptr_out = fopen("fiboPares.txt", "w");

  if (fptr_out == NULL) {
    printf("Erro ao criar o arquivo!");
    exit(1);
  }

  for (int i = 0; i < 8; ++i) {
    for (int j = 0; j < 8; j++) {
      if (fmod(arr[i][j], 2) == 0) {
        fprintf(fptr_out, "%.2lf\n", arr[i][j]);
      }
    }
  }

  fclose(fptr_out);

  /* IMPRESSÃO DOS NÚMEROS ÍMPARES DA ARRAY EM fiboImpares */
  fptr_out = fopen("fiboImpares.txt", "w");

  if (fptr_out == NULL) {
    printf("Erro ao criar o arquivo!");
    exit(1);
  }

  for (int i = 0; i < 8; ++i) {
    for (int j = 0; j < 8; j++) {
      if (fmod(arr[i][j], 2) == 1) {
        fprintf(fptr_out, "%.2lf\n", arr[i][j]);
      }
    }
  }

  fclose(fptr_out);

  return 0;
}