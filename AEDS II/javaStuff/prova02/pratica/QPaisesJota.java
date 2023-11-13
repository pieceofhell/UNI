import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Pais {

  String nome;
  int medalhasOuro;
  int medalhasPrata;
  int medalhasBronze;

  Pais() {
    medalhasOuro = 0;
    medalhasPrata = 0;
    medalhasBronze = 0;
    nome = "";
  }
}

public class QPaisesJota {

  public static ArrayList<Pais> sort(Pais[] paises) {
    ArrayList<Pais> paisesOrdenados = new ArrayList<Pais>();

    Collections.addAll(paisesOrdenados, paises);

    Collections.sort(
      paisesOrdenados,
      new Comparator<Pais>() {
        @Override
        public int compare(Pais p1, Pais p2) {
          int compareGold = Integer.compare(p2.medalhasOuro, p1.medalhasOuro);
          if (compareGold != 0) {
            return compareGold;
          }

          int compareSilver = Integer.compare(
            p2.medalhasPrata,
            p1.medalhasPrata
          );
          if (compareSilver != 0) {
            return compareSilver;
          }

          return Integer.compare(p2.medalhasBronze, p1.medalhasBronze);
        }
      }
    );

    return paisesOrdenados;
  }

  public static void main(String[] args) {
    String modalidade = "";
    Pais[] paises = new Pais[300];
    Scanner sc = new Scanner(System.in);
    for (int i = 0; i < paises.length; i++) {
      paises[i] = new Pais();
    }
    while (sc.hasNextLine()) {
      modalidade = sc.nextLine();

      if (modalidade.equals("FIM")) {
        break;
      }

      for (int i = 0; i < 3; i++) {
        paises[i].nome = sc.nextLine();
        if (i == 0) {
          paises[i].medalhasOuro++;
        } else if (i == 1) {
          paises[i].medalhasPrata++;
        } else if (i == 2) {
          paises[i].medalhasBronze++;
        }
      }
    }

    ArrayList<Pais> paisesOrdenados = new ArrayList<Pais>();
    paisesOrdenados = sort(paises);

    System.out.println("Quadro de medalhas:");
    for (Pais pais : paisesOrdenados) {
      System.out.println(
        pais.nome +
        " " +
        pais.medalhasOuro +
        " " +
        pais.medalhasPrata +
        " " +
        pais.medalhasBronze
      );
    }

    sc.close();
  }
}
