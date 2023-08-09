#include <stdio.h>
#include <stdlib.h>

int main(){
    //planta da casa
    printf("planta da casa\n                   --------------\n                   |xxxxxxxxxxxx|\n                 1<|xxxxxxxxxxxx|\n                   --------|xxxx|\n                     2   3<|xxxx|\n                           ------\n                              4  \n");

    //variaveis
    float x1, x2, x3, x4, x5, y1, y2;

    //insercao dos valores
    printf("\ndigite o rendimento da lata de  tinta em litros por metro\t", y1);
    scanf("%f",&y1);
  
    printf("\ndigite a altura das paredes\t", y2);
    scanf("%f",&y2);
  
    printf("\ndigite o comprimento da parede 1\t", x1);
    scanf("%f",&x1);

    printf("\ndigite o comprimento da parede 2\t", x2);
    scanf("%f",&x2);

    printf("\ndigite o comprimento da parede 3\t", x3);
    scanf("%f",&x3);

    printf("\ndigite o comprimento da parede 4\t", x4);
    scanf("%f",&x4);

    //formula de resolucao
    x5=2*(3*(x1+x2))+2*(3*(x3+x4));
    y1=

    //resultado
    printf("\ntinta necessaria para pintura: %f\n\n", x5);

return 0;
}
