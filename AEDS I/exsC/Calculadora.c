#include <math.h>
#include <stdio.h>

int soma(float x);
int sub(float x);
int multi(float x);
int div(float x);

int soma(float x) {
  float a, b, num = 1, op;
  if (x == 0) {
    printf("\nDigite os números que você deseja serem somados. \nNúmero %.f: ",
           num);
    scanf("%f", &a);
  } else {
    a = x;
  }

  printf("Número %.f: ", num + 1);
  scanf("%f", &b);
  x = a + b;
  printf("\nA soma de todos os números é: %.2f.\n\n", x);
  printf("Se quiser realizar mais operações, digite o número da operação de "
         "acordo com a tabela previamente apresentada acima. ");
  scanf("%f", &op);
  if (op == 1) {
    soma(x);
  }
  return 0;
}

int sub(float x) {
  float a, b, num = 1, op;
  if (x == 0) {
    printf("\nDigite os números que você deseja serem somados. \nNúmero %.f: ",
           num);
    scanf("%f", &a);
  } else {
    a = x;
  }

  printf("Número %.f: ", num + 1);
  scanf("%f", &b);
  x = a - b;
  printf("\nA subtração de todos os números é: %.2f.\n\n", x);
  printf("Se quiser realizar mais operações, digite o número da operação de "
         "acordo com a tabela previamente apresentada acima. ");
  scanf("%f", &op);
  if (op == 1) {
    soma(x);
  }
  if (op == 2) {
    sub(x);
  }
  if (op == 3) {
    multi(x);
  }
  if (op == 4) {
    div(x);
  }
  return 0;
}

int multi(float x) {
  float a, b, num = 1, op;
  if (x == 0) {
    printf("\nDigite os números que você deseja serem somados. \nNúmero %.f: ",
           num);
    scanf("%f", &a);
  } else {
    a = x;
  }

  printf("Número %.f: ", num + 1);
  scanf("%f", &b);
  x = a * b;
  printf("\nA subtração de todos os números é: %.2f.\n\n", x);
  printf("Se quiser realizar mais operações, digite o número da operação de "
         "acordo com a tabela previamente apresentada acima. ");
  scanf("%f", &op);
  if (op == 1) {
    soma(x);
  }
  if (op == 2) {
    sub(x);
  }
  if (op == 3) {
    multi(x);
  }
  if (op == 4) {
    div(x);
  }
  return 0;
}

int divisao(float x) {
  float a, b, num = 1, op;
  if (x == 0) {
    printf("\nDigite os números que você deseja serem somados. \nNúmero %.f: ",
           num);
    scanf("%f", &a);
  } else {
    a = x;
  }

  printf("Número %.f: ", num + 1);
  scanf("%f", &b);
  x = a / b;
  printf("\nA subtração de todos os números é: %.2f.\n\n", x);
  printf("Se quiser realizar mais operações, digite o número da operação de "
         "acordo com a tabela previamente apresentada acima. ");
  scanf("%f", &op);
  if (op == 1) {
    soma(x);
  }
  if (op == 2) {
    sub(x);
  }
  if (op == 3) {
    multi(x);
  }
  if (op == 4) {
    div(x);
  }
  return 0;
}

int main(void) {
  int op = 0;
  printf("Insira o tipo de operação desejada com base na tabela "
         "abaixo:\nAdição          - 1\nSubtração       - 2\nMultiplicação   - "
         "3\nDivisão         - 4\n\nSair do programa: 9 ");
  scanf("%d", &op);
  if (op == 1) {
    soma(0);
  }
  if (op == 2) {
    sub(0);
  }
  if (op == 3) {
    multi(0);
  }
  if (op == 4) {
    div(0);
  }
  return 0;
}