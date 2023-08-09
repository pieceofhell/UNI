#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *compacta(char *string) {
    int len = strlen(string);
    char *compactada = (char *) malloc((2 * len + 1) * sizeof(char)); // Aloca memória para a string compactada
    int i = 0, j = 0, count;

    while (i < len) {
        compactada[j++] = string[i]; // Copia a letra atual para a string compactada

        count = 1;
        while (i + 1 < len && string[i] == string[i + 1]) {
            count++; // Conta o número de ocorrências da letra atual
            i++;
        }

        if (count >= 1) {
            char count_str[10];
            sprintf(count_str, "%d", count); // Converte o número de ocorrências para string
            int count_len = strlen(count_str);
            strncpy(compactada + j, count_str, count_len); // Copia o número de ocorrências para a string compactada
            j += count_len;
        }

        i++;
    }

    compactada[j] = '\0'; // Adiciona o caractere nulo para finalizar a string compactada
    return compactada;
}

int main() {
    char string[] = "aaaaabcdddeeeffffff abc";
    char *compactada = compacta(string);
    printf("Compactada: %s\n", compactada);
    free(compactada); // Libera a memória alocada
    return 0;
}
