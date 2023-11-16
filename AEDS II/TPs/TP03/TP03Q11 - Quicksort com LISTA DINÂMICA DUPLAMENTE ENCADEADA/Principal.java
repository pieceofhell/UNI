import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

class QuickSort {

  public class Jogador {

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;
    static String[] omegaString;

    public Jogador(
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

    public Jogador clone() {
      Jogador res = new Jogador(
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

    // ---------------------------METODOS-DE-BUSCA---------------------------//

    public static void getOmegaString(String nomeArq) {
      try {
        RandomAccessFile raf = new RandomAccessFile(nomeArq, "r");
        String[] stringList = new String[10000];
        int i = 0;
        while (raf.getFilePointer() < raf.length()) {
          stringList[i] = raf.readLine();
          i++;
        }
        omegaString = stringList;
        raf.close();
      } catch (IOException e) {
        System.out.println("Erro ao abrir o arquivo");
      }
    }

    public void ler(int id) {
      id += 1;
      if (omegaString == null) {
        getOmegaString("/tmp/players.csv");
      }
      String[] propriedades = new String[8];

      String str = omegaString[id];
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

  // ---------------------------CELULA---------------------------//

  public class Celula {

    Jogador jogador;
    Celula ant;
    Celula prox;

    Celula() {
      this.jogador = null;
      this.ant = null;
      this.prox = null;
    }

    Celula(Jogador jogador) {
      this.jogador = jogador;
      this.ant = null;
      this.prox = null;
    }
  }

  // ---------------------------LISTA---------------------------//

  public class Lista {

    private Celula inicio;
    private Celula ultimo;
    private int length = 0;

    public Lista() {
      this.inicio = null;
      this.ultimo = null;
      length = 0;
    }

    // ---------------------------METODOS-DE-MANIPULACAO---------------------------//

    public void inserirInicio(Jogador jogador) {
      Celula tmp = new Celula(jogador);
      if (this.inicio == null) {
        this.ultimo = tmp;
      }
      tmp.prox = this.inicio;
      this.inicio.ant = tmp;
      this.inicio = tmp;
      length++;
    }

    public void inserirFim(Jogador jogador) {
      Celula tmp = new Celula(jogador);
      if (this.inicio == null) {
        this.inicio = tmp;
      } else {
        this.ultimo.prox = tmp;
      }
      tmp.ant = this.ultimo;
      this.ultimo = tmp;
      length++;
    }

    public void inserir(Jogador jogador, int pos) {
      Celula aux = this.inicio;
      if (pos == 0) {
        inserirInicio(jogador);
        return;
      } else if (pos == length) {
        inserirFim(jogador);
        return;
      } else if (pos > length) {
        System.out.println("Erro");
        return;
      }
      for (int i = 0; i < pos; i++) {
        aux = aux.prox;
        if (aux == null) {
          System.out.println("Erro");
          return;
        }
      }

      length++;

      Celula tmp = new Celula(jogador);
      tmp.prox = aux.prox;
      aux.prox = tmp;
      tmp.prox.ant = tmp;
      tmp.ant = aux;
    }

    public Jogador removerInicio() {
      if (this.inicio == null) {
        System.out.println("Erro");
        return null;
      }

      Jogador res = this.inicio.jogador;
      this.inicio.prox.ant = null;
      this.inicio = this.inicio.prox;

      length--;

      return res;
    }

    public Jogador removerFim() {
      if (this.ultimo == null) {
        System.out.println("Erro");
        return null;
      }
      Jogador res = this.ultimo.jogador;
      this.ultimo.ant.prox = null;
      this.ultimo = this.ultimo.ant;

      length--;

      return res;
    }

    public Jogador remover(int pos) {
      if (pos == 0) {
        removerInicio();
      } else if (pos == length - 1) {
        removerFim();
      } else if (pos >= length) {
        System.out.println("Erro");
        return null;
      }

      Jogador res;
      Celula aux = this.inicio;

      for (int i = 0; i < pos; i++) {
        aux = aux.prox;
      }
      res = aux.jogador;
      aux.ant.prox = aux.prox;
      aux.prox.ant = aux.ant;

      return res;
    }

    // ---------------------------METODOS-DE-IMPRESSAO---------------------------//

    public void print() {
      Celula aux = this.inicio;
      while (aux != null) {
        aux.jogador.imprimir();
        aux = aux.prox;
      }
    }

    public void print(Celula i, Celula j) {
      System.out.println(i.jogador.id + " -> " + j.jogador.id);
      for (Celula aux = i; aux != j.prox; aux = aux.prox) {
        System.out.print(aux.jogador.id + " ");
      }
      System.out.println();
      System.out.println("All: ");
      for (Celula aux = this.inicio; aux != null; aux = aux.prox) {
        System.out.print(aux.jogador.id + " ");
      }
      System.out.println();
      System.out.println();
      System.out.println();
    }

    public void comando(String str) {
      int id = Integer.parseInt(str);
      Jogador j = new Jogador(id);
      inserirFim(j);
    }

    public void troca(Celula i, Celula j) {
      Jogador aux = i.jogador;

      i.jogador = j.jogador;
      j.jogador = aux;
    }

    public static int cmpstr(String str1, String str2) {
      int i = 0;
      while (i < str1.length() && i < str2.length()) {
        if (str1.charAt(i) > str2.charAt(i)) {
          return 1;
        } else if (str1.charAt(i) < str2.charAt(i)) {
          return -1;
        }
        i++;
      }
      if (str1.length() < str2.length()) {
        return -1;
      } else if (str1.length() > str2.length()) {
        return 1;
      } else {
        return 0;
      }
    }

    public int cmpJogadores(Jogador i, Jogador j) {
      int res = cmpstr(i.estadoNascimento, j.estadoNascimento);
      if (res != 0) return res;

      return cmpstr(i.nome, j.nome);
    }

    public void quicksort(Celula start, Celula end) {
      Celula i = start;
      Celula j = end;
      Jogador pivot = end.jogador;

      while (j != i.ant && j != i.ant.ant) {
        while (cmpJogadores(i.jogador, pivot) == -1) {
          i = i.prox;
        }
        while (cmpJogadores(pivot, j.jogador) == -1) {
          j = j.ant;
        }

        if (j != i.ant) {
          troca(i, j);
          i = i.prox;
          j = j.ant;
        }
      }

      if (start != j && j != start.ant) {
        quicksort(start, j);
      }
      if (end != i && i != end.prox) {
        quicksort(i, end);
      }
    }

    public void quicksort() {
      this.inicio.ant = new Celula();
      this.inicio.ant.prox = inicio;
      this.ultimo.prox = new Celula();
      this.ultimo.prox.ant = ultimo;
      quicksort(this.inicio, this.ultimo);
      this.inicio.ant = null;
      this.ultimo.prox = null;
    }
  }
}

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuickSort qs = new QuickSort();
        QuickSort.Lista lista = qs.new Lista();
        String in = sc.nextLine();
        while (!in.equals("FIM")) {
            lista.comando(in);
            in = sc.nextLine();
        }

        lista.quicksort();

        lista.print();

        sc.close();
    }
}
