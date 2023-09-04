import java.util.Random;
import java.util.Scanner;

public class AlteracaoAleatoria {

  public static String alt(String str, int i) {
    Random gerador = new Random();
    gerador.setSeed(4);
    char sub = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
    char sub2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
    if (i > 0) {
      if (str.charAt(i - 1) == sub) {
        str = str.substring(0, i - 1) + sub2 + str.substring(i);
      }
      return alt(str, i - 1);
    } else {
      System.out.println(str);
      return str;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String str = sc.nextLine();
    boolean fim = false;
    while (!fim) {
      if (str.length() == 0 || str.equals("FIM")) {
        fim = true;
      } else {
        str = alt(str, str.length());
        str = sc.nextLine();
      }
    }
    sc.close();
  }
}