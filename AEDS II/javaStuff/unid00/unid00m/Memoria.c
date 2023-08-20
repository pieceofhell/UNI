#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define MAXTAM 10

int main(int argc, char const *argv[])
{   
    int a[10], *b;
    b = a;
    b[5] = 100;
    printf("\n % d-- % d", a[5], b[5]);
    b = (int *)malloc(10 * sizeof(int));
    b[7] = 100;
    printf("\n % d-- % d", a[7], b[7]);

    // int *x1;
    // int x2;
    // int *x3;
    // x1 = (int *)malloc(sizeof(int));
    // *x1 = 20;
    // x2 = *x1;
    // *x3 = x2 * *x1;
    // x3 = &x2;
    // x2 = 15;
    // x2 = 13 & 3;
    // x2 = 13 | 3;
    // x2 = 13 >> 1;
    // x2 = 13 << 1;

    return 0;
}