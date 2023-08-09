#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int ex1() {
  srand(time(NULL));
  double m = rand() % 100, x, y, *z = &m;

  printf("\nExercício 1 - Apresentando a declaração básica de ponteiros:\n");
  printf("----------------------------------------------------------\n\n");
  printf("Digamos que m = %.lf, x e y são duas variáveis do tipo int e *z "
         "também é uma variável do tipo int.\n\n",
         m);
  printf("z armazena o endereço de   [m] = %p\n",
         z); // z é um ponteiro, então %p retorna o endereço
  printf("\n*z armazena o valor de     [m] = %.lf\n", *z);
  printf("\n&m é o endereço de         [m] = %p\n",
         &m); // &m retorna o endereço da variável m
              // %p serve para especificar que o retorno será um endereço
  printf("\n&n armazena o endereço de  [x] = %p\n", &x);
  printf("\n&o armazena o endereço de  [y] = %p\n", &y);
  printf("\n&z armazena o endereço de  [z] = %p\n\n",
         &z); // &z retorna o endereço de onde o ponteiro z está
              // armazenado -> ainda um endereço -> %p é o especificador correto
  return 0;
}

int ex2() {
  srand(time(NULL));
  int *ab, m = rand() % 200;

  printf("\n\nExercício 2 - Ponteiros: apresentação de interações entre "
         "ponteiros a "
         "partir\nda "
         "redefinição de valores:\n");
  printf("------------------------------------------------------------");
  printf("\n\nTemos a seguite declaração:\nab = ponteiro int\ne digamos também "
         "que\nint m = %d\n\n",
         m);

  printf("Endereço de m:  [ %p ]\n", &m);
  printf("Valor de m:     ( %d )\n\n", m);
  printf("Valor de ab:    ( %d )\n", *ab);
  printf("Endereço de ab: [ %p ]\n\n", &ab);
  ab = &m;
  printf("Agora, ab (sem *) carrega o valor do endereço de m.\n\n");
  printf("Endereço do ponteiro ab (sem *):             [ %p ]\n", ab);
  printf("Conteúdo carregado pelo ponteiro ab (com *): %d\n\n", *ab);
  m = rand() % 100;
  printf("Agora, redefinimos o valor da variável m. O novo valor designado "
         "agora é ( %d ).\n",
         m);
  printf("Endereço de ab (aponta para o endereço de m, como designado antes): "
         "[ %p ]\n",
         ab);
  printf("Conteúdo de ab: ( %d )\n\n", *ab);
  *ab = rand() % 50;
  printf("Agora, redefinimos que o valor da variável ab agora seja de %d.\n",
         *ab);
  printf("Endereço de m:  [ %p ]\n", &m);
  printf("Endereço de ab: [ %p ]\n",
         &ab); // as ab contain the address of m
               // so *ab changed the value of m and now m become 7
  printf(
      "\n\nObservação: Note que parênteses () foram utilizados para destacar "
      "números inteiros, enquanto colchetes foram usados para se referir a "
      "endereços de variáveis. Além disso, percebe-se que apesar de seus "
      "valores tenham sidos redesignados "
      "algumas vezes, observe que o endereço das variáveis m e ab sempre foram "
      "os mesmos.\n\n");
  printf("Valor final de m: ( %d )\n\n", m);
  return 0;
}

int ex3() {
  srand(time(NULL));
  int intX = rand() % 300;
  float floatX = 300.60;
  char cht = 65 + (random() % 27);
  // 65 = A, de acordo com a table ASCII. A adição é o fator responsável por
  // tornar a variável char aleatória

  printf("\n\nExercício 3 - Demonstração do uso dos operadores & e *\n");
  printf("--------------------------------------------------------\n\n");
  int *pt1;
  float *pt2;
  char *pt3;
  pt1 = &intX, pt2 = &floatX, pt3 = &cht;

  printf("intX   = %d\n", intX);
  printf("floatX = %f\n", floatX);
  printf("cht    = %c\n", cht);
  printf("\nUtilizando o operador &:\n");
  printf("--------------------------\n\n");
  printf("Endereço de intX   = %p\n", &intX);
  printf("Endereço de floatX = %p\n", &floatX);
  printf("Endereço de cht    = %p\n", &cht);
  printf("\nUtilizando os operadores & e *:\n");
  printf("---------------------------------\n\n");
  printf("Valor no endereço de intX   = %d\n", *(&intX));
  printf("Valor no endereço de floatX = %f\n", *(&floatX));
  printf("Valor no endereço de cht    = %c\n", *(&cht));
  printf("\nUtilizando apenas a variável ponteiro (%%p):\n");
  printf("--------------------------------------------\n\n");
  printf("Endereço de intX   = %p\n", pt1);
  printf("Endereço de floatX = %p\n", pt2);
  printf("Endereço de cht    = %p\n", pt3);
  printf("\nUtilizando apenas o operador ponteiro (*):\n");
  printf("----------------------------------\n\n");
  printf("Valor no endereço de intX   = %d\n", *pt1);
  printf("Valor no endereço de floatX = %f\n", *pt2);
  printf("Valor no endereço de cht    = %c\n\n", *pt3);
  return 0;
}

