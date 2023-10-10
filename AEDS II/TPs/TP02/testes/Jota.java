import java.io.*;
import java.util.Scanner;

public class Jota {

  private int id;
  private String nome;
  private int altura;
  private int peso;
  private String universidade;
  private int anoNascimento;
  private String cidadeNascimento;
  private String estadoNascimento;
  static String[] allStrings;

  public Jota(
    int nId,
    String nNome,
    int nAltura,
    int nPeso,
    String nUniversidade,
    int nAnoNascimento,
    String nCidadeNascimento,
    String nEstadoNascimento
  ) {
    this.id = nId;
    this.nome = nNome;
    this.altura = nAltura;
    this.peso = nPeso;
    this.universidade = nUniversidade;
    this.anoNascimento = nAnoNascimento;
    this.cidadeNascimento = nCidadeNascimento;
    this.estadoNascimento = nEstadoNascimento;
  }

  public Jota() {
    this.id = -1;
    this.nome = "";
    this.altura = -1;
    this.peso = -1;
    this.universidade = "";
    this.anoNascimento = -1;
    this.cidadeNascimento = "";
    this.estadoNascimento = "";
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nNome) {
    this.nome = nNome;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int nId) {
    this.id = nId;
  }

  public int getAltura() {
    return this.altura;
  }

  public void setAltura(int nAltura) {
    this.altura = nAltura;
  }

  public int getPeso() {
    return this.peso;
  }

  public void setPeso(int nPeso) {
    this.peso = nPeso;
  }

  public String getUniversidade() {
    return this.universidade;
  }

  public void setUniversidade(String nUniversidade) {
    this.universidade = nUniversidade;
  }

  public int getAnoNascimento() {
    return this.anoNascimento;
  }

  public void setAnoNascimento(int nAnoNascimento) {
    this.anoNascimento = nAnoNascimento;
  }

  public String getCidadeNascimento() {
    return this.cidadeNascimento;
  }

  public void setCidadeNascimento(String nCidadeNascimento) {
    this.cidadeNascimento = nCidadeNascimento;
  }

  public String getEstadoNascimento() {
    return this.estadoNascimento;
  }

  public void setEstadoNascimento(String nEstadoNascimento) {
    this.estadoNascimento = nEstadoNascimento;
  }

  public Jota clone() {
    Jota res = new Jota(
      this.id,
      this.nome,
      this.altura,
      this.peso,
      this.universidade,
      this.anoNascimento,
      this.cidadeNascimento,
      this.estadoNascimento
    );
    return res;
  }

  public void imprimir() {
    System.out.println(
      "[" +
      this.id +
      " ## " +
      this.nome +
      " ## " +
      this.altura +
      " ## " +
      this.peso +
      " ## " +
      this.anoNascimento +
      " ## " +
      this.universidade +
      " ## " +
      this.cidadeNascimento +
      " ## " +
      this.estadoNascimento +
      "]"
    );
  }

  public static void getAllStrings(String nomeArq) {
    try {
      RandomAccessFile raf = new RandomAccessFile(nomeArq, "r");
      String[] stringList = new String[10000];
      int i = 0;
      while (raf.getFilePointer() < raf.length()) {
        stringList[i] = raf.readLine();
        i++;
      }
      allStrings = stringList;
      raf.close();
    } catch (IOException e) {
      System.out.println("Erro ao abrir o arquivo");
    }
  }

  public void ler(int id) {
    id += 1;
    if (allStrings == null) {
      getAllStrings("/tmp/players.csv");
    }
    String[] propriedades = new String[8];

    String str = allStrings[id];
    int j = 0;
    propriedades[0] = "";
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ',') {
        if (propriedades[j] == "") {
          propriedades[j] = "nao informado";
        }
        j++;
        propriedades[j] = "";
      } else {
        propriedades[j] += str.charAt(i);
      }
    }

    if (propriedades[7] == "") {
      propriedades[7] = "nao informado";
    }

    this.id = Integer.parseInt(propriedades[0]);
    this.nome = propriedades[1];
    this.altura = Integer.parseInt(propriedades[2]);
    this.peso = Integer.parseInt(propriedades[3]);
    this.universidade = propriedades[4];
    this.anoNascimento = Integer.parseInt(propriedades[5]);
    this.cidadeNascimento = propriedades[6];
    this.estadoNascimento = propriedades[7];
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String entrada = sc.nextLine();
    while (!entrada.equals("FIM")) {
      Jota player = new Jota();
      player.ler(Integer.parseInt(entrada));
      player.imprimir();
      entrada = sc.nextLine();
    }
    sc.close();
  }
}
