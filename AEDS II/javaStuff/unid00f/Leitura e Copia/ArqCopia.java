import java.io.*;
import java.util.*;

public class ArqCopia {
    public static void main(String[] args) {
        try {
            File arquivo = new File("exemplo3.txt");
            Scanner ArqR = new Scanner(arquivo);

            // Defina a configuração regional para usar o ponto como separador decimal
            ArqR.useLocale(Locale.US);

            String ola = ArqR.nextLine();
            int inteiro = ArqR.nextInt();
            double real = ArqR.nextDouble();

            System.out.println("string: " + ola);
            System.out.println("inteiro: " + inteiro);
            System.out.println("real: " + real);

            ArqR.close();

            PrintWriter ArqW = new PrintWriter("exemplo3copy.txt");
            ArqW.println(ola);
            ArqW.println(inteiro);
            ArqW.println(real);

            ArqW.close();

            // Alternativamente também é possível fazer o seguinte...

            File arqMain = new File("exemplo3.txt");
            Scanner ArqR2 = new Scanner(arqMain);

            String str = "";

            while (ArqR2.hasNextLine()) {
                str += ArqR2.nextLine() + "\n";
                // Lê a linha inteira e adiciona uma quebra de linha (manutenção da formatação
                // original)
            }

            ArqR2.close();

            PrintWriter ArqW2 = new PrintWriter("exemplo3copy2.txt");
            ArqW2.print(str);
            // Utilização print() em vez de println() para não adicionar
            // uma linha extra no final

            ArqW2.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }
    }
}