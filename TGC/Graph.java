class Graph {Undirected {
    private int[][] adjacencyMatrix;
    private int numVertices;

    // Constructor to initialize the graph with a specific number of vertices
    public Undirected(int numVertices) {
        this.numVertices = numVertices;
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    // Method to add an edge between two vertices
    public void addEdge(int i, int j) {
        if (i >= 0 && i < numVertices && j >= 0 && j < numVertices) {
            adjacencyMatrix[i][j] = 1;
            adjacencyMatrix[j][i] = 1; // For undirected graph
        }
    }

    

    // Method to remove an edge between two vertices
    public void removeEdge(int i, int j) {
        if (i >= 0 && i < numVertices && j >= 0 && j < numVertices) {
            adjacencyMatrix[i][j] = 0;
            adjacencyMatrix[j][i] = 0; // For undirected graph
        }
    }

    // Method to print the adjacency matrix
    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numVertices = 5;
        Undirected graph = new Undirected

    // Print the graph
    graph.printGraph();
  }
}