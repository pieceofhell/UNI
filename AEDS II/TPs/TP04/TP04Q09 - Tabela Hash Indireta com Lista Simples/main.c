#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int comparisons = 0;

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

void ler(FILE *arq, Jogador *jogadores)
{
    char dados[300];
    fscanf(arq, "%s", dados);
    char propriedades[8][100];
    int i = 0;
    fgets(dados, 300, arq);
    while (!feof(arq))
    {
        fgets(dados, 300, arq);

        int j = 0;
        int a = 0;
        size_t dados_length = strlen(dados);

        for (size_t k = 0; k < dados_length; k++)
        {
            char current_char = dados[k];

            if (current_char == ',' || current_char == '\n')
            {
                propriedades[j][a] = '\0';
                j++;
                a = 0;
            }
            else
            {
                propriedades[j][a] = current_char;
                a++;
            }
        }

        for (int b = 0; b < 8; b++)
        {
            if (propriedades[b][0] == '\0')
            {
                strcpy(propriedades[b], "nao informado");
            }
        }

        jogadores[i].id = atoi(propriedades[0]);
        strcpy(jogadores[i].nome, propriedades[1]);
        jogadores[i].altura = atoi(propriedades[2]);
        jogadores[i].peso = atoi(propriedades[3]);
        strcpy(jogadores[i].universidade, propriedades[4]);
        jogadores[i].anoNascimento = atoi(propriedades[5]);
        strcpy(jogadores[i].cidadeNascimento, propriedades[6]);
        strcpy(jogadores[i].estadoNascimento, propriedades[7]);

        i++;
    }
    fclose(arq);
}

// Funcao de comparacao de nomes

int compareTo(const char *strX, const char *strY)
{
    int i = 0;
    while (i < strlen(strX) && i < strlen(strY))
    {
        if (strX[i] < strY[i])
        {
            return -1;
        }
        if (strX[i] > strY[i])
        {
            return 1;
        }
        i++;
    }
    if (strlen(strX) < strlen(strY))
    {
        return -1;
    }
    if (strlen(strX) > strlen(strY))
    {
        return 1;
    }
    return 0;
}

// Declaracao da estrutura de celula da lista

typedef struct Node
{
    Jogador *jogador;
    struct Node *prox;
} Node;

// Declaração da estrutura de tabela hash

typedef struct Tabela
{
    Node *tabela;
    int tam;
} Tabela;

// Funcoes de manipulacao da tabela hash

Jogador *start(Jogador jogador)
{
    Jogador *tmp = (Jogador *)malloc(sizeof(Jogador));
    *tmp = jogador;
    return tmp;
}

Node *startNode(Jogador jogador)
{
    Node *tmp = (Node *)malloc(sizeof(Node));
    tmp->jogador = start(jogador);
    tmp->prox = NULL;
    return tmp;
}

Node *emptyNode()
{
    Node *tmp = (Node *)malloc(sizeof(Node));
    tmp->jogador = NULL;
    tmp->prox = NULL;
    return tmp;
}

Tabela *startTabela(int x)
{
    Tabela *newTabela = (Tabela *)malloc(sizeof(Tabela));
    newTabela->tam = x;
    newTabela->tabela = (Node *)malloc(x * sizeof(Node));
    for (int i = 0; i < newTabela->tam; i++)
    {
        newTabela->tabela[i] = *emptyNode();
    }
    return newTabela;
}

Tabela *emptyTabela()
{
    Tabela *newTabela = (Tabela *)malloc(sizeof(Tabela));
    newTabela->tam = 0;
    newTabela->tabela = NULL;
    return newTabela;
}

int hash(Jogador jogador, int tam)
{
    return jogador.altura % tam;
}

void insert(Tabela *tabela, Jogador jogador)
{
    int pos = hash(jogador, tabela->tam);
    Node *head = &tabela->tabela[pos];
    Node *tmp = startNode(jogador);

    tmp->prox = head->prox;
    head->prox = tmp;
}

int search(Tabela *tabela, char *nome)
{
    for (int i = 0; i < tabela->tam; i++)
    {
        Node *tmp = tabela->tabela[i].prox;
        while (tmp != NULL)
        {
            comparisons += 2;
            if (strcmp(nome, tmp->jogador->nome) == 0)
            {
                return 1;
            }
            tmp = tmp->prox;
        }
    }
    return 0;
}

int main(void)
{
    clock_t inicio = clock();

    // PARTE 1 DO EXERICICO (LEITURA E ARMAZENAMENTO DOS JOGADORES)

    FILE *arq = fopen("/tmp/players.csv", "r");
    Jogador *jogadores = (Jogador *)malloc(5000 * sizeof(Jogador));

    ler(arq, jogadores);

    Tabela *tabelaAlpha = startTabela(25);

    char entrada[23];

    scanf("%s", entrada);

    // PARTE 2 DO EXERCICIO (INSERCAO DE JOGADORES NA TABELA HASH)

    while (1)
    {
        if (strcmp(entrada, "FIM") == 0)
        {
            break;
        }
        insert(tabelaAlpha, jogadores[atoi(entrada)]);
        scanf("%s", entrada);
    }

    // PARTE 3 DO EXERCICIO (BUSCA DE JOGADORES NA TABELA HASH)

    getchar();
    scanf("%[^\n]", entrada);
    while (1)
    {
        if (strcmp(entrada, "FIM") == 0)
        {
            break;
        }
        int res = search(tabelaAlpha, entrada);
        printf(entrada);
        if (res == 1)
        {
            printf(" SIM\n");
        }
        else
        {
            printf(" NAO\n");
        }
        getchar();
        scanf("%[^\n]", entrada);
    }

    // PARTE 4 DO EXERCICIO (REGISTRO DE MATRICULA)

    FILE *matricula = fopen("matricula_hashIndireta.txt", "w");
    clock_t fim = clock();
    double tempo = ((double)(fim - inicio)) / CLOCKS_PER_SEC * 1000.0;
    char mat[] = "805688";
    
    fprintf(matricula, "%s\t%lf\t%d", matricula, tempo, comparisons);
    fclose(matricula);

    return 0;
}