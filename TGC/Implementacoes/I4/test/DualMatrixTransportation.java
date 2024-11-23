package test;

import java.util.Arrays;

public class DualMatrixTransportation {

  private int m, n;
  private int[][] costMatrix;
  private int[] supply, demand;
  private double[] u, v;
  private double[][] dMatrix;
  private double psi;

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
    this.dMatrix = new double[m + n][m + n];
  }

  public void solve() {
    // Step 0.1: Initialize A
    int[] A = new int[m + n];
    for (int i = 0; i < n; i++) {
      A[i] = demand[i];
    }
    for (int i = 0; i < m; i++) {
      A[n + i] = -supply[i];
    }
    System.out.println("Vector A: " + Arrays.toString(A));

    // Step 0.2: Initialize u and compute v
    Arrays.fill(u, 0); // u_i = 0
    for (int j = 0; j < n; j++) {
      v[j] = Double.POSITIVE_INFINITY;
      for (int i = 0; i < m; i++) {
        v[j] = Math.min(v[j], costMatrix[i][j]);
      }
    }
    System.out.println("Vector u: " + Arrays.toString(u));
    System.out.println("Vector v: " + Arrays.toString(v));

    // Step 0.3: Define basic cell set Γ
    System.out.println("Basic cell set Γ: ");
    for (int j = 0; j < n; j++) {
      int minIndex = 0;
      for (int i = 1; i < m; i++) {
        if (costMatrix[i][j] < costMatrix[minIndex][j]) {
          minIndex = i;
        }
      }
      System.out.println("Basic cell: (" + minIndex + ", " + j + ")");
    }
    System.out.println("Virtual cells: ");
    for (int i = 0; i < m; i++) {
      System.out.println("Virtual cell: (" + i + ", 0)");
    }

    // Step 0.4: Construct matrix D
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dMatrix[i][j] = 1;
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < m; j++) {
        dMatrix[n + i][n + j] = -1;
      }
    }
    System.out.println("Matrix D:");
    for (double[] row : dMatrix) {
      System.out.println(Arrays.toString(row));
    }

    // Compute objective ψ
    psi = 0;
    for (int j = 0; j < n; j++) {
      psi += demand[j] * v[j];
    }
    for (int i = 0; i < m; i++) {
      psi -= supply[i] * u[i];
    }
    System.out.println("Objective function ψ: " + psi);
  }

  public static void main(String[] args) {
    // Example input
    int m = 3, n = 2;
    int[][] costMatrix = { { 3, 6 }, { 4, 5 }, { 7, 3 } };
    int[] supply = { 400, 300, 400 };
    int[] demand = { 450, 350 };

    System.out.println("Número de nós de oferta: " + m);
    System.out.println("Número de nós de demanda: " + n);
    System.out.println("Matriz de custos:");
    for (int[] row : costMatrix) {
      System.out.println(Arrays.toString(row));
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
