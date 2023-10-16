#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
int comparacoes = 0;
int trocas = 0;

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
        removerQuebraDeLinha(linha);

        struct Jogador *jogador = &jogadores[indice];

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

void adicionarJogadoresPorID(struct Jogador jogadores[], int tamanhoJogadores, struct Jogador jogadoresSelecionados[], int *contadorSelecionados)
{
    char entrada[100];

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

        int id = atoi(entrada);
        if (id < 0 || id >= tamanhoJogadores)
        {
            printf("ID %d nao encontrado.\n", id);
        }
        else
        {

            jogadoresSelecionados[*contadorSelecionados] = jogadores[id];
            (*contadorSelecionados)++;
        }
    }
}

void countingSort(struct Jogador jogadores[], int tamanho, int lugar) {
    const int faixa = 10;
    int *contagem = (int *)malloc((faixa + 1) * sizeof(int));

    if (contagem == NULL) {
        perror("Erro na alocacao de memoria");
        exit(1);
    }

    for (int i = 0; i <= faixa; i++) {
        contagem[i] = 0;
    }

    for (int i = 0; i < tamanho; i++) {
        comparacoes++;
        contagem[(jogadores[i].id / lugar) % 10]++;
    }

    for (int i = 1; i <= faixa; i++) {
        contagem[i] += contagem[i - 1];
    }

    struct Jogador *saida = (struct Jogador *)malloc(tamanho * sizeof(struct Jogador));

    if (saida == NULL) {
        perror("Erro na alocacao de memoria");
        exit(1);
    }

    for (int i = tamanho - 1; i >= 0; i--) {
        trocas++;
        saida[contagem[(jogadores[i].id / lugar) % 10] - 1] = jogadores[i];
        contagem[(jogadores[i].id / lugar) % 10]--;
    }

    for (int i = 0; i < tamanho; i++) {
        jogadores[i] = saida[i];
    }

    free(contagem);
    free(saida);
}

int getMaxId(struct Jogador jogadores[], int tamanho) {
    int maxId = jogadores[0].id;
    for (int i = 1; i < tamanho; i++) {
        if (jogadores[i].id > maxId) {
            maxId = jogadores[i].id;
        }
    }
    return maxId;
}

void radixSort(struct Jogador jogadores[], int tamanho) {
    int maxId = getMaxId(jogadores, tamanho);

    for (int lugar = 1; maxId / lugar > 0; lugar *= 10) {
        countingSort(jogadores, tamanho, lugar);
    }
}


int main()
{
    FILE *fptr;
    clock_t inicio = clock();
    fptr = fopen("matricula_radixsort.txt", "w");
    struct Jogador jogadores[4000];
    int tamanhoJogadores = 4000;

    ler("/tmp/playersAtualizado.csv", jogadores, tamanhoJogadores);

    struct Jogador jogadoresSelecionados[500];
    int contadorSelecionados = 0;

    int id;
    char entrada[500];

    adicionarJogadoresPorID(jogadores, tamanhoJogadores, jogadoresSelecionados, &contadorSelecionados);
    radixSort(jogadoresSelecionados, contadorSelecionados);

    char newline = '\n';
    for (int i = 0; i < contadorSelecionados; i++)
    {
        printf("[%d ## %s ## %d ## %.lf ## %s ## %s ## %s ## %s]%c",
               jogadoresSelecionados[i].id, jogadoresSelecionados[i].nome, jogadoresSelecionados[i].altura,
               jogadoresSelecionados[i].peso, jogadoresSelecionados[i].anoNascimento, jogadoresSelecionados[i].universidade,
               jogadoresSelecionados[i].cidadeNascimento, jogadoresSelecionados[i].estadoNascimento, newline);
    }
    clock_t fim = clock();
    double tempoGasto = (double)(fim - inicio) / CLOCKS_PER_SEC;
    fprintf(fptr, "805688\t%d\t%d\t%.lfms", comparacoes, trocas, tempoGasto);
    fclose(fptr);
    return 0;
}
