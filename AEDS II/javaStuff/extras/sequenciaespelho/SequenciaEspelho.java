import java.util.Scanner;

public class SequenciaEspelho {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
      if (sc.hasNextInt()) {
        int start = sc.nextInt();
        int end = sc.nextInt();
        String str = "";

        if (start == 0 && end == 0) {
          break;
        }

        for (int i = start; i <= end; i++) {
          str += i;
        }

        for (int i = str.length() - 1; i >= 0; i--) {
          str += str.charAt(i);
        }
        System.out.println(str);
      } else {
        break;
      }
    }

    sc.close();
  }
}
