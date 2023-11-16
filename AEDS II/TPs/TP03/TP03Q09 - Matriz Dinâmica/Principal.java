import java.util.Scanner;

class MatrizFlexivel {

  static Scanner sc = new Scanner(System.in);

  public class Celula {

    Celula esq;
    Celula dir;
    Celula cima;
    Celula baixo;
    int elemento;

    Celula() {
      esq = null;
      dir = null;
      cima = null;
      baixo = null;
      elemento = 0;
    }
  }

  public class Matriz {

    Celula primeiro;
    int linhas;
    int colunas;

    Matriz(int l, int c) {
      linhas = l;
      colunas = c;
      Celula[][] mat = new Celula[l][c];
      for (int i = 0; i < l; i++) {
        for (int j = 0; j < c; j++) {
          mat[i][j] = new Celula();
        }
      }

      for (int i = 0; i < l; i++) {
        for (int j = 0; j < c; j++) {
          if (j != c - 1) {
            mat[i][j].dir = mat[i][j + 1];
          }
          if (j != 0) {
            mat[i][j].esq = mat[i][j - 1];
          }
          if (i != 0) {
            mat[i][j].cima = mat[i - 1][j];
          }
          if (i != l - 1) {
            mat[i][j].baixo = mat[i + 1][j];
          }
        }
      }

      primeiro = mat[0][0];
    }

    public void lerLinhas() {
      Celula aux = primeiro;
      Celula aux2 = primeiro;
      for (int i = 0; i < linhas; i++) {
        aux2 = aux;
        for (int j = 0; j < colunas; j++) {
          aux2.elemento = sc.nextInt();
          aux2 = aux2.dir;
        }
        aux = aux.baixo;
      }
    }

    public Matriz sum(Matriz mat) {
      if (linhas != mat.linhas) {
        return null;
      }

      if (colunas != mat.colunas) {
        return null;
      }

      Matriz result = new Matriz(linhas, colunas);
      Celula aux11 = primeiro;
      Celula aux12 = primeiro;
      Celula aux21 = mat.primeiro;
      Celula aux22 = mat.primeiro;
      Celula aux31 = result.primeiro;
      Celula aux32 = result.primeiro;

      for (int i = 0; i < linhas; i++) {
        aux12 = aux11;
        aux22 = aux21;
        aux32 = aux31;
        for (int j = 0; j < colunas; j++) {
          aux32.elemento = aux12.elemento + aux22.elemento;
          aux12 = aux12.dir;
          aux22 = aux22.dir;
          aux32 = aux32.dir;
        }
        aux11 = aux11.baixo;
        aux21 = aux21.baixo;
        aux31 = aux31.baixo;
      }

      return result;
    }

    public Matriz mult(Matriz mat) {
      if (colunas != mat.linhas) {
        return null;
      }

      Matriz res = new Matriz(linhas, mat.colunas);

      Celula aux11 = primeiro;
      Celula aux12 = primeiro;
      Celula aux21 = mat.primeiro;
      Celula aux22 = mat.primeiro;
      Celula aux31 = res.primeiro;
      Celula aux32 = res.primeiro;

      for (int i = 0; i < linhas; i++) {
        aux11 = primeiro;
        aux32 = aux31;
        for (int j = 0; j < mat.colunas; j++) {
          int sum = 0;
          aux12 = aux11;
          aux22 = aux21;
          for (int k = 0; k < linhas; k++) {
            sum += aux12.elemento * aux22.elemento;
            aux12 = aux12.baixo;
            aux22 = aux22.dir;
          }

          aux32.elemento = sum;
          aux11 = aux11.dir;
          aux32 = aux32.dir;
        }
        aux21 = aux21.baixo;
        aux31 = aux31.baixo;
      }

      return res;
    }

    void diagonalPrincipal() {
      Celula aux = primeiro;
      for (int i = 0; i < linhas; i++) {
        System.out.print(aux.elemento + " ");
        if (i != linhas - 1) {
          aux = aux.baixo;
          aux = aux.dir;
        }
      }
      System.out.println();
    }

    void diagonalSecundaria() {
      Celula aux = primeiro;
      for (int i = 0; i < linhas - 1; i++) {
        aux = aux.dir;
      }

      for (int i = 0; i < linhas; i++) {
        System.out.print(aux.elemento + " ");
        if (i != linhas - 1) {
          aux = aux.baixo;
          aux = aux.esq;
        }
      }
      System.out.println();
    }

    public void print() {
      Celula aux = primeiro;
      while (aux != null) {
        Celula aux2 = aux;
        while (aux2 != null) {
          System.out.print(aux2.elemento + " ");
          aux2 = aux2.dir;
        }
        System.out.println();
        aux = aux.baixo;
      }
    }
  }
}

class Principal {

  public static void main(String[] args) {
    MatrizFlexivel mat = new MatrizFlexivel();
    MatrizFlexivel.Matriz mat1;
    MatrizFlexivel.Matriz mat2;

    int qtd = MatrizFlexivel.sc.nextInt();
    for (int i = 0; i < qtd; i++) {
      mat1 =
        mat.new Matriz(
            MatrizFlexivel.sc.nextInt(),
            MatrizFlexivel.sc.nextInt()
          );
      mat1.lerLinhas();
      mat2 =
        mat.new Matriz(
            MatrizFlexivel.sc.nextInt(),
            MatrizFlexivel.sc.nextInt()
          );
      mat2.lerLinhas();

      mat1.diagonalPrincipal();
      mat1.diagonalSecundaria();
      mat1.sum(mat2).print();
      mat2.mult(mat1).print();
    }

    MatrizFlexivel.sc.close();
  }
}
