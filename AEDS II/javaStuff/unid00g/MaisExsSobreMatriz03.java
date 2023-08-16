import java.util.*;

public class MaisExsSobreMatriz03 {

  public static void sumMat(int[][] arr1, int[][] arr2) {
    int[][] array = new int[arr1.length][arr1[0].length];
    System.out.println("Soma das matrizes:");

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        array[i][j] = arr1[i][j] + arr2[i][j];
        System.out.print("[ " + arr1[i][j] + " ]\t");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o tamanho de linhas e de colunas:");

    int n = sc.nextInt();
    int m = sc.nextInt();

    int[][] arr1 = new int[n][m];
    int[][] arr2 = new int[n][m];

    System.out.println("Insira os valores da primeira matriz:");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (sc.hasNextInt()) {
          arr1[i][j] = sc.nextInt();
        }
      }
    }

    System.out.println("Insira os valores da segunda matriz:");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (sc.hasNextInt()) {
          arr2[i][j] = sc.nextInt();
        }
      }
    }

    sumMat(arr1, arr2);

    sc.close();
  }
}