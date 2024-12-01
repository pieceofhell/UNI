package I4;

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
  public static String getBalancedFile() {
    File folder = new File(".");
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
      if (file.isFile() && file.getName().contains("1-B")) {
        return file.getAbsolutePath();
      }
    }
    return null;
  }

  public static String getBalancedFile2() {
    File folder = new File(".");
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
      if (file.isFile() && file.getName().contains("2-B")) {
        return file.getAbsolutePath();
      }
    }
    return null;
  }

  public static String getUnbalancedFile1() {
    File folder = new File(".");
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
      if (file.isFile() && file.getName().contains("1-U")) {
        return file.getAbsolutePath();
      }
    }
    return null;
  }

  public static String getUnbalancedFile2() {
    File folder = new File(".");
    File[] listOfFiles = folder.listFiles();
    for (File file : listOfFiles) {
      if (file.isFile() && file.getName().contains("2-U")) {
        return file.getAbsolutePath();
      }
    }
    return null;
  }

  public void writeDataToFile(String outputPath, DualMatrixTransportation s)
    throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
      bw.write("Supply Nodes (m): " + m + "\n");
      bw.write("Demand Nodes (n): " + n + "\n");

      bw.write("Supply Array: " + Arrays.toString(supply) + "\n");
      bw.write("Demand Array: " + Arrays.toString(demand) + "\n");

      bw.write("Cost Matrix:\n");
      for (int[] row : costMatrix) {
        bw.write(Arrays.toString(row) + "\n");
      }

      bw.write("Total cost of the given problem: " + s.psi + "\n");
      for (int i = 0; i < s.T.length; i++) {
        bw.write(
          "Supply point " +
          s.T[i][0] +
          " and demand point " +
          s.T[i][1] +
          ": " +
          s.Y[i] +
          "\n"
        );
      }
      bw.write("Total execution time: " + s.executionTime + " ms\n");
    }
  }

  @SuppressWarnings("static-access")
  public static void main(String[] args) {
    TransportationProblem tp = new TransportationProblem();
    TransportationProblemGenerator tpGen = new TransportationProblemGenerator();

    try {
      //  tpGen.generateRandomProblem(
      //    "1-B_transport_problem.txt",
      //    3,
      //    2,
      //    50,
      //    100,
      //    true
      //  );

      // tpGen.generateRandomProblem(
      //   "2-B_transport_problem.txt",
      //   6,
      //   5,
      //   150,
      //   200,
      //   true
      // );

      // tpGen.generateRandomProblem(
      //   "3-B_transport_problem.txt",
      //   10,
      //   10,
      //   500,
      //   300,
      //   true
      // );

      // tpGen.generateRandomProblem(
      //   "1-U_transport_problem.txt",
      //   3,
      //   2,
      //   50,
      //   100,
      //   false
      // );

      // tpGen.generateRandomProblem(
      //   "2-U_transport_problem.txt",
      //   6,
      //   5,
      //   150,
      //   200,
      //   false
      // );

      // tpGen.generateRandomProblem(
      //   "3-U_transport_problem.txt",
      //   10,
      //   10,
      //   500,
      //   300,
      //   false
      // );

      String filePath1 =
        "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/1-B_transport_problem.txt";
      String filePath2 =
        "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/2-B_transport_problem.txt";
      String filePath3 =
        "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/3-B_transport_problem.txt";
      String filePath4 =
        "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/1-U_transport_problem.txt";
      String filePath5 =
        "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/2-U_transport_problem.txt";
      String filePath6 =
        "D:/gaming/site inovador/code/github/UNI/TGC/Implementacoes/3-U_transport_problem.txt";

      String[] balancedFiles = { filePath1, filePath2, filePath3 };
      String[] unbalancedFiles = { filePath4, filePath5, filePath6 };

      //
      //   tp.readFile(originalProblemFilePath);
      //   DualMatrixTransportation solver = new DualMatrixTransportation(
      //     tp.m,
      //     tp.n,
      //     tp.costMatrix,
      //     tp.supply,
      //     tp.demand
      //   );
      //   solver.solve();

      //   String outputFileName =
      //     "output_" + new File(originalProblemFilePath).getName();
      //   tp.writeDataToFile(outputFileName, solver);
      //   System.out.println(
      //     "Solved and saved results for: " + originalProblemFilePath
      //   );
      //

      for (String filePath : balancedFiles) {
        tp.readFile(filePath);
        DualMatrixTransportation solver = new DualMatrixTransportation(
          tp.m,
          tp.n,
          tp.costMatrix,
          tp.supply,
          tp.demand
        );
        solver.solve();

        String outputFileName = "output_" + new File(filePath).getName();
        tp.writeDataToFile(outputFileName, solver);
        System.out.println("Solved and saved results for: " + filePath);
      }

      for (String filePath : unbalancedFiles) {
        tp.readFile(filePath);
        DualMatrixTransportation solver = new DualMatrixTransportation(
          tp.m,
          tp.n,
          tp.costMatrix,
          tp.supply,
          tp.demand
        );
        solver.solve();

        String outputFileName = "output_" + new File(filePath).getName();
        tp.writeDataToFile(outputFileName, solver);
        System.out.println("Solved and saved results for: " + filePath);
      }
      // for (String filePath : unbalancedFilePaths) {
      //   System.out.println("Unbalanced file found: " + filePath);
      //   System.out.println("Problemas de transporte gerados com sucesso!");
      //   tp.readFile(filePath); // Replace with your file path
      //   tp.printData(); // Print the data to verify correctness

      //   DualMatrixTransportation solver = new DualMatrixTransportation(
      //     tp.m,
      //     tp.n,
      //     tp.costMatrix,
      //     tp.supply,
      //     tp.demand
      //   );
      //   solver.solve();
      // }
    } catch (IOException e) {
      System.out.println(
        "Erro ao gerar o problema de transporte: " + e.getMessage()
      );
      e.printStackTrace();
    }
  }
}
