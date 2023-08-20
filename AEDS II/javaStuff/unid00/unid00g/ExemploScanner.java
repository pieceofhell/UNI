import java.util.Scanner;

public class ExemploScanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite uma String: ");
        String str = sc.nextLine();

        System.out.print("Digite um caractere: ");
        char ch = sc.nextLine().charAt(0);

        System.out.print("Digite um inteiro: ");
        int num = sc.nextInt();

        System.out.print("Digite um numero real: ");
        double bigNum = sc.nextDouble();

        System.out.println("Valores lidos:");
        System.out.println("String: " + str);
        System.out.println("Caractere: " + ch);
        System.out.println("Inteiro: " + num);
        System.out.println("Numero real: " + bigNum);

        sc.close();
    }
}