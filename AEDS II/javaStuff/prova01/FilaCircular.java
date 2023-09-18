public class FilaCircular {

  int[] array;
  int inicio, fim;
  int total;

  public FilaCircular(int total) {
    this.total = total;
    inicio = 0;
    fim = 0;
    array = new int[total];
  }

  public void inserir(int x) {
    if (fim == (inicio - 1) % total) {
      System.out.println("fila cheia");
      return;
    }
    array[fim] = x;
    fim = (fim + 1) % total;
  }

  public void remover() {
    if (inicio == fim) {
      System.out.println("zebra");
      return;
    }
    inicio = (inicio + 1) % total;
  }

  public void print() {
    for (int i = inicio; i != fim; i = (i + 1) % total) {
      System.out.println(array[i]);
    }
  }
}
