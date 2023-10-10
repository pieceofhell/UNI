import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

  public static List<Jogador> countingSort(List<Jogador> jogadores) {
    int n = jogadores.size();
    int maxAltura = getMaxAltura(jogadores);

    // Criar um vetor de contagem
    int[] count = new int[maxAltura + 1];
    for (int i = 0; i <= maxAltura; i++) {
      count[i] = 0;
    }

    // Contar a frequência das alturas
    for (int i = 0; i < n; i++) {
      count[jogadores.get(i).getAltura()]++;
    }

    // Calcular as posições de início das alturas
    for (int i = 1; i <= maxAltura; i++) {
      count[i] += count[i - 1];
    }

    // Criar um vetor de saída ordenado
    Jogador[] output = new Jogador[n];
    for (int i = n - 1; i >= 0; i--) {
      output[count[jogadores.get(i).getAltura()] - 1] = jogadores.get(i);
      count[jogadores.get(i).getAltura()]--;
    }

    // Copiar o vetor ordenado de volta para o vetor original
    for (int i = 0; i < n; i++) {
      jogadores.set(i, output[i]);
    }

    // Realizar ordenação adicional por nome em caso de empate na altura
    for (int i = 1; i < n; i++) {
      if (jogadores.get(i).getAltura() == jogadores.get(i - 1).getAltura()) {
        int j = i - 1;
        while (
          j >= 0 && jogadores.get(j).getAltura() == jogadores.get(i).getAltura()
        ) {
          if (
            jogadores
              .get(j)
              .getNome()
              .compareTo(jogadores.get(j + 1).getNome()) >
            0
          ) {
            // Trocar jogadores
            Jogador temp = jogadores.get(j);
            jogadores.set(j, jogadores.get(j + 1));
            jogadores.set(j + 1, temp);
          }
          j--;
        }
      }
    }
    return jogadores;
  }

  public static int getMaxAltura(List<Jogador> jogadores) {
    int max = Integer.MIN_VALUE;
    for (Jogador jogador : jogadores) {
      if (jogador.getAltura() > max) {
        max = jogador.getAltura();
      }
    }
    return max;
  }

  public static void main(String[] args) {
    long tempoInicial = System.currentTimeMillis();

    String arq = "players.csv";
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

      List<Jogador> jogadoresOrdenados = countingSort(jogadoresInseridos);

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

      FileWriter myWriter = new FileWriter("matricula_countingsort.txt");
      long tempoFinal = System.currentTimeMillis();
      long duracao = tempoFinal - tempoInicial;
      myWriter.write("805688" + "\t" + duracao + "ms" + "\t" + comparacoes);
      myWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