int ex4() {
  int *nums = malloc(2 * sizeof(int));

  printf("\n\nExercício 4 - Adiçao de dois números utilizando ponteiros:\n");
  printf("--------------------------------\n");

  printf("Insira o primeiro número: ");
  scanf("%d", nums);
  printf("Insira o segundo número: ");
  scanf("%d", (nums + sizeof(int)));

  printf("A soma dos números inseridos é: %d\n\n",
         *nums + *(nums + sizeof(int)));

  return 0;
}

// Módulo do exercício 5
long ex5_addNums(long *n1, long *n2) {
  long sum = *n1 + *n2;
  return sum;
}

int ex5() {
  long num1, num2;

  printf("\n\nExercício 5 - Adição de dois números a partir da chamada de "
         "variáveis "
         "(função de adição se encontra modularizada)\n");
  printf("---------------------------------------------------------------------"
         "-------\n");

  printf("Insira o primeiro número: ");
  scanf("%ld", &num1);
  printf("Insira o segundo número: ");
  scanf("%ld", &num2);
  printf("A soma de %ld e %ld é %ld\n\n", num1, num2,
         ex5_addNums(&num1, &num2));
  return 0;
}

// Módulo do exercício 6
int ex6_achaMaior(int *ptr1, int *ptr2) {

  if (*ptr1 > *ptr2) {
    printf("\n%d é o número maior.\n\n", *ptr1);
  } else {
    printf("\n%d é o número maior.\n\n", *ptr2);
  }
  return 0;
}

int ex6() {
  int fno, sno, *ptr1 = &fno, *ptr2 = &sno;

  printf("\n\nExercício 6 - Encontre o maior valor entre dois números:\n");
  printf("------------------------------------------------------------\n");

  printf("Insira o primeiro número: ");
  scanf("%d", ptr1);
  printf("Insira o primeiro número: ");
  scanf("%d", ptr2);
  ex6_achaMaior(&fno, &sno);
  return 0;
}

int ex7() {
  int arr1[25], i, n;
  printf("\n\nExercício 7 - Armazenar e fazer a chamada de elementos contidos "
         "em um vetor"
         "vetor: \n");
  printf("------------------------------------------------------------\n");
  printf("Insira o número de elementos que serão armazenados no vetor: ");
  scanf("%d", &n);

  printf("Insira %d elementos ao vetor:\n", n);
  for (i = 0; i < n; i++) {
    printf("Elemento - [ %d ]: ", i);
    scanf("%d", arr1 + i);
  }
  printf("Os elementos designados ao vetor são:\n");
  for (i = 0; i < n; i++) {
    printf("Elemento - [ %d ]: %d \n", i, *(arr1 + i));
  }
  return 0;
}

// Módulos do Exercício 8
void ex8_changePosition(char *ch1, char *ch2) {
  char tmp;
  tmp = *ch1;
  *ch1 = *ch2;
  *ch2 = tmp;
}

void ex8_charPermu(char *cht, int stno, int endno) {
  int i;
  if (stno == endno)
    printf("%s  ", cht);
  else {
    for (i = stno; i <= endno; i++) {
      ex8_changePosition((cht + stno), (cht + i));
      ex8_charPermu(cht, stno + 1, endno);
      ex8_changePosition((cht + stno), (cht + i));
    }
  }
}

