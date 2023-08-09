#include <math.h>
#include <stdio.h>

int main() {
  int figura;
  float base, altura, base_M, base_m, diagonal_M, diagonal_m, lado, raio;
  float area, PI = 3.1415;

  printf("Calculadora de área de figuras\n\nTriangulo - 1\nQuadrado  - "
         "2\nLosango   - 3\nCirculo   - 4\nTrapezio "
         " - 5\n\n Escolha uma das figuras acima digitando o seu numero "
         "correspondente. ");
  scanf("\n%d", &figura);

  switch (figura) {
  case 1:
    printf("Você selecionou o triangulo. Agora digite a medida de sua base e "
           "altura, seperados por um unico espaco. ");
    scanf("%f%f", &base, &altura);
    area = base * altura / 2;
    printf("Area do triangulo:\n\n %.f", area);
    break;
  case 2:
    printf(
        "Você selecionou o quadrado.\n Agora digite a medida de seus lados. ");
    scanf("%f", &lado);
    area = pow(lado, 2);
    printf("Area do triangulo:\n\n %.f", area);
    break;
  case 3:
    printf("Você selecionou o losango.\n Agora digite a medida de sua diagonal "
           "menor e diagonal maior, seperados por um unico espaco. ");
    scanf("%f%f", &diagonal_m, &diagonal_M);
    area = diagonal_M * diagonal_m / 2;
    printf("Area do losango:\n\n %.f", area);
    break;
  case 4:
    printf("Você selecionou o circulo.\n Agora digite a medida de seu raio. ");
    scanf("%f", &raio);
    area = PI * pow(raio, 2);
    printf("Area do circulo:\n\n %.f", area);
    break;
  case 5:
    printf("Voce selecionou o trapezio. Agora digite a medida de sua base "
           "maior, sua base menor e sua altura.\n Base maior: ");
    scanf("%f", &base_M);
    printf(" Base menor: ");
    scanf("%f", &base_m);
    printf(" Altura: ");
    scanf("%f", &altura);
    area = (base_M + base_m) * altura / 2;
    printf("Area do trapezio:\n\n %.f", area);
    break;
  }

  return 0;
}