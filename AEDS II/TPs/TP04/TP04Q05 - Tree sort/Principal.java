import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Jogador {

  public int id;
  public String nome;
  public int altura;
  public int peso;
  public String universidade;
  public String anoNascimento;
  public String cidadeNascimento;
  public String estadoNascimento;

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

  public int comparar(Jogador other) {
    return this.nome.compareTo(other.nome);
  }

  public Jogador clone() {
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
}

class No {

  public Jogador jogador;
  public No esq;
  public No dir;

  public No(Jogador jogador) {
    this.jogador = jogador;
    this.esq = null;
    this.dir = null;
  }
}

class ArvoreBinaria {

  int comparacoes;
  public No raiz;

  public ArvoreBinaria() {
    this.raiz = null;
  }

  public ArvoreBinaria ler(String arq) throws IOException {
    ArvoreBinaria allJogadores = new ArvoreBinaria();

    try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
      String linha;
      boolean primeiraLinha = true;

      while ((linha = br.readLine()) != null) {
        if (primeiraLinha) {
          primeiraLinha = false;
          continue;
        }

        String[] dados = linha.split(",");
        Jogador jogador = new Jogador();
        jogador.setId(Integer.parseInt(dados[0]));
        jogador.setNome(dados[1]);
        jogador.setAltura(Integer.parseInt(dados[2]));
        jogador.setPeso(Integer.parseInt(dados[3]));
        jogador.setUniversidade(
          dados.length > 4 && !dados[4].isEmpty() ? dados[4] : "nao informado"
        );
        jogador.setAnoNascimento(
          dados.length > 5 && !dados[5].isEmpty() ? dados[5] : "nao informado"
        );
        jogador.setCidadeNascimento(
          dados.length > 6 && !dados[6].isEmpty() ? dados[6] : "nao informado"
        );
        jogador.setEstadoNascimento(
          dados.length > 7 && !dados[7].isEmpty() ? dados[7] : "nao informado"
        );

        allJogadores.inserir(jogador);
      }
    } catch (IOException ioe) {
      System.out.println("Erro ao ler o arquivo");
    }
    return allJogadores;
  }

  public int compareTo(String str1, String str2) {
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

  public List<Jogador> treeSort() {
    List<Jogador> sortedList = new ArrayList<>();
    treeSort(raiz, sortedList);
    return sortedList;
  }

  private void treeSort(No no, List<Jogador> sortedList) {
    if (no != null) {
      treeSort(no.esq, sortedList);
      sortedList.add(no.jogador);
      treeSort(no.dir, sortedList);
    }
  }

  public void inserir(Jogador jogador) {
    raiz = inserir(raiz, jogador);
  }

  public No inserir(No i, Jogador jogador) {
    if (i == null) {
      comparacoes++;
      i = new No(jogador);
    } else if (compareTo(jogador.nome, i.jogador.nome) == -1) {
      comparacoes++;
      i.esq = inserir(i.esq, jogador);
    } else {
      comparacoes++;
      i.dir = inserir(i.dir, jogador);
    }
    return i;
  }

  public Jogador pesquisar(String chave, boolean porNome) {
    return pesquisarRec(raiz, chave, porNome);
  }

  private Jogador pesquisarRec(No no, String chave, boolean porNome) {
    if (
      no == null ||
      (porNome && no.jogador.getNome().equals(chave)) ||
      (!porNome && Integer.toString(no.jogador.getId()).equals(chave))
    ) {
      return (no != null) ? no.jogador : null;
    }

    if (
      (porNome && chave.compareTo(no.jogador.getNome()) < 0) ||
      (!porNome && Integer.parseInt(chave) < no.jogador.getId())
    ) {
      return pesquisarRec(no.esq, chave, porNome);
    } else {
      return pesquisarRec(no.dir, chave, porNome);
    }
  }

  public boolean pesquisar(String nome) {
    // System.out.print(nome + " raiz ");
    return pesquisar(raiz, nome);
  }

  public boolean pesquisar(No i, String nome) {
    comparacoes++;
    if (i == null) return false;
    comparacoes++;
    if (compareTo(nome, i.jogador.nome) == -1) {
      // System.out.print("esq ");
      return pesquisar(i.esq, nome);
    }
    comparacoes++;
    if (compareTo(nome, i.jogador.nome) == 1) {
      // System.out.print("dir ");
      return pesquisar(i.dir, nome);
    }
    return true;
  }

  public Jogador pesquisarPorId(int id) {
    return pesquisarPorIdRec(raiz, id);
  }

  private Jogador pesquisarPorIdRec(No no, int id) {
    if (no == null || no.jogador.getId() == id) {
      return (no != null) ? no.jogador : null;
    }

    if (id < no.jogador.getId()) {
      return pesquisarPorIdRec(no.esq, id);
    } else {
      return pesquisarPorIdRec(no.dir, id);
    }
  }

  public Jogador bruteSearch(int id) {
    return bruteSearchRec(raiz, id);
  }

  private Jogador bruteSearchRec(No no, int id) {
    if (no == null) {
      return null;
    }

    Jogador jogadorEncontrado = null;

    if (no.jogador.getId() == id) {
      jogadorEncontrado = no.jogador;
    }

    Jogador jogadorEsquerda = bruteSearchRec(no.esq, id);
    if (jogadorEsquerda != null) {
      jogadorEncontrado = jogadorEsquerda;
    }

    Jogador jogadorDireita = bruteSearchRec(no.dir, id);
    if (jogadorDireita != null) {
      jogadorEncontrado = jogadorDireita;
    }

    return jogadorEncontrado;
  }

  public void caminharCentral() {
    caminharCentral(raiz);
  }

  private void caminharCentral(No no) {
    if (no != null) {
      caminharCentral(no.esq);
      System.out.println(no.jogador.nome);
      caminharCentral(no.dir);
    }
  }
}

public class Principal {

  public static void main(String[] args) {
    long inicio = System.nanoTime();
    Scanner sc = new Scanner(System.in);
    ArvoreBinaria arvoreAlpha = new ArvoreBinaria();
    try {
      arvoreAlpha = arvoreAlpha.ler("/tmp/players.csv");
      ArvoreBinaria arvoreBeta = new ArvoreBinaria();
      String entrada = "";

      // PARTE 1 DO EXERCICIO (ADICAO DE JOGADORES NA ARVORE)
      while (true) {
        entrada = sc.nextLine();
        if (entrada.equals("FIM")) {
          break;
        }
        int id = Integer.parseInt(entrada);
        Jogador novo = new Jogador();
        novo = arvoreAlpha.bruteSearch(id);
        arvoreBeta.inserir(novo);
      }

      List<Jogador> jogadoresOrdenados = arvoreBeta.treeSort();

      for (Jogador jogador : jogadoresOrdenados) {
        System.out.println(jogador.getNome());
      }

      // PARTE 2 DO EXERCICIO (PESQUISA DE JOGADORES NA ARVORE)
      String entrada2 = "";
      while (true) {
        entrada2 = sc.nextLine();
        if (entrada2.equals("FIM")) {
          break;
        }
        if (arvoreBeta.pesquisar(entrada2)) {
          // System.out.println("SIM");
        } else {
          // System.out.println("NAO");
        }
      }

      // PARTE 3 DO EXERCICIO (REGISTRO EM ARQUIVO DE MATRICULA, TEMPO DE EXECUCAO E COMPARACOES)
      int mat = 805688;
      double fim = System.nanoTime();
      double duracao = (fim - inicio);

      FileWriter arq = new FileWriter("matricula_treesort.txt");
      arq.write(
        mat + "\t" + duracao / 1000000000 + "s" + "\t" + arvoreBeta.comparacoes
      );

      arq.close();
      sc.close();
    } catch (Exception e) {
      System.err.println("FATAL ERROR" + e);
    }
  }
}
