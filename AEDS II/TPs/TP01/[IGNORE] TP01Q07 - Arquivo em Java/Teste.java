import java.util.Scanner;
import java.io.*;

public class Teste {

  public static String removerZero(String str) {
    int i = str.length() - 1;
    while (str.charAt(i) == '0' || str.charAt(i) == '.') {
      i--;
    }
    return str.substring(0, i + 1);
  }

  public static String decimalCorreto(String str) {
    String res = "";
    if (str.charAt(0) == '.') {
      res += "0";
    }

    String buffer = "";
    boolean parada = false;
    boolean isFloat = false;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == '.' || str.charAt(i) == '0') {
        parada = true;
        buffer += str.charAt(i);
        if (str.charAt(i) == '.') {
          isFloat = true;
        }
        continue;
      } else if (parada) {
        res += buffer;
        parada = false;
        buffer = "";
      }
      res += str.charAt(i);
    }
    if (!isFloat) {
      res += buffer;
    }
    return res;
  }

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    int nNumeros = entrada.nextInt();
    entrada.nextLine();
    try {
      RandomAccessFile raf = new RandomAccessFile("pinto.txt", "rw");
      for (int i = 0; i < nNumeros; i++) {
        String numeros = entrada.nextLine();
        numeros = decimalCorreto(numeros);
        raf.writeBytes(numeros);
        raf.writeBytes(" " + numeros.length());
        if (i != nNumeros - 1) raf.writeByte('\n');
      }
      raf.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    try {
      RandomAccessFile raf = new RandomAccessFile("pinto.txt", "r");
      raf.seek(raf.length() - 1);
      while (raf.getFilePointer() >= 0) {
        int size = raf.readByte() - '0';
        //System.out.println(size);
        raf.seek(raf.getFilePointer() - size - 2);
        String nStr = "";
        for (int i = 0; i < size; i++) {
          nStr += (char) raf.readByte();
        }
        System.out.println(nStr);
        raf.seek(raf.getFilePointer() - size - 2);
      }
      raf.close();
    } catch (IOException e) {}

    entrada.close();
  }
}
