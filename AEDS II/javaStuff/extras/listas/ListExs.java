public class ListExs {

  int[] array;
  int n;

  public ListExs() {
    this(10);
  }

  public ListExs(int tamanho) {
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

  void soma() {
    if (n == 0) throw new IllegalArgumentException("Erro");
    int total = 0;
    for (int i = 0; i < n; i++) {
      total += array[i];
    }
    System.out.println(total);
  }

  void maiorElemento() {
    if (n == 0) throw new IllegalArgumentException("Erro");
    int maior = array[0];
    for (int i = 0; i < n; i++) {
      if (maior < array[i]) {
        maior = array[i];
      }
    }
    System.out.println(maior);
  }

  void inverteOrdem(){
    if (n == 0) throw new IllegalArgumentException("Erro");
    int temp;
    for (int i = 0; i < n/2; i++) {
      temp = array[i];
      array[i] = array[n - i - 1];
      array[n - i - 1] = temp;
    }
    mostrar();
  }

  void paresMultCinco(){
    if (n == 0) throw new IllegalArgumentException("Erro");

    for (int i = 0; i < n; i++) {
      if (array[i] % 2 == 0 || array[i] % 5 == 0) {
        System.out.print("[ " + array[i] + " ]");
      }
    }
  }

  void mostrar() {
    System.out.print("[ ");
    for (int i = 0; i < n; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println("]");
  }

  public static void main(String[] args) {
    ListExs teste = new ListExs(5);
    teste.inserirInicio(2);
    teste.inserirInicio(4);
    teste.inserirInicio(6);
    teste.inserirInicio(10);
    teste.inserirInicio(12);
    teste.mostrar();
    teste.soma();
    teste.maiorElemento();
    teste.inverteOrdem();
    teste.paresMultCinco();
  }
}