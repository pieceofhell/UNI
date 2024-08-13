import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;

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
    // Caso esteja no desktop
    String filePath1 =
      "C:/Users/henri/code/github/UNI/TGC/Implementacoes/I1/graph-test-100-1.txt";
    // Caso esteja no notebook
    String filePath2 =
      "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/I1/graph-test-100-1.txt";
    MainGraph g = new MainGraph();
    try {
      g.read(filePath2);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // g.lastVertice(); // Debug

    g.fillGraph();
    g.printGraph();

    // System.out.println("Teste 1: ");
    // Teste(1, g.firstColumn.stream().mapToInt(i -> i).toArray(), g.secondColumn.stream().mapToInt(i -> i).toArray());

    // System.out.println(g.vertices[0].get(0));
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

      System.out.println(
        "Numero de vertices: " + numVertices + " Numero de arestas: " + numEdges
      ); // Debug

      // Opcional; Inicialização da lista de adjacência
      this.vertices = new LinkedList[numVertices+1]; // +1 para evitar problemas com o indice 0
      // vale notar tambem que o indice 0 não é utilizado, pois o grafo começa no vertice 1
      // e vai até o vertice numVertices
      // por que isso acontece? porque o arquivo de entrada começa a contar os vertices a partir do 1
      // e não do 0

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

  // Preenche o grafo (array de linked lists - vertices) com as arestas do arquivo guardado nas arrays
  public void fillGraph() {
    for (int i = 0; i < firstColumn.size(); i++) {
      System.out.println(firstColumn.get(i) + " " + secondColumn.get(i)); // Debug
      addEdge(firstColumn.get(i), secondColumn.get(i));
    }
  }

  public void lastVertice() {
    System.out.println(
      firstColumn.get(firstColumn.size() - 5) +
      " " +
      secondColumn.get(firstColumn.size() - 5)
    ); 
  } // Debug

  // Imprime o grafo
  public void printGraph() {
    for (int i = 1; i <= numVertices; i++) {
      System.out.print("Vertice " + i + ":");
      for (Integer adjacente : vertices[i]) {
        System.out.print(" -> " + adjacente);
      }
      System.out.println();
    }
  }

  public static int findTargetIndex(int target, int array[]) {
    int index = -1;
    for (int i = 0; i < array.length; i++) {
      if (target == array[i]) {
        index = i;
        return index;
      }
    }
    return index;
  }

  public static int findNumSuccessors(int targetIndex, int array[]) {
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

  public static int[] findSuccessors(
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

  public static void Teste(int num, int[] tails, int[] heads) {
    int targetIndex = findTargetIndex(num, tails);
    int numSuccessors = findNumSuccessors(targetIndex, tails);

    System.out.println(
      "Existem " + numSuccessors + " sucessores ao vertice de número " + num
    );

    int[] successors = findSuccessors(targetIndex, numSuccessors, tails, heads);
    System.out.println("Seus sucessores são:");
    for (int i = 0; i < numSuccessors; i++) {
      System.out.println(successors[i]);
    }
  }
}