void ex8_randString(char *str, int num) {
  srand(time(NULL));
  int rando;
  for (int i = 0; i < num; i++) {
    // str[i] = rand() % ('z' - 'a' + 1) + 'a';
    int j = i + 1;
    str[i] = 97 + (random() % 26);
  }
  str[num] = 0;
}

int ex8() {
  char str[16];
  int c;
  printf("\n\nExercício 8 - Geração de permutações de uma dada string:\n");
  printf("--------------------------------------------------------\n");
  printf("Quantas letras aleatórias você deseja inserir à string? ");
  scanf("%d", &c);
  ex8_randString(str, c);
  int n = strlen(str);
  printf("As permutações da string são: \n");
  ex8_charPermu(str, 0, n - 1);
  printf("\n\n");
  return 0;
}

int ex9() {
  int i, n;
  float *element;
  printf("\n\nExercício 9 - Encontre o maior elemento através da utilização de "
         "Memória "
         "Dinâmica\n");
  printf("---------------------------------------------------------------------"
         "----\n");
  printf("Selecione o número total de elementos: ");
  scanf("%d", &n);
  element =
      (float *)calloc(n, sizeof(float)); // A memória é alocada para 'n'
                                         // elementos inseridos pelo usuário
  if (element == NULL) {
    printf("Não há memória alocada o suficiente.\n");
    exit(0);
  }
  printf("\n");
  for (i = 0; i < n; ++i) {
    printf("Número %d: ", i + 1);
    scanf("%f", element + i);
  }
  for (i = 1; i < n; ++i) {
    if (*element < *(element + i))
      *element = *(element + i);
  }
  printf("O maior elemento é: %.2f \n\n", *element);
  return 0;
}

int ex10() {
  int num1, num2, *ptr, *qtr, sum;

  printf("\n\n4 - Adiçao de dois números utilizando ponteiros:\n");
  printf("--------------------------------\n");

  int *nums = malloc(2 * sizeof(int));
  scanf("%d%d", nums, (nums + sizeof(int)));
  printf("%d", (*nums + *(nums + sizeof(int))));

  /*
  printf("Insira o primeiro número: ");
  scanf("%d", &num1);
  printf("Insira o segundo número:");
  scanf("%d", &num2);

  ptr = &num1;
  qtr = &num2;

  sum = *ptr + *qtr; */

  printf(" The sum of the entered numbers is : %d\n\n", sum);

  return 0;
}

int ex11() {
  int num1, num2, *ptr, *qtr, sum;

  printf("\n\n4 - Adiçao de dois números utilizando ponteiros:\n");
  printf("--------------------------------\n");

  int *nums = malloc(2 * sizeof(int));
  scanf("%d%d", nums, (nums + sizeof(int)));
  printf("%d", (*nums + *(nums + sizeof(int))));

  /*
  printf("Insira o primeiro número: ");
  scanf("%d", &num1);
  printf("Insira o segundo número:");
  scanf("%d", &num2);

  ptr = &num1;
  qtr = &num2;

  sum = *ptr + *qtr; */

  printf(" The sum of the entered numbers is : %d\n\n", sum);

  return 0;
}

// Módulo do exercício 12
void achaFat(int n, int *f) {
  int i;

  *f = 1;
  for (i = 1; i <= n; i++)
    *f = *f * i;
}

int ex12() {
  int fact;
  int num1;
  printf("\n\n12 - Ponteiros: Encontre o fatorial de um dado número, através "
         "da utilização de ponteiros:\n");
  printf(
      "------------------------------------------------------------------\n");
  printf("Insira um número: ");
  scanf("%d", &num1);

  achaFat(num1, &fact);
  printf("O Fatorial de %d é: %d \n\n", num1, fact);
  return 0;
}

int ex13() {
  char str1[50];
  char *pt;
  int ctrV, ctrC;
  printf("\n\n13 - O ponteiro conta o número de vogais e consoantes da string: "
         "\n");
  printf("----------------------------------------------------------\n");
  printf("Digite a string: ");
  fgets(str1, sizeof str1, stdin);

  pt = str1;
  // 0 para começar a busca no primeiro array.
  ctrV = ctrC = 0;

  while (*pt != '\0') {
    if (*pt == 'A' || *pt == 'E' || *pt == 'I' || *pt == 'O' || *pt == 'U' ||
        *pt == 'a' || *pt == 'e' || *pt == 'i' || *pt == 'o' || *pt == 'u')
      ctrV++;
    else
      ctrC++;
    pt++; // O número do ponteiro aumenta de acordo cada váriavel para buscar
          // por consoantes/vogais.
  }

  printf(" Número de vogais : %d\n Número de consoantes : %d\n", ctrV,
         ctrC - 1);
  return 0;
}

