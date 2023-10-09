import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Jogador {

  private int id;
  private String nome;
  private int altura;
  private double peso;
  private String universidade;
  private String anoNascimento;
  private String cidadeNascimento;
  private String estadoNascimento;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getAltura() {
    return altura;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }

  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
  }

  public String getUniversidade() {
    return universidade;
  }

  public void setUniversidade(String universidade) {
    this.universidade = universidade;
  }

  public String getAnoNascimento() {
    return anoNascimento;
  }

  public void setAnoNascimento(String anoNascimento) {
    this.anoNascimento = anoNascimento;
  }

  public String getCidadeNascimento() {
    return cidadeNascimento;
  }

  public void setCidadeNascimento(String cidadeNascimento) {
    this.cidadeNascimento = cidadeNascimento;
  }

  public String getEstadoNascimento() {
    return estadoNascimento;
  }

  public void setEstadoNascimento(String estadoNascimento) {
    this.estadoNascimento = estadoNascimento;
  }

  protected Jogador clone() throws CloneNotSupportedException {
    Jogador novo = new Jogador();
    novo.id = this.id;
    novo.nome = this.nome;
    novo.altura = this.altura;
    novo.universidade = this.universidade;
    novo.anoNascimento = this.anoNascimento;
    novo.cidadeNascimento = this.cidadeNascimento;
    novo.estadoNascimento = this.estadoNascimento;
    return novo;
  }

  public void ler(String nomeArquivo) throws IOException {
    FileReader file = new FileReader(nomeArquivo);
    BufferedReader buffer = new BufferedReader(file);

    String line;
    boolean firstLine = true;

    while ((line = buffer.readLine()) != null) {
      if (firstLine) {
        firstLine = false;
        continue;
      }

      String[] fields = line.split(",");

      this.id = fields.length > 0 ? Integer.parseInt(fields[0]) : -1;
      this.nome = fields.length > 1 ? fields[1] : "nao informado";
      this.altura = fields.length > 2 ? Integer.parseInt(fields[2]) : -1;
      this.peso = fields.length > 3 ? Double.parseDouble(fields[3]) : -1.0;
      this.universidade = fields.length > 4 ? fields[4] : "nao informado";
      this.anoNascimento = fields.length > 5 ? fields[5] : "nao informado";
      this.cidadeNascimento = fields.length > 6 ? fields[6] : "nao informado";
      this.estadoNascimento = fields.length > 7 ? fields[7] : "nao informado";
    }

    buffer.close();
    file.close();
  }

  public String buscarPorId(int playerId, String nomeArquivo) throws IOException {
    FileReader file = new FileReader(nomeArquivo);
    BufferedReader buffer = new BufferedReader(file);

    String line;
    boolean firstLine = true;
    boolean found = false;

    while ((line = buffer.readLine()) != null) {
      if (firstLine) {
        firstLine = false;
        continue;
      }

      String[] fields = line.split(",");

      if (fields.length >= 1) {
        this.id = fields.length > 0 ? Integer.parseInt(fields[0]) : -1;

        if (id == playerId) {
          this.nome = fields.length > 1 ? fields[1] : "nao informado";
          this.altura = fields.length > 2 ? Integer.parseInt(fields[2]) : -1;
          this.peso = fields.length > 3 ? Double.parseDouble(fields[3]) : -1.0;
          this.universidade = fields.length > 4 ? fields[4] : "nao informado";
          this.anoNascimento = fields.length > 5 ? fields[5] : "nao informado";
          this.cidadeNascimento =
            fields.length > 6 ? fields[6] : "nao informado";
          this.estadoNascimento =
            fields.length > 7 ? fields[7] : "nao informado";
          found = true;
          break;
        }
      }
    }

    buffer.close();
    file.close();

    if (found) {
      DecimalFormat df = new DecimalFormat("#0.##");
      String players = "[" + id + " ## " + nome + " ## " + altura + " ## ";
      players += df.format(peso) + " ## " + anoNascimento + " ## ";
      players += universidade + " ## " + cidadeNascimento + " ## ";
      players += estadoNascimento + "]";
      // System.out.println(players);
      return players;
    } else {
      System.out.println("ERRO");
      return "ERRO";
    }
    
  }

  public void imprimir() {
    System.out.println(toString());
  }

  public String toString() {
    DecimalFormat df = new DecimalFormat("#0.##");
    String resp = "[" + id + " ## " + nome + " ## " + altura + " ## ";
    resp += df.format(peso) + " ## " + anoNascimento + " ## ";
    resp += universidade + " ## " + cidadeNascimento + " ## ";
    resp += estadoNascimento + "]";
    return resp;
  }

  public void imprimirNome() {
    System.out.println(nome);
  }

  public static void main(String[] args) {
    try {
      Jogador jogador = new Jogador();

      String arquivo = "/tmp/players.csv";

      jogador.ler(arquivo);

      Scanner sc = new Scanner(System.in);

      String entrada = "";
      
      String omegaString = "";

      while (!entrada.equalsIgnoreCase("FIM")) {
        entrada = sc.nextLine();

        if (!entrada.equalsIgnoreCase("FIM")) {
          try {
            int idJogador = Integer.parseInt(entrada);
            omegaString += jogador.buscarPorId(idJogador, arquivo);
          } catch (NumberFormatException e) {
            System.out.println("ERRO");
          }
        }
      }

      entrada = "";

      while (!entrada.equalsIgnoreCase("FIM")) {
        entrada = sc.nextLine();

        if (!entrada.equalsIgnoreCase("FIM")) {
          if (omegaString.contains(entrada)) {
            System.out.println("SIM");
          } else {
            System.out.println("NAO");
          }
        }
      }

      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
