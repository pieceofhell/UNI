import java.util.Scanner;

public class NumCutoff {

  public static void main(String[] args) {
    String num1 = "";
    String num2 = "";
    double numeroTodo1 = 0.0, numDecimal1 = 0.0, decimalTarget = 0.0;
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
      for (int i = 0; i < 2; i++) {
        num1 = sc.nextLine();
        String[] decimal = new String[num1.length()];
        decimal = num1.split("\\.");
        decimal[1] = "0." + decimal[1];
        numDecimal1 = Double.parseDouble(decimal[1]);
        num1.replace(".", ",");
        numeroTodo1 = Double.parseDouble(num1);

        num2 = sc.nextLine();
        num2.replace(".", ",");
        decimalTarget = Double.parseDouble(num2);

        if (numDecimal1 > decimalTarget) {
            numeroTodo1 = Math.ceil(numeroTodo1);
            System.out.println((int) numeroTodo1);
        } else {
            numeroTodo1 = Math.floor(numeroTodo1);
            System.out.println((int) numeroTodo1);
        }
      }
    }
    sc.close();
  }
}