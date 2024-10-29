import java.util.*;

public class DepthFirstSearch {

  private boolean[] visited;
  private int[] discoveryTime;
  private int[] finishTime;
  private int[] parent;
  private int time;
  private int numVertices;

  public DepthFirstSearch(int numVertices) {
    this.numVertices = numVertices;
    visited = new boolean[numVertices + 1];
    discoveryTime = new int[numVertices + 1];
    finishTime = new int[numVertices + 1];
    parent = new int[numVertices + 1];
    Arrays.fill(parent, -1);
    time = 0;
  }

  private void depthFirstSearchFromVertex(MainGraph graph, int startVertex) {
    Stack<Integer> stack = new Stack<>();
    stack.push(startVertex);
    visited[startVertex] = true;
    discoveryTime[startVertex] = ++time;

    while (!stack.isEmpty()) {
      int currentVertex = stack.peek();
      boolean finished = true;

      int[] adjacents = graph.getAdjacents(currentVertex);
      Arrays.sort(adjacents);

      for (int adj : adjacents) {
        if (!visited[adj]) {
          visited[adj] = true;
          parent[adj] = currentVertex;
          discoveryTime[adj] = ++time;
          stack.push(adj);
          finished = false;
          break;
        }
      }

      if (finished) {
        finishTime[currentVertex] = ++time;
        stack.pop();
      }
    }
  }

  public void depthFirstSearch(MainGraph graph) {
    for (int i = 1; i <= numVertices; i++) {
      if (!visited[i]) {
        depthFirstSearchFromVertex(graph, i);
      }
    }
  }

  public void classifyEdges(MainGraph graph, int vertex) {
    System.out.println("Classificacao das arestas do vertice " + vertex + ":");

    for (int adj : graph.getAdjacents(vertex)) {
      if (discoveryTime[adj] > discoveryTime[vertex]) {
        if (parent[adj] == vertex) {
          System.out.println(vertex + " -> " + adj + " (Aresta de arvore)");
        } else {
          System.out.println(vertex + " -> " + adj + " (Aresta de avanco)");
        }
      } else if (parent[vertex] == adj) {
        System.out.println(vertex + " -> " + adj + " (Aresta de retorno)");
      } else if (
        discoveryTime[adj] < discoveryTime[vertex] &&
        finishTime[adj] > finishTime[vertex]
      ) {
        System.out.println(vertex + " -> " + adj + " (Aresta de retorno)");
      } else {
        System.out.println(vertex + " -> " + adj + " (Aresta de cuzamento)");
      }
    }
  }

  public void printTreeEdges(MainGraph graph) {
    System.out.println("Arestas de arvore:");
    int treeEdgeCount = 0; // Reset the tree edge count before counting
    for (int i = 1; i <= numVertices; i++) {
      for (int adj : graph.getAdjacents(i)) {
        if (parent[adj] == i) {
          treeEdgeCount++;
          System.out.println("Aresta de arvore numero " + treeEdgeCount + ": " + i + " -> " + adj);
        }
      }
    }
    // System.out.println("Numero total de arestas de arvore: " + treeEdgeCount);
  }
}
