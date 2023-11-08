import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
  public Jogador prox;

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
  //   for (int i = 0; i < tamanho(); i++) {
  //       if (jogador != null) {
  //         System.out.println(
  //           "[" +
  //           i +
  //           "]" +
  //           " ## " +
  //           jogador.getNome() +
  //           " ## " +
  //           jogador.getAltura() +
  //           " ## " +
  //           jogador.getPeso() +
  //           " ## " +
  //           jogador.getAnoNascimento() +
  //           " ## " +
  //           jogador.getUniversidade() +
  //           " ## " +
  //           jogador.getCidadeNascimento() +
  //           " ## " +
  //           jogador.getEstadoNascimento() +
  //           " ##"
  //         );
  //       }
  //     }
  // }
}

class Lista {

  public Jogador primeiro;
  public Jogador ultimo;

  public Lista() {
    primeiro = null;
    ultimo = null;
  }

  public Lista encontrarPorId(Lista omegaJogadores, Lista jogadoresTratados, int id) {
    for (Jogador i = omegaJogadores.primeiro; i != null; i = i.prox) {
          if (i.getId() == id) {
            jogadoresTratados.inserirFim(i);
          }
        }
    return jogadoresTratados;
  }

  public Jogador encontrarPorId(Lista jogadoresTratados, int id) {
    for (Jogador i = jogadoresTratados.primeiro; i != jogadoresTratados.ultimo.prox; i = i.prox) {
          if (i.getId() == id) {
            return i;
          }
        }
    return null;
  }

  public Lista ler(String arq) throws IOException {
    Lista allJogadores = new Lista();

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

        if (allJogadores.primeiro == null) {
          allJogadores.primeiro = jogador;
          allJogadores.ultimo = jogador;
        } else {
          allJogadores.ultimo.prox = jogador;
          allJogadores.ultimo = jogador;
        }
      }
    } catch (IOException ioe) {
      System.out.println("Erro ao ler o arquivo");
    }
    return allJogadores;
  }

  public void inserirInicio(Lista jogadores, Jogador jogador) {
    Jogador tmp = jogador.clone();
    tmp.prox = primeiro;
    primeiro = tmp;
    if (primeiro.prox == null) {
      ultimo = primeiro;
    }
  }

  // Método de inserir início somente com parâmetro do jogador passado (que é o que vai ser inserido)

  public void inserirInicio(Jogador jogador) {
    Jogador tmp = jogador.clone();
    tmp.prox = primeiro;
    primeiro = tmp;
    if (primeiro.prox == null) {
      ultimo = primeiro;
    }
  }

  public void inserirFim(Jogador jogador) {
    if (ultimo == null) {
      primeiro = jogador;
      ultimo = jogador;
    } else {
      ultimo.prox = jogador;
      ultimo = jogador;
    }
  }

  public Jogador removerInicio() {
    if (primeiro == ultimo) {
      System.out.println("Erro ao remover (vazia)!");
    }

    Jogador tmp = primeiro;
    primeiro = primeiro.prox;
    tmp.prox = null;
    System.out.println("(R) " + tmp.getNome());

    return tmp;
  }

  public Jogador removerFim() {
    if (primeiro == null) {
      return null;
    }

    if (primeiro == ultimo) {
      Jogador tmp = primeiro;
      primeiro = null;
      ultimo = null;
      return tmp;
    }

    Jogador i;
    for (i = primeiro; i.prox != ultimo; i = i.prox);

    Jogador tmp = ultimo;
    ultimo = i;
    i.prox = null;

    System.out.println("(R) " + tmp.getNome());

    return tmp;
  }

  public void inserir(Lista jogadores, int pos, Jogador jogador)
    throws Exception {
    int tamanho = tamanhoLista(jogadores);

    if (pos < 0 || pos > tamanho) {
      throw new Exception(
        "Erro ao inserir posicao (" +
        pos +
        " / tamanho = " +
        tamanho +
        ") invalida!"
      );
    }
    if (pos == 0) {
      inserirInicio(jogador);
    } else if (pos == tamanhoLista(jogadores)) {
      inserirFim(jogador);
    } else {
      Jogador i = primeiro;
      for (int j = 0; j < pos - 1; j++, i = i.prox);

      Jogador tmp = jogador;
      tmp.prox = i.prox;
      i.prox = tmp;
    }
  }

  public Jogador remover(Lista jogadores, int pos) {
    int tamanho = tamanhoLista(jogadores);

    if (primeiro == ultimo) {
      System.out.println("Erro ao remover (vazia)!");      
    } else if (pos < 0 || pos >= tamanho) {
      System.out.println(
        "Erro ao remover (posicao " +
        pos +
        " / " +
        tamanho +
        " invalida!"
      );
    }

    if (pos == 0) {
      return removerInicio();
    } else if (pos == tamanhoLista(jogadores) - 1) {
      return removerFim();
    } else {
      Jogador i = primeiro;

      for (int j = 0; j < pos - 1; j++, i = i.prox);

      Jogador tmp = i.prox;
      i.prox = tmp.prox;
      tmp.prox = null;
      return tmp;
    }
  }

  public void imprimir() {
    int j = 0;
    for (Jogador i = primeiro; i != ultimo.prox; i = i.prox) {
      System.out.println(
        "[" +
        j +
        "]" +
        " ## " +
        i.getNome() +
        " ## " +
        i.getAltura() +
        " ## " +
        i.getPeso() +
        " ## " +
        i.getAnoNascimento() +
        " ## " +
        i.getUniversidade() +
        " ## " +
        i.getCidadeNascimento() +
        " ## " +
        i.getEstadoNascimento() +
        " ##"
      );
      j++;
    }
  }

  public void imprimirPrim() {
    int j = 0;
    Jogador i = primeiro;
    System.out.println(
      "[" +
      j +
      "]" +
      " ## " +
      i.getNome() +
      " ## " +
      i.getAltura() +
      " ## " +
      i.getPeso() +
      " ## " +
      i.getAnoNascimento() +
      " ## " +
      i.getUniversidade() +
      " ## " +
      i.getCidadeNascimento() +
      " ## " +
      i.getEstadoNascimento() +
      " ##"
    );
    j++;
  }

  public void imprimirUlt() {
    int j = 0;
    Jogador i = ultimo;
    System.out.println(
      "[" +
      j +
      "]" +
      " ## " +
      i.getNome() +
      " ## " +
      i.getAltura() +
      " ## " +
      i.getPeso() +
      " ## " +
      i.getAnoNascimento() +
      " ## " +
      i.getUniversidade() +
      " ## " +
      i.getCidadeNascimento() +
      " ## " +
      i.getEstadoNascimento() +
      " ##"
    );
    j++;
  }

  public int tamanhoLista(Lista jogadores) {
    int tamanho = 0;
    for (Jogador i = jogadores.primeiro; i != ultimo; i = i.prox, tamanho++);
    return tamanho;
  }
}

