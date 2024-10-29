import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GraphGenerator {

  // Configurações para grafos esparsos e densos (vértices, arestas)
  private final int[][] sparseConfigs = {
    { 20, 30 },
    { 50, 70 },
    { 70, 100 },
    { 100, 150 },
  };
  private final int[][] denseConfigs = {
    { 20, 90 },
    { 50, 400 },
    { 70, 700 },
    { 100, 900 },
  };

  // Gera subgrafos a partir do arquivo original
  public void generateGraphs(String filePath) {
    try {
      generateSubgraphs(filePath, sparseConfigs, "sparseGraph");
      generateSubgraphs(filePath, denseConfigs, "denseGraph");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Método para gerar subgrafos com base nas configurações fornecidas
  private void generateSubgraphs(
    String filePath,
    int[][] configs,
    String outputPrefix
  ) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      reader.readLine(); // Ignorar a primeira linha (cabeçalho do arquivo)

      for (int[] config : configs) {
        int numVertices = config[0];
        int numEdges = config[1];

        List<String> edges = new ArrayList<>();
        String line;
        int edgeCount = 0;
        Map<Integer, Integer> vertexMap = new HashMap<>(); // Mapeamento de vértices

        int[] nextIndex = { 1 }; // Usar um array para que seja mutável dentro da lambda

        // Ler e normalizar as arestas para o subgrafo
        while (edgeCount < numEdges && (line = reader.readLine()) != null) {
          String[] parts = line.trim().split("\\s+");
          int originalTail = Integer.parseInt(parts[0]);
          int originalHead = Integer.parseInt(parts[1]);

          // Normalizar os índices dos vértices
          int tail = vertexMap.computeIfAbsent(
            originalTail,
            k -> nextIndex[0]++
          );
          int head = vertexMap.computeIfAbsent(
            originalHead,
            k -> nextIndex[0]++
          );

          edges.add(tail + " " + head);
          edgeCount++;
        }

        // Gravar o subgrafo normalizado em um novo arquivo
        try (
          PrintWriter writer = new PrintWriter(
            new FileWriter(
              outputPrefix + "_" + numVertices + "_" + numEdges + ".txt"
            )
          )
        ) {
          writer.println(vertexMap.size() + " " + numEdges); // Número de vértices e arestas
          for (String edge : edges) {
            writer.println(edge);
          }
        }
      }
    }
  }
}
