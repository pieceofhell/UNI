import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
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

  public static String replaceDotToComma(float value) {
    return String.valueOf(value).replace('.', ',');
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

  public static void writeToCsv(
    String filePath,
    List<InvoiceRegister> transactions
  ) {
    try {
      FileWriter file = new FileWriter(filePath);
      BufferedWriter buffer = new BufferedWriter(file);

      for (int i = transactions.size() - 1; i >= 0; i--) {
        InvoiceRegister transaction = transactions.get(i);
        String negativeValue = removeNegative(transaction.valor) == 0
          ? ""
          : replaceDotToComma(removeNegative(transaction.valor));
        String positiveValue = removePositive(transaction.valor) == 0
          ? ""
          : replaceDotToComma((removePositive(transaction.valor) * -1));

        buffer.write(
          transaction.dataNum +
          ";" +
          ";" +
          transaction.historico +
          ";" +
          ";" +
          ";" +
          ";" +
          ";" +
          negativeValue +
          ";" +
          positiveValue +
          "\n"
        );
      }

      /* ordem inversa
      for (int i = transactions.size() - 1; i >= 0; i--) {
        InvoiceRegister transaction = transactions.get(i);
        String negativeValue = removeNegative(transaction.valor) == 0
          ? ""
          : replaceDotToComma(removeNegative(transaction.valor));
        String positiveValue = removePositive(transaction.valor) == 0
          ? ""
          : replaceDotToComma((removePositive(transaction.valor) * -1));

        buffer.write(
          transaction.dataNum +
          ";" +
          ";" +
          transaction.historico +
          ";" +
          ";" +
          ";" +
          ";" +
          ";" +
          negativeValue +
          ";" +
          positiveValue +
          "\n"
        );
      }
       */

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
      "[" +
      dataString +
      " ## " +
      dataNum +
      " ## " +
      metodoPagamento +
      " ## " +
      historico +
      " ## ";
    resp += valor + "]";
    return resp;
  }

  public static List<String> findCSVFiles(File folder) {
    List<String> csvFiles = new ArrayList<>();
    if (folder.isDirectory()) {
      for (File file : folder.listFiles()) {
        if (file.isFile() && file.getName().toLowerCase().endsWith(".csv")) {
          csvFiles.add(file.getAbsolutePath());
        }
      }
    }
    return csvFiles;
  }

  public static void main(String[] args) {
    try {
      String filePath = "";
      Scanner scanner = new Scanner(System.in);
      List<InvoiceRegister> allTransactions = new ArrayList<>();
      String folderPath =
        "D:/gaming/site inovador/code/github/UNI/projects/extractDataFromCSV";
      File folder = new File(folderPath);
      List<String> csvFiles = findCSVFiles(folder);

      if (csvFiles.isEmpty()) {
        System.out.println("No CSV files found in the specified folder.");
      } else if (csvFiles.size() == 1) {
        System.out.println("Found 1 CSV file: " + csvFiles.get(0));
        filePath = csvFiles.get(0);
      } else {
        System.out.println("Found multiple CSV files:");
        for (int i = 0; i < csvFiles.size(); i++) {
          System.out.println((i + 1) + ". " + csvFiles.get(i));
        }
        System.out.print("Enter the number of the CSV file you want: ");
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= csvFiles.size()) {
          System.out.println("You chose: " + csvFiles.get(choice - 1));
          filePath = csvFiles.get(choice - 1);
        } else {
          System.out.println("Invalid choice.");
        }
      }

      allTransactions = read(filePath);

      int countRegisters = 0;

      for (InvoiceRegister transaction : allTransactions) {
        transaction.print();
        countRegisters++;
      }

      System.out.println("Total de registros: " + countRegisters);
      // float[] valores = new float[countRegisters];
      // float[] valoresPositivos = new float[countRegisters];
      // float[] valoresNegativos = new float[countRegisters];

      // for (int i = 0; i < countRegisters; i++) {
      //   valores[i] = allTransactions.get(i).valor;
      //   valoresPositivos[i] = removeNegative(allTransactions.get(i).valor);
      //   valoresNegativos[i] = removePositive(allTransactions.get(i).valor);

      //   writeToCsv(
      //     "D:/gaming/site inovador/code/github/UNI/projects/extractDataFromCSV/teste.csv",
      //     allTransactions
      //   );
      // }
      
      // TODO: implementar maneira de usuário escolher nome/pasta do arquivo de saída

      writeToCsv(
        "D:/gaming/site inovador/code/github/UNI/projects/extractDataFromCSV/extradoTratado.csv",
        allTransactions
      );
      scanner.close();
    } catch (IOException e) {
      System.out.println("ERRO NA LEITURA DO ARQUIVO!");
      e.printStackTrace();
    }
  }
}
