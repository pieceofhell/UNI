#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_SIZE 5

// Definição da struct do tipo Jogador, contendo todos os atributos necessários

typedef struct Jogador
{
  int id;
  char nome[50];
  int altura;
  int peso;
  char universidade[100];
  int anoNasc;
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
    jogadores[i].anoNasc = atoi(props[5]);
    strcpy(jogadores[i].cidadeNascimento, props[6]);
    strcpy(jogadores[i].estadoNascimento, props[7]);

    i++;
  }
  fclose(arq);
}

// Inicialização da estrutura Celula, que será utilizada na Fila; Seus atributos são um ponteiro para um jogador e um ponteiro para a próxima célula

typedef struct Celula
{
  Jogador jogador;
  struct Celula *prox;
} Celula;

// Inicialização da estrutura Fila, que contém um ponteiro para o início da fila, um ponteiro para o fim da fila e um inteiro n que representa o tamanho da fila

typedef struct Fila
{
  Celula *inicio;
  Celula *fim;
  int n;
} Fila;

// Método de inicialização da fila que retorna um ponteiro para a fila e aloca o espaço necessário para sua manipulação

Fila *start()
{
  Fila *fila = (Fila *)malloc(sizeof(Fila));
  fila->inicio = NULL;
  fila->fim = NULL;
  fila->n = 0;
  if (fila == NULL)
  {
    fprintf(stderr, "Erro ao alocar memoria para a fila.\n");
    exit(EXIT_FAILURE);
  }
  return fila;
}

// Método de inserção na fila, que recebe a fila e o jogador a ser inserido

void inserir(Fila *fila, Jogador jogador)
{
  Celula *newPlayer = (Celula *)malloc(sizeof(Celula));
  newPlayer->jogador = jogador;
  newPlayer->prox = NULL;
  if (fila->n == MAX_SIZE)
  {
    Celula *removido = fila->inicio;
    fila->inicio = removido->prox;
    free(removido);
    fila->n--;
  }
  if (fila->fim == NULL)
  {
    fila->inicio = newPlayer;
    fila->fim = newPlayer;
  }
  else
  {
    fila->fim->prox = newPlayer;
    fila->fim = newPlayer;
  }
  fila->n++;
  float avg = 0;
  int count = 0;
  Celula *temp = fila->inicio;
  while (temp != NULL)
  {
    avg += temp->jogador.altura;
    count++;
    temp = temp->prox;
  }
  avg /= count;
  printf("%d\n", (int)(avg + 0.5));
}

// Método de remoção da fila, que recebe a fila e remove o jogador mais antigo

void remover(Fila *fila)
{
  Celula *removido = fila->inicio;
  fila->inicio = removido->prox;

  printf("(R) %s\n", removido->jogador.nome);

  free(removido);
  fila->n--;
}

void manipulacao(Fila *fila, Jogador *jogadoresSelecionados)
{
  char *comando = (char *)malloc(3 * sizeof(char));
  scanf("%s", comando);
  if (strcmp(comando, "I") == 0)
  {
    int id;
    scanf("%d", &id);
    inserir(fila, jogadoresSelecionados[id]);
  }
  else if (strcmp(comando, "R") == 0)
  {
    remover(fila);
  }
  free(comando);
}

void imprimir(Fila *fila)
{
  int a = 0;
  Celula *temp = fila->inicio;
  while (temp != NULL)
  {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", a,
           temp->jogador.nome, temp->jogador.altura, temp->jogador.peso,
           temp->jogador.anoNasc, temp->jogador.universidade,
           temp->jogador.cidadeNascimento, temp->jogador.estadoNascimento);
    temp = temp->prox;
    a++;
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
  int numOperacoes;
  ler(arq, jogadoresSelecionados);

  // Segunda parte do exercício;
  // Manipulação da fila de jogadores nela presentes

  scanf("%s", linha);
  while (strcmp(linha, "FIM") != 0)
  {
    inserir(fila, jogadoresSelecionados[atoi(linha)]);
    scanf("%s", linha);
  }
  scanf("%d", &numOperacoes);
  for (int i = 0; i < numOperacoes; i++)
  {
    manipulacao(fila, jogadoresSelecionados);
  }
  imprimir(fila);
  Celula *tmp = fila->inicio;
  while (tmp != NULL)
  {
    Celula *prox = tmp->prox;
    free(tmp);
    tmp = prox;
  }
  free(fila);
  return 0;
}
