import java.util.Scanner;

public class MaisExsIfElse05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o salario bruto: ");
        double sal = sc.nextDouble();

        System.out.print("Digite o valor da prestação: ");
        double prest = sc.nextDouble();

        double limPrest = sal * 0.4;

        if (prest <= limPrest) {
            System.out.println("Empréstimo concedido!");
        } else {
            System.out.println("Empréstimo não concedido. O valor da prestação ultrapassa o limite permitido.");
        }
        sc.close();
    }
}