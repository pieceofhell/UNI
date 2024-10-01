public class DepthFirstSearch {
    private int time;
    private boolean[] visited;
    private int[] discoveryTime;
    private int[] finishTime;
    private int[] parent;

    public DepthFirstSearch(int numVertices) {
        this.time = 0;
        this.visited = new boolean[numVertices + 1];
        this.discoveryTime = new int[numVertices + 1];
        this.finishTime = new int[numVertices + 1];
        this.parent = new int[numVertices + 1];
    }

    public void depthFirstSearch(MainGraph graph) {
        for (int v = 1; v <= graph.numVertices; v++) {
            if (discoveryTime[v] == 0) {
                depthFirstSearchVisit(graph, v);
            }
        }
    }

    private void depthFirstSearchVisit(MainGraph graph, int v) {
        time++;
        discoveryTime[v] = time;
        visited[v] = true;

        for (int w : graph.vertices[v]) {
            if (discoveryTime[w] == 0) {
                parent[w] = v;
                depthFirstSearchVisit(graph, w);
            }
        }

        time++;
        finishTime[v] = time;
    }
}
