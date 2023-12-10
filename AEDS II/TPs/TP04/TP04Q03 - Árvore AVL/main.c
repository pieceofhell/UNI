#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int comparacoes = 0;

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
    char linha[300];
    fscanf(arq, "%s", linha);
    char dados[8][100];
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
                dados[j][a] = '\0';
                j++;
                a = 0;
            }
            else
            {
                dados[j][a] = linha[k];
                a++;
            }
        }

        for (int b = 0; b < 8; b++)
        {
            if (strcmp(dados[b], "") == 0)
            {
                strcpy(dados[b], "nao informado");
            }
        }

        jogadores[i].id = atoi(dados[0]);
        strcpy(jogadores[i].nome, dados[1]);
        jogadores[i].altura = atoi(dados[2]);
        jogadores[i].peso = atoi(dados[3]);
        strcpy(jogadores[i].universidade, dados[4]);
        jogadores[i].anoNascimento = atoi(dados[5]);
        strcpy(jogadores[i].cidadeNascimento, dados[6]);
        strcpy(jogadores[i].estadoNascimento, dados[7]);
        i++;
    }
    fclose(arq);
}

int compareTo(char *nomeX, char *nomeY)
{
    int i = 0;
    while (i < strlen(nomeX) && i < strlen(nomeY))
    {
        if (nomeX[i] < nomeY[i])
        {
            return -1;
        }
        if (nomeX[i] > nomeY[i])
        {
            return 1;
        }
        i++;
    }
    if (strlen(nomeX) < strlen(nomeY))
    {
        return -1;
    }
    if (strlen(nomeX) > strlen(nomeY))
    {
        return 1;
    }
    return 0;
}

typedef struct No
{
    Jogador jogador;
    struct No *esq;
    struct No *dir;
    int nivel;
} No;

typedef struct Arvore
{
    No *raiz;
} Arvore;

No *nozar(Jogador jogador)
{
    No *tmp = (No *)malloc(sizeof(No));
    tmp->jogador = jogador;
    tmp->esq = NULL;
    tmp->dir = NULL;
    tmp->nivel = 0;
    return tmp;
}

Arvore *start()
{
    Arvore *newArvore = (Arvore *)malloc(sizeof(Arvore));
    newArvore->raiz = NULL;
    return newArvore;
}

int getNivel(No *c)
{
    comparacoes++;
    if (c != NULL)
    {
        return c->nivel;
    }
    else
    {
        return 0;
    }
}

void setNivel(No *c)
{
    int nivelEsq = getNivel(c->esq);
    int nivelDir = getNivel(c->dir);

    comparacoes++;
    c->nivel = 1 + ((nivelEsq > nivelDir) ? nivelEsq : nivelDir);
}

No *rotacaoEsq(No *c)
{
    No *dir = c->dir;
    No *dirEsq = dir->esq;

    dir->esq = c;
    c->dir = dirEsq;

    setNivel(c);
    setNivel(dir);

    return dir;
}

No *rotacionarDir(No *c)
{
    No *esq = c->esq;
    No *esqDir = esq->dir;

    esq->dir = c;
    c->esq = esqDir;

    setNivel(c);
    setNivel(esq);

    return esq;
}

No *balancear(No *no)
{
    if (no == NULL)
        return no;

    int fatorB = getNivel(no->dir) - getNivel(no->esq);

    if (abs(fatorB) <= 1)
    {
        comparacoes++;
        setNivel(no);
    }
    else if (fatorB == 2)
    {
        comparacoes++;
        int fatorBDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);

        if (fatorBDir == -1)
        {
            no->dir = rotacionarDir(no->dir);
        }

        no = rotacaoEsq(no);
    }
    else if (fatorB == -2)
    {
        comparacoes += 3;
        int fatorBEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);

        comparacoes++;
        if (fatorBEsq == 1)
        {
            no->esq = rotacaoEsq(no->esq);
        }

        no = rotacionarDir(no);
    }
    else
    {
        comparacoes += 4;
        printf("FATAL ERROR: fator de balanceamento invalido: %d\n", fatorB);
    }

    return no;
}

No *insertRec(No *i, Jogador jogador)
{
    if (i == NULL)
    {
        comparacoes++;
        i = nozar(jogador);
    }
    else if (compareTo(jogador.nome, i->jogador.nome) == -1)
    {
        comparacoes += 2;
        i->esq = insertRec(i->esq, jogador);
    }
    else if (compareTo(jogador.nome, i->jogador.nome) == 1)
    {
        comparacoes += 3;
        i->dir = insertRec(i->dir, jogador);
    }
    return balancear(i);
}

void insert(Arvore *a, Jogador jogador)
{
    a->raiz = insertRec(a->raiz, jogador);
}

int searchRec(No *i, char *nome)
{
    comparacoes++;
    if (i == NULL)
        return 0;
    if (compareTo(nome, i->jogador.nome) == -1)
    {
        printf("esq ");
        return searchRec(i->esq, nome);
    }
    if (compareTo(nome, i->jogador.nome) == 1)
    {
        printf("dir ");
        return searchRec(i->dir, nome);
    }
    return 1;
}

int search(Arvore *a, char *nome)
{
    printf("%s raiz ", nome);
    return searchRec(a->raiz, nome);
}

void printCentral(No *i)
{
    if (i == NULL)
        return;
    printCentral(i->esq);
    printf("%s\n", i->jogador.nome);
    printCentral(i->dir);
}

void caminharCentral(Arvore *a)
{
    printCentral(a->raiz);
}

int main()
{
    clock_t inicio = clock();

    FILE *arq = fopen("players.csv", "r");
    Jogador *jogadores = (Jogador *)malloc(10000 * sizeof(Jogador));

    ler(arq, jogadores);
    Arvore *arvoreAlpha = start();

    char *entrada = (char *)malloc(50 * sizeof(char));
    scanf("%s", entrada);

    while (1)
    {
        if (strcmp(entrada, "FIM") == 0)
        {
            break;
        }
        insert(arvoreAlpha, jogadores[atoi(entrada)]);
        scanf("%s", entrada);
    }

    getchar();
    scanf("%[^\n]", entrada);
    while (1)
    {
        if (strcmp(entrada, "FIM") == 0)
        {
            break;
        }
        int encontrado = search(arvoreAlpha, entrada);
        if (encontrado == 1)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        getchar();
        scanf("%[^\n]", entrada);
    }

    clock_t fim = clock();
    double duracao = ((double)fim - (double)inicio) / CLOCKS_PER_SEC;

    char mat[] = "805688";

    FILE *matricula = fopen("matricula_avl.txt", "w");
    fprintf(matricula, "%s\t%.2lf\t%d", mat, duracao, comparacoes);
    fclose(matricula);

    return 0;
}