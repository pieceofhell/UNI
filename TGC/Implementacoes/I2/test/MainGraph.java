import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
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

  public int[] getAdjacents(int vertice) {
    int[] adjacents = new int[vertices[vertice].size()];
    int i = 0;
    for (Integer adjacente : vertices[vertice]) {
      adjacents[i] = adjacente;
      i++;
    }
    return adjacents;
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

  public void Implementacao1(int vertice) {
    printExitDegree(vertice);
    printEntryDegree(vertice);
    printSuccessors(vertice);
    printPredecessors(vertice);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // Caso esteja no notebook
    String filePath1 =
      "C:/Users/henri/code/github/UNI/TGC/Implementacoes/I1/graph-test-100-1.txt";
    // Caso esteja no desktop
    String filePath2 =
      "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-100-1.txt";
    String filePath3 =
      "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-6-1.txt";
    String filePath4 =
      "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-smart.txt";
    // String userFilePath = sc.nextLine();
    MainGraph g = new MainGraph();
    try {
      g.read(filePath4);
    } catch (IOException e) {
      e.printStackTrace();
    }

    g.fillGraph();
    // g.printGraph(); // Para debug

    // int verticeEscolhido = sc.nextInt();

    // Implementacao 1
    // g.Implementacao1(verticeEscolhido);

    // Implementacao 2
    DepthFirstSearch dfs = new DepthFirstSearch(g.numVertices);
    dfs.depthFirstSearch(g);
    dfs.printTimes();
    sc.close();
  }
}
