#include <stdlib.h>
#include <stdio.h>

int main(int argc, char **argv) {
    if (argc < 2) {
        printf("Uso: %s <n>\n", argv[0]);
        return 1;
    }

    int n = atoi(argv[1]);
    double *a, *b;

    char *raw = malloc(sizeof(double) * 2 * n + 1);
    a = (double *)(raw + 1);
    b = malloc(sizeof(double) * n);

    if (!a || !b) return 1;

    for (int i = 0; i < n; i++)
        b[i] = i * 0.5;

    for (int i = 0; i < n; i++) {
        a[2 * i] = b[i] + 1.0;
    }

    free(raw);
    free(b);
    return 0;
}