class Principal {

  public static void main(String[] args) {
    String arq = "players.csv";

    try {
      Lista omegaJogadores = new Lista();
      omegaJogadores = omegaJogadores.ler(arq);

      Lista jogadoresSelecionados = new Lista();
      Scanner sc = new Scanner(System.in);

      while (true) {
        String entrada = sc.nextLine();

        if (entrada.equals("FIM") || entrada.equals("fim")) {
          break;
        }

        int id = Integer.parseInt(entrada);

        jogadoresSelecionados = jogadoresSelecionados.encontrarPorId(omegaJogadores, jogadoresSelecionados, id);

        // Jogador jogador = omegaJogadores.encontrarPorId(jogadoresSelecionados, id);

        // Pequeno método de inserção de jogadoresSelecionados que funciona garantidamente.

        // for (Jogador i = omegaJogadores.primeiro; i != null; i = i.prox) {
        //   if (i.getId() == id) {
        //     jogadoresSelecionados.inserirFim(i);
        //   }
        // }
      }      

      // PARTE 2 DO EXERCICIO (MANIPULACAO DA LISTA DE JOGADORES)

      int numeroOperacoes = Integer.parseInt(sc.nextLine());

      for (int i = 0; i < numeroOperacoes; i++) {
        String in = sc.nextLine();

        String[] entrada = in.split(" ");

        if (entrada.length == 1) {
          String comando = entrada[0];
          if (comando.equals("RI")) {
            jogadoresSelecionados.removerInicio();
          } else if (comando.equals("RF")) {
            jogadoresSelecionados.removerFim();
          }
        }

        if (entrada.length == 2) {
          String comando = entrada[0];
          int param = Integer.parseInt(entrada[1]);
          if (comando.equals("II")) {
            Jogador jogador = jogadoresSelecionados.encontrarPorId(jogadoresSelecionados, param);
            jogadoresSelecionados.inserirInicio(jogadoresSelecionados, jogador);
          } else if (comando.equals("IF")) {
            Jogador jogador = jogadoresSelecionados.encontrarPorId(jogadoresSelecionados, param);
            jogadoresSelecionados.inserirFim(jogador);
          } else if (comando.equals("R*")) {
            jogadoresSelecionados.remover(jogadoresSelecionados, param);
          }
        }

        // if (entrada.length == 3) {
        //   String comando = entrada[0];
        //   int param1 = Integer.parseInt(entrada[1]);
        //   int param2 = Integer.parseInt(entrada[2]);
        //   if (comando.equals("I*")) {
        //     Jogador jogador = Jogador.encontrarPorId(jogadoresSelecionados, param2);
        //     jogadoresSelecionados.inserir(jogadoresSelecionados, param1, jogador);
        //   }
        // }
      }

      jogadoresSelecionados.imprimir();

      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
