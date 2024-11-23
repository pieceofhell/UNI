import java.io.*;
import java.util.Random;

public class TransportationProblemGenerator {
    public static void generateRandomProblem(String filePath, int m, int n, int supplyMax, int costMax, boolean isBalanced) throws IOException {
        Random random = new Random();

        // Arrays para oferta e demanda
        int[] supply = new int[m];
        int[] demand = new int[n];
        int totalSupply = 0;
        int totalDemand = 0;

        // Gera valores aleatórios para oferta
        for (int i = 0; i < m; i++) {
            supply[i] = random.nextInt(supplyMax) + 1; // Valores de 1 a supplyMax
            totalSupply += supply[i];
        }

        // Gera valores aleatórios para demanda
        for (int i = 0; i < n; i++) {
            demand[i] = random.nextInt(supplyMax) + 1; // Valores de 1 a supplyMax
            totalDemand += demand[i];
        }

        // Se o problema deve ser balanceado, ajusta demanda para igualar oferta
        if (isBalanced) {
            if (totalSupply > totalDemand) {
                // Incrementa o último ponto de demanda para igualar oferta
                demand[n - 1] += (totalSupply - totalDemand);
            } else if (totalDemand > totalSupply) {
                // Incrementa o último ponto de oferta para igualar demanda
                supply[m - 1] += (totalDemand - totalSupply);
            }
        }

        // Matriz de custos
        int[][] costMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                costMatrix[i][j] = random.nextInt(costMax) + 1; // Valores de 1 a costMax
            }
        }

        // Escreve no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Escreve cabeçalho
            writer.write(m + " " + n);
            writer.newLine();

            // Escreve oferta
            for (int s : supply) {
                writer.write(s + "");
                writer.newLine();
            }

            writer.newLine();

            // Escreve demanda
            for (int d : demand) {
                writer.write(d + "");
                writer.newLine();
            }

            writer.newLine();

            // Escreve matriz de custos
            for (int[] row : costMatrix) {
                for (int cost : row) {
                    writer.write(cost + " ");
                }
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Gera um problema de transporte balanceado
            generateRandomProblem("balanced_transport_problem.txt", 10, 10, 50, 100, true);

            // Gera um problema de transporte desbalanceado
            generateRandomProblem("unbalanced_transport_problem.txt", 10, 10, 50, 100, false);

            System.out.println("Problemas de transporte gerados com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}