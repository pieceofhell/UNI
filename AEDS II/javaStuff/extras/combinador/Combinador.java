import java.util.Scanner;

public class Combinador {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str1 = sc.nextLine();
    String str2 = sc.nextLine();
    StringBuilder res = new StringBuilder();

    int i = 0;
    int j = 0;

    while (i < str1.length() || j < str2.length()) {
      if (i < str1.length()) {
        res.append(str1.charAt(i));
        i++;
      }
      if (j < str2.length()) {
        res.append(str2.charAt(j));
        j++;
      }
    }

    String resultadoFinal = res.toString();
    System.out.println(resultadoFinal);

    sc.close();
  }
}
