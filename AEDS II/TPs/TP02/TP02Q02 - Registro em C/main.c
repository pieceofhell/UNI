#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Jogador
{
    int id;
    char nome[100];
    int altura;
    double peso;
    char universidade[100];
    char anoNascimento[10];
    char cidadeNascimento[100];
    char estadoNascimento[100];
};

void lerJogadoresDoCSV(const char *nomeArquivo, struct Jogador jogadores[], int tamanho)
{
    FILE *arquivo = fopen(nomeArquivo, "r");
    if (arquivo == NULL)
    {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    char linha[256];
    int indice = 0;

    if (fgets(linha, sizeof(linha), arquivo) == NULL)
    {
        perror("Erro ao ler a linha de cabeçalho");
        exit(1);
    }

    while (fgets(linha, sizeof(linha), arquivo) != NULL && indice < tamanho)
    {
        struct Jogador *jogador = &jogadores[indice];

        strcpy(jogador->universidade, "nao informado");
        strcpy(jogador->cidadeNascimento, "nao informado");
        strcpy(jogador->estadoNascimento, "nao informado");

        sscanf(linha, "%d,%99[^,],%d,%lf,%99[^,],%9[^,],%99[^,],%99[^,]",
               &jogador->id, jogador->nome, &jogador->altura, &jogador->peso,
               jogador->universidade, jogador->anoNascimento, jogador->cidadeNascimento, jogador->estadoNascimento);
        indice++;
    }

    fclose(arquivo);
}

void adicionarJogadorPorID(int id, struct Jogador jogadores[], int tamanho, struct Jogador jogadoresSelecionados[], int *contadorSelecionados)
{
    for (int i = 0; i < tamanho; i++)
    {
        if (jogadores[i].id == id)
        {
            jogadoresSelecionados[*contadorSelecionados] = jogadores[i];
            (*contadorSelecionados)++;
            return;
        }
    }
    printf("ID %d nao encontrado.\n", id);
}

int main()
{
    struct Jogador jogadores[4000];
    int tamanhoJogadores = 4000;

    // Lê jogadores do arquivo CSV "players.csv"
    lerJogadoresDoCSV("players.csv", jogadores, tamanhoJogadores);

    struct Jogador jogadoresSelecionados[500];
    int contadorSelecionados = 0;

    int id;
    char entrada[500];

    while (1)
    {
        if (fgets(entrada, sizeof(entrada), stdin) == NULL)
        {
            break;
        }

        if (strcmp(entrada, "FIM\n") == 0)
        {
            break;
        }

        id = atoi(entrada);
        adicionarJogadorPorID(id, jogadores, tamanhoJogadores, jogadoresSelecionados, &contadorSelecionados);
    }

    // Exibe os jogadores selecionados

    char newline = '\n';
    for (int i = 0; i < contadorSelecionados; i++)
    {
        printf("[%d ## %s ## %d ## %.lf ## %s ## %s ## %s ## %s]%c",
               jogadoresSelecionados[i].id, jogadoresSelecionados[i].nome, jogadoresSelecionados[i].altura,
               jogadoresSelecionados[i].peso, jogadoresSelecionados[i].anoNascimento, jogadoresSelecionados[i].universidade,
               jogadoresSelecionados[i].cidadeNascimento, jogadoresSelecionados[i].estadoNascimento, newline);
    }

    return 0;
}
