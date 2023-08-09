#include <stdio.h>
#include <string.h>

#define L 50

int main(void) {
  // Comando: Criar um programa que leia a entrada de uma string do usuário
  // e verifique/conte a quantidade de caracteres que se repetem
  
  char str[L];
  char rev[L];
  scanf("%[^\n]", str);
  int l = strlen(str);
  for(int i=0;i<l;i++){
    rev[i] = str[l-1-i];
  }
  for(int i=0;i<l;i++){
    printf("%c", rev[i]);
  }
  return 0;
}