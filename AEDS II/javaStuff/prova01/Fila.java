public class Fila {

  int[] array;
  int n;
  int total;

  public Fila(int total) {
    this.total = total;
    array = new int[total];
    n = 0;
  }

  public void inserir(int x){
    if (n == total) {
        System.out.println("zebra");
    }
    array[n] = x; 
    n++;
  }

  public int remover(){
    if (n == 0) {
        System.out.println("zebra");
        return -1;
    }
    for (int i = 0; i < n-1; i++) {
        array[i] = array[i+1];
    }
    n--;
    return array[n];
  }
  public void print() {
    for (int i = 0; i < n; i++) {
        System.out.println(array[i]);
    }
  }
}