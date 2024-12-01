package I4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main class for the dual matrix transportation problem
 */
public class DualMatrixTransportation {

  private int[][] costMatrix;
  private int[] supply, demand, A, leavingCell, enteringCell;
  private double[] u, v, Q, P;
  public double[] Y;
  public double psi;
  private List<int[]> basicCells, virtualCells, gammaCellSet;
  public double[][] T, theta, dMatrix;
  private int m, n, k, s, t;
  private int thetast;
  private boolean isOptimal;
  private long startTime;
  private long endTime;
  public long executionTime;

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
    this.u = new double[m];
    this.v = new double[n];
    this.A = new int[m + n];
    this.dMatrix = new double[m + n][m + n];
    this.basicCells = new ArrayList<>();
    this.virtualCells = new ArrayList<>();
    this.gammaCellSet = new ArrayList<>();
    this.Y = new double[m + n];
    this.isOptimal = false;
    this.leavingCell = new int[2];
    this.enteringCell = new int[2];
    theta = new double[m][n];
    T = new double[m + n][2];
    Q = new double[n];
    P = new double[m];
    thetast = 0;
    s = 0;
    t = 0;
  }

  // Step 0.1: Initialize vector A
  private int[] initializeA() {
    int[] A = new int[m + n];
    for (int i = 0; i < n; i++) {
      A[i] = demand[i];
    }
    for (int i = 0; i < m; i++) {
      A[n + i] = -supply[i];
    }
    return A;
  }

  // Step 0.2: Initialize u and compute v
  private void initializeUAndComputeV() {
    Arrays.fill(u, 0); // u_i = 0
    for (int j = 0; j < n; j++) {
      v[j] = Double.POSITIVE_INFINITY;
      for (int i = 0; i < m; i++) {
        v[j] = Math.min(v[j], costMatrix[i][j]);
      }
    }
  }

  // Step 0.3: Define basic cell set
  private void defineBasicCellSet() {
    for (int j = 0; j < n; j++) {
      int minIndex = 0;
      for (int i = 1; i < m; i++) {
        if (costMatrix[i][j] < costMatrix[minIndex][j]) {
          minIndex = i;
        }
      }
      basicCells.add(new int[] { minIndex, j });
    }
    for (int i = 0; i < m; i++) {
      virtualCells.add(new int[] { i, 0 });
    }

    // System.out.println("Basic cells: ");
    // for (int[] cell : basicCells) {
    //   System.out.println(Arrays.toString(cell));
    // }

    // System.out.println("Virtual cells: ");

    // for (int[] cell : virtualCells) {
    //   System.out.println(Arrays.toString(cell));
    // }

    gammaCellSet.addAll(basicCells);
    gammaCellSet.addAll(virtualCells);
    // System.out.println("Gamma cell set: ");
    // for (int[] cell : gammaCellSet) {
    //   System.out.println(Arrays.toString(cell));
    // }
  }

  // Step 0.4: Compute objective function psi
  private void computeObjectiveFunction() {
    psi = 0;
    for (int[] cell : basicCells) {
      int j = cell[1];
      psi += demand[j] * v[j];
    }

    for (int i = 0; i < m; i++) {
      psi -= supply[i] * u[i];
    }
  }

  // Step 1: Determine the leaving cell
  private void determineLeavingCell() {
    // Step 1.1: Compute Y = A * D
    Y = computeYVector();

    // System.out.println("Vector Y (A * D): " + Arrays.toString(Y));

    // Step 1.2: Find the smallest value in Y
    int leavingIndex = -1;
    double smallestY = Double.MAX_VALUE;
    for (int k = 0; k < Y.length; k++) {
      if (Y[k] < smallestY) {
        smallestY = Y[k];
        leavingIndex = k;
      }
    }

    // System.out.println(
    //   "Smallest value in Y: " + smallestY + " at index " + leavingIndex
    // );

    // Step 1.3: Check optimality or determine leaving cell
    if (smallestY >= 0) {
      // System.out.println("Solution is optimal.");
      // System.out.println("Dual and primal solutions are optimal.");
      isOptimal = true;
    } else {
      // System.out.println("Leaving cell identified:");
      // System.out.println("Index in Gamma: " + leavingIndex);
      int[] leavingCell = gammaCellSet.get(leavingIndex);
      // System.out.println(
      //   "Cell coordinates: (" + leavingCell[0] + ", " + leavingCell[1] + ")"
      // );
      this.leavingCell = leavingCell; // Store the leaving cell
    }
  }

  // Step 1.1: Compute Y = A * D
  private double[] computeYVector() {
    double[] Y = new double[dMatrix[0].length];
    for (int j = 0; j < dMatrix[0].length; j++) {
      Y[j] = 0.0;
      for (int i = 0; i < dMatrix.length; i++) {
        Y[j] += A[i] * dMatrix[i][j];
      }
    }
    return Y;
  }

  private void constructDMatrix() {
    int[] minOfColumnIndex = new int[n];

    for (int i = 0; i < m; i++) {
      u[i] = 0;
    }
    for (int i = 0; i < n; i++) {
      int minOfColumn = costMatrix[0][i];
      minOfColumnIndex[i] = 0;
      for (int j = 1; j < m; j++) {
        if (costMatrix[j][i] < minOfColumn) {
          minOfColumn = costMatrix[j][i];
          minOfColumnIndex[i] = j;
        }
      }
      v[i] = minOfColumn;
    }

    for (int i = 0; i < n; i++) {
      T[i][0] = minOfColumnIndex[i] + 1;
      T[i][1] = i + 1;
    }
    for (int i = n; i < m + n; i++) {
      T[i][0] = i - n + 1;
      T[i][1] = 0;
    }

    // System.out.println("Matrix T:");
    // for (double[] row : T) {
    //   System.out.println(Arrays.toString(row));
    // }

    for (int i = 0; i < m + n; i++) {
      for (int j = 0; j < m + n; j++) {
        if (i == j && i < n) {
          dMatrix[i][j] = 1;
        } else if (i < n && j == n + minOfColumnIndex[i]) {
          dMatrix[i][j] = -1;
        } else if (i == j && i >= n) {
          dMatrix[i][j] = -1;
        } else {
          dMatrix[i][j] = 0;
        }
      }
    }
  }

  private boolean isBasicCell(int i, int j) {
    for (int[] cell : basicCells) {
      if (cell[0] == i && cell[1] == j) {
        return true;
      }
    }
    return false;
  }

  private void determineEnteringCell() {
    k = gammaCellSet.indexOf(leavingCell);

    // Passo 2.1: Define Q and P vectors based off of D matrix

    for (int j = 0; j < n; j++) {
      Q[j] = dMatrix[j][k];
    }
    for (int i = 0; i < m; i++) {
      P[i] = dMatrix[n + i][k];
    }

    // System.out.println("Vector Q: " + Arrays.toString(Q));
    // System.out.println("Vector P: " + Arrays.toString(P));

    // Passo 2.2: Verificar se o problema é ilimitado e calcular theta

    boolean hasValidTheta = false;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // Check if the cell is not basic
        if (!isBasicCell(i, j)) {
          if (P[i] - Q[j] > 0) { // Condition for a valid theta
            theta[i][j] = costMatrix[i][j] + u[i] - v[j];
            hasValidTheta = true;
          } else {
            theta[i][j] = Double.MAX_VALUE; // Indicate that the cell is not valid
          }
        } else {
          theta[i][j] = Double.MAX_VALUE; // Basic cells are not valid
        }
      }
    }

    if (!hasValidTheta) { // In case of an unbounded problem
      System.out.println(
        "Problema ilimitado. Não foi possível encontrar um theta válido."
      );
      isOptimal = true;
      return;
    }

    // System.out.println("Theta Matrix: ");
    // for (double[] row : theta) {
    //   System.out.println(Arrays.toString(row));
    // }

    // Passo 2.3: Find the smallest theta and determine the entering cell
    double minTheta = Double.MAX_VALUE;
    int enteringI = -1, enteringJ = -1;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (theta[i][j] < minTheta) { // Find the smallest theta
          minTheta = theta[i][j];
          enteringI = i;
          enteringJ = j;
        }
      }
    }

    thetast = (int) minTheta;

    // System.out.println("Smallest Theta: " + minTheta);
    // System.out.println("Entering Cell: (" + enteringI + ", " + enteringJ + ")");

    // Atualizar a célula de entrada
    enteringCell = new int[] { enteringI, enteringJ };
  }

  public void updateDMatrix() {
    double[][] nD = new double[m + n][m + n];

    for (int i = 0; i < m + n; i++) {
      nD[i][k] = -dMatrix[i][k];
    }

    for (int l = 0; l < m + n; l++) {
      for (int r = 0; r < m + n; r++) {
        if (r == k) continue;
        nD[l][r] =
          dMatrix[l][r] + (dMatrix[s + n][r] - dMatrix[t][r]) * nD[l][k];
      }
    }

    dMatrix = nD;

    // for (int i = 0; i < T.length; i++) {
    //   for (int j = 0; j < T[i].length; j++) {
    //     System.out.print(T[i][j] + "\t");
    //   }
    //   System.out.println();
    // }

    T[k][0] = s + 1;
    T[k][1] = t + 1;

    for (int i = 0; i < m; i++) {
      u[i] = u[i] - thetast * P[i];
    }
    for (int j = 0; j < n; j++) {
      v[j] = v[j] - thetast * Q[j];
    }

    psi = 0;

    for (int j = 0; j < n; j++) {
      psi += demand[j] * v[j];
    }
    for (int i = 0; i < m; i++) {
      psi -= supply[i] * u[i];
    }
    // System.out.println("Updated u: " + Arrays.toString(u));
    // System.out.println("Updated v: " + Arrays.toString(v));
    //    System.out.println("Updated psi: " + psi);
  }

  public void solve() {
    startTime = System.currentTimeMillis();
    // Passo 0: Inicialização
    // System.out.println("Step 0: Initializing...");
    A = initializeA();

    // System.out.println("Vector A: " + Arrays.toString(A));

    initializeUAndComputeV();
    // System.out.println("Vector u: " + Arrays.toString(u));
    // System.out.println("Vector v: " + Arrays.toString(v));

    defineBasicCellSet();

    constructDMatrix();
    // System.out.println("Matrix D:");
    // for (double[] row : dMatrix) {
    //   System.out.println(Arrays.toString(row));
    // }

    computeObjectiveFunction();
    // System.out.println("Objective function psi: " + psi);

    // Iterações

    while (!isOptimal) {
      // System.out.println("Step 1: Determining the leaving cell...");
      determineLeavingCell();

      if (isOptimal) {
        break;
      }

      // System.out.println("Step 2: Determining the entering cell...");
      determineEnteringCell();

      if (isOptimal) {
        break;
      }

      // System.out.println("Step 3: Updating...");
      updateDMatrix();
    }

    endTime = System.currentTimeMillis();

    executionTime = endTime - startTime;

    System.out.println("Total cost of the given problem: " + psi);
    for (int i = 0; i < T.length; i++) {
      System.out.println(
        "Supply point " + T[i][0] + " and demand point " + T[i][1] + ": " + Y[i]
      );
    }

    System.out.println("Execution time: " + executionTime + " ms");
  }
  // public static void main(String[] args) {
  //   int m = 3, n = 2;
  //   int[][] costMatrix = { { 3, 6 }, { 4, 5 }, { 7, 3 } };
  //   int[] supply = { 400, 300, 400 };
  //   int[] demand = { 450, 350 };

  //   // System.out.println("M (supply): " + m);
  //   // System.out.println("N (demand): " + n);
  //   // System.out.println("Cost matrix:");
  //   // for (int[] row : costMatrix) {
  //   // System.out.println(Arrays.toString(row));
  //   // }

  //   DualMatrixTransportation solver = new DualMatrixTransportation(
  //     m,
  //     n,
  //     costMatrix,
  //     supply,
  //     demand
  //   );
  //   solver.solve();
  // }
}
