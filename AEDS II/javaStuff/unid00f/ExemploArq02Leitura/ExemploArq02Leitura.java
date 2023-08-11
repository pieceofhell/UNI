import java.io.*;
import java.util.*;

public class ExemploArq02Leitura {
    public static void main(String[] args) {
        try {
            File arquivo = new File("exemplo02.txt");
            Scanner Arq = new Scanner(arquivo);
            
            // Defina a configuração regional para usar o ponto como separador decimal
            Arq.useLocale(Locale.US);

            int inteiro = Arq.nextInt();
            double real = Arq.nextDouble();
            char caractere = Arq.next().charAt(0);
            boolean boleano = Arq.nextBoolean();
            Arq.nextLine();
            String str = Arq.nextLine();

            System.out.println("inteiro: " + inteiro);
            System.out.println("real: " + real);
            System.out.println("caractere: " + caractere);
            System.out.println("boleano: " + boleano);
            System.out.println("str: " + str);

            Arq.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }
    }
}
