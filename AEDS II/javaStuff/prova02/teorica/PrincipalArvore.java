import java.util.Random;

/**
 * Codigo main para Arvore Binaria de Pesquisa
 * @author henrique
 * @author código fonte: Max do Val Machado
 */
public class PrincipalArvore {

  public static Lista randomLista() {
    Lista listaRandom = new Lista();
    Random rand = new Random();
    int upperbound = 250;
    for (int i = 0; i < 15; i++) {
      int int_random = rand.nextInt(upperbound);
      listaRandom.inserirInicio(int_random);
    }
    return listaRandom;
  }

  public static void main(String[] args) throws Exception {
    ListedBinaryTree arvoreBinaria = new ListedBinaryTree();
    for (int k = 1; k < 15; k++) {
      arvoreBinaria.inserir(randomLista());

      System.out.println("No. nos: " + k);
      arvoreBinaria.caminharCentral();
    }
  }
}