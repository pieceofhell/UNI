#include <stdio.h>
#include <time.h>

int main(void) {
    int qtd = 20;
    srand(time(NULL)); // inicializa o gerador com o tempo atual (aumenta o não determinismo)

    for (int i = 0; i < qtd; i++) {
        int x = rand();
        int rest = x % 2;

        if (rest == 0) {
            printf("par\n");
        } else {
            break;
        }
    }

    return 0;
}