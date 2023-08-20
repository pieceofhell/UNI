import java.io.*;

public class ExemploArq01Escrita {
  public static void main(String[] args) {
    try {
      PrintWriter Arq = new PrintWriter("exemplo01.txt");
      Arq.println("Olá mundo! Esse é um teste de escrita de arquivos em java.\n");
      Arq.println("Segunda linha de texto.\n");
      int x = 24 + 1;
      Arq.println(x);
      Arq.println(1);
      Arq.println(5.3);
      Arq.println('X');
      Arq.println(true);
      Arq.println("Algoritmos");
      Arq.close();
      System.out.println("Dados inseridos no arquivo com sucesso.");
    } catch (IOException e) {
      System.out.println("Ocorreu um erro.");
      e.printStackTrace();
    }
    // Note a utilização dos atributos try e catch, sendo eles um pequeno
    // fator de verificação para caso algo dê errado na manipulação do arquivo.
  }
}
