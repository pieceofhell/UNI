public class DirectedGraph {
    private int[][] adjacencyMatrix;
    private int numVertices;

    // Constructor to initialize the graph with a specific number of vertices
    public DirectedGraph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyMatrix = new int[numVertices][numVertices];
    }

    // Method to add an edge between two vertices
    public void addEdge(int tails, int heads) {
        if (tails >= 0 && tails < numVertices && heads >= 0 && heads < numVertices) {
            adjacencyMatrix[tails][heads] = 1;
        }
    }

    public static void main(String[] args) {
        int numVertices = 5;
        DirectedGraph graph = new DirectedGraph(numVertices);

        // Adding directed edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);

        // Print the graph
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(graph.adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
