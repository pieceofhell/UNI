import java.util.*;

public class MaisExsSobreString05 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira uma string:");
    String str = sc.nextLine();
    char c = 'A';
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