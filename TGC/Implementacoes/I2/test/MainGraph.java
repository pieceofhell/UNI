import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayDeque;
import java.util.Deque;

public class MainGraph {

  int numVertices;
  int numEdges;

  Deque<Integer>[] vertices; // Usar Deque ao invés de LinkedList para melhor performance

  // Construtor
  MainGraph() {
    this.numVertices = 0;
    this.numEdges = 0;
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

      // Inicialização da lista de adjacência com Deque
      this.vertices = new ArrayDeque[numVertices + 1];

      for (int i = 1; i <= numVertices; i++) {
        this.vertices[i] = new ArrayDeque<>();
      }

      // Leitura das arestas
      while ((line = file.readLine()) != null) {
        String[] numbers = line.trim().split("\\s+");
        int tails = Integer.parseInt(numbers[0]);
        int heads = Integer.parseInt(numbers[1]);
        addEdge(tails, heads);
      }
    }
  }

  // Adiciona uma aresta ao grafo
  public void addEdge(int tails, int heads) {
    vertices[tails].add(heads);
  }

  // Método para imprimir o grafo (apenas para verificação)
  public void printGraph() {
    for (int i = 1; i <= numVertices; i++) {
      System.out.print("Vértice " + i + ":");
      for (Integer adj : vertices[i]) {
        System.out.print(" -> " + adj);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    MainGraph g = new MainGraph();
    try {
      // Substituir pelo caminho do arquivo com 50k vértices
      g.read(
        "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-50000-1.txt"
      );
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Teste de impressão ou outra operação no grafo
    g.printGraph();
  }
}
