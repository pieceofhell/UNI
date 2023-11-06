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

  public Jogador clone() throws CloneNotSupportedException {
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

class Lista {

  private Jogador primeiro;
  private Jogador ultimo;

  public Lista() {
    primeiro = null;
    ultimo = null;
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

  public void inserirFim(Jogador jogador) {
    if (ultimo == null) {
      primeiro = jogador;
      ultimo = jogador;
    } else {
      ultimo.prox = jogador;
      ultimo = jogador;
    }
  }

  public void imprimir() {
    int j = 0;
    for (Jogador i = primeiro.prox; i != null; i = i.prox) {
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

  class Principal {

    public static void main(String[] args) {
      String arq = "players.csv";

      try {
        Lista omegaJogadores = new Lista();
        omegaJogadores = omegaJogadores.ler(arq);
        // BAGULHO CHEIO

        Lista jogadoresSelecionados = new Lista();
        Scanner sc = new Scanner(System.in);

        while (true) {
          String entrada = sc.nextLine();

          if (entrada.equals("FIM") || entrada.equals("fim")) {
            break;
          }

          int id = Integer.parseInt(entrada);
          
        }

        jogadoresSelecionados.imprimir();

        sc.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
