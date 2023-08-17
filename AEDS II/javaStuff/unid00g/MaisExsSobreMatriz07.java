import java.util.*;

public class MaisExsSobreMatriz07 {

  public static void matColAvg(int[][] arr1) {
    System.out.println("Media dos elementos de cada coluna da matriz:");
    double sum = 0;
    for (int i = 0; i < arr1[0].length; i++) { 
      for (int j = 0; j < arr1.length; j++) {
        sum += arr1[j][i];
      }
      double avg = sum / arr1.length;
      System.out.println(avg);
      sum = 0;
    }    
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o tamanho de linhas e de colunas:");
    int n = sc.nextInt();
    int m = sc.nextInt();

    int[][] arr1 = new int[n][m];

    System.out.println("Insira os valores da matriz:");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (sc.hasNextInt()) {
          arr1[i][j] = sc.nextInt();
        }
      }
    }

    System.out.println();
    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr1[i].length; j++) {
        System.out.print("[ " + arr1[i][j] + " ]\t");
      }
      System.out.println();
    }

    matColAvg(arr1);

    sc.close();
  }
}
