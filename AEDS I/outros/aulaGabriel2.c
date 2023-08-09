#include <math.h>
#include <stdio.h>

int ex1(int n) {

  if (n == 1) {
    return 1;
  }
  return n * (ex1(n - 1));
}

int ex2(int n) {

  if (n == 1) {
    return 1;
  }
  return n + ex2(n - 1);
}

int ex3(int n, int m) {
  if (m == 0) {
    return 0;
  }
  m--;
  return n + ex3(n, m);
}

int ex4(int n, int total) {
  if (n == 0) {
    return 1;
  }
  total += ex4(n - 1, total);
  return total;
}

int main(void) {
  printf("Hello World\n\n", ex4(4, 0));

  return 0;
}