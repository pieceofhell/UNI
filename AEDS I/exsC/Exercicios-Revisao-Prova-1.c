#include <math.h>
#include <stdio.h>

float quest1(void) {
  int N, num, i, j, numMediana;
  float somaTotal, mediaAritmetica, pontoMedio, mediana;
  long double desvioPadrao, desvioPadrao1, sigma;

  printf("Insira o valor equivalente a quantidade de números que serão "
         "inseridos. \n\n");
  scanf("%d", &N);
  float arr[N];
  printf("Insira os números. \n\n");

  for (i = 0; i < N; ++i) {
    scanf("%f", &arr[i]);
  }

  for (i = 0; i < N; ++i) {

    for (j = i + 1; j < N; ++j) {

      if (arr[i] > arr[j]) {
        num = arr[i];
        arr[i] = arr[j];
        arr[j] = num;
      }
    }
  }
  // teste

  printf("\nOs números inseridos, em ordem crescente, são: \n\n");

  for (i = 0; i < N; i++) {
    printf("%.2f\n", arr[i]);
  }

  for (i = 0; i < N; i++) {
    somaTotal += arr[i];
  }

  mediaAritmetica = somaTotal / N;

  for (i = 0; i < N; i++) {
    desvioPadrao = arr[i] - mediaAritmetica;
    desvioPadrao1 += desvioPadrao * desvioPadrao;
  }

  sigma = sqrt(desvioPadrao1 / N);

  if (N % 2 == 1) {
    numMediana = N / 2.0;
    mediana = arr[numMediana];
  } else {
    numMediana = N / 2.0;
    mediana = (arr[numMediana] + arr[numMediana - 1]) / 2.0;
  }

  pontoMedio = (arr[0] + arr[N - 1]) / 2.0;

  printf("\nMediana: %.2f", mediana);
  printf("\nMédia Aritmética: %.2f", mediaAritmetica);
  printf("\nPonto Médio: %.2f", pontoMedio);
  printf("\nDesvio Padrão: %.4Lf", sigma);
  return 0;
}

float quest2(void) {
  int N, mi, mj;
  float dist, maior = 0;

  printf("Insira o valor equivalente a quantidade de pares que serão "
         "inseridos.\n\n");
  scanf("%d", &N);
  float arr[N][2];

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < 2; j++) {
      printf("Insira o valor da posição - [%d][%d]: ", i, j);
      scanf("%f", &arr[i][j]);
    }
  }

  printf("\nPontos selecionados:\n\n");
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < 2; j++) {
      printf("(%.2f)    ", arr[i][j]);
      if (j == 1) {
        printf("\n");
      }
    }
  }

  for (int i = 0; i < N; i++) {
    for (int j = 1; j < N - i; j++) {
      dist =
          sqrt(pow(arr[i][0] - arr[j][0], 2) + pow(arr[i][1] - arr[j][1], 2));
      if (dist > maior) {
        maior = dist;
        mi = i;
        mj = j;
      }
    }
  }

  /*  for (int i = 0; i < N; i += 2) {
    distX = ((arr[0][0] < arr[1][0]) ? (arr[1][0] - arr[0][0])
                                     : (arr[0][0] - arr[1][0]));

    distY = ((arr[0][1] < arr[1][1]) ? (arr[1][1] - arr[0][1])
                                     : (arr[0][1] - arr[1][1]));
  } */

  printf("\nDistância: %.6f", maior);
  printf("\nPontos: (%.2f, %.2f)  e  (%.2f, %.2f)", arr[mi][0], arr[mi][1],
         arr[mj][0], arr[mj][1]);
  return 0;
}

int main() {
  int func;
  printf("Exercício 1: [1]\nExercício 2: [2]\n");
  scanf("%d", &func);
  if (func == 1) {
    printf("\nQuestão 1\n\n");
    quest1();
  } else {
    printf("\nQuestão 2\n\n");
    quest2();
  }

  return 0;
}
