#include <math.h>
#include <stdio.h>

int ex1(void) {
  int i, n, ano_I = 2006, ano_User;
  float aumento = 0.015, sal_I = 1000;
  printf("Digite o nome do ano que você deseja conferir o salário deste "
         "funcionário.");
  scanf(" %d", &n);
  for (i = ano_I; i <= n; i++) {
    sal_I *= aumento + 1;
    aumento *= 2;
  }
  printf("O valor correspondente ao salário do funcionário nesse ano é de %.2f",
         sal_I);
  return 0;
}

int ex2() {
  int i, n = 1, num = 10, min, fatorial = 1;
  float numb = 1;
  if (num < 0) {
    printf("Não é possível trabalhar com números negativos.");
  } else {
    for (i = n; i <= num; i++) {
      fatorial *= i;
      numb += pow(fatorial, -1);
    }
    printf("\n\nNúmero: %.6f", numb);
  }
  return 0;
}

int ex3() {
  int i, n = 1, num, min, fatorial = 1, num_Count = 0;
  printf("Digite um número x qualquer. ");
  scanf("%d", &num);
  if (num < 0) {
    printf("Não há fatorial de números negativos.");
  } else {
    for (i = n; i <= num; ++i) {
      fatorial *= i;
      num_Count += 1;
      printf("\n\nFatorial: %d! = %d ", num_Count, fatorial);
    }
  }
  return 0;
}

int main(void) {
  int ex;
  printf("Selecione o exercício que você deseja ser executado.\n\nExercício 1: Salário do Funcionário\nExercício 2: Número Neperiano\nExercício 3: Fatoriais ");
  scanf("%d", &ex);
  if (ex == 1) {
    ex1();
  } else if (ex == 2) {
    ex2();
  } else {
    ex3();
  }
  return 0;
}