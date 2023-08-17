import java.util.*;

public class MaisExsSobreString01 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira uma string:");
    String str = sc.nextLine();
    boolean achou = false;

    char[] chars = str.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == '0' || chars[i] <= '9') {
        achou = true;
      }
    }

    if (achou) {
      System.out.println("Ha numeros na string");
    } else {
      System.out.println("Nao ha numeros na string");
    }

    sc.close();
  }
}
