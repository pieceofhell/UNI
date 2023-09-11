public class Pilha {

  int[] array;
  int n;
  int total;

  public Pilha(int total) {
    this.total = total;
    array = new int[total];
    n = 0;
  }

  public void inserir(int x) {
    if (n == total) {
      System.out.println("zebra");
      return;
    }
    array[n] = x;
    n++;
  }

  public int remover() {
    if (n == 0) {
      System.out.println("zebra");
      return -1;
    }
    n--;
    return array[n];
  }

  public void print() {
    for (int i : array) {
      System.out.println(i);
    }
  }
}