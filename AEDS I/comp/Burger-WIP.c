#include <stdio.h>

int pedido2(void) {
  int menu, qntd;
  float v_Hamb=30, v_Cheese=35.5, v_fritas=20.5, v_Refrigerante=10, v_Milkshake=30;
  char s, n;
  printf("Selecione um item do Menu a seguir:\n\n1 - Hambúrguer............... "
         "R$ 30,00\n2 - Cheeseburger............. R$ 35,50\n3 - "
         "Fritas................... R$ 20,50\n4 - Refrigerante..............R$ "
         "10,00\n5 - Milkshake................ R$ 30,00\n\n");
  scanf("%d", &menu);
  switch (menu) {
  case 1:
    printf("\n\nVocê selecionou o Hambúrguer. Selecione a quantidade de itens "
           "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 2:
    printf(
        "\n\nVocê selecionou o Cheeseburguer. Selecione a quantidade de itens "
        "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 3:
    printf("\n\nVocê selecionou o Fritas. Selecione a quantidade de itens "
           "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 4:
    printf(
        "\n\nVocê selecionou o Refrigerante. Selecione a quantidade de itens "
        "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 5:
    printf("\n\nVocê selecionou o Milkshake. Selecione a quantidade de itens "
           "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 6:
    printf("\n\nVocê selecionou o Milkshake. Selecione a quantidade de itens "
           "consumidos. ");
    scanf("%d", &qntd);
  }

  printf("Deseja selecionar mais algum item do menu? s/n")
    scanf(" %c", opcao)
    
  
  if (opcao == 's') {
    
  }

  return 0;
}

int main(void) {
  int menu, qntd;
  char s, n;
  printf("Selecione um item do Menu a seguir:\n\n1 - Hambúrguer............... "
         "R$ 30,00\n2 - Cheeseburger............. R$ 35,50\n3 - "
         "Fritas................... R$ 20,50\n4 - Refrigerante..............R$ "
         "10,00\n5 - Milkshake................ R$ 30,00\n\n");
  scanf("%d", &menu);
  switch (menu) {
  case 1:
    printf("\n\nVocê selecionou o Hambúrguer. Selecione a quantidade de itens "
           "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 2:
    printf(
        "\n\nVocê selecionou o Cheeseburguer. Selecione a quantidade de itens "
        "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 3:
    printf("\n\nVocê selecionou o Fritas. Selecione a quantidade de itens "
           "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 4:
    printf(
        "\n\nVocê selecionou o Refrigerante. Selecione a quantidade de itens "
        "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 5:
    printf("\n\nVocê selecionou o Milkshake. Selecione a quantidade de itens "
           "consumidos. ");
    scanf("%d", &qntd);
    break;
  case 6:
    printf("\n\nVocê selecionou o Milkshake. Selecione a quantidade de itens "
           "consumidos. ");
    scanf("%d", &qntd);
  }

  printf("Deseja selecionar mais algum item do menu? s/n")
    scanf(" %c", opcao)
    
  
  if (opcao == 's') {
    pedido2();
  } else 
    
    printf("Você pagará ")

  return 0;
}