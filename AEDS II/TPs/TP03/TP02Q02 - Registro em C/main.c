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

void removerQuebraDeLinha(char *str)
{
    size_t len = strlen(str);
    if (len > 0 && (str[len - 1] == '\n' || str[len - 1] == '\r'))
    {
        str[len - 1] = '\0';
    }
}

void ler(const char *nomeArquivo, struct Jogador jogadores[], int tamanho)
{
    FILE *arquivo = fopen(nomeArquivo, "r");
    if (arquivo == NULL)
    {
        perror("ERRO ABERTURA ARQUIVO");
        exit(1);
    }

    char linha[256];
    int indice = 0;

    if (fgets(linha, sizeof(linha), arquivo) == NULL)
    {
        perror("ERRO CABEÇALHO");
        exit(1);
    }

    while (fgets(linha, sizeof(linha), arquivo) != NULL && indice < tamanho)
    {
        removerQuebraDeLinha(linha); // Remove quebra de linha e retorno de carro

        struct Jogador *jogador = &jogadores[indice];

        // Use strtok para dividir a linha em campos separados por vírgulas
        char *token = strtok(linha, ",");
        if (token != NULL)
        {
            jogador->id = atoi(token);
        }

        token = strtok(NULL, ",");
        if (token != NULL)
        {
            strncpy(jogador->nome, token, sizeof(jogador->nome));
        }

        token = strtok(NULL, ",");
        if (token != NULL)
        {
            jogador->altura = atof(token);
        }

        token = strtok(NULL, ",");
        if (token != NULL)
        {
            jogador->peso = atof(token);
        }

        token = strtok(NULL, ",");
        if (token != NULL)
        {
            strncpy(jogador->universidade, token, sizeof(jogador->universidade));
        }

        token = strtok(NULL, ",");
        if (token != NULL)
        {
            strncpy(jogador->anoNascimento, token, sizeof(jogador->anoNascimento));
        }

        token = strtok(NULL, ",");
        if (token != NULL)
        {
            strncpy(jogador->cidadeNascimento, token, sizeof(jogador->cidadeNascimento));
        }

        token = strtok(NULL, ",");
        if (token != NULL)
        {
            strncpy(jogador->estadoNascimento, token, sizeof(jogador->estadoNascimento));
        }

        // Preencha os campos ausentes com "nao informado" se necessário
        if (strlen(jogador->anoNascimento) == 0)
        {
            strcpy(jogador->anoNascimento, "nao informado");
        }

        if (strlen(jogador->universidade) == 0)
        {
            strcpy(jogador->universidade, "nao informado");
        }

        if (strlen(jogador->cidadeNascimento) == 0)
        {
            strcpy(jogador->cidadeNascimento, "nao informado");
        }

        if (strlen(jogador->estadoNascimento) == 0)
        {
            strcpy(jogador->estadoNascimento, "nao informado");
        }

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
    printf("Jogador %d nao encontrado.\n", id);
}

int main()
{
    struct Jogador jogadores[4000];
    int tamanhoJogadores = 4000;

    ler("/tmp/players.csv", jogadores, tamanhoJogadores);

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
