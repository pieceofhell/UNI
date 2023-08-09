#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
  int n;
  scanf("%d", &n);
  float enc = 0, tabs = 0;

  for (int i = 0; i < n; i++) {
    scanf("%f", &enc);
    if (enc <= 0) {
      enc = 0;
    } else {
      tabs += enc;
    }
  }
  tabs = ceil(tabs / 16);
  printf("%d", (int)tabs + 1);
  return 0;
}