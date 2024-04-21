import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InvoiceRegister {

  private Date data;
  private String dataString;
  private String metodoPagamento;
  private String historico;
  private float valor;

  public static List<InvoiceRegister> ler(String nomeArquivo)
    throws IOException {
    FileReader file = new FileReader(nomeArquivo);
    BufferedReader buffer = new BufferedReader(file);

    String line;
    int lineCount = 0;
    List<InvoiceRegister> allTransacoes = new ArrayList<>();

    while ((line = buffer.readLine()) != null) {
      lineCount++;
      if (lineCount <= 5) {
        continue;
      }

      String[] fields = line.split(";");

      // SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      InvoiceRegister extrato = new InvoiceRegister();
      extrato.dataString = fields.length > 0 ? fields[0] : "null";
      extrato.metodoPagamento =
        fields.length > 1 ? removeAccents(fields[1]) : "null";
      extrato.historico =
        fields.length > 2 ? removeAccents(fields[2]) : "Poupanca";
      extrato.valor =
        fields.length > 3
          ? Float.parseFloat(removeDotsReplaceComma(fields[3]))
          : -1;
      // imprimir();
      allTransacoes.add(extrato);
    }

    buffer.close();
    file.close();
    return allTransacoes;
  }

  public static String removeDoubleSpaces(String str) {
    return str.replaceAll("\\s+", " ");
  }

  public static String removeAccents(String str) {
    str = removeDoubleSpaces(str);
    return str
      .replaceAll("[éêè]", "e")
      .replaceAll("[óôò]", "o")
      .replaceAll("[áãâà]", "a")
      .replaceAll("[í]", "i")
      .replaceAll("[ú]", "u")
      .replaceAll("[ç]", "c");
  }

  public static String removeDotsReplaceComma(String valor) {
    valor = valor.replace(".", "");
    return valor.replace(',', '.');
  }

  public void imprimir() {
    System.out.println(toString());
  }

  public String toString() {
    String resp =
      "[" + dataString + " ## " + metodoPagamento + " ## " + historico + " ## ";
    resp += valor + "]";
    return resp;
  }

  public static void main(String[] args) {
    try {
      List<InvoiceRegister> allTransacoes = new ArrayList<>();

      String arquivo =
        "D:/gaming/site inovador/code/github/UNI/projects/extractDataFromCSV/Extrator.csv";

      allTransacoes = ler(arquivo);

      for (InvoiceRegister transacao : allTransacoes) {
        transacao.imprimir();
      }

    } catch (IOException e) {
      System.out.println("ERRO NA LEITURA DO ARQUIVO!");
      e.printStackTrace();
    }
  }
}
