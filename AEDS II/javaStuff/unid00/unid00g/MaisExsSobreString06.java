import java.util.*;

public class MaisExsSobreString06 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Digite uma string:");
    String str = sc.nextLine();
    int countChar = 0;
    int countVow = 0;
    int countCons = 0;
    int countOther = 0;
    char c = ' ';

    char[] chars = str.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      countChar++;
      c = str.charAt(i);
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        countVow++;
      } else if (Character.isLetter(c)) {
        countCons++;
      } else {
        countOther++;
      }

    }
    System.out.println("Essa palavra possui " + countChar + " caracteres, " + countVow + " vogais, " + countCons
        + " consoantes e " + countOther + " outros elementos nao letras.");
    sc.close();
  }
}