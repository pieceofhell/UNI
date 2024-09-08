package Implementacoes.I2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Vertice {

  ArrayList<Aresta> arestas;
  int grauSaida;
  int grauEntrada;
  int index;

  public Vertice() {
    this.arestas = new ArrayList<>();
    this.grauSaida = 0;
    this.grauEntrada = 0;
    this.index = 0;
  }

  public void addEdge(Aresta aresta) {
    this.arestas.add(aresta);
    this.grauSaida++;
  }

  public void removeEdge(Aresta aresta) {
    this.arestas.remove(aresta);
    this.grauSaida--;
  }
}

class Aresta {

  Vertice origem;
  Vertice destino;
  String categoria;

  public Aresta(Vertice destino) {
    this.destino = destino;
    this.categoria = "";
  }

  public Aresta(Vertice origem, Vertice destino) {
    this.origem = origem;
    this.destino = destino;
  }

  public Aresta(Vertice origem, Vertice destino, String categoria) {
    this.origem = origem;
    this.destino = destino;
    this.categoria = categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getCategoria() {
    return categoria;
  }
}

public class MainGraph {

  int numVertices, numEdges;
  ArrayList<Vertice> vertices;

  int time;

  ArrayList<Integer> firstColumn;
  ArrayList<Integer> secondColumn;

  public MainGraph() {
    this.numVertices = 0;
    this.numEdges = 0;
    this.firstColumn = new ArrayList<>();
    this.secondColumn = new ArrayList<>();
  }

  public void addEdge(int origem, int destino) {
    Aresta aresta = new Aresta(vertices.get(origem), vertices.get(destino));
    vertices.get(origem).addEdge(aresta);
  }

  public void removeEdge(int origem, int destino) {
    Aresta aresta = new Aresta(vertices.get(origem), vertices.get(destino));
    vertices.get(origem).removeEdge(aresta);
  }

  public void specialDepthFirstSearch(int vertice) {
    boolean[] visited = new boolean[numVertices];
    int[] discoveryTime = new int[numVertices];
    int[] finishTime = new int[numVertices];
    time = 0;
    specialDepthFirstSearch(vertice, visited, discoveryTime, finishTime);
  }

  private void specialDepthFirstSearch(
    int vertice,
    boolean[] visited,
    int[] discoveryTime,
    int[] finishTime
  ) {
    visited[vertice] = true;
    discoveryTime[vertice] = ++time;

    // Sort edges lexicographically
    ArrayList<Aresta> sortedEdges = new ArrayList<>(
      vertices.get(vertice).arestas
    );
    Collections.sort(
      sortedEdges,
      Comparator.comparingInt(a -> vertices.indexOf(a.destino))
    );

    for (Aresta aresta : sortedEdges) {
      int destinoIndex = vertices.indexOf(aresta.destino);
      if (!visited[destinoIndex]) {
        aresta.setCategoria("Tree Edge");
        System.out.println("Tree Edge: " + vertice + " -> " + destinoIndex);
        specialDepthFirstSearch(
          destinoIndex,
          visited,
          discoveryTime,
          finishTime
        );
      } else if (
        discoveryTime[destinoIndex] < discoveryTime[vertice] &&
        finishTime[destinoIndex] == 0
      ) {
        aresta.setCategoria("Back Edge");
        System.out.println("Back Edge: " + vertice + " -> " + destinoIndex);
      } else if (discoveryTime[destinoIndex] > discoveryTime[vertice]) {
        aresta.setCategoria("Forward Edge");
        System.out.println("Forward Edge: " + vertice + " -> " + destinoIndex);
      } else {
        aresta.setCategoria("Cross Edge");
        System.out.println("Cross Edge: " + vertice + " -> " + destinoIndex);
      }
    }

    finishTime[vertice] = ++time;
  }

  public void fillGraph() {
    for (int i = 0; i < firstColumn.size(); i++) {
      addEdge(firstColumn.get(i), secondColumn.get(i));
    }
  }

