import java.io.*;

public class GetFilePointer {
    public static void main(String[] args) {
        try {
            // Abra o arquivo de entrada
            FileInputStream fis = new FileInputStream("pub.in");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            // Abra o arquivo de saída
            FileOutputStream fos = new FileOutputStream("pub_reverse.out");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            // Leitura do arquivo de entrada e escrita reversa no arquivo de saída
            String line;
            while ((line = br.readLine()) != null) {
                // Use StringBuilder para inverter a linha
                StringBuilder reversedLine = new StringBuilder(line);
                reversedLine.reverse();

                // Escreva a linha invertida no arquivo de saída
                bw.write(reversedLine.toString());
                bw.newLine();
            }

            // Feche os arquivos
            br.close();
            bw.close();

            System.out.println("Arquivo revertido com sucesso.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}