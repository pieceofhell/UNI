import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Arvore {

  public class Jogador {

    public int id;
    public String nome;
    public int altura;
    public int peso;
    public String universidade;
    public int anoNascimento;
    public String cidadeNascimento;
    public String estadoNascimento;
    public String[] dados;

    public Jogador(
      int id,
      String nome,
      int altura,
      int peso,
      String universidade,
      int anoNascimento,
      String cidadeNascimento,
      String estadoNascimento
    ) {
      this.id = id;
      this.nome = nome;
      this.altura = altura;
      this.peso = peso;
      this.universidade = universidade;
      this.anoNascimento = anoNascimento;
      this.cidadeNascimento = cidadeNascimento;
      this.estadoNascimento = estadoNascimento;
    }

    public Jogador() {
      this.id = -1;
      this.nome = "";
      this.altura = -1;
      this.peso = -1;
      this.universidade = "";
      this.anoNascimento = -1;
      this.cidadeNascimento = "";
      this.estadoNascimento = "";
    }

    public Jogador(int id) {
      this();
      this.ler(id);
    }

    public String getNome() {
      return this.nome;
    }

    public void setNome(String nome) {
      this.nome = nome;
    }

    public int getId() {
      return this.id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getAltura() {
      return this.altura;
    }

    public void setAltura(int altura) {
      this.altura = altura;
    }

    public int getPeso() {
      return this.peso;
    }

    public void setPeso(int peso) {
      this.peso = peso;
    }

    public String getUniversidade() {
      return this.universidade;
    }

    public void setUniversidade(String universidade) {
      this.universidade = universidade;
    }

    public int getAnoNascimento() {
      return this.anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
      this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento() {
      return this.cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
      this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
      return this.estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
      this.estadoNascimento = estadoNascimento;
    }

    public Jogador clone() {
      Jogador clone = new Jogador(
        this.id,
        this.nome,
        this.altura,
        this.peso,
        this.universidade,
        this.anoNascimento,
        this.cidadeNascimento,
        this.estadoNascimento
      );
      return clone;
    }

    public void getDados(String arq) {
      try {
        RandomAccessFile sc = new RandomAccessFile(arq, "r");
        String[] props = new String[10000];
        int i = 0;
        while (sc.getFilePointer() < sc.length()) {
          props[i] = sc.readLine();
          i++;
        }
        dados = props;
        sc.close();
      } catch (IOException e) {
        System.out.println("FATAL ERROR" + e.toString());
      }
    }

    public void ler(int id) {
      id++;
      if (dados == null) {
        getDados("/tmp/players.csv");
      }
      String[] props = new String[8];

      String str = dados[id];
      int j = 0;
      props[0] = "";
      for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == ',') {
          if (props[j] == "") {
            props[j] = "nao informado";
          }
          j++;
          props[j] = "";
        } else {
          props[j] += str.charAt(i);
        }
      }

      if (props[7] == "") {
        props[7] = "nao informado";
      }

      this.id = Integer.parseInt(props[0]);
      this.nome = props[1];
      this.altura = Integer.parseInt(props[2]);
      this.peso = Integer.parseInt(props[3]);
      this.universidade = props[4];
      this.anoNascimento = Integer.parseInt(props[5]);
      this.cidadeNascimento = props[6];
      this.estadoNascimento = props[7];
    }
  }

  public class No {

    char ch;
    No[] prox;

    No() {
      ch = '#';
      prox = new No[27];
      for (int i = 0; i < 27; i++) {
        prox[i] = null;
      }
    }

    No(char c) {
      ch = c;
      prox = new No[27];
      for (int i = 0; i < 27; i++) {
        prox[i] = null;
      }
    }
  }

  public No raiz;
  int comparacoes = 0;

  public Arvore() {
    this.raiz = new No();
  }

  public int hash(char c) {
    c = Character.toLowerCase(c);
    return (c >= 'a' && c <= 'z') ? c - 'a' : 26;
  }

  public void insert(String nome) {
    char[] arrNome = nome.toCharArray();
    insertRec(raiz, arrNome);
  }

  public void insertRec(No no, char[] nome) {
    comparacoes++;
    if (nome.length == 0) {
      return;
    }
    char[] tempNome = new char[nome.length - 1];
    for (int j = 1; j < nome.length; j++) {
      tempNome[j - 1] = nome[j];
    }

    int indexPos = hash(nome[0]);

    comparacoes++;
    if (no.prox[indexPos] == null) {
      no.prox[indexPos] = new No(nome[0]);
    }

    insertRec(no.prox[indexPos], tempNome);
  }

  public boolean search(String nome) {
    char[] arrNome = nome.toCharArray();
    return searchRec(raiz, arrNome);
  }

  public boolean searchRec(No no, char[] nome) {
    if (nome.length == 0) {
      return true;
    }
    comparacoes++;
    char[] tempNome = new char[nome.length - 1];
    for (int j = 1; j < nome.length; j++) {
      tempNome[j - 1] = nome[j];
    }

    int indexPos = hash(nome[0]);

    return (no.prox[indexPos] == null)
      ? false
      : searchRec(no.prox[indexPos], tempNome);
  }

  public void merge(Arvore b) {
    raiz = merge(raiz, b.raiz);
  }

  public No merge(No no1, No no2) {
    comparacoes++;
    no1 = (no1 == null) ? new No(no2.ch) : no1;
    for (int k = 0; k < no2.prox.length; k++) {
      no1.prox[k] =
        (no2.prox[k] != null) ? merge(no1.prox[k], no2.prox[k]) : no1.prox[k];
    }

    return no1;
  }
}