  public void printGraph() {
    for (int i = 1; i <= numVertices; i++) {
      System.out.print("Vertice " + i + ":");
      for (Aresta aresta : vertices.get(i).arestas) {
        int destinoIndex = vertices.indexOf(aresta.destino);
        System.out.print(" -> " + destinoIndex);
      }
      System.out.println();
    }
  }

  public void printSuccessors(int vertice) {
    System.out.println("Sucessores do vertice " + vertice + ":");
    for (Aresta aresta : vertices.get(vertice).arestas) {
      int destinoIndex = vertices.indexOf(aresta.destino);
      System.out.print(destinoIndex + " ");
    }
    System.out.println();
  }

  public void printPredecessors(int vertice) {
    System.out.println("Predecessores do vertice " + vertice + ":");
    for (int i = 1; i <= numVertices; i++) {
      for (Aresta aresta : vertices.get(i).arestas) {
        int destinoIndex = vertices.indexOf(aresta.destino);
        if (destinoIndex == vertice) {
          System.out.print(i + " ");
        }
      }
    }
    System.out.println();
  }

  public void printEntryDegree(int vertice) {
    int count = 0;
    for (int i = 1; i <= numVertices; i++) {
      for (Aresta aresta : vertices.get(i).arestas) {
        int destinoIndex = vertices.indexOf(aresta.destino);
        if (destinoIndex == vertice) {
          count++;
        }
      }
    }
    System.out.println("Grau de entrada do vertice " + vertice + ": " + count);
  }

  public void printExitDegree(int vertice) {
    System.out.println(
      "Grau de saida do vertice " +
      vertice +
      ": " +
      vertices.get(vertice).grauSaida
    );
  }

  public void depthFirstSearch(int vertice) {
    boolean[] visited = new boolean[numVertices + 1];
    depthFirstSearch(vertice, visited);
  }

  private void depthFirstSearch(int vertice, boolean[] visited) {
    visited[vertice] = true;
    System.out.print(vertice + " ");
    for (Aresta aresta : vertices.get(vertice).arestas) {
      int destinoIndex = vertices.indexOf(aresta.destino);
      if (!visited[destinoIndex]) {
        depthFirstSearch(destinoIndex, visited);
      }
    }
  }

  public void breadthFirstSearch(int vertice) {
    boolean[] visited = new boolean[numVertices + 1];
    breadthFirstSearch(vertice, visited);
  }

  private void breadthFirstSearch(int vertice, boolean[] visited) {
    ArrayList<Integer> queue = new ArrayList<>();
    visited[vertice] = true;
    queue.add(vertice);
    while (!queue.isEmpty()) {
      vertice = queue.remove(0);
      System.out.print(vertice + " ");
      for (Aresta aresta : vertices.get(vertice).arestas) {
        int destinoIndex = vertices.indexOf(aresta.destino);
        if (!visited[destinoIndex]) {
          visited[destinoIndex] = true;
          queue.add(destinoIndex);
        }
      }
    }
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
      this.vertices = new ArrayList<>(numVertices);

      // Inicialização das listas de adjacência

      for (int i = 0; i <= numVertices; i++) {
        this.vertices.add(new Vertice());
      }

      // Leitura das linhas e armazenamento dos vértices em colunas
      while ((line = file.readLine()) != null) {
        String[] numbers = line.trim().split("\\s+");
        firstColumn.add(Integer.parseInt(numbers[0]));
        secondColumn.add(Integer.parseInt(numbers[1]));
      }
    }
  }

  public static void main(String[] args) {
    String filepath =
      "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/I2/graph-test-100-1.txt";
    MainGraph g = new MainGraph();
    try {
      g.read(filepath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    g.fillGraph();
    g.printGraph();

    System.out.println("Special search: ");
    g.specialDepthFirstSearch(1);
  }
}
