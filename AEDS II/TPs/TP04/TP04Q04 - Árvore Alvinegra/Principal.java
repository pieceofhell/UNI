import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class Arvore {

  public class Jogador {

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;
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
        RandomAccessFile raf = new RandomAccessFile(arq, "r");
        String[] stringList = new String[10000];
        int i = 0;
        while (raf.getFilePointer() < raf.length()) {
          stringList[i] = raf.readLine();
          i++;
        }
        dados = stringList;
        raf.close();
      } catch (IOException e) {
        System.out.println("Erro ao abrir o arquivo");
      }
    }

    public void ler(int id) {
      id += 1;
      if (dados == null) {
        getDados("players.csv");
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

  int comparacoes = 0;
  public No raiz;

  public class No {

    boolean hasColor;
    Jogador jogador;
    No esq;
    No dir;

    No() {
      this.jogador = null;
      this.esq = null;
      this.dir = null;
      this.hasColor = false;
    }

    No(Jogador jogador) {
      this.jogador = jogador;
      this.esq = null;
      this.dir = null;
      this.hasColor = false;
    }

    No(Jogador jogador, boolean hasColor) {
      this.esq = null;
      this.dir = null;
      this.jogador = jogador;
      this.hasColor = hasColor;
    }
  }

  public Arvore() {
    this.raiz = null;
  }

  public int compareTo(String strX, String strY) {
    int i = 0;
    while (i < strX.length() && i < strY.length()) {
      if (strX.charAt(i) < strY.charAt(i)) {
        return -1;
      }
      if (strX.charAt(i) > strY.charAt(i)) {
        return 1;
      }
      i++;
    }
    if (strX.length() < strY.length()) {
      return -1;
    }
    if (strX.length() > strY.length()) {
      return 1;
    }
    return 0;
  }

  public No rotacionarEsq(No i) {
    No dir = i.dir;
    No dirEsq = dir.esq;

    dir.esq = i;
    i.dir = dirEsq;

    return dir;
  }

  public No rotacionarDir(No i) {
    No esq = i.esq;
    No esqDir = esq.dir;

    esq.dir = i;
    i.esq = esqDir;

    return esq;
  }

  public No rotacionarDirEsq(No no) {
    no.dir = rotacionarDir(no.dir);
    no = rotacionarEsq(no);
    return no;
  }

  public No rotacionarEsqDir(No no) {
    no.esq = rotacionarEsq(no.esq);
    no = rotacionarDir(no);

    return no;
  }

  public void balancear(No bisavo, No paipai, No pai, No no) {
    if (pai.hasColor == true) {
      comparacoes++;
      if (compareTo(pai.jogador.nome, paipai.jogador.nome) == 1) {
        if (compareTo(no.jogador.nome, pai.jogador.nome) == 1) {
          paipai = rotacionarEsq(paipai);
        } else {
          paipai = rotacionarDirEsq(paipai);
        }
      } else {
        if (compareTo(no.jogador.nome, pai.jogador.nome) == -1) {
          paipai = rotacionarDir(paipai);
        } else {
          paipai = rotacionarEsqDir(paipai);
        }
      }
      comparacoes++;
      if (bisavo == null) {
        raiz = paipai;
      } else if (compareTo(paipai.jogador.nome, bisavo.jogador.nome) == -1) {
        comparacoes++;
        bisavo.esq = paipai;
      } else {
        comparacoes++;
        bisavo.dir = paipai;
      }

      paipai.hasColor = false;
      paipai.esq.hasColor = true;
      paipai.dir.hasColor = true;
    }
  }

  public void insert(Jogador jogador) {
    comparacoes++;
    if (raiz == null) {
      raiz = new No(jogador);
      return;
    }

    comparacoes++;
    if (raiz.esq == null && raiz.dir == null) {
      comparacoes++;
      if (compareTo(jogador.nome, raiz.jogador.nome) == -1) {
        raiz.esq = new No(jogador);
      } else {
        raiz.dir = new No(jogador);
      }

      raiz.hasColor = false;
      return;
    }

    if (raiz.esq == null) {
      comparacoes++;
      if (compareTo(jogador.nome, raiz.jogador.nome) == -1) {
        raiz.esq = new No(jogador);
      } else if (compareTo(jogador.nome, raiz.dir.jogador.nome) == -1) {
        comparacoes++;
        raiz.esq = new No(raiz.jogador);
        raiz.jogador = jogador;
      } else {
        comparacoes++;
        raiz.esq = new No(raiz.jogador);
        raiz.jogador = raiz.dir.jogador;
        raiz.dir.jogador = jogador;
      }

      raiz.esq.hasColor = false;
      raiz.dir.hasColor = false;
      raiz.hasColor = false;
      return;
    }

    comparacoes++;
    if (raiz.dir == null) {
      comparacoes++;
      if (compareTo(jogador.nome, raiz.jogador.nome) == 1) {
        raiz.dir = new No(jogador);
      } else if (compareTo(jogador.nome, raiz.esq.jogador.nome) == 1) {
        comparacoes++;
        raiz.dir = new No(raiz.jogador);
        raiz.jogador = jogador;
      } else {
        comparacoes++;
        raiz.dir = new No(raiz.jogador);
        raiz.jogador = raiz.esq.jogador;
        raiz.esq.jogador = jogador;
      }
      raiz.esq.hasColor = false;
      raiz.dir.hasColor = false;
      raiz.hasColor = false;
      return;
    }

    inserirBalanceado(null, null, null, raiz, jogador);

    raiz.hasColor = false;
  }

  public void inserirBalanceado(
    No bisavo,
    No avo,
    No pai,
    No i,
    Jogador jogador
  ) {
    comparacoes++;
    if (i == null) {
      i = new No(jogador, true);
      if (compareTo(jogador.nome, pai.jogador.nome) == -1) {
        pai.esq = i;
      } else {
        pai.dir = i;
      }

      comparacoes++;
      if (pai.hasColor == true) {
        balancear(bisavo, avo, pai, i);
      }
      return;
    }

    if (
      i.esq != null &&
      i.dir != null &&
      i.esq.hasColor == true &&
      i.dir.hasColor == true
    ) {
      i.hasColor = true;
      i.esq.hasColor = false;
      i.dir.hasColor = false;

      comparacoes++;
      if (i == raiz) {
        i.hasColor = false;
      } else if (pai.hasColor == true) {
        comparacoes++;
        balancear(bisavo, avo, pai, i);
      }
    }

    comparacoes++;
    if (compareTo(jogador.nome, i.jogador.nome) == -1) {
      inserirBalanceado(avo, pai, i, i.esq, jogador);
    } else {
      inserirBalanceado(avo, pai, i, i.dir, jogador);
    }
  }

  public void imprimirCentral() {
    caminharCentral(raiz);
  }

  public void caminharCentral(No i) {
    if (i == null) return;
    caminharCentral(i.esq);
    System.out.println(i.jogador.nome);
    caminharCentral(i.dir);
  }

  public boolean search(String nome) {
    System.out.print(nome + " raiz ");
    return search(raiz, nome);
  }

  public boolean search(No i, String nome) {
    comparacoes++;
    if (i == null) return false;
    comparacoes++;
    if (compareTo(nome, i.jogador.nome) == -1) {
      System.out.print("esq ");
      return search(i.esq, nome);
    }
    comparacoes++;
    if (compareTo(nome, i.jogador.nome) == 1) {
      System.out.print("dir ");
      return search(i.dir, nome);
    }
    return true;
  }
}

public class Principal {

  public static void main(String[] args) {
    long inicio = System.nanoTime();

    // PARTE 1 DO EXERCICIO (LEITURA E INSERCAO)
    Scanner sc = new Scanner(System.in);
    Arvore arvoreAlpha = new Arvore();
    String entrada = sc.nextLine();
    while (true) {
      if (entrada.equals("FIM")) {
        break;
      }
      arvoreAlpha.insert(arvoreAlpha.new Jogador(Integer.parseInt(entrada)));
      entrada = sc.nextLine();
    }

    // PARTE 2 DO EXERCICIO (BUSCA)

    entrada = sc.nextLine();

    while (true) {
      if (entrada.equals("FIM")) {
        break;
      }
      if (arvoreAlpha.search(entrada)) {
        System.out.println("SIM");
      } else {
        System.out.println("NAO");
      }
      entrada = sc.nextLine();
    }

    // PARTE 3 DO EXERCICIO (ENCERRAMENTO E REGISTRO DE MATRICULA)

    long fim = System.nanoTime();
    long duracao = fim - inicio;

    try {
      RandomAccessFile raf = new RandomAccessFile(
        "matricula_alvinegra.txt",
        "rw"
      );
      raf.writeBytes(
        "805688" + "\t" + (double) duracao + "\t" + arvoreAlpha.comparacoes
      );
      raf.close();
    } catch (IOException e) {
      System.out.println("FATAL ERROR" + e);
    }

    sc.close();
  }
}
