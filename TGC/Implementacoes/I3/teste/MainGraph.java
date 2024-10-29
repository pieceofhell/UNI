import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class MainGraph {

  int numVertices;
  int numEdges;

  ArrayList<Integer> firstColumn;
  ArrayList<Integer> secondColumn;
  LinkedList<Integer>[] vertices;

  // Construtor (potencialmente faltando inicializacao dos vertices, mas precisaria do numero de vertices)
  MainGraph() {
    this.numVertices = 0;
    this.numEdges = 0;
    this.firstColumn = new ArrayList<>();
    this.secondColumn = new ArrayList<>();
  }

  @SuppressWarnings("unchecked")
  public void read(String filePath) throws IOException {
    try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
      String line;

      // Leitura da primeira linha
      if ((line = file.readLine()) != null) {
        String[] firstLineNumbers = line.trim().split("\\s+");
        if (firstLineNumbers.length == 2) {
          numVertices = Integer.parseInt(firstLineNumbers[0]);
          numEdges = Integer.parseInt(firstLineNumbers[1]);
        }
      }

      // System.out.println(
      //   "Numero de vertices: " + numVertices + " Numero de arestas: " + numEdges
      // ); // Debug - exibe numero de vertices e arestas

      // Inicialização da lista de adjacência
      this.vertices = new LinkedList[numVertices + 1];

      /* +1 para evitar problemas com o indice 0 vale notar tambem que o indice 0 não é utilizado, pois o grafo começa no vertice 1 e vai até o vertice numVertices por que isso acontece? porque o arquivo de entrada começa a contar os vertices a partir do 1 e não do 0 */

      // Inicialização das listas de adjacência

      for (int i = 1; i <= numVertices; i++) {
        this.vertices[i] = new LinkedList<>();
      }

      // Leitura das linhas e armazenamento dos vértices em colunas
      while ((line = file.readLine()) != null) {
        String[] numbers = line.trim().split("\\s+");
        firstColumn.add(Integer.parseInt(numbers[0]));
        secondColumn.add(Integer.parseInt(numbers[1]));
      }
    }
  }

  // Adiciona uma aresta ao grafo
  public void addEdge(int tails, int heads) {
    vertices[tails].add(heads);
  }

  // Remove uma aresta do grafo (nao testado)
  public void removeEdge(int tails, int heads) {
    vertices[tails].remove(heads);
  }

  // Preenche o grafo (array de linked lists - vertices) com as arestas do arquivo
  // guardado nas arrays da classe (firustColumn e secondColumn)
  public void fillGraph() {
    for (int i = 0; i < firstColumn.size(); i++) {
      // System.out.println(firstColumn.get(i) + " " + secondColumn.get(i)); // Debug
      addEdge(firstColumn.get(i), secondColumn.get(i));
    }
  }

  // Preenche o grafo (array de linked lists - vertices) com as arestas passadas como argumento
  public void fillGraph(int[] tails, int[] heads) {
    for (int i = 0; i < tails.length; i++) {
      // System.out.println(tails[i] + " " + heads[i]); // Debug
      addEdge(tails[i], heads[i]);
    }
  }

  // Imprime o grafo inteiro
  public void printGraph() {
    for (int i = 1; i <= numVertices; i++) {
      System.out.print("Vertice " + i + ":");
      for (Integer adjacente : vertices[i]) {
        System.out.print(" -> " + adjacente);
      }
      System.out.println();
    }
  }

  public int[] getAdjacents(int vertice) {
    int[] adjacents = new int[vertices[vertice].size()];
    int i = 0;
    for (Integer adjacente : vertices[vertice]) {
      adjacents[i] = adjacente;
      i++;
    }
    return adjacents;
  }

  // Imprime os sucessores de um vertice
  public void printSuccessors(int vertice) {
    System.out.println("Sucessores do vertice " + vertice + ":");
    for (Integer adjacente : vertices[vertice]) {
      System.out.print(adjacente + " ");
    }
    System.out.println();
  }

  public void printPredecessors(int vertice) {
    System.out.println("Predecessores do vertice " + vertice + ":");
    for (int i = 1; i <= numVertices; i++) {
      if (vertices[i].contains(vertice)) {
        System.out.print(i + " ");
      }
    }
    System.out.println();
  }

  public void printEntryDegree(int vertice) {
    int count = 0;
    for (int i = 1; i <= numVertices; i++) {
      if (vertices[i].contains(vertice)) {
        count++;
      }
    }
    System.out.println("Grau do entrada do vertice " + vertice + ": " + count);
  }

  public void printExitDegree(int vertice) {
    System.out.println(
      "Grau de saida do vertice " + vertice + ": " + vertices[vertice].size()
    );
  }

  // Método para gerar subgrafos e aplicar Implementacao3 neles
  public void gerarSubgrafos(String filePath, int source, int sink) {
    GraphGenerator generator = new GraphGenerator();
    generator.generateGraphs(filePath);

    System.out.println("Subgrafos gerados com sucesso.");

    // Para cada subgrafo gerado, ler, preencher e aplicar Implementacao3
    String[] prefixes = { "sparseGraph", "denseGraph" };
    int[][] configs = {
      { 20, 30 },
      { 50, 70 },
      { 70, 100 },
      { 100, 150 }, // Configurações para grafos esparsos
      { 20, 90 },
      { 50, 400 },
      { 70, 700 },
      { 100, 900 }, // Configurações para grafos densos
    };

    for (int i = 0; i < configs.length; i++) {
      String prefix = prefixes[i < 4 ? 0 : 1]; // "sparseGraph" para os quatro primeiros, "denseGraph" para os demais
      int numVertices = configs[i][0];
      int numEdges = configs[i][1];

      String subgraphFilePath =
        prefix + "_" + numVertices + "_" + numEdges + ".txt";
      MainGraph subgraph = new MainGraph();

      try {
        // Ler e preencher o subgrafo
        subgraph.read(subgraphFilePath);
        subgraph.fillGraph();

        // Aplicar Implementacao3 e medir o tempo
        System.out.println(
          "\nAplicando Implementação 3 no subgrafo: " +
          prefix +
          " com " +
          numVertices +
          " vértices e " +
          numEdges +
          " arestas."
        );
        subgraph.Implementacao3(source, sink, subgraph);
      } catch (IOException e) {
        System.out.println("Erro ao ler o subgrafo: " + subgraphFilePath);
        e.printStackTrace();
      }
    }
  }

  public void Implementacao1(int vertice) {
    printExitDegree(vertice);
    printEntryDegree(vertice);
    printSuccessors(vertice);
    printPredecessors(vertice);
  }

  public void Implementacao2(DepthFirstSearch dfs, MainGraph g, int vertice) {
    dfs.depthFirstSearch(g); // Realiza a DFS iterativa no grafo completo
    dfs.printTreeEdges(g); // Imprime apenas as arestas de árvore
    dfs.classifyEdges(g, vertice); // Classifica as arestas do vértice escolhido
  }

  // Método Implementacao3 com medição de tempo
  public void Implementacao3(int source, int sink, MainGraph graph) {
    MaxFlowPaths maxFlowPaths = new MaxFlowPaths(
      graph.vertices,
      graph.numVertices
    );

    // Medir o tempo de execução
    long startTime = System.nanoTime();
    maxFlowPaths.printEdgeDisjointPaths(source, sink);
    long endTime = System.nanoTime();

    // Calcular o tempo em milissegundos e exibir
    long duration = (endTime - startTime) / 1_000_000;
    System.out.println("Tempo de execução: " + duration + " ms");
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    // Caso esteja no notebook
    // String filePathEasyNotebook =
    //   "C:/Users/henri/code/github/UNI/TGC/Implementacoes/I1/graph-test-100-1.txt";
    // // Caso esteja no desktop
    // String filePathEasyDesktop =
    //   "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-100-1.txt";
    // String filePathSampleTestDesktop =
    //   "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-smart.txt";
    // String filePathHardDesktop =
    //   "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-50000-1.txt";
    // System.out.println("Insira o caminho para acesso ao arquivo do grafo: ");
    String userFilePath = sc.nextLine();
    MainGraph g = new MainGraph();
    try {
      g.read(userFilePath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // System.out.println("Leitura do arquivo realizada com sucesso, preenchendo grafo...");
    g.fillGraph();

    // System.out.println(
    //   "Grafo preenchido com sucesso, insira número do vértice escolhido: "
    // );
    // int verticeEscolhido = sc.nextInt();

    // System.out.println(
    //   "Grafo preenchido com sucesso, insira número do vértice sumidou e do vértice terminal: "
    // );
    // int source = sc.nextInt();
    // int sink = sc.nextInt();

    int source = 1;
    int sink = 10;

    // Implementacao 1
    // g.Implementacao1(verticeEscolhido);

    // g.printGraph(); // Para debug

    // Implementacao 2
    // DepthFirstSearch dfs = new DepthFirstSearch(g.numVertices);

    // g.Implementacao2(dfs, g, verticeEscolhido);

    // Implementacao 3

    g.Implementacao3(source, sink, g);
    g.gerarSubgrafos(userFilePath, source, sink);

    sc.close();
  }
}
