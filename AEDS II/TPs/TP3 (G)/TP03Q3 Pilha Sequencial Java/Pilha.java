import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Pilha {
  static Scanner scan = new Scanner(System.in);

  public class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private String anoNasc;
    private String cidadeNasc;
    private String estadoNasc;

    public Jogador() {
      this.id = 0;
      this.nome = "";
      this.altura = 0;
      this.peso = 0;
      this.universidade = "";
      this.anoNasc = "";
      this.cidadeNasc = "";
      this.estadoNasc = "";
    }

    public Jogador(int x) {
      buscarPorId(x, "/tmp/players.csv");
    }

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

    public int getPeso() {
      return peso;
    }

    public void setPeso(int peso) {
      this.peso = peso;
    }

    public String getUniversidade() {
      return universidade;
    }

    public void setUniversidade(String universidade) {
      this.universidade = universidade;
    }

    public String getAnoNasc() {
      return anoNasc;
    }

    public void setAnoNasc(String anoNasc) {
      this.anoNasc = anoNasc;
    }

    public String getCidadeNasc() {
      return cidadeNasc;
    }

    public void setCidadeNasc(String cidadeNasc) {
      this.cidadeNasc = cidadeNasc;
    }

    public String getEstadoNasc() {
      return estadoNasc;
    }

    public void setEstadoNasc(String estadoNasc) {
      this.estadoNasc = estadoNasc;
    }

    protected Jogador clone() throws CloneNotSupportedException {
      Jogador novo = new Jogador();
      novo.id = this.id;
      novo.nome = this.nome;
      novo.altura = this.altura;
      novo.universidade = this.universidade;
      novo.anoNasc = this.anoNasc;
      novo.cidadeNasc = this.cidadeNasc;
      novo.estadoNasc = this.estadoNasc;
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
        this.peso = fields.length > 3 ? Integer.parseInt(fields[3]) : -1;
        this.universidade = fields.length > 4 && !fields[4].isEmpty() ? fields[4] : "nao informado";
        this.anoNasc = fields.length > 5 ? fields[5] : "nao informado";
        this.cidadeNasc = fields.length > 6 ? fields[6] : "nao informado";
        this.estadoNasc = fields.length > 7 ? fields[7] : "nao informado";
      }

      buffer.close();
      file.close();
    }

    public void buscarPorId(int playerId, String nomeArquivo) {
      try {
        FileReader file = new FileReader(nomeArquivo);
        BufferedReader buffer = new BufferedReader(file);

        String line;
        boolean firstLine = true;
        // boolean found = false;
        while ((line = buffer.readLine()) != null) {
          if (firstLine) {
            firstLine = false;
            continue;
          }
          String[] fields = line.split(",");

          if (fields.length >= 1) {
            this.id = fields.length > 0 ? Integer.parseInt(fields[0]) : -1;

            if (id == playerId) {
              this.id = fields.length > 0 ? Integer.parseInt(fields[0]) : -1;
              this.nome = fields.length > 1 ? fields[1] : "nao informado";
              this.altura = fields.length > 2 ? Integer.parseInt(fields[2]) : -1;
              this.peso = fields.length > 3 ? Integer.parseInt(fields[3]) : -1;
              this.universidade = fields.length > 4 && !fields[4].isEmpty() ? fields[4] : "nao informado";
              this.anoNasc = fields.length > 5 ? fields[5] : "nao informado";
              this.cidadeNasc = fields.length > 6 ? fields[6] : "nao informado";
              this.estadoNasc = fields.length > 7 ? fields[7] : "nao informado";
              // found = true;
              break;
            }
          }
        }

        buffer.close();
        file.close();

      } catch (Exception jorge) {
        // String toja = "bro";
      }
    }

    public void imprimir() {
      System.out.println(toString());
    }

    public String toString() {
      String resp = "[" + id + " ## " + nome + " ## " + altura + " ## ";
      resp += peso + " ## " + anoNasc + " ## ";
      resp += universidade + " ## " + cidadeNasc + " ## ";
      resp += estadoNasc + "]";
      return resp;
    }

    public void imprimirNome() {
      System.out.println(nome);
    }
  }

  int top;
  Jogador[] arr;

  public Pilha(int tamanho) {
    top = -1;
    arr = new Jogador[tamanho];
  }

  public void inserir(Jogador x) {
    if (top < arr.length - 1) {
      arr[++top] = x;
    }
  }

  public void remover() {
    if (top >= 0) {
      System.out.println("(R) " + arr[top].getNome());
      top--;
    }
  }

  public void imprimir() {
    for (int i = 0; i <= top; i++) {
      arr[i].imprimir();
    }
  }

  public void mostrar() {
    int a = 0;
    for (int i = 0; i <= top; i++) {
        Jogador jogador = arr[i];
        System.out.printf("[%d] ## %s ## %d ## %d ## %s ## %s ## %s ## %s ##%n",
                a, jogador.getNome(), jogador.getAltura(), jogador.getPeso(),
                jogador.getAnoNasc(), jogador.getUniversidade(),
                jogador.getCidadeNasc(), jogador.getEstadoNasc());
        a++;
    }
}

  public void metodos() {
    String metodo;
    metodo = scan.next();
    if (metodo.equals("I")) {
      int id = scan.nextInt();
      inserir(new Jogador(id));
    }
    if (metodo.equals("R")) {
      remover();
    }
  }

  public static void main(String[] args) {
    Pilha pilha = new Pilha(10000);
    String linha;
    linha = scan.nextLine();
    while (!linha.equals("FIM")) {
      int num = Integer.parseInt(linha);
      pilha.inserir(pilha.new Jogador(num));
      linha = scan.nextLine();
    }
    int n = scan.nextInt();
    for (int i = 0; i < n; i++) {
      pilha.metodos();
    }
    pilha.mostrar();
  }
}
