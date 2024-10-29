import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
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

  // Gera um grafo aleatório fortemente conexo com o número especificado de vértices e arestas
  @SuppressWarnings("unchecked")
  public void generateRandomStronglyConnectedGraph(
    int numVertices,
    int numEdges
  ) {
    this.numVertices = numVertices;
    this.numEdges = numEdges;

    // Inicialização da lista de adjacência
    this.vertices = new LinkedList[numVertices + 1];
    for (int i = 1; i <= numVertices; i++) {
      this.vertices[i] = new LinkedList<>();
    }

    // Adiciona um ciclo para garantir que o grafo seja conexo
    for (int i = 1; i < numVertices; i++) {
      addEdge(i, i + 1);
    }
    addEdge(numVertices, 1); // Fecha o ciclo

    // Gera arestas adicionais, garantindo que o grafo permaneça fortemente conexo
    HashSet<String> edges = new HashSet<>();
    Random random = new Random();

    while (numEdges > numVertices) { // Apenas adicione arestas se o total de arestas permitir
      int tails, heads;
      do {
        tails = random.nextInt(numVertices) + 1; // Gera um número entre 1 e numVertices
        heads = random.nextInt(numVertices) + 1; // Gera um número entre 1 e numVertices
      } while (tails == heads || edges.contains(tails + "-" + heads)); // Evita laços e arestas duplicadas

      addEdge(tails, heads);
      edges.add(tails + "-" + heads);
      numEdges--;
    }
  }

  // Gera um grafo aleatório com o número especificado de vértices e arestas
  @SuppressWarnings("unchecked")
  public void generateRandomGraph(int numVertices, int numEdges) {
    this.numVertices = numVertices;
    this.numEdges = numEdges;

    this.vertices = new LinkedList[numVertices + 1];

    // Inicialização das listas de adjacência
    for (int i = 1; i <= numVertices; i++) {
      this.vertices[i] = new LinkedList<>();
    }

    Random random = new Random();

    // Garantir que o grafo seja conexo
    HashSet<String> edges = new HashSet<>();

    // Primeiro, crie uma árvore para garantir a conexão
    for (int i = 2; i <= numVertices; i++) {
      int tails = random.nextInt(i - 1) + 1; // Escolha um vértice existente
      addEdge(tails, i); // Adicione uma aresta
      edges.add(tails + "-" + i); // Adicione a aresta ao conjunto
    }

    // Agora adicione as arestas restantes, garantindo que não haja duplicatas ou laços
    for (int i = edges.size(); i < numEdges; i++) {
      int tails, heads;
      do {
        tails = random.nextInt(numVertices) + 1;
        heads = random.nextInt(numVertices) + 1;
      } while (tails == heads || edges.contains(tails + "-" + heads));

      addEdge(tails, heads);
      edges.add(tails + "-" + heads);
      firstColumn.add(tails);
      secondColumn.add(heads);
    }
  }

  // Gera um grafo esparso fortemente conexo
  public void generateSparseGraph(int numVertices, int numEdges) {
    // Para grafos esparsos, garantimos que o número de arestas é menor que n
    if (numEdges > numVertices) {
      numEdges = numVertices; // Limita o número de arestas
    }
    generateRandomStronglyConnectedGraph(numVertices, numEdges);
  }

  // Gera um grafo denso fortemente conexo
  public void generateDenseGraph(int numVertices, int numEdges) {
    // Para grafos densos, garantimos que o número de arestas seja o máximo possível
    int maxEdges = numVertices * (numVertices - 1) / 2; // Número máximo de arestas em um grafo não direcionado
    if (numEdges > maxEdges) {
      numEdges = maxEdges; // Limita o número de arestas
    }
    generateRandomStronglyConnectedGraph(numVertices, numEdges);
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
    System.out.println("Tempo de execuçao: " + duration + " ms");
  }

  public MainGraph[] denseGraphGenerator(int numVertices, int numEdges) {
    MainGraph[] graphs = new MainGraph[4];
    graphs[0] = new MainGraph();
    graphs[1] = new MainGraph();
    graphs[2] = new MainGraph();
    graphs[3] = new MainGraph();

    graphs[0].generateDenseGraph(numVertices, numEdges);
    graphs[1].generateDenseGraph(numVertices * 10, numEdges * 100);
    graphs[2].generateDenseGraph(numVertices * 100, numEdges * 1000);
    graphs[3].generateDenseGraph(numVertices * 1000, numEdges * 10000);

    return graphs;
  }

  public MainGraph[] sparseGraphGenerator(int numVertices, int numEdges) {
    MainGraph[] graphs = new MainGraph[4];
    graphs[0] = new MainGraph();
    graphs[1] = new MainGraph();
    graphs[2] = new MainGraph();
    graphs[3] = new MainGraph();

    graphs[0].generateSparseGraph(numVertices, numEdges);
    graphs[1].generateSparseGraph(numVertices * 2, numEdges * 10);
    graphs[2].generateSparseGraph(numVertices * 5, numEdges * 100);
    graphs[3].generateSparseGraph(numVertices * 10, numEdges * 1000);

    return graphs;
  }

  public void Implementacao3Applier(MainGraph[] graphs) {
    // int source sempre começará do vértice de menor número e sink do vértice de maior número, a fim de garantir que o fluxo máximo calculado seja o mais caro possível
    int source = 1;

    for (MainGraph graph : graphs) {
      int sink = graph.numVertices;
      System.out.println(
        "Aplicando Implementacao 3 em grafo com " +
        graph.numVertices +
        " vértices e " +
        graph.numEdges +
        " arestas:"
      );
      Implementacao3(source, sink, graph);
    }
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

    System.out.println("Insira o caminho para acesso ao arquivo do grafo: ");
    String userFilePath = sc.nextLine();
    // especial: se userfilepath conter "xablingus", gerar grafos densos e esparsos (conjuntos de teste)
    if (userFilePath.contains("xablingus")) {
      int verticesBase = 100;
      int arestasBase = 500;
      MainGraph g = new MainGraph();
      MainGraph[] sparseGraphs = g.sparseGraphGenerator(
        verticesBase,
        arestasBase
      );

      MainGraph[] denseGraphs = g.denseGraphGenerator(
        verticesBase,
        arestasBase
      );

      System.out.println("Aplicaçao da Implementaçao 3 em grafos esparsos:\n");
      g.Implementacao3Applier(sparseGraphs);

      System.out.println("Aplicaçao da Implementaçao 3 em grafos densos:\n");
      g.Implementacao3Applier(denseGraphs);
      sc.close();
      return;
    }
    MainGraph g = new MainGraph();
    try {
      g.read(userFilePath);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(
      "Leitura do arquivo realizada com sucesso, preenchendo grafo..."
    );
    g.fillGraph();

    // System.out.println(
    //   "Grafo preenchido com sucesso, insira número do vértice escolhido: "
    // );
    // int verticeEscolhido = sc.nextInt();

    System.out.println(
      "Grafo preenchido com sucesso, insira número do vértice sumidouro e do vértice terminal: "
    );
    int source = sc.nextInt();
    int sink = sc.nextInt();

    // Implementacao 1
    // g.Implementacao1(verticeEscolhido);

    // g.printGraph(); // Para debug

    // Implementacao 2
    // DepthFirstSearch dfs = new DepthFirstSearch(g.numVertices);

    // g.Implementacao2(dfs, g, verticeEscolhido);

    // Implementacao 3

    g.Implementacao3(source, sink, g);

    sc.close();
  }
}
