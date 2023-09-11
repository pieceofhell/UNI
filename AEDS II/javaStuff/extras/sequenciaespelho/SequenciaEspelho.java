import java.util.Scanner;

public class SequenciaEspelho {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
      if (sc.hasNextInt()) {
        int start = sc.nextInt();
        int end = sc.nextInt();

        if (start == 0 && end == 0) {
          break;
        }

        StringBuilder res = new StringBuilder();

        for (int i = start; i <= end; i++) {
          res.append(i);
        }

        String str = res.toString();

        for (int i = str.length() - 1; i >= 0; i--) {
          res.append(str.charAt(i));
        }

        str = res.toString();

        System.out.println(str);
      } else {
        break;
      }
    }

    sc.close();
  }
}