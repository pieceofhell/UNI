import java.util.Random;
import java.util.Scanner;

public class AlteracaoAleatoria {
  static Random gerador = new Random();

  public static String alt(String str) {
    char sub = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
    char sub2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
    String res = "";
    for (int i = 0; i < str.length(); i++) {
      if (sub == str.charAt(i) || (int) str.charAt(i) == 65553) {
        res += (char) sub2;
      } else {
        res += str.charAt(i);
      }
    }
    return res;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    gerador.setSeed(4);
    String str = sc.nextLine();
    boolean fim = false;
    while (!fim) {
      if (str.length() == 0 || str.equals("FIM")) {
        fim = true;
      } else {
        System.out.println(alt(str));
        str = sc.nextLine();
      }
    }
    sc.close();
  }
}