public class DepthFirstSearch {
  private int time;  // Variável de tempo global
  private boolean[] visited;  // Array para marcar os vértices visitados
  private int[] discoveryTime;  // Armazena o tempo de descoberta (TD)
  private int[] finishTime;  // Armazena o tempo de término (TT)
  private int[] parent;  // Armazena o pai (ancestral direto) de cada vértice

  // Construtor da classe
  public DepthFirstSearch(int numVertices) {
      this.time = 0;
      this.visited = new boolean[numVertices + 1];
      this.discoveryTime = new int[numVertices + 1];
      this.finishTime = new int[numVertices + 1];
      this.parent = new int[numVertices + 1];

      // Inicializa os arrays
      for (int i = 1; i <= numVertices; i++) {
          discoveryTime[i] = 0;
          finishTime[i] = 0;
          parent[i] = -1;  // -1 indica que não tem pai
      }
  }

  // Método para executar a busca em profundidade
  public void depthFirstSearch(MainGraph graph) {
      for (int v = 1; v <= graph.numVertices; v++) {
          if (discoveryTime[v] == 0) {  // Se o vértice ainda não foi visitado
              depthFirstSearchVisit(graph, v);  // Executa a busca a partir dele
          }
      }
  }

  // Método que visita um vértice e seus adjacentes
  private void depthFirstSearchVisit(MainGraph graph, int v) {
      time++;
      discoveryTime[v] = time;  // Define o tempo de descoberta de v
      visited[v] = true;  // Marca o vértice como visitado

      for (int w : graph.getAdjacents(v)) {  // Itera sobre os adjacentes de v
          if (discoveryTime[w] == 0) {  // Se w ainda não foi descoberto
              parent[w] = v;  // Define v como pai de w
              System.out.println("Aresta (" + v + ", " + w + ") classificada como TREE");
              depthFirstSearchVisit(graph, w);  // Visita o vértice adjacente w
          } else if (finishTime[w] == 0) {  // Se w foi descoberto, mas não finalizado (ancestral)
              System.out.println("Aresta (" + v + ", " + w + ") classificada como BACK");
          } else if (discoveryTime[v] < discoveryTime[w]) {  // Se v foi descoberto antes de w
              System.out.println("Aresta (" + v + ", " + w + ") classificada como FORWARD");
          } else {  // Caso contrário, aresta cruzada
              System.out.println("Aresta (" + v + ", " + w + ") classificada como CROSS");
          }
      }

      time++;
      finishTime[v] = time;  // Define o tempo de término de v
  }

  // Método para imprimir os tempos de descoberta e término
  public void printTimes() {
      for (int i = 1; i < discoveryTime.length; i++) {
          System.out.println("Vértice: " + i + " TD: " + discoveryTime[i] + " TT: " + finishTime[i]);
      }
  }
}
