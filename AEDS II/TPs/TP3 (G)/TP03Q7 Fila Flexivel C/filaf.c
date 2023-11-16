#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Jogador
{
  int id;
  char nome[50];
  int altura;
  int peso;
  char universidade[100];
  int anoNasc;
  char cidadeNasc[50];
  char estadoNasc[50];
} Jogador;

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
    jogadores[i].anoNasc = atoi(props[5]);
    strcpy(jogadores[i].cidadeNasc, props[6]);
    strcpy(jogadores[i].estadoNasc, props[7]);

    i++;
  }
  fclose(entrada);
}

typedef struct Cel
{
  Jogador jogador;
  struct Cel *prox;
} Cel;

typedef struct Fila
{
  Cel *inicio;
  Cel *fim;
  int tamanho;
} Fila;

Fila *newFila()
{
  Fila *fila = (Fila *)malloc(sizeof(Fila));
  fila->inicio = NULL;
  fila->fim = NULL;
  fila->tamanho = 0;
  return fila;
}

void inserir(Fila *fila, Jogador jogador)
{
  Cel *novoNode = (Cel *)malloc(sizeof(Cel));
  novoNode->jogador = jogador;
  novoNode->prox = NULL;
  if (fila->tamanho == 5)
  {
    Cel *removido = fila->inicio;
    fila->inicio = removido->prox;
    free(removido);
    fila->tamanho--;
  }
  if (fila->fim == NULL)
  {
    fila->inicio = novoNode;
    fila->fim = novoNode;
  }
  else
  {
    fila->fim->prox = novoNode;
    fila->fim = novoNode;
  }
  fila->tamanho++;
  float media = 0;
  int amount = 0;
  Cel *temp = fila->inicio;
  while (temp != NULL)
  {
    media += temp->jogador.altura;
    amount++;
    temp = temp->prox;
  }
  media /= amount;
  printf("%d\n", (int)(media + 0.5));
}

void imprimirMensagemRemocao(char *nome) { printf("(R) %s\n", nome); }

void remover(Fila *fila)
{
  Cel *removido = fila->inicio;
  fila->inicio = removido->prox;
  imprimirMensagemRemocao(removido->jogador.nome);
  free(removido);
  fila->tamanho--;
}

void metodos(Fila *fila, Jogador *jogadores)
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
    remover(fila);
  }
  free(metodo);
}

void mostrar(Fila *fila)
{
  int a = 0;
  Cel *temp = fila->inicio;
  while (temp != NULL)
  {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", a,
           temp->jogador.nome, temp->jogador.altura, temp->jogador.peso,
           temp->jogador.anoNasc, temp->jogador.universidade,
           temp->jogador.cidadeNasc, temp->jogador.estadoNasc);
    temp = temp->prox;
    a++;
  }
}

int main(void)
{
  FILE *entrada = fopen("/tmp/players.csv", "r");
  Jogador *jogadores = (Jogador *)malloc(10000 * sizeof(Jogador));
  Fila *fila = newFila();
  char *linha = (char *)malloc(23 * sizeof(char));
  int n;
  ler(entrada, jogadores);
  scanf("%s", linha);
  while (strcmp(linha, "FIM") != 0)
  {
    inserir(fila, jogadores[atoi(linha)]);
    scanf("%s", linha);
  }
  scanf("%d", &n);
  for (int i = 0; i < n; i++)
  {
    metodos(fila, jogadores);
  }
  mostrar(fila);
  Cel *temp = fila->inicio;
  while (temp != NULL)
  {
    Cel *prox = temp->prox;
    free(temp);
    temp = prox;
  }
  free(fila);
  return 0;
}
