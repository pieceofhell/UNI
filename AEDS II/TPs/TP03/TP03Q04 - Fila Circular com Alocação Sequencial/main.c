#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definição da struct do tipo Jogador, contendo todos os atributos necessários

typedef struct Jogador
{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

// Função de ler, que recebe arquivo e um array de jogadores e preenche o array

void ler(FILE *entrada, Jogador *jogadores)
{
    char linha[300];
    fscanf(entrada, "%s", linha);
    char props[8][100];
    int i = 0;
    fgets(linha, 300, entrada);
    while (!feof(entrada))
    {
        fgets(linha, 300, entrada);

        int j = 0;
        int a = 0;
        for (int k = 0; k < strlen(linha); k++)
        {
            if (linha[k] == ',' || linha[k] == '\n')
            {
                props[j][a] = '\0';
                j++;
                a = 0;
            }
            else
            {
                props[j][a] = linha[k];
                a++;
            }
        }

        for (int b = 0; b < 8; b++)
        {
            if (strcmp(props[b], "") == 0)
            {
                strcpy(props[b], "nao informado");
            }
        }

        jogadores[i].id = atoi(props[0]);
        strcpy(jogadores[i].nome, props[1]);
        jogadores[i].altura = atoi(props[2]);
        jogadores[i].peso = atoi(props[3]);
        strcpy(jogadores[i].universidade, props[4]);
        jogadores[i].anoNascimento = atoi(props[5]);
        strcpy(jogadores[i].cidadeNascimento, props[6]);
        strcpy(jogadores[i].estadoNascimento, props[7]);

        i++;
    }
    fclose(entrada);
}

// Inicialização de uma struct do tipo Fila, contendo um ponteiro para Jogador, bem como os inteiros (que servirão como índices para a fila) inicio, fim e total

typedef struct Fila
{
    Jogador *arr;
    int inicio;
    int fim;
    int total;
} Fila;

// Inicialização padrão da fila, com alocação de memória para a array circular dos jogadores

Fila *start()
{
    Fila *fila = (Fila *)malloc(sizeof(Fila));
    fila->arr = (Jogador *)malloc(6 * sizeof(Jogador));
    fila->inicio = 0;
    fila->fim = 0;
    fila->total = 6;
    return fila;
}

// Método inserir exclusivo da fila circular, que recebe a fila e o jogador a ser inserido

// Caso o fim da fila seja igual ao total, o início da fila é incrementado, para que o jogador mais antigo seja removido

// O jogador é inserido no fim da fila e o fim é incrementado

void inserir(Fila *fila, Jogador jogador)
{
    if ((fila->fim + 1) % fila->total == fila->inicio)
    {
        fila->inicio = (fila->inicio + 1) % fila->total;
    }
    fila->arr[fila->fim] = jogador;
    fila->fim = (fila->fim + 1) % fila->total;
    float avg = 0;
    int count = 0;
    int i = fila->inicio;
    while (i != fila->fim)
    {
        avg += fila->arr[i].altura;
        count++;
        i = (i + 1) % fila->total;
    }
    avg /= count;
    printf("%d\n", (int)(avg + 0.5));
}

// Método remover exclusivo da fila circular, que recebe a fila e remove o jogador mais antigo

// O início da fila é incrementado e a mensagem de remoção é impressa

void remover(Fila *fila)
{
    Jogador removido = fila->arr[fila->inicio];
    fila->inicio = (fila->inicio + 1) % fila->total;
    printf("(R) %s\n", removido.nome);
}

void manipulacao(Fila *fila, Jogador *jogadores)
{
    char *metodo = (char *)malloc(3 * sizeof(char));
    scanf("%s", metodo);
    if (strcmp(metodo, "I") == 0)
    {
        int id;
        scanf("%d", &id);
        inserir(fila, jogadores[id]);
    }
    else if (strcmp(metodo, "R") == 0)
    {
        int id;
        scanf("%d", &id);
        remover(fila);
    }
    free(metodo);
}

void imprimir(Fila *fila)
{
    int tmp = 0;
    for (int i = fila->inicio; i != fila->fim; i = (i + 1) % fila->total)
    {
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", tmp,
               fila->arr[i].nome, fila->arr[i].altura, fila->arr[i].peso,
               fila->arr[i].anoNascimento, fila->arr[i].universidade,
               fila->arr[i].cidadeNascimento, fila->arr[i].estadoNascimento);
        tmp++;
    }
}

int main()
{
    FILE *arq = fopen("/tmp/players.csv", "r");

    // Inicialização do array de jogadores e da fila

    Jogador *jogadoresSelecionados = (Jogador *)malloc(10000 * sizeof(Jogador));
    Fila *fila = start();

    // Primeira parte do exercício;
    // Leitura dos jogadores e inserção na fila
    char *linha = (char *)malloc(23 * sizeof(char));
    int n;
    ler(arq, jogadoresSelecionados);
    scanf("%s", linha);
    while (strcmp(linha, "FIM") != 0)
    {
        inserir(fila, jogadoresSelecionados[atoi(linha)]);
        scanf("%s", linha);
    }

    // Segunda parte do exercício;
    // Manipulação da fila de jogadores nela presentes
    scanf("%d", &n);
    for (int i = 0; i < n; i++)
    {
        manipulacao(fila, jogadoresSelecionados);
    }
    imprimir(fila);
    return 0;
}
