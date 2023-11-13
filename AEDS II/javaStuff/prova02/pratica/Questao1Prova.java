import java.util.*;

class Pais {

  String pais;
  String mc;

  Pais() {
    pais = "";
    mc = "--- NOT FOUND ---";
  }

  Pais(String p, String m) {
    pais = p;
    mc = m;
  }

  public String getPais() {
    return pais;
  }

  public String getMc() {
    return mc;
  }
}

class Questao1Prova {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Pais[] paises = new Pais[24];
    paises[0] = new Pais("brasil", "Feliz Natal!");
    paises[1] = new Pais("alemanha", "Frohliche Weihnachten!");
    paises[2] = new Pais("austria", "Frohe Weihnacht!");
    paises[3] = new Pais("coreia", "Chuk Sung Tan!");
    paises[4] = new Pais("espanha", "Feliz Navidad!");
    paises[5] = new Pais("grecia", "Kala Christougena!");
    paises[6] = new Pais("estados-unidos", "Merry Christmas!");
    paises[7] = new Pais("inglaterra", "Merry Christmas!");
    paises[8] = new Pais("australia", "Merry Christmas!");
    paises[9] = new Pais("portugal", "Feliz Natal!");
    paises[10] = new Pais("suecia", "God Jul!");
    paises[11] = new Pais("turquia", "Mutlu Noeller");
    paises[12] = new Pais("argentina", "Feliz Navidad!");
    paises[13] = new Pais("chile", "Feliz Navidad!");
    paises[14] = new Pais("mexico", "Feliz Navidad!");
    paises[15] = new Pais("antardida", "Merry Christmas!");
    paises[16] = new Pais("canada", "Merry Christmas!");
    paises[17] = new Pais("irlanda", "Nollaig Shona Dhuit!");
    paises[18] = new Pais("belgica", "Zalig Kerstfeest!");
    paises[19] = new Pais("italia", "Buon Natale!");
    paises[20] = new Pais("libia", "Buon Natale!");
    paises[21] = new Pais("siria", "Milad Mubarak!");
    paises[22] = new Pais("marrocos", "Frohe Weihnacht!");
    paises[23] = new Pais("japao", "Merii Kurisumasu!");
    while (sc.hasNextLine()) {
      String paisInserido = sc.nextLine();
      boolean found = false;
      for (int i = 0; i < paises.length; i++) {
        if (paisInserido.equals(paises[i].pais)) {
          System.out.println(paises[i].mc);
          found = true;
          break;
        }
      }
      if (!found) {
        System.out.println("--- NOT FOUND ---");
      }
    }
    sc.close();
  }
}
