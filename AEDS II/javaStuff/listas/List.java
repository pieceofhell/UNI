public class List {

  int[] array;
  int n;

  public List() {
    this(10);
  }

  public List(int tamanho) {
    if (tamanho <= 0) throw new IllegalArgumentException("Tamanho invalido");
    array = new int[tamanho];
    n = 0;
  }

  void inserirInicio(int elementoNovo) {
    if (n >= array.length) throw new IllegalArgumentException("Erro");

    for (int i = n; i > 0; i--) {
      array[i] = array[i - 1];
    }
    array[0] = elementoNovo;
    n++;
  }

  void inserirFim(int elementoNovo) {
    if (n >= array.length) throw new IllegalArgumentException("Erro");
    array[n] = elementoNovo;
    n++;
  }

  void inserir(int elementoNovo, int posicao) {
    if (
      n >= array.length || posicao < 0 || posicao > n
    ) throw new IllegalArgumentException("Erro");

    for (int i = n; i > posicao; i--) {
      array[i] = array[i - 1];
    }
    array[posicao] = elementoNovo;
    n++;
  }

  int removerInicio() {
    if (n == 0) throw new IllegalArgumentException("Erro");

    int resp = array[0];
    n--;

    for (int i = 0; i < n; i++) {
      array[i] = array[i + 1];
    }

    return resp;
  }

  int removerFim() {
    if (n == 0) throw new IllegalArgumentException("Erro");

    return array[--n];
  }

  int remover(int posicao) {
    if (
      n == 0 || posicao < 0 || posicao >= n
    ) throw new IllegalArgumentException("Erro");

    int resp = array[posicao];
    n--;

    for (int i = posicao; i < n; i++) {
      array[i] = array[i + 1];
    }

    return resp;
  }

  void mostrar() {
    System.out.print("[ ");
    for (int i = 0; i < n; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println("]");
  }

  void mostrarPos(int posicao) {
    System.out.print("[ " + array[posicao] + " ");
  }

  public static void main(String[] args) {}
}
