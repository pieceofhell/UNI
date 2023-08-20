import java.io.*;
import java.util.Scanner;

public class Programa {

    public static void copiaInvChar() {
        try {
            File ArqR = new File("frase.txt");
            File ArqW = new File("invertido.txt");

            Scanner sc = new Scanner(ArqR);
            PrintWriter pw = new PrintWriter(ArqW);

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                String invertida = "";

                for (int i = linha.length() - 1; i >= 0; i--) {
                    char ch = linha.charAt(i);
                    invertida += ch;
                }

                pw.println(invertida);
            }

            sc.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }
    }

    public static void copiaMaiuscula() {
        try {
            File ArqR = new File("frase.txt");
            File ArqW = new File("maiusculas.txt");

            Scanner sc = new Scanner(ArqR);
            PrintWriter pw = new PrintWriter(ArqW);

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                pw.println(linha.toUpperCase());
            }

            sc.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }
    }

    public static void copia() {
        try {
            File ArqR = new File("frase.txt");
            File ArqW = new File("vazio.txt");

            Scanner sc = new Scanner(ArqR);
            PrintWriter pw = new PrintWriter(ArqW);

            while (sc.hasNextLine()) {
                String linha = sc.nextLine();
                pw.println(linha);
            }

            sc.close();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        try {

            File Arq = new File("frase.txt");
            Scanner sc = new Scanner(Arq);

            // Busca pelo conteúdo - frase (string) contida no arquivo .txt
            String str = sc.nextLine();

            // Busca pelo nome (também string) do arquivo
            String nome = Arq.getName();

            System.out.println("Nome do arquivo: " + nome + "\n");
            System.out.println("Frase contida no arquivo: " + str + "\n");
            sc.close();

            // Conversão do conteúdo para letras maiúsculas

            System.out.println("Conteudo do arquivo convertido para letras maiusculas:"
                    + str.toUpperCase());

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // Método declarado acima da main para copiar o conteúdo
        // de um arquivo para outro, conforme pedido

        copia();

        // Método declarado acima da main para copiar o conteúdo
        // de um arquivo, convertendo-o apenas para letras maiúsculas,
        // e copiando-o para outro arquivo, conforme pedido

        copiaMaiuscula();

        // Método declarado acima da main para copiar o conteúdo
        // de um arquivo, invertendo-o e o copiando para outro arquivo, conforme pedido

        copiaInvChar();
    }
}