interface IA {
  public void oi1();
}

class C implements IA {

  public void oi1() {
    System.out.println("operacao o1 efetuada");
  }
}

/**
 * OutraClasse
 */
class X extends C {
    public void oi1() {
        System.out.println("operacao o1 de X efetuada");
    }

    public X(){
        System.err.println("X inicializado");
    }
}

/**
 * OutraMain
 */
public class OutraMain {

  public static void main(String[] args) {
    X novaclasse = new X();
    novaclasse.oi1();
    // C outraNovaClasse = new C();
    // outraNovaClasse.oi1();
  }
}
