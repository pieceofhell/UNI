import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InvoiceRegister {

  private Date data;
  private long dataNum;
  private String dataString;
  private String metodoPagamento;
  private String historico;
  private float valor;

  public static List<InvoiceRegister> read(String nomeArquivo)
    throws IOException {
    FileReader file = new FileReader(nomeArquivo);
    BufferedReader buffer = new BufferedReader(file);

    String line;
    int lineCount = 0;
    List<InvoiceRegister> allTransactions = new ArrayList<>();

    while ((line = buffer.readLine()) != null) {
      lineCount++;
      if (lineCount <= 5) {
        continue;
      }

      String[] fields = line.split(";");

      InvoiceRegister transaction = new InvoiceRegister();
      transaction.dataString =
        (fields.length > 0 && !fields[0].isEmpty()) ? fields[0] : "null";
      transaction.metodoPagamento =
        (fields.length > 1 && !fields[1].isEmpty())
          ? removeAccents(fields[1])
          : "null";
      transaction.historico =
        (fields.length > 2 && !fields[2].isEmpty())
          ? removeAccents(fields[2])
          : "Poupanca";
      transaction.valor =
        (fields.length > 3 && !fields[3].isEmpty())
          ? Float.parseFloat(removeDotsReplaceComma(fields[3]))
          : -1;

      transaction.data = stringToDate(transaction.dataString);
      transaction.dataNum = dateToLong(transaction.data);

      allTransactions.add(transaction);
    }

    buffer.close();
    file.close();
    return allTransactions;
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

    public static long dateToLong(Date date) {
      Calendar baseDate = Calendar.getInstance();
      baseDate.set(1900, 0, -2); // 00/01/1900
      long diffInMillies = Math.abs(date.getTime() - baseDate.getTimeInMillis());
      long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
      return diff;
    }

    public static float removeNegative(float num) {
      if (num < 0) {
        num = 0;
      }
      return num;
    }

  public static float removePositive(float num) {
    if (num > 0) {
      num = 0;
    }
    return num;
  }

  public static Date stringToDate(String date) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    Date formattedDate = null;
    try {
      formattedDate = format.parse(date);
    } catch (Exception e) {
      System.out.println("ERRO NA CONVERSAO DA DATA!");
    }
    return formattedDate;
  }

  public static float invertNumber(float valor) {
    return valor * -1;
  }

  public static void writeToCsv(String filePath, List<InvoiceRegister> transactions) {
    try {
      FileWriter file = new FileWriter(filePath);
      BufferedWriter buffer = new BufferedWriter(file);

      buffer.write("Data;Metodo de Pagamento;Historico;Valor\n");

      for (InvoiceRegister transaction : transactions) {
        buffer.write(
          transaction.dataString +
          ";" +
          transaction.metodoPagamento +
          ";" +
          transaction.historico +
          ";" +
          transaction.valor +
          "\n"
        );
      }

      buffer.close();
      file.close();
    } catch (IOException e) {
      System.out.println("ERRO NA ESCRITA DO ARQUIVO!");
      e.printStackTrace();
    }
  }

  public void print() {
    System.out.println(toString());
  }

  public String toString() {
    String resp =
      "[" + dataString + " ## " + dataNum + " ## " + metodoPagamento + " ## " + historico + " ## ";
    resp += valor + "]";
    return resp;
  }

  public static void main(String[] args) {
    try {
      List<InvoiceRegister> allTransactions = new ArrayList<>();

      String filePath =
        "D:/gaming/site inovador/code/github/UNI/projects/extractDataFromCSV/Extrato.csv";

      allTransactions = read(filePath);

      int countRegisters = 0;

      for (InvoiceRegister transaction : allTransactions) {
        transaction.print();
        countRegisters++;
      }

      System.out.println("Total de registros: " + countRegisters);

      // float[] valores = new float[countRegisters];
      float[] valoresPositivos = new float[countRegisters];
      float[] valoresNegativos = new float[countRegisters];

      for (int i = 0; i < countRegisters; i++) {
        // valores[i] = allTransactions.get(i).valor;
        valoresPositivos[i] = removeNegative(allTransactions.get(i).valor);
        valoresNegativos[i] = removePositive(allTransactions.get(i).valor);

      writeToCsv("D:/gaming/site inovador/code/github/UNI/projects/extractDataFromCSV/teste.csv", allTransactions);
      }
    } catch (IOException e) {
      System.out.println("ERRO NA LEITURA DO ARQUIVO!");
      e.printStackTrace();
    }
  }
}
