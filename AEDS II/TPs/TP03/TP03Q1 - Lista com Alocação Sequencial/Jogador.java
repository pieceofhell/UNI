import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Jogador {

  private int id;
  private String nome;
  private int altura;
  private int peso;
  private String universidade;
  private String anoNascimento;
  private String cidadeNascimento;
  private String estadoNascimento;
  public static int comparacoes;
  public static int trocas;

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

  public static Jogador encontrarPorId(Jogador[] jogadores, int id) {
    for (Jogador jogador : jogadores) {
      if (jogador != null && jogador.getId() == id) {
        return jogador;
      }
    }
    return null;
  }

  public static Jogador[] ler(String arq) throws IOException {
    Jogador[] allJogadores = new Jogador[4000];
    int currentIndex = 0;

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

        allJogadores[currentIndex] = jogador;
        currentIndex++;
      }
    }
    return allJogadores;
  }

  private static Jogador[] jogadoresSelecionados = new Jogador[3923];

  public static void inserirInicio(Jogador jogador) {
    if (jogador != null) {
      for (int i = jogadoresSelecionados.length - 1; i > 0; i--) {
        jogadoresSelecionados[i] = jogadoresSelecionados[i - 1];
      }
      jogadoresSelecionados[0] = jogador;
    }
  }

  public static void inserirFim(Jogador jogador) {
    if (jogador != null) {
      for (int i = 0; i < jogadoresSelecionados.length; i++) {
        if (jogadoresSelecionados[i] == null) {
          jogadoresSelecionados[i] = jogador;
          break;
        }
      }
    }
  }

  public static void inserir(int pos, Jogador jogador) {
    if (jogador != null && pos >= 0 && pos < jogadoresSelecionados.length) {
      for (int i = jogadoresSelecionados.length - 1; i > pos; i--) {
        jogadoresSelecionados[i] = jogadoresSelecionados[i - 1];
      }
      jogadoresSelecionados[pos] = jogador;
    }
  }

  public static Jogador removerInicio() {
    if (jogadoresSelecionados[0] != null) {
      Jogador removido = jogadoresSelecionados[0];
      for (int i = 0; i < jogadoresSelecionados.length - 1; i++) {
        jogadoresSelecionados[i] = jogadoresSelecionados[i + 1];
      }
      jogadoresSelecionados[jogadoresSelecionados.length - 1] = null;
      System.out.println("(R) " + removido.getNome());
      return removido;
    }
    return null;
  }

  public static Jogador removerFim() {
    for (int i = jogadoresSelecionados.length - 1; i >= 0; i--) {
      if (jogadoresSelecionados[i] != null) {
        Jogador removido = jogadoresSelecionados[i];
        jogadoresSelecionados[i] = null;
        System.out.println("(R) " + removido.getNome());
        return removido;
      }
    }
    return null;
  }

  public static Jogador remover(int pos) {
    if (
      pos >= 0 &&
      pos < jogadoresSelecionados.length &&
      jogadoresSelecionados[pos] != null
    ) {
      Jogador removido = jogadoresSelecionados[pos];
      for (int i = pos; i < jogadoresSelecionados.length - 1; i++) {
        jogadoresSelecionados[i] = jogadoresSelecionados[i + 1];
      }
      jogadoresSelecionados[jogadoresSelecionados.length - 1] = null;
      System.out.println("(R) " + removido.getNome());
      return removido;
    }
    return null;
  }

  public static void main(String[] args) {
    String arq = "/tmp/players.csv";

    try {
      Jogador[] omegaJogadores = Jogador.ler(arq);
      Scanner sc = new Scanner(System.in);

      while (true) {
        String entrada = sc.nextLine();

        if (entrada.equals("FIM") || entrada.equals("fim")) {
          break;
        }

        int id = Integer.parseInt(entrada);
        Jogador jogador = Jogador.encontrarPorId(omegaJogadores, id);
        if (jogador != null) {
          inserirFim(jogador);
        }
      }

      // PARTE 2 DO EXERCICIO (MANIPULACAO DA LISTA DE JOGADORES)

      int numeroOperacoes = Integer.parseInt(sc.nextLine());

      for (int i = 0; i < numeroOperacoes; i++) {
        String in = sc.nextLine();

        if (in.equals("FIM") || in.equals("fim") || in == null) {
          break;
        }

        String[] entrada = in.split(" ");

        if (entrada.length == 1) {
          String comando = entrada[0];
          if (comando.equals("RI")) {
            removerInicio();
          } else if (comando.equals("RF")) {
            removerFim();
          }
        }

        if (entrada.length == 2) {
          String comando = entrada[0];
          int param = Integer.parseInt(entrada[1]);
          if (comando.equals("II")) {
            Jogador jogador = Jogador.encontrarPorId(omegaJogadores, param);
            inserirInicio(jogador);
          } else if (comando.equals("IF")) {
            Jogador jogador = Jogador.encontrarPorId(omegaJogadores, param);
            inserirFim(jogador);
          } else if (comando.equals("R*")) {
            remover(param);
          }
        }

        if (entrada.length == 3) {
          String comando = entrada[0];
          int param1 = Integer.parseInt(entrada[1]);
          int param2 = Integer.parseInt(entrada[2]);
          if (comando.equals("I*")) {
            Jogador jogador = Jogador.encontrarPorId(omegaJogadores, param2);
            inserir(param1, jogador);
          }
        }
      }

      for (int i = 0; i < jogadoresSelecionados.length; i++) {
        Jogador jogador = jogadoresSelecionados[i];
        if (jogador != null) {
          System.out.println(
            "[" +
            i +
            "]" +
            " ## " +
            jogador.getNome() +
            " ## " +
            jogador.getAltura() +
            " ## " +
            jogador.getPeso() +
            " ## " +
            jogador.getAnoNascimento() +
            " ## " +
            jogador.getUniversidade() +
            " ## " +
            jogador.getCidadeNascimento() +
            " ## " +
            jogador.getEstadoNascimento() +
            " ##"
          );
        }
      }

      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
