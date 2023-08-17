import java.util.*;

public class ExemploString {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira uma string:");
    String str = sc.nextLine();
    System.out.println("Insira um caractere:");
    char c = sc.next().charAt(0);
    boolean achou = false;

    char[] chars = str.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == c) {
        System.out.println("O caractere " + c + " esta na posicao " + (i + 1));
        achou = true;
      }
    }

    if (!achou) {
      System.out.println("O caractere " + c + " nao esta na string");
    }

    sc.close();
  }
}
