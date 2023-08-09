#include <stdio.h>
int main() {
  printf("\n\n\t\t Menor de 2 \n\n\n");
  double x1, x2;
  printf("Insira 2 numeros nesta ordem x1, x2.\n");
  // Entrada de dados pelo teclado
  scanf("%lf%lf", &x1, &x2);
  printf("\n\n  Os numeros que voce lancou sao \n%lf\t%lf\t \n\n", x1, x2);
  // Resolvendo o problema
  if (x1 >= x2)
    printf("%.2f e o menor numero.", x2);
  if (x2 >= x1)
    printf("%.2f e o menor numero.", x1);
  return 0;
}
