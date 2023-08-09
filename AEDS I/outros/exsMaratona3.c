#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
  int pecas, falt = 0;
  scanf("%d", &pecas);
  int qbr[pecas], certo[pecas];

  for (int i = 0; i < pecas - 1; i++) {
    scanf("%d", &qbr[i]);
  }
  for (int i = 0; i < pecas; i++) {
    certo[i] = i + 1;
  }

  for (int i = 0; i < pecas; i++) {
    printf("%d\n", certo[i]);
  }

  for (int i = 0; i < pecas; i++) {
    if (qbr[pecas] != certo[pecas]) {
      falt = certo[pecas];
    }
  }

  printf("%d", falt+1);
  return 0;
}