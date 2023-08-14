import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExemploBufferedReader {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Digite uma String: ");
        String str = sc.readLine();

        System.out.print("Digite um caractere: ");
        char ch = sc.readLine().charAt(0);

        System.out.print("Digite um inteiro: ");
        int num = Integer.parseInt(sc.readLine());

        System.out.print("Digite um número real: ");
        double bigNum = Double.parseDouble(sc.readLine());

        System.out.println("Valores lidos:");
        System.out.println("String: " + str);
        System.out.println("Caractere: " + ch);
        System.out.println("Inteiro: " + num);
        System.out.println("Número real: " + bigNum);

        sc.close();
    }
}