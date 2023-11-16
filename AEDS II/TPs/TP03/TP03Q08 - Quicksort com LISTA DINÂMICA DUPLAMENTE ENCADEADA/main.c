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

void ler(FILE *arq, Jogador *jogadores)
{
    char linha[300];
    fscanf(arq, "%s", linha);
    char props[8][100];
    int i = 0;
    fgets(linha, 300, arq);
    while (!feof(arq))
    {
        fgets(linha, 300, arq);

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
    fclose(arq);
}

void printPlayer(Jogador jogador)
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento, jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
}

// Inicialização da estrutura Celula, que será utilizada na Lista; Seus atributos são um ponteiro para um jogador e dois ponteiros para a próxima célula e para a célula anterior

typedef struct Celula
{
    Jogador jogador;
    struct Celula *prox;
    struct Celula *ant;
} Celula;

// Inicialização da estrutura Lista, que contém um ponteiro para o início da lista, um ponteiro para o fim da lista e um inteiro tamanho que representa o tamanho da lista

typedef struct Lista
{
    Celula *inicio;
    Celula *fim;
    int tamanho;
} Lista;

// Função que cria uma nova célula, alocando memória para ela e preenchendo seus atributos

Celula *novaCelula(Jogador jogador)
{
    Celula *tmp = (Celula *)malloc(sizeof(Celula));
    tmp->jogador = jogador;
    tmp->prox = NULL;
    tmp->ant = NULL;
    return tmp;
}

// Função que cria uma nova lista, alocando memória para ela e preenchendo seus atributos

Lista *start()
{
    Lista *lista = (Lista *)malloc(sizeof(Lista));
    lista->inicio = NULL;
    lista->fim = NULL;
    lista->tamanho = 0;
    return lista;
}

// Função que insere um jogador no início da lista

void inserirInicio(Lista *lista, Jogador jogador)
{
    if (lista->inicio == NULL)
    {
        lista->inicio = novaCelula(jogador);
        lista->fim = lista->inicio;
        return;
    }

    lista->inicio->ant = novaCelula(jogador);
    lista->inicio->ant->prox = lista->inicio;
    lista->inicio = lista->inicio->ant;
}

// Função que insere um jogador no fim da lista

void inserirFim(Lista *lista, Jogador jogador)
{
    if (lista->fim == NULL)
    {
        lista->fim = novaCelula(jogador);
        lista->inicio = lista->fim;
        return;
    }

    lista->fim->prox = novaCelula(jogador);
    lista->fim->prox->ant = lista->fim;
    lista->fim = lista->fim->prox;
}

// Função que insere um jogador em uma posição específica da lista, passada como parâmetro do método

void inserir(Lista *lista, Jogador jogador, int pos)
{
    if (pos == 0)
    {
        inserirInicio(lista, jogador);
        return;
    }

    Celula *aux = lista->inicio;
    for (int i = 0; i < pos; i++)
    {
        aux = aux->prox;
        if (aux == NULL)
        {
            printf("Posição n existe");
            return;
        }
    }

    if (aux->prox == NULL)
    {
        inserirFim(lista, jogador);
        return;
    }

    Celula *tmp = novaCelula(jogador);
    tmp->ant = aux->ant;
    tmp->prox = aux;
    aux->prox->ant = tmp;
    aux->prox = tmp;
}

// Função que remove um jogador do início da lista

Jogador removerInicio(Lista *lista)
{
    if (lista->inicio == NULL)
    {
        printf("Erro");
        Jogador j;
        return j;
    }

    Celula *tmp = lista->inicio;
    Jogador res = lista->inicio->jogador;
    lista->inicio = lista->inicio->prox;
    lista->inicio->ant = NULL;

    free(tmp);
    return res;
}

// Função que remove um jogador do fim da lista

Jogador removerFim(Lista *lista)
{
    if (lista->fim == NULL)
    {
        printf("Erro");
        Jogador j;
        return j;
    }

    Celula *tmp = lista->fim;
    Jogador res = tmp->jogador;
    lista->fim = lista->fim->ant;
    lista->fim->prox = NULL;

    free(tmp);
    return res;
}

// Função que remove um jogador de uma posição específica da lista, passada como parâmetro do método

Jogador remover(Lista *lista, int pos)
{
    if (pos == 0)
    {
        return removerInicio(lista);
    }

    Celula *aux = lista->inicio;
    for (int i = 0; i < pos; i++)
    {
        aux = aux->prox;
        if (aux == NULL)
        {
            printf("Erro");
            Jogador j;
            return j;
        }
    }

    aux->ant->prox = aux->prox;
    aux->prox->ant = aux->ant;

    Jogador res = aux->jogador;
    free(aux);

    return res;
}

// Função que imprime todos os jogadores de uma dada lista passada como parâmetro do método

void imprimir(Lista *lista)
{
    for (Celula *i = lista->inicio; i != NULL; i = i->prox)
    {
        printPlayer(i->jogador);
        printf("\n");
    }
}

// Variáveis universais (má prática, mas necessária) que conta o número de comparações e trocas realizadas - depois serão impressas no arquivo de log no final da main
int comps = 0;
int trocas = 0;

// Função que compara duas strings, retornando 1 se a primeira for maior que a segunda, -1 se a primeira for menor que a segunda e 0 se forem iguais

int cmpstr(char *str1, char *str2)
{
    int i = 0;

    while (i < strlen(str1) && i < strlen(str2))
    {
        if (str1[i] < str2[i])
        {
            return -1;
        }
        if (str1[i] > str2[i])
        {
            return 1;
        }
        i++;
    }

    if (strlen(str1) < strlen(str2))
    {
        return -1;
    }
    if (strlen(str1) > strlen(str2))
    {
        return 1;
    }
    return 0;
}

// Função que compara dois jogadores, retornando 1 se o primeiro for maior que o segundo, -1 se o primeiro for menor que o segundo e 0 se forem iguais

int jgdrcmp(Jogador j1, Jogador j2)
{
    comps++;
    int a = cmpstr(j1.estadoNascimento, j2.estadoNascimento);
    if (a != 0)
        return a;

    return cmpstr(j1.nome, j2.nome);
}

// Função que troca dois jogadores de posição na lista

void swap(Lista *lista, Celula *i, Celula *j)
{
    Jogador aux = i->jogador;
    i->jogador = j->jogador;
    j->jogador = aux;
}

// Função que ordena uma lista utilizando o algoritmo de quicksort

void quicksort(Lista *lista, Celula *esq, Celula *dir)
{
    Celula *i = esq;
    Celula *j = dir;
    Jogador pivo = dir->jogador;

    while (j != i->ant && j != i->ant->ant)
    {
        // printf("i: %s\n", i->jogador.nome);
        while (jgdrcmp(i->jogador, pivo) == -1)
            i = i->prox;
        // printf("j: %s\n", j->jogador.nome);
        while (jgdrcmp(pivo, j->jogador) == -1)
            j = j->ant;

        if (j != i->ant && j != i->ant->ant)
        {
            swap(lista, i, j);
            i = i->prox;
            j = j->ant;
        }
    }
    // printf("i: %s\n", i->jogador.nome);
    if (esq != j && j != esq->ant)
    {
        quicksort(lista, esq, j);
    }
    // printf("j: %s\n", j->jogador.nome);
    if (dir != i && i != dir->prox)
    {
        quicksort(lista, i, dir);
    }
}

void sort(Lista *lista)
{
    Jogador j;
    Celula *c1 = novaCelula(j);
    Celula *c2 = novaCelula(j);
    lista->inicio->ant = c1;
    lista->inicio->ant->prox = lista->inicio;
    lista->fim->prox = c2;
    lista->fim->prox->ant = lista->fim;

    quicksort(lista, lista->inicio, lista->fim);

    lista->inicio->ant = NULL;
    lista->fim->prox = NULL;

    free(c1);
    free(c2);
}

int main()
{
    FILE *arq = fopen("/tmp/players.csv", "r");
    FILE *logFile = fopen("matricula_quicksort2.txt", "w");

    // Inicialização do array de jogadores e da fila

    Jogador *jogadoresSelecionados = (Jogador *)malloc(10000 * sizeof(Jogador));

    ler(arq, jogadoresSelecionados);

    // Segunda parte do exercício;
    // Manipulação da fila de jogadores nela presentes

    Lista *lista = start();

    char *line = (char *)malloc(23 * sizeof(char));

    scanf("%s", line);

    while (strcmp(line, "FIM") != 0)
    {
        inserirFim(lista, jogadoresSelecionados[atoi(line)]);
        scanf("%s", line);
    }

    fprintf(logFile, "1448652\t%d\t%d", comps, trocas);

    sort(lista);
    imprimir(lista);
    return 0;
}