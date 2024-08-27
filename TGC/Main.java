import java.util.ArrayList;
import java.util.List;

class Grafo {
    private int numVertices;
    private List<List<Integer>> adjList;

    // Construtor
    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Adiciona uma aresta ao grafo
    public void adicionarAresta(int origem, int destino) {
        adjList.get(origem).add(destino);
    }

    // Método DFS auxiliar
    private void dfsRecursivo(int v, boolean[] visitado) {
        // Marca o vértice atual como visitado e imprime
        visitado[v] = true;
        System.out.print(v + " ");

        // Visita todos os vértices adjacentes não visitados
        for (int adj : adjList.get(v)) {
            if (!visitado[adj]) {
                dfsRecursivo(adj, visitado);
            }
        }
    }

    // Método para iniciar a DFS
    public void dfs(int vInicial) {
        // Marca todos os vértices como não visitados inicialmente
        boolean[] visitado = new boolean[numVertices];

        // Chama a função recursiva a partir do vértice inicial
        dfsRecursivo(vInicial, visitado);
    }
}

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(5);

        grafo.adicionarAresta(0, 1);
        grafo.adicionarAresta(0, 2);
        grafo.adicionarAresta(1, 3);
        grafo.adicionarAresta(2, 3);
        grafo.adicionarAresta(3, 4);

        System.out.println("DFS a partir do vértice 0:");
        grafo.dfs(0);
    }
}
