#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Jogador {
    int id;
    char nome[100];
    int altura;
    double peso;
    char universidade[100];
    char anoNascimento[10];
    char cidadeNascimento[100];
    char estadoNascimento[100];
};

void ler(struct Jogador *jogador, const char *nomeArquivo) {
    FILE *file = fopen(nomeArquivo, "r");
    if (file == NULL) {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char line[1024];
    int firstLine = 1;

    while (fgets(line, sizeof(line), file) != NULL) {
        if (firstLine) {
            firstLine = 0;
            continue;
        }

        char *token = strtok(line, ",");
        jogador->id = atoi(token);

        token = strtok(NULL, ",");
        strncpy(jogador->nome, token, sizeof(jogador->nome));

        token = strtok(NULL, ",");
        jogador->altura = atoi(token);

        token = strtok(NULL, ",");
        jogador->peso = atof(token);

        token = strtok(NULL, ",");
        strncpy(jogador->universidade, token, sizeof(jogador->universidade));

        token = strtok(NULL, ",");
        strncpy(jogador->anoNascimento, token, sizeof(jogador->anoNascimento));

        token = strtok(NULL, ",");
        strncpy(jogador->cidadeNascimento, token, sizeof(jogador->cidadeNascimento));

        token = strtok(NULL, ",");
        strncpy(jogador->estadoNascimento, token, sizeof(jogador->estadoNascimento));
    }

    fclose(file);
}

void buscarPorId(struct Jogador *jogador, int playerId, const char *nomeArquivo) {
    FILE *file = fopen(nomeArquivo, "r");
    if (file == NULL) {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char line[1024];
    int firstLine = 1;
    int found = 0;

    while (fgets(line, sizeof(line), file) != NULL) {
        if (firstLine) {
            firstLine = 0;
            continue;
        }

        char *token = strtok(line, ",");
        int id = atoi(token);

        if (id == playerId) {
            token = strtok(NULL, ",");
            strncpy(jogador->nome, token, sizeof(jogador->nome));

            token = strtok(NULL, ",");
            jogador->altura = atoi(token);

            token = strtok(NULL, ",");
            jogador->peso = atof(token);

            token = strtok(NULL, ",");
            strncpy(jogador->universidade, token, sizeof(jogador->universidade));

            token = strtok(NULL, ",");
            strncpy(jogador->anoNascimento, token, sizeof(jogador->anoNascimento));

            token = strtok(NULL, ",");
            strncpy(jogador->cidadeNascimento, token, sizeof(jogador->cidadeNascimento));

            token = strtok(NULL, ",");
            strncpy(jogador->estadoNascimento, token, sizeof(jogador->estadoNascimento));

            found = 1;
            break;
        }
    }

    fclose(file);

    if (found) {
        printf("[%d ## %s ## %d ## %.2lf ## %s ## %s ## %s ## %s]\n", jogador->id, jogador->nome, jogador->altura,
               jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento,
               jogador->estadoNascimento);
    } else {
        printf("ERRO\n");
    }
}

int main() {
    struct Jogador jogador;

    const char *arquivo = "players.csv";

    ler(&jogador, arquivo);

    char entrada[100];

    while (1) {
        fgets(entrada, sizeof(entrada), stdin);
        entrada[strlen(entrada) - 1] = '\0'; // Remover o caractere de nova linha

        if (strcmp(entrada, "FIM") == 0) {
            break;
        }

        int idJogador = atoi(entrada);
        buscarPorId(&jogador, idJogador, arquivo);
    }

    return 0;
}
