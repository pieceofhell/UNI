public class DepthFirstSearch {

  MainGraph graph;
  boolean[] visited;
  int[] discoveryTime;
  int[] finishTime;
  int[] parent;
  int time;

  public DepthFirstSearch(MainGraph graph) {
    this.graph = graph;
    this.visited = new boolean[graph.numVertices + 1];
    this.discoveryTime = new int[graph.numVertices + 1];
    this.finishTime = new int[graph.numVertices + 1];
    this.parent = new int[graph.numVertices + 1];
    this.time = 0;
  }

  public void depthFirstSearch() {
    for (int i = 1; i <= graph.numVertices; i++) {
      visited[i] = false;
      parent[i] = -1;
    }

    for (int i = 1; i <= graph.numVertices; i++) {
      if (!visited[i]) {
        depthFirstSearchVisit(
          graph,
          i,
          visited,
          discoveryTime,
          finishTime,
          parent,
          time
        );
      }
    }
  }

  public void depthFirstSearchVisit(
    MainGraph graph,
    int vertice,
    boolean[] visited,
    int[] discoveryTime,
    int[] finishTime,
    int[] parent,
    int time
  ) {
    visited[vertice] = true;
    time++;
    discoveryTime[vertice] = time;
    for (int adjacente : graph.vertices[vertice]) {
      if (!visited[adjacente]) {
        parent[adjacente] = vertice;
        depthFirstSearchVisit(
          graph,
          adjacente,
          visited,
          discoveryTime,
          finishTime,
          parent,
          time
        );
      }
    }
    time++;
    finishTime[vertice] = time;
  }

  public String edgeClassifier(int vertice, int adjacente) {
    if (parent[adjacente] == vertice) {
      return "TREE";
    } else if (visited[adjacente] && discoveryTime[vertice] < discoveryTime[adjacente]) {
      return "FORWARD";
    } else if (visited[adjacente] && discoveryTime[vertice] > discoveryTime[adjacente] && finishTime[adjacente] == 0) {
      return "BACK";
    } else if (visited[adjacente] && discoveryTime[vertice] > discoveryTime[adjacente] && finishTime[adjacente] != 0) {
      return "CROSS";
    } else {
      return "NOT_CLASSIFIED";
    }
  }

  public void printVerticeEdges(int vertice) {
    for (int adjacente : graph.vertices[vertice]) {
      System.out.println(
        "Vertice: " +
        vertice +
        " Adjacente: " +
        adjacente +
        " Classificacao: " +
        edgeClassifier(vertice, adjacente)
      );
    }
  }
}
