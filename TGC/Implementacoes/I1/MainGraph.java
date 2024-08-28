import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainGraph {

  int numVertices;
  int numEdges;

  ArrayList<Integer> firstColumn;
  ArrayList<Integer> secondColumn;
  LinkedList<Integer>[] vertices;

  // Construtor (potencialmente faltando inicializacao dos vertices, mas precisaria do numero de vertices)
  MainGraph() {
    this.numVertices = 0;
    this.numEdges = 0;
    this.firstColumn = new ArrayList<>();
    this.secondColumn = new ArrayList<>();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // Caso esteja no notebook
    String filePath1 =
      "C:/Users/henri/code/github/UNI/TGC/Implementacoes/I1/graph-test-100-1.txt";
    // Caso esteja no desktop
    String filePath2 =
      "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/I1/graph-test-100-1.txt";
    MainGraph g = new MainGraph();
    try {
      g.read(filePath2);
    } catch (IOException e) {
      e.printStackTrace();
    }

    g.fillGraph();
    // g.printGraph(); // Para debug

    // dumbTeste(1, g.firstColumn.stream().mapToInt(i -> i).toArray(), g.secondColumn.stream().mapToInt(i -> i).toArray());

    int verticeEscolhido = sc.nextInt();

    // Implementacao 1
    g.Implementacao1(verticeEscolhido);
    /**
     * Orientação: Seu programa deverá ler o conteúdo do arquivo e representar o grafo direcionado em memória utilizando uma das estruturas discutidas em nossas aulas. Depois disso, sua implementação deve utilizar a estrutura escolhida para produzir as seguintes informações para o vértice informado pelo usuário: (i) grau de saída; (ii) grau de entrada; (iii) conjunto de sucessores; e (iv) conjunto de predecessores. OBS.: É necessário produzir tais informações apenas para o vértice informado.
     */

    // System.out.println(g.vertices[0].get(0));
    sc.close();
  }

  public void Implementacao1(int vertice) {
    printExitDegree(vertice);
    printEntryDegree(vertice);
    printSuccessors(vertice);
    printPredecessors(vertice);
  }

  public void read(String filePath) throws IOException {
    try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
      String line;

      // Leitura da primeira linha
      if ((line = file.readLine()) != null) {
        String[] firstLineNumbers = line.trim().split("\\s+");
        if (firstLineNumbers.length == 2) {
          numVertices = Integer.parseInt(firstLineNumbers[0]);
          numEdges = Integer.parseInt(firstLineNumbers[1]);
        }
      }

      // System.out.println(
      //   "Numero de vertices: " + numVertices + " Numero de arestas: " + numEdges
      // ); // Debug - exibe numero de vertices e arestas

      // Inicialização da lista de adjacência
      this.vertices = new LinkedList[numVertices + 1];

      /* +1 para evitar problemas com o indice 0 vale notar tambem que o indice 0 não é utilizado, pois o grafo começa no vertice 1 e vai até o vertice numVertices por que isso acontece? porque o arquivo de entrada começa a contar os vertices a partir do 1 e não do 0 */

      // Inicialização das listas de adjacência

      for (int i = 1; i <= numVertices; i++) {
        this.vertices[i] = new LinkedList<>();
      }

      // Leitura das linhas e armazenamento dos vértices em colunas
      while ((line = file.readLine()) != null) {
        String[] numbers = line.trim().split("\\s+");
        firstColumn.add(Integer.parseInt(numbers[0]));
        secondColumn.add(Integer.parseInt(numbers[1]));
      }
    }
  }

  // Adiciona uma aresta ao grafo
  public void addEdge(int tails, int heads) {
    vertices[tails].add(heads);
  }

  // Remove uma aresta do grafo (nao testado)
  public void removeEdge(int tails, int heads) {
    vertices[tails].remove(heads);
  }

  // Preenche o grafo (array de linked lists - vertices) com as arestas do arquivo
  // guardado nas arrays da classe (firustColumn e secondColumn)
  public void fillGraph() {
    for (int i = 0; i < firstColumn.size(); i++) {
      // System.out.println(firstColumn.get(i) + " " + secondColumn.get(i)); // Debug
      addEdge(firstColumn.get(i), secondColumn.get(i));
    }
  }

  // Preenche o grafo (array de linked lists - vertices) com as arestas passadas como argumento
  public void fillGraph(int[] tails, int[] heads) {
    for (int i = 0; i < tails.length; i++) {
      // System.out.println(tails[i] + " " + heads[i]); // Debug
      addEdge(tails[i], heads[i]);
    }
  }

  // Imprime o grafo inteiro
  public void printGraph() {
    for (int i = 1; i <= numVertices; i++) {
      System.out.print("Vertice " + i + ":");
      for (Integer adjacente : vertices[i]) {
        System.out.print(" -> " + adjacente);
      }
      System.out.println();
    }
  }

  // Imprime os sucessores de um vertice
  public void printSuccessors(int vertice) {
    System.out.println("Sucessores do vertice " + vertice + ":");
    for (Integer adjacente : vertices[vertice]) {
      System.out.print(adjacente + " ");
    }
    System.out.println();
  }

  public void printPredecessors(int vertice) {
    System.out.println("Predecessores do vertice " + vertice + ":");
    for (int i = 1; i <= numVertices; i++) {
      if (vertices[i].contains(vertice)) {
        System.out.print(i + " ");
      }
    }
    System.out.println();
  }

  public void printEntryDegree(int vertice) {
    int count = 0;
    for (int i = 1; i <= numVertices; i++) {
      if (vertices[i].contains(vertice)) {
        count++;
      }
    }
    System.out.println("Grau do entrada do vertice " + vertice + ": " + count);
  }

  public void printExitDegree(int vertice) {
    System.out.println(
      "Grau de saida do vertice " + vertice + ": " + vertices[vertice].size()
    );
  }

  // IGNORAR CÓDIGO DAQUI PRA BAIXO - ANÁLISE FEITA SOMENTE USANDO ARQUIVO DE ENTRADA E ARRAYS DE VÉRTICES

  // Encontra o indice do vertice alvo (dentro da array dada)
  public static int dumbFindTargetIndex(int target, int array[]) {
    int index = -1;
    for (int i = 0; i < array.length; i++) {
      if (target == array[i]) {
        index = i;
        return index;
      }
    }
    return index;
  }

  // Encontra o numero de sucessores do vertice alvo (dentro da array dada)
  public static int dumbFindNumSuccessors(int targetIndex, int array[]) {
    int numSuccessors = 1;
    for (int i = targetIndex; i < array.length - 1; i++) {
      if (array[i] != array[i + 1]) {
        return numSuccessors;
      } else {
        numSuccessors++;
      }
    }
    return numSuccessors;
  }

  // Encontra os sucessores do vertice alvo (dentro da array dada)
  public static int[] dumbFindSuccessors(
    int index,
    int numSuccessors,
    int[] tails,
    int[] heads
  ) {
    int[] succ = new int[numSuccessors];
    int j = 0;
    for (int i = index; j < numSuccessors; i++) {
      succ[j] = heads[i];
      j++;
    }
    return succ;
  }

  // Teste
  public static void dumbTeste(int num, int[] tails, int[] heads) {
    int targetIndex = dumbFindTargetIndex(num, tails);
    int numSuccessors = dumbFindNumSuccessors(targetIndex, tails);

    System.out.println(
      "Existem " + numSuccessors + " sucessores ao vertice de número " + num
    );

    int[] successors = dumbFindSuccessors(
      targetIndex,
      numSuccessors,
      tails,
      heads
    );
    System.out.println("Seus sucessores são:");
    for (int i = 0; i < numSuccessors; i++) {
      System.out.println(successors[i]);
    }
  }
}
