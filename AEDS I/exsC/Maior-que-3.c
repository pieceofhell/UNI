
#include <stdio.h>
int maiorde3() {
  printf("\n\n\t\t Maior de 3 \n\n\n");
  double x1, x2, x3;
  printf("Entre 3 numeros nesta ordem x1, x2, x3\n");
  // Entrada de dados pelo teclado
  scanf("%lf%lf%lf", &x1, &x2, &x3);
  printf("\n\n  Os numeros que voce lancou sao %lf\t%lf\t%lf \n\n", x1, x2, x3);
  // Resolvendo o problema
  if (x1 >= x2 && x1 >= x3)
    printf("%.2f e o maior numero.", x1);
  if (x2 >= x1 && x2 >= x3)
    printf("%.2f e o maior numero.", x2);
  if (x3 >= x1 && x3 >= x2)
    printf("%.2f e o maior numero.", x3);
  return 0;
}

int main(); {
  printf("poggers"); }
