import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class MainGraph {

  int numVertices;
  int numEdges;
  ArrayList<Integer> firstColumn;
  ArrayList<Integer> secondColumn;
  LinkedList<Integer>[] vertices;

  MainGraph() {
    this.numVertices = 0;
    this.numEdges = 0;
    this.firstColumn = new ArrayList<>();
    this.secondColumn = new ArrayList<>();
  }

  public void read(String filePath) throws IOException {
    try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
      String line;
      if ((line = file.readLine()) != null) {
        String[] firstLineNumbers = line.trim().split("\\s+");
        if (firstLineNumbers.length == 2) {
          numVertices = Integer.parseInt(firstLineNumbers[0]);
          numEdges = Integer.parseInt(firstLineNumbers[1]);
        }
      }

      this.vertices = new LinkedList[numVertices + 1];
      for (int i = 1; i <= numVertices; i++) {
        this.vertices[i] = new LinkedList<>();
      }

      while ((line = file.readLine()) != null) {
        String[] numbers = line.trim().split("\\s+");
        firstColumn.add(Integer.parseInt(numbers[0]));
        secondColumn.add(Integer.parseInt(numbers[1]));
      }
    }
  }

  public void addEdge(int tails, int heads) {
    vertices[tails].add(heads);
  }

  public void fillGraph() {
    for (int i = 0; i < firstColumn.size(); i++) {
      addEdge(firstColumn.get(i), secondColumn.get(i));
    }
  }

  // Método que retorna os adjacentes de um vértice em ordem lexicográfica
  public int[] getAdjacents(int vertice) {
    // Converte a lista de adjacência para um array
    int[] adjacents = new int[vertices[vertice].size()];
    int i = 0;
    for (Integer adj : vertices[vertice]) {
      adjacents[i] = adj;
      i++;
    }
    // Ordena o array de adjacentes em ordem crescente (lexicográfica)
    Arrays.sort(adjacents);
    return adjacents;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String filePath =
      "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-50000-1.txt";

    MainGraph g = new MainGraph();
    try {
      g.read(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    g.fillGraph();

    // Redireciona a saída para um arquivo
    try (PrintStream fileOut = new PrintStream("output.txt")) {
      System.setOut(fileOut); // Redireciona a saída para arquivo

      DepthFirstSearch dfs = new DepthFirstSearch(g.numVertices);
      dfs.depthFirstSearch(g);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    sc.close();
  }
}