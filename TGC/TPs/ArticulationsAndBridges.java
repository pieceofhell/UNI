package TPs;
import java.util.*;

public class ArticulationsAndBridges {
    private int V; // Número de vértices
    private List<List<Integer>> adjList; // Lista de adjacências
    private boolean[] visited;
    private int[] disc; // Tempo de descoberta
    private int[] low;  // Menor tempo de descoberta acessível
    private int[] parent; // Pais no DFS
    private boolean[] isArticulation; // Marca vértices de articulação
    private List<int[]> bridges; // Lista de pontes (arestas críticas)

    public ArticulationsAndBridges(int vertices) {
        this.V = vertices;
        this.adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        this.visited = new boolean[vertices];
        this.disc = new int[vertices];
        this.low = new int[vertices];
        this.parent = new int[vertices];
        Arrays.fill(parent, -1);
        this.isArticulation = new boolean[vertices];
        this.bridges = new ArrayList<>();
    }

    // Adiciona arestas
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u); // Grafo não-direcionado
    }

    public void findArticulationsAndBridges() {
        for (int u = 0; u < V; u++) {
            if (!visited[u]) {
                dfs(u);
            }
        }

        // Exibe articulações
        System.out.println("Articulation points:");
        for (int u = 0; u < V; u++) {
            if (isArticulation[u]) {
                System.out.println("Vertex " + (u + 1)); // Converte de 0-indexado para 1-indexado
            }
        }

        // Exibe pontes
        System.out.println("Bridges:");
        for (int[] bridge : bridges) {
            System.out.println("Edge: " + (bridge[0] + 1) + " - " + (bridge[1] + 1)); // Converte de 0-indexado para 1-indexado
        }
    }

    private void dfs(int u) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adjList.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                children++;
                dfs(v);

                // Atualiza o valor de low[u]
                low[u] = Math.min(low[u], low[v]);

                // Verifica se u é uma articulação
                if (parent[u] == -1 && children > 1) {
                    isArticulation[u] = true; // Caso raiz com mais de 1 filho
                }
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    isArticulation[u] = true; // Caso geral
                }

                // Verifica se (u, v) é uma ponte
                if (low[v] > disc[u]) {
                    bridges.add(new int[]{u, v});
                }
            } else if (v != parent[u]) {
                // Atualiza o valor de low[u] para back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    private static int time = 0;

    public static void main(String[] args) {
        // Grafo da imagem fornecida
        ArticulationsAndBridges graph = new ArticulationsAndBridges(11);

        // Adicionando arestas
        graph.addEdge(0, 1); // 1 - 2
        graph.addEdge(0, 2); // 1 - 3
        graph.addEdge(1, 2); // 2 - 3
        graph.addEdge(2, 4); // 3 - 5
        graph.addEdge(1, 3); // 2 - 4
        graph.addEdge(3, 4); // 4 - 5
        graph.addEdge(3, 6); // 4 - 7
        graph.addEdge(6, 7); // 7 - 8
        graph.addEdge(4, 5); // 5 - 6
        graph.addEdge(7, 8); // 8 - 9
        graph.addEdge(6, 9); // 7 - 10
        graph.addEdge(9, 10); // 10 - 11

        // Executa o algoritmo
        graph.findArticulationsAndBridges();
    }
}
