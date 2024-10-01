import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class DepthFirstSearch {
  private int time; // Variável de tempo global
  private boolean[] visited; // Array para marcar os vértices visitados
  private int[] discoveryTime; // Armazena o tempo de descoberta (TD)
  private int[] finishTime; // Armazena o tempo de término (TT)
  private int[] parent; // Armazena o pai (ancestral direto) de cada vértice

  // Construtor da classe
  public DepthFirstSearch(int numVertices) {
    this.time = 0;
    this.visited = new boolean[numVertices + 1];
    this.discoveryTime = new int[numVertices + 1];
    this.finishTime = new int[numVertices + 1];
    this.parent = new int[numVertices + 1];

    // Inicializa os arrays
    Arrays.fill(discoveryTime, 0);
    Arrays.fill(finishTime, 0);
    Arrays.fill(parent, -1); // -1 indica que não tem pai
  }

  // Método para executar a busca em profundidade iterativa
  public void depthFirstSearch(MainGraph graph) {
    for (int v = 1; v <= graph.numVertices; v++) {
      if (discoveryTime[v] == 0) { // Se o vértice ainda não foi visitado
        depthFirstSearchIterative(graph, v); // Executa a busca a partir dele
      }
    }
  }

  // Método que visita um vértice e seus adjacentes de forma iterativa
  private void depthFirstSearchIterative(MainGraph graph, int startVertex) {
    Stack<Integer> stack = new Stack<>();
    stack.push(startVertex);

    while (!stack.isEmpty()) {
      int v = stack.peek();

      if (discoveryTime[v] == 0) { // Se o vértice ainda não foi descoberto
        time++;
        discoveryTime[v] = time;
        visited[v] = true;
      }

      boolean finished = true;

      // Processa os adjacentes de v em ordem lexicográfica
      for (int w : graph.getAdjacents(v)) {
        if (discoveryTime[w] == 0) { // Se w ainda não foi descoberto
          stack.push(w); // Visita o vértice adjacente w
          parent[w] = v; // Define v como pai de w
          System.out.println("Aresta (" + v + ", " + w + ") classificada como TREE");
          finished = false; // Continua a busca a partir de w
          break;
        } else if (finishTime[w] == 0) { // Se w foi descoberto, mas não finalizado
          System.out.println("Aresta (" + v + ", " + w + ") classificada como BACK");
        } else if (discoveryTime[v] < discoveryTime[w]) {
          System.out.println("Aresta (" + v + ", " + w + ") classificada como FORWARD");
        } else {
          System.out.println("Aresta (" + v + ", " + w + ") classificada como CROSS");
        }
      }

      // Se todos os adjacentes de v foram processados, finaliza v
      if (finished) {
        time++;
        finishTime[v] = time;
        stack.pop();
      }
    }
  }

  // Método para salvar os tempos de descoberta e término em arquivo
  public void saveTimes(String filePath) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      for (int i = 1; i < discoveryTime.length; i++) {
        writer.write("Vértice: " + i + " TD: " + discoveryTime[i] + " TT: " + finishTime[i]);
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
