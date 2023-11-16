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
    char linha[200];
    fscanf(arq, "%s", linha);
    char props[8][100];
    int i = 0;
    fgets(linha, 200, arq);
    while (!feof(arq))
    {
        fgets(linha, 200, arq);

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

// Inicialização da struct Celula, que será utilizada na estrutura de Pilha

typedef struct Celula
{
    Jogador jogador;
    struct Celula *prox;
} Celula;

// Inicialização da struct Pilha, que contém um ponteiro para a célula do topo da pilha

typedef struct Pilha
{
    Celula *top;
} Pilha;

// Método newCelula, que recebe um jogador e retorna uma célula com o jogador recebido

Celula *newCelula(Jogador jogador)
{
    Celula *tmp = (Celula *)malloc(sizeof(Celula));
    tmp->jogador = jogador;
    tmp->prox = NULL;
    return tmp;
}

Pilha *start()
{
    Pilha *pilha = (Pilha *)malloc(sizeof(Pilha));
    pilha->top = (Celula *)malloc(sizeof(Celula));
    pilha->top->prox = NULL;
    return pilha;
}

// Função de remover, que recebe uma pilha, remove o elemento do topo da pilha (caso exista) e retorna o jogador removido
// Segue o padrão de tratamento de dados FIFO

Jogador remover(Pilha *pilha)
{
    Celula *tmp = pilha->top->prox;
    Jogador removido = tmp->jogador;
    pilha->top->prox = tmp->prox;
    printf("(R) %s\n", removido.nome);
    free(tmp);
    return removido;
}

// Função de inserir, que recebe uma pilha e um jogador e insere o jogador no topo da pilha
// Segue o padrão de tratamento de dados FIFO

void inserir(Pilha *pilha, Jogador jogador)
{
    Celula *tmp = newCelula(jogador);
    tmp->prox = pilha->top->prox;
    pilha->top->prox = tmp;
}

// Função de mostrar, que recebe uma pilha e mostra todos os elementos da pilha, do topo para a base

void imprimir(Pilha *pilha)
{
    int count = 0;
    for (Celula *i = pilha->top->prox; i != NULL; i = i->prox)
    {
        count++;
    }
    Celula *tmp = pilha->top->prox;
    for (int i = count - 1; i >= 0; i--)
    {
        tmp = pilha->top->prox;
        for (int j = 0; j < i; j++)
        {
            tmp = tmp->prox;
        }
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", count - i - 1, tmp->jogador.nome, tmp->jogador.altura, tmp->jogador.peso, tmp->jogador.anoNascimento, tmp->jogador.universidade, tmp->jogador.cidadeNascimento, tmp->jogador.estadoNascimento);
    }
}

int main()
{
    FILE *arq = fopen("/tmp/players.csv", "r");
    Jogador *jogadoresSelecionados = (Jogador *)malloc(10000 * sizeof(Jogador));

    ler(arq, jogadoresSelecionados);

    Pilha *pilha = start();

    // Primeira parte do exercício;
    // Leitura dos jogadores e inserção na fila

    char *linha = (char *)malloc(23 * sizeof(char));

    scanf("%s", linha);

    while (strcmp(linha, "FIM") != 0)
    {
        inserir(pilha, jogadoresSelecionados[atoi(linha)]);
        scanf("%s", linha);
    }

    // Segunda parte do exercício; Manipulação da fila de jogadores nela presentes

    int numOperacoes;
    scanf("%d", &numOperacoes);

    for (int i = 0; i < numOperacoes; i++)
    {
        char *comando = (char *)malloc(3 * sizeof(char));
        scanf("%s", comando);
        if (strcmp(comando, "I") == 0)
        {
            int id;
            scanf("%d", &id);
            inserir(pilha, jogadoresSelecionados[id]);
        }
        else if (strcmp(comando, "R") == 0)
        {
            remover(pilha);
        }
        free(comando);
    }

    imprimir(pilha);

    return 0;
}