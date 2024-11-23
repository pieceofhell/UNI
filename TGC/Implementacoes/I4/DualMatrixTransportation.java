import java.util.*;

public class DualMatrixTransportation {

  private int m, n; // Número de nós de oferta e demanda
  private int[][] costMatrix; // Matriz de custos
  private int[] supply; // Vetor de oferta
  private int[] demand; // Vetor de demanda
  private double[][] D; // Matriz Dual
  private double psi; // Objetivo atual
  private List<int[]> basicCells; // Conjunto básico

  public DualMatrixTransportation(
    int m,
    int n,
    int[][] costMatrix,
    int[] supply,
    int[] demand
  ) {
    this.m = m;
    this.n = n;
    this.costMatrix = costMatrix;
    this.supply = supply;
    this.demand = demand;
    this.D = new double[m + n][m + n]; // Tamanho estendido para virtual cells
    this.basicCells = new ArrayList<>();
  }

  // Step 0: Initialization
  public void initialize() {
    // Inicializa u, v e valores básicos
    double[] u = new double[m]; // Dual variables for supply
    double[] v = new double[n]; // Dual variables for demand

    Arrays.fill(u, 0); // Inicializar u como 0

    // Calcular vj como o custo mínimo da coluna j
    for (int j = 0; j < n; j++) {
      int minCost = Integer.MAX_VALUE;
      int selectedCol = -1;

      for (int i = 0; i < m; i++) {
        if (costMatrix[i][j] < minCost) {
          minCost = costMatrix[i][j];
          selectedCol = i;
        }
      }
      v[j] = minCost;
      basicCells.add(new int[] { selectedCol, j }); // Armazena células básicas iniciais
      // printing all basic cells
      System.out.println("Basic cell: (" + selectedCol + ", " + j + ")");
    }

    // Prepara as virtual cells
    for (int i = 0; i < m; i++) {
      basicCells.add(new int[] { i, -1 }); // Virtual cells (m, 0)
    }

    // Calcula valor inicial do objetivo
    psi = calculateObjective(u, v);
  }

  private double calculateObjective(double[] u, double[] v) {
    double result = 0;

    for (int j = 0; j < n; j++) {
      result += demand[j] * v[j];
    }
    for (int i = 0; i < m; i++) {
      result -= supply[i] * u[i];
    }
    return result;
  }

  // Step 1: Determination of the leaving cell
  public boolean determineLeavingCell() {
    double[] Y = computeY();
    int leavingIndex = -1;
    double minY = Double.MAX_VALUE;

    for (int i = 0; i < Y.length; i++) {
      if (Y[i] < minY) {
        minY = Y[i];
        leavingIndex = i;
      }
    }

    if (minY >= 0) {
      System.out.println("Optimal solution found!");
      return true; // Solução ótima
    }

    // Remove a célula correspondente
    int[] leavingCell = basicCells.get(leavingIndex);
    basicCells.remove(leavingIndex);

    return false;
  }

  private double[] computeY() {
    double[] A = new double[m + n];
    // Preenche A conforme o passo 0.1
    for (int i = 0; i < n; i++) {
      // System.out.println("demand[i]: " + demand[i]);
      A[i] = demand[i];
    }
    for (int i = 0; i < m; i++) {
      // System.out.println("supply[i]: " + -supply[i]);
      A[n + i] = -supply[i];
    }
    // Calcula Y = AD
    double[] Y = new double[m + n];
    for (int i = 0; i < m + n; i++) {
      Y[i] = 0;
      for (int j = 0; j < m + n; j++) {
        Y[i] += A[j] * D[j][i];
      }
    }
    System.out.println("Y: " + Arrays.toString(Y));
    return Y;
  }

  // Step 2: Determination of the entering cell
  public void determineEnteringCell() {
    // Calcula os vetores Q e P
    double[] Q = new double[n];
    double[] P = new double[m];

    for (int j = 0; j < n; j++) {
      Q[j] = D[j][basicCells.size()]; // Coluna correspondente a k em D
    }
    for (int i = 0; i < m; i++) {
      P[i] = D[n + i][basicCells.size()]; // Coluna correspondente a k em D
    }

    // Determina θ para células não-básicas
    double minTheta = Double.MAX_VALUE;
    int enteringRow = -1, enteringCol = -1;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (!isBasicCell(i, j)) { // Ignora células básicas
          double piMinusQj = P[i] - Q[j];
          if (piMinusQj <= 0) {
            System.out.println(
              "Problema dual não limitado; solução primal inviável."
            );
            return; // Parar se o problema não é viável
          }
          double theta = costMatrix[i][j] + P[i] - Q[j];
          if (theta < minTheta) {
            minTheta = theta;
            enteringRow = i;
            enteringCol = j;
          }
        }
      }
    }

    // Atualiza a célula de entrada
    if (enteringRow != -1 && enteringCol != -1) {
      basicCells.add(new int[] { enteringRow, enteringCol });
      System.out.println(
        "Célula de entrada adicionada: (" +
        enteringRow +
        ", " +
        enteringCol +
        ")"
      );
    }
  }

  private boolean isBasicCell(int row, int col) {
    for (int[] cell : basicCells) {
      if (cell[0] == row && cell[1] == col) {
        return true;
      }
    }
    return false;
  }

  // Step 3: Updating
  public void update() {
    // Atualiza a matriz D
    int leavingIndex = basicCells.size() - 1; // Última célula removida no Step 1
    int enteringIndex = basicCells.size() - 1; // Última célula adicionada no Step 2
    int leavingRow = basicCells.get(leavingIndex)[0];
    int leavingCol = basicCells.get(leavingIndex)[1];
    int enteringRow = basicCells.get(enteringIndex)[0];
    int enteringCol = basicCells.get(enteringIndex)[1];

    // Atualiza os elementos da coluna correspondente na matriz D
    for (int i = 0; i < m + n; i++) {
      D[i][leavingIndex] *= -1;
    }

    // Atualiza os elementos de outras colunas em D
    for (int r = 0; r < m + n; r++) {
      for (int c = 0; c < basicCells.size(); c++) {
        if (c != leavingIndex) {
          D[r][c] =
            D[r][c] +
            (D[enteringRow][c] - D[r][leavingIndex]) *
            D[r][leavingIndex];
        }
      }
    }

    // Atualiza o conjunto básico
    basicCells.set(leavingIndex, new int[] { enteringRow, enteringCol });

    // Atualiza as variáveis duais u e v
    double[] u = new double[m];
    double[] v = new double[n];
    double theta = costMatrix[enteringRow][enteringCol]; // Valor de θ para atualização

    for (int i = 0; i < m; i++) {
      u[i] -= theta * D[i][leavingIndex];
    }
    for (int j = 0; j < n; j++) {
      v[j] -= theta * D[m + j][leavingIndex];
    }

    // Recalcula o valor do objetivo
    psi = calculateObjective(u, v);

    System.out.println(
      "Célula básica atualizada: (" + enteringRow + ", " + enteringCol + ")"
    );
    System.out.println("Novo valor do objetivo: " + psi);
  }

  public void solve() {
    initialize(); // Step 0
    while (!determineLeavingCell()) { // Step 1
      determineEnteringCell(); // Step 2
      update(); // Step 3
    }
    System.out.println("Solucao ótima encontrada. Valor do objetivo: " + psi);
  }

  public static void main(String[] args) {
    // Exemplo de entrada
    int m = 3, n = 2;
    int[][] costMatrix = { { 3, 6 }, { 4, 5 }, { 7, 3 } };
    int[] supply = { 400, 300, 400 };
    int[] demand = { 450, 350 };

    System.out.println("Número de nós de oferta: " + m);
    System.out.println("Número de nós de demanda: " + n);

    System.out.println("Matriz de custos:");

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(costMatrix[i][j] + " ");
      }
      System.out.println();
    }

    DualMatrixTransportation solver = new DualMatrixTransportation(
      m,
      n,
      costMatrix,
      supply,
      demand
    );
    solver.solve();
  }
}