int ex14() {
  int *a, i, j, tmp, n;
  printf("\n\n 14 - Rearranjando Array com Ponteiros :\n");
  printf("--------------------------------------------\n");

  printf(" Qual o tamanho do array: ");
  scanf("%d", &n);

  printf(" Qual o número do elemento %d da array : \n", n);
  for (i = 0; i < n; i++) {
    printf(" elemento - %d : ", i + 1);
    scanf("%d", a + i);
  }
  for (i = 0; i < n; i++) {
    for (j = i + 1; j < n; j++) {
      if (*(a + i) > *(a + j)) {
        tmp = *(a + i);
        *(a + i) = *(a + j);
        *(a + j) = tmp;
      }
    }
  }
  printf("\n Os elementos das arrays agora são : \n");
  for (i = 0; i < n; i++) {
    printf(" elemento - %d : %d \n", i + 1, *(a + i));
  }
  printf("\n");
  return 0;
}

// Módulo do exercício 15
int *ex15_findLarger(int *n1, int *n2) {
  if (*n1 > *n2)
    return n1;
  else
    return n2;
}

int ex15() {
  int *ex15_findLarger(int *, int *);
  int numa = 0;
  int numb = 0;
  int *result;
  printf("\n\n 15 - Mostre uma função usando ponteiros :\n");
  printf("--------------------------------------------------\n");
  printf(" Escolha o primeiro número : ");
  scanf("%d", &numa);
  printf(" Escolha o segundo número : ");
  scanf("%d", &numb);
  result = ex15_findLarger(&numa, &numb);
  printf(" O número %d é maior.  \n\n", *result);
  return 0;
}

int ex16() {
  int arr1[10];
  int i, n, sum = 0;
  int *pt;

  printf("\n\n 16 - Somando todos os elementos de uma array com ponteiro:\n");
  printf("------------------------------------------------\n");

  printf(" Qual o tamanho da array (máximo 10) ");
  scanf("%d", &n);

  printf(" Selecione os %d números da array: \n", n);
  for (i = 0; i < n; i++) {
    printf(" elemento - %d : ", i + 1);
    scanf("%d", &arr1[i]);
  }

  pt = arr1; // pt guarda o endereço de arr1

  for (i = 0; i < n; i++) {
    sum = sum + *pt;
    pt++;
  }

  printf(" A soma do array é : %d\n\n", sum);
  return 0;
}

int ex17() {
  int n, i, arr1[15];
  int *pt;
  printf("\n\n 17 - Escrevendo os números da array em ordem inversa :\n");
  printf("----------------------------------------------------------------\n");

  printf(" Qual o tamanho da array (máximo 15) ");
  scanf("%d", &n);
  pt = &arr1[0]; // pt guarda o endereço de arr1
  printf(" Escreva os %d números da array: \n", n);
  for (i = 0; i < n; i++) {
    printf(" Elemento - %d : ", i + 1);
    scanf("%d", pt); // Endereço do valor
    pt++;
  }

  pt = &arr1[n - 1];

  printf("\n Os elementos da array em ordem reversa é:");

  // Escrevendo os Valores ja definidos com o for ao contrário (do final da
  // array para baixo)

  for (i = n; i > 0; i--) {
    printf("\n element - %d : %d  ", i, *pt);
    pt--;
  }
  printf("\n\n");
  return 0;
}

// Módulo usado na questão 18

struct ex18_EmpAddress {
  char *ename;
  char stname[20];
  int pincode;
} employee = {"John Alter", "Court Street \n", 654134}, *pt = &employee;

int ex18() {
  printf("\n\n 18 - Uso de ponteiros em estruturas :\n");
  printf("--------------------------------------------------------\n");
  printf(" %s de %s \n\n", pt->ename, (*pt).stname);
  return 0;
}

union empAdd {
  char *ename;
  char stname[20];
  int pincode;
};

int ex19() {
  printf("\n\n Mostrando ponteiro com union:\n");
  printf("----------------------------------------\n");
  union empAdd employee, *pt;
  employee.ename = "Jhon Mc\0Donald"; // Unir char com caracteres null'\0'

  pt = &employee;

  printf(" %s %s\n\n", pt->ename, (*pt).ename);

  return 0;
}

struct employee {
  char *empname;
  int empid;
};

int ex20() {
  printf("\n\n Usando Ponteiros que apontam para uma array que guarda uma "
         "structure :\n");
  printf("---------------------------------------------------------------------"
         "--------------\n");

  struct employee emp1 = {"Jhon", 1001}, emp2 = {"Alex", 1002},
                         emp3 = {"Taylor", 1003};
  struct employee(*arr[]) = {&emp1, &emp2, &emp3};
  struct employee(*(*pt)[3]) = &arr;

  printf(" Nome do Empresário : %s \n", (**(*pt + 1)).empname);
  printf("---------------- Explicação --------------------\n");
  printf("(**(*pt+1)).empname\n");
  printf("= (**(*&arr+1)).empname   usando pt=&arr\n");
  printf("= (**(arr+1)).empname     de acordo com *&pt = pt\n");
  printf("= (*arr[1]).empname       de acordo com *(pt+i) = pt[i]\n");
  printf("= (*&emp2).empname        usando arr[1] = &emp2\n");
  printf("= emp2.empname = Alex     de acordo com *&pt = pt\n\n");
  printf(" Employee ID :  %d\n", (*(*pt + 1))->empid);
  printf("---------------- Explicação --------------------\n");
  printf("(*(*pt+1))-> empid\n");
  printf("= (**(*pt+1)).empid     de acordo com -> = (*).\n");
  printf("= emp2.empid = 1002\n");
  printf("\n\n");
  return 0;
}

int ex21() {
  char alph[27];
  int x;
  char *ptr;
  printf("\n\n 21 - Escrevendo o alfabeto com ponteiros:\n");
  printf("----------------------------------------\n");
  ptr = alph;
  // Alocando as letras do Alfabeto para seus respectivos endereços na array
  for (x = 0; x < 26; x++) {
    *ptr = x + 'A';
    ptr++;
  }
  ptr = alph;
  // Escrevendo o Alfabeto
  printf(" O alfabeto é : \n");
  for (x = 0; x < 26; x++) {
    printf(" %c ", *ptr);
    ptr++;
  }
  
  printf("\n\n");
  return (0);
}

int ex22() {
  char str1[50];
  char revstr[50];
  char *stptr = str1;
  char *rvptr = revstr;
  int i = -1; 
  printf("\n\n 22 - String ao invertida com ponteiros :\n");
  printf("------------------------------------------------\n");
  // Definindo a String
  printf(" Escreva uma palavra : ");
  scanf("%s", str1);
  while (*stptr) {
    stptr++;
    i++; 
  }
  // Invertendo ela
  while (i >= 0) {
    stptr--;
    *rvptr = *stptr;
    rvptr++;
    --i;
  }
  *rvptr = '\0';
  printf(" A string reversa é : %s\n\n", revstr);
  return 0;
}
int main(void) {
  int num;
  printf(
      "Escolha o exercicio desejado atraves da entrada de um numero de 1 a 22 "
      "ao terminal.\n"
      "Tabela de Exercicios:\n\n"
      "[1] - Apresentando a declaracao basica de ponteiros           \n"
      "[2] - Apresentacao de interacoes entre ponteiros              \n"
      "[3] - Demonstracao do uso dos operadores & e *                \n"
      "[4] - Adicao de dois numeros utilizando ponteiros             \n"
      "[5] - Adicao de dois numeros a partir da chamada de variaveis \n"
      "[6] - Encontre o maior valor entre dois numeros               \n"
      "[7] - Armazenar e fazer a chamada de elementos contidos em um vetor\n"
      "[8] - Geracao de permutacoes de uma dada string\n"
      "[9] - Encontre o maior elemento atraves da utilizacao de Memoria "
      "Dinamica\n"
      "[12] - Encontre o fatorial de um dado número, através da utilização de ponteiros:\n"
      "[13] - Contando o número de vogais e consoantes na string com "
      "pointeiro: x\n"
      "[14] - Rearranjando Array com Ponteiros:\n"
      "[15] - Mostrando uma função usando ponteiros:\n\n");
  ex22();
  return 0;
}