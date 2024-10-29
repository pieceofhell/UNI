// MaxFlowPaths.java
import java.util.*;

public class MaxFlowPaths {

    private int vertices;
    private LinkedList<Integer>[] adjList;  // Grafo original em forma de lista de adjacência
    private Map<String, Integer> residualGraph;  // Grafo residual para cálculo do fluxo máximo

    // Construtor que recebe o grafo e o número de vértices
    public MaxFlowPaths(LinkedList<Integer>[] graph, int vertices) {
        this.vertices = vertices;
        this.adjList = graph;
        this.residualGraph = new HashMap<>();

        // Inicializar o grafo residual com as capacidades iniciais de 1 para cada aresta
        for (int u = 1; u < adjList.length; u++) {
            for (int v : adjList[u]) {
                String edge = u + "->" + v;
                residualGraph.put(edge, 1);  // Define capacidade inicial como 1
            }
        }
    }

    // Implementação de BFS para encontrar um caminho de aumento no grafo residual
    private boolean bfs(int source, int sink, int[] parent) {
        boolean[] visited = new boolean[vertices + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adjList[u]) {
                String edge = u + "->" + v;
                if (!visited[v] && residualGraph.getOrDefault(edge, 0) > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[sink];
    }

    // Método para calcular o fluxo máximo e encontrar os caminhos disjuntos
    public void printEdgeDisjointPaths(int source, int sink) {
        int maxFlow = 0;
        int[] parent = new int[vertices + 1];
        List<List<Integer>> disjointPaths = new ArrayList<>();

        // Executa o algoritmo de Edmonds-Karp para encontrar caminhos disjuntos
        while (bfs(source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            // Encontra a capacidade mínima ao longo do caminho encontrado
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                String edge = u + "->" + v;
                pathFlow = Math.min(pathFlow, residualGraph.getOrDefault(edge, 0));
            }

            // Atualiza o grafo residual e armazena o caminho encontrado
            List<Integer> path = new ArrayList<>();
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                String edge = u + "->" + v;
                residualGraph.put(edge, residualGraph.get(edge) - pathFlow);
                path.add(0, v);
            }
            path.add(0, source);
            disjointPaths.add(path);
            maxFlow += pathFlow;
        }

        System.out.println("Número máximo de caminhos disjuntos em arestas: " + maxFlow);
        for (List<Integer> path : disjointPaths) {
            System.out.println("Caminho disjunto: " + path);
        }
    }
}
