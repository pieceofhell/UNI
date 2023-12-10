import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class OuterTree {

  public class Jogador {

    int id;
    String nome;
    int altura;
    int peso;
    String universidade;
    int anoNascimento;
    String cidadeNascimento;
    String estadoNascimento;
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

    public void getDados(String nomeArq) {
      try {
        RandomAccessFile raf = new RandomAccessFile(nomeArq, "r");
        String[] propriedades = new String[10000];
        int i = 0;
        while (raf.getFilePointer() < raf.length()) {
          propriedades[i] = raf.readLine();
          i++;
        }
        dados = propriedades;
        raf.close();
      } catch (IOException e) {
        System.out.println("getDados FATAL ERROR" + e.getMessage());
      }
    }

    public void ler(int id) {
      id += 1;
      if (dados == null) {
        getDados("/tmp/players.csv");
      }
      String[] propriedades = new String[8];

      String str = dados[id];
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
  }

  public class OuterNode {

    OuterNode esq;
    OuterNode dir;
    InnerTree InnerTree;
    int elem;

    OuterNode() {
      this.InnerTree = null;
      this.esq = null;
      this.dir = null;
      this.elem = -1;
    }

    OuterNode(int n) {
      this.InnerTree = new InnerTree();
      this.esq = null;
      this.dir = null;
      this.elem = n;
    }
  }

  public class InnerTree {

    public class InnerNode {

      Jogador jogador;
      InnerNode esq;
      InnerNode dir;

      public InnerNode() {
        this.jogador = null;
        this.esq = null;
        this.dir = null;
      }

      public InnerNode(Jogador jogador) {
        this.jogador = jogador;
        this.esq = null;
        this.dir = null;
      }
    }

    InnerNode raiz;

    public InnerTree() {
      this.raiz = null;
    }

    public void inserir(Jogador jogador) {
      raiz = inserir(raiz, jogador);
    }

    public InnerNode inserir(InnerNode no, Jogador jogador) {
      if (no == null) {
        comparacoes++;
        no = new InnerNode(jogador);
      } else if (compareTo(jogador.nome, no.jogador.nome) == -1) {
        no.esq = inserir(no.esq, jogador);
      } else {
        no.dir = inserir(no.dir, jogador);
      }
      return no;
    }

    public boolean search(String nome) {
      return search(raiz, nome);
    }

    public boolean search(InnerNode i, String nome) {
      if (i == null) return false;
      comparacoes++;
      if (compareTo(nome, i.jogador.nome) == 0) {
        return true;
      }
      System.out.print("ESQ ");
      if (search(i.esq, nome) == true) {
        return true;
      }
      System.out.print("DIR ");
      if (search(i.dir, nome) == true) {
        return true;
      }

      return false;
    }
  }

  private OuterNode raiz;
  int comparacoes = 0;

  public OuterTree() {
    this.raiz = null;
  }

  public static int compareTo(String str1, String str2) {
    int i = 0;
    while (i < str1.length() && i < str2.length()) {
      if (str1.charAt(i) < str2.charAt(i)) {
        return -1;
      }
      if (str1.charAt(i) > str2.charAt(i)) {
        return 1;
      }
      i++;
    }
    if (str1.length() < str2.length()) {
      return -1;
    }
    if (str1.length() > str2.length()) {
      return 1;
    }
    return 0;
  }

  public void inserir(Jogador jogador) {
    raiz = insertRec(raiz, jogador);
  }

  public OuterNode insertRec(OuterNode i, Jogador jogador) {
    if (i == null) {
      comparacoes++;
      i = new OuterNode(jogador.altura % 15);
      i.InnerTree.inserir(jogador);
    } else if (jogador.altura % 15 < i.elem) {
      comparacoes += 2;
      i.esq = insertRec(i.esq, jogador);
    } else if (jogador.altura % 15 > i.elem) {
      comparacoes += 3;
      i.dir = insertRec(i.dir, jogador);
    } else {
      i.InnerTree.inserir(jogador);
    }
    return i;
  }

  public void insert(int n) {
    raiz = insertRec(raiz, n);
  }

  public OuterNode insertRec(OuterNode i, int n) {
    if (i == null) {
      comparacoes++;
      i = new OuterNode(n);
    } else if (n < i.elem) {
      comparacoes += 2;
      i.esq = insertRec(i.esq, n);
    } else if (n > i.elem) {
      comparacoes += 3;
      i.dir = insertRec(i.dir, n);
    }
    return i;
  }

  public boolean search(String nome) {
    System.out.print(nome + " raiz ");
    return searchRec(raiz, nome);
  }

  public boolean searchRec(OuterNode i, String nome) {
    if (i == null) return false;
    if (i.InnerTree.search(nome) == true) {
      return true;
    }
    System.out.print("esq ");
    if (searchRec(i.esq, nome) == true) {
      return true;
    }
    System.out.print("dir ");
    if (searchRec(i.dir, nome) == true) {
      return true;
    }
    return false;
  }
}

public class Principal {

  public static void main(String[] args) {
    long inicio = System.nanoTime();
    try {
      Scanner sc = new Scanner(System.in);
      OuterTree arvoreAlpha = new OuterTree();
      String entrada = sc.nextLine();

      int[] arr = new int[] {
        7,
        3,
        11,
        1,
        5,
        9,
        12,
        0,
        2,
        4,
        6,
        8,
        10,
        13,
        14,
      };

      for (int i = 0; i < arr.length; i++) {
        arvoreAlpha.insert(arr[i]);
      }

      while (true) {
        if (entrada.equals("FIM")) {
          break;
        }
        arvoreAlpha.inserir(arvoreAlpha.new Jogador(Integer.parseInt(entrada)));
        entrada = sc.nextLine();
      }

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

      // PARTE 3 DO EXERCICIO (REGISTRO EM ARQUIVO DE MATRICULA, TEMPO DE EXECUCAO E COMPARACOES)
      int mat = 805688;
      double fim = System.nanoTime();
      double duracao = fim - inicio;

      FileWriter arq = new FileWriter("matricula_arvoreBinaria.txt");
      arq.write(
        mat + "\t" + duracao / 1000000000 + "s" + "\t" + arvoreAlpha.comparacoes
      );

      arq.close();
      sc.close();
    } catch (IOException e) {
      System.out.println("FATAL ERROR" + e.getMessage());
    }
  }
}