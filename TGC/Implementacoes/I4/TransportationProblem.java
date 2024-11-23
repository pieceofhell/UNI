import java.io.*;
import java.util.*;

public class TransportationProblem {

  private int m; // Number of supply nodes
  private int n; // Number of demand nodes
  private int[] supply;
  private int[] demand;
  private int[][] costMatrix;

  /**
   * Função para ler o arquivo contendo os dados do problema de transporte
   * @param filePath
   * @throws IOException
   */
  public void readFile(String filePath) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      // Leitura do cabeçalho
      String[] dimensions = br.readLine().split(" ");
      m = Integer.parseInt(dimensions[0]);
      n = Integer.parseInt(dimensions[1]);
      // System.out.println("m: " + m + ", n: " + n);

      // Inicialização de arrays
      supply = new int[m];
      demand = new int[n];
      costMatrix = new int[m][n];

      // Leitura da lista dos valores dos pontos de oferta
      for (int i = 0; i < m; i++) {
        supply[i] = Integer.parseInt(br.readLine().trim());
        // System.out.println("Supply " + i + ": " + supply[i]);
      }

      br.readLine(); // Skippar a linha em branco entre os arrays de oferta e demanda

      // Leitura da lista dos valores dos pontos de demanda
      for (int i = 0; i < n; i++) {
        demand[i] = Integer.parseInt(br.readLine().trim());
        // System.out.println("Demand " + i + ": " + demand[i]);
      }

      br.readLine(); // Skippar a linha em branco entre os arrays de demanda e matriz de custos

      for (int i = 0; i < m; i++) {
        String[] line = br.readLine().trim().split("\\s+");
        for (int j = 0; j < n; j++) {
          line[j] = line[j].replaceAll("[^\\d]", "");
          costMatrix[i][j] = Integer.parseInt(line[j]);
        }
      }
    } catch (IOException e) {
      System.out.println("Erro ao ler o arquivo: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public void printData() {
    System.out.println("Supply Nodes (m): " + m);
    System.out.println("Demand Nodes (n): " + n);

    System.out.println("Supply Array: " + Arrays.toString(supply));
    System.out.println("Demand Array: " + Arrays.toString(demand));

    System.out.println("Cost Matrix:");
    for (int[] row : costMatrix) {
      System.out.println(Arrays.toString(row));
    }
  }

  /**
   * Função para retornar o caminho do arquivo balanceado
   * @return String
   */
  public String getBalancedFile() {
    File folder = new File(".");
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
      if (file.isFile() && file.getName().contains("balanced")) {
        return file.getAbsolutePath();
      }
    }
    return null;
  }

  public static void main(String[] args) {
    TransportationProblem tp = new TransportationProblem();
    try {
      // Gera um problema de transporte balanceado
      TransportationProblemGenerator.generateRandomProblem(
        "balanced_transport_problem.txt",
        10,
        10,
        50,
        100,
        true
      );

      // Gera um problema de transporte desbalanceado
      //   TransportationProblemGenerator.generateRandomProblem(
      //     "unbalanced_transport_problem.txt",
      //     10,
      //     10,
      //     50,
      //     100,
      //     false
      //   );

      String filePath = tp.getBalancedFile();

      if (filePath == null) {
        System.out.println("No balanced file found in the current directory.");
        return;
      }

      System.out.println("Balanced file found: " + filePath);
      System.out.println("Problemas de transporte gerados com sucesso!");
      tp.readFile(filePath); // Replace with your file path
      tp.printData(); // Print the data to verify correctness
    } catch (IOException e) {
      System.out.println(
        "Erro ao gerar o problema de transporte: " + e.getMessage()
      );
      e.printStackTrace();
    }
  }
}
