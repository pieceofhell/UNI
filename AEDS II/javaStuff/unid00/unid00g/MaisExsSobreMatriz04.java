import java.util.*;

public class MaisExsSobreMatriz04 {

  public static void printDg(int[][] arr1) {
    System.out.println("Impressao da diagonal da matriz:");
    for (int i = 0; i < arr1.length; i++) {
      System.out.print(arr1[i][i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o tamanho de linhas e de colunas:");

    int n = sc.nextInt();

    int[][] arr1 = new int[n][n];

    System.out.println("Insira os valores da matriz:");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (sc.hasNextInt()) {
          arr1[i][j] = sc.nextInt();
        }
      }
    }

    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr1[i].length; j++) {
        System.out.print("[ " + arr1[i][j] + " ]\t");
      }
      System.out.println();
    }

    printDg(arr1);

    sc.close();
  }
}