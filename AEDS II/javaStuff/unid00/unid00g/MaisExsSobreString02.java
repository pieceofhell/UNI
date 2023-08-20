import java.util.*;

public class MaisExsSobreString02 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira uma string:");
    String str = sc.nextLine();

    char[] chars = str.toCharArray();

    // funcao de conversao dos caracteres para inteiros
    
    for (int i = 0; i < chars.length; i++) {
      int num = Character.getNumericValue(chars[i]);
      System.out.print(num + " ");
    }

    sc.close();
  }
}
