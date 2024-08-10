class Graph {

  private int[][] adjMatrix;
  private int numVertices;

  public Graph(int numVertices) {
    this.numVertices = numVertices;
    adjMatrix = new int[numVertices][numVertices];
  }

  // Method to add an edge
  public void addEdge(int vertex1, int vertex2) {
    adjMatrix[vertex1][vertex2] = 1;
    adjMatrix[vertex2][vertex1] = 1; // For undirected graph
  }

  // Method to remove an edge
  public void removeEdge(int vertex1, int vertex2) {
    adjMatrix[vertex1][vertex2] = 0;
    adjMatrix[vertex2][vertex1] = 0;
  }

  // Method to print the graph
  public void printGraph() {
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + ": ");
      for (int j = 0; j < numVertices; j++) {
        System.out.print(adjMatrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph(4);

    // Adding edges
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 3);

    // Print the graph
    graph.printGraph();
  }
}
