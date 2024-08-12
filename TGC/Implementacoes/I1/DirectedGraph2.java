import java.util.LinkedList;

public class DirectedGraph2 {
    private int numVertices;
    private LinkedList<Integer> adjacencyList[];

    public DirectedGraph2(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int i, int j){
    }
    public static void main(String[] args) {
        
    }
}
