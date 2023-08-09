#include <stdio.h>
#include <string.h>

int main() {
  char nome[50], olhos[10], sexo;
  char profissao[50];
  int idade;
  int result_olhos;

  printf("Digite o seu nome: ");
  fgets(nome, 50, stdin);

  printf("Digite a sua idade: ");
  scanf("%d", &idade);

  printf("Digite o seu sexo (m/f): ");
  scanf(" %c", &sexo);

  printf("Digite a cor dos seus olhos: ");
  scanf(" %s", olhos);

  printf("Digite a sua profissao: ");
  scanf(" %s", profissao);

  result_olhos = strcmp(olhos, "verde");

  if (sexo == 'f' && idade < 20 && result_olhos == 0) {
    printf("Voce foi escolhida, %s", nome);
  } else {
    printf("Voce nao foi escolhido.");
  }

  return 0;
}
