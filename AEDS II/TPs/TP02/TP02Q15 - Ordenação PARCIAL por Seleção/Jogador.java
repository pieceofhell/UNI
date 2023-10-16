import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

  public static Jogador encontrarPorId(List<Jogador> jogadores, int id) {
    for (Jogador jogador : jogadores) {
      if (jogador.getId() == id) {
        return jogador;
      }
    }
    return null;
  }

  public static List<Jogador> ler(String arq) throws IOException {
    List<Jogador> omegaJogadores = new ArrayList<>();

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

        omegaJogadores.add(jogador);
      }
    }

    return omegaJogadores;
  }

  public static List<Jogador> ordenacaoParcialPorQuicksort(
    List<Jogador> jogadores,
    int k
  ) {
    if (k <= 0 || k > jogadores.size()) {
      k = jogadores.size();
    }

    List<Jogador> menoresJogadores = new ArrayList<>();
    quicksortMenores(jogadores, menoresJogadores, 0, jogadores.size() - 1, k);

    return menoresJogadores;
  }

  private static void quicksortMenores(
    List<Jogador> jogadores,
    List<Jogador> menores,
    int inicio,
    int fim,
    int k
  ) {
    if (inicio < fim) {
      int pivo = particionar(jogadores, inicio, fim);
      if (pivo == k) {
        menores.addAll(jogadores.subList(inicio, pivo));
        return;
      } else if (pivo < k) {
        menores.addAll(jogadores.subList(inicio, pivo + 1));
        quicksortMenores(jogadores, menores, pivo + 1, fim, k);
      } else {
        quicksortMenores(jogadores, menores, inicio, pivo - 1, k);
      }
    }
  }

  private static int particionar(List<Jogador> jogadores, int inicio, int fim) {
    Jogador pivo = jogadores.get(inicio);
    int i = inicio + 1;
    for (int j = inicio + 1; j <= fim; j++) {
      if (
        jogadores
          .get(j)
          .getEstadoNascimento()
          .compareTo(pivo.getEstadoNascimento()) <
        0
      ) {
        trocar(jogadores, i, j);
        i++;
      }
    }
    trocar(jogadores, inicio, i - 1);
    return i - 1;
  }

  private static void trocar(List<Jogador> jogadores, int i, int j) {
    Jogador temp = jogadores.get(i);
    jogadores.set(i, jogadores.get(j));
    jogadores.set(j, temp);
  }

  public static void main(String[] args) {
    String arq = "/tmp/players.csv";
    try {
      List<Jogador> omegaJogadores = Jogador.ler(arq);

      Scanner sc = new Scanner(System.in);
      List<Jogador> jogadoresInseridos = new ArrayList<>();

      while (true) {
        String entrada = sc.nextLine();

        if (entrada.equals("FIM")) {
          break;
        }

        int id = Integer.parseInt(entrada);
        Jogador jogador = Jogador.encontrarPorId(omegaJogadores, id);
        if (jogador != null) {
          jogadoresInseridos.add(jogador);
        }
      }

      // Magia da ordenação

      List<Jogador> jogadoresOrdenados = ordenacaoParcialPorQuicksort(
        jogadoresInseridos,
        10
      );

      for (Jogador jogador : jogadoresOrdenados) {
        System.out.println(
          "[" +
          jogador.getId() +
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
          "]"
        );
      }

      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