public class Principal {

  public static void main(String[] args) {
    long inicio = System.nanoTime();

    // PARTE 1 DO EXERCICIO (ADICAO DE JOGADORES NA ARVORE)

    Scanner sc = new Scanner(System.in);
    Arvore arvoreAlpha = new Arvore();
    String entrada = sc.nextLine();
    while (true) {
      if (entrada.equals("FIM")) break;
      Arvore.Jogador jogador =
        arvoreAlpha.new Jogador(Integer.parseInt(entrada));
      arvoreAlpha.insert(jogador.nome);
      System.out.println(entrada + "\t" + jogador.nome);
      entrada = sc.nextLine();
    }

    // PARTE 2 DO EXERCICIO (ADICAO DE JOGADORES NA OUTRA ARVORE)

    Arvore arvoreBeta = new Arvore();
    entrada = sc.nextLine();
    while (true) {
      if (entrada.equals("FIM")) break;
      Arvore.Jogador jogador =
        arvoreBeta.new Jogador(Integer.parseInt(entrada));
      arvoreBeta.insert(jogador.nome);
      System.out.println(entrada + "\t" + jogador.nome);
      entrada = sc.nextLine();
    }

    // PARTE 3 DO EXERCICIO (MERGE DAS ARVORES E PESQUISA)

    arvoreAlpha.merge(arvoreBeta);

    entrada = sc.nextLine();
    while (true) {
      if (entrada.equals("FIM")) break;
      System.out.print(entrada);
      if (arvoreAlpha.search(entrada)) {
        System.out.println(" SIM");
      } else {
        System.out.println(" NAO");
      }
      entrada = sc.nextLine();
    }

    // PARTE 4 DO EXERCICIO (REGISTRO DE MATRICULA)
    long fim = System.nanoTime();
    long tempo = fim - inicio;

    try {
      RandomAccessFile matricula = new RandomAccessFile(
        "matricula_arvoreTrie.txt",
        "rw"
      );
      matricula.writeBytes(
        "805688" +
        "\t" +
        (double) tempo /
        10000000000.0 +
        "\t" +
        arvoreAlpha.comparacoes
      );
      matricula.close();
    } catch (IOException e) {
      System.out.println("FATAL ERROR" + e.toString());
    }

    sc.close();
  }
}
