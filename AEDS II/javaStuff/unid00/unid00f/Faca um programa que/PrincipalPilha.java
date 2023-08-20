import java.util.*;

public class PrincipalPilha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pilha pilha = new Pilha();
        pilha.loadFromFile("pilha.dat");

        int choice = 0;

        while (choice != 4) {
            System.out.println("Menu:");
            System.out.println("(1) Inserir");
            System.out.println("(2) Remover");
            System.out.println("(3) Listar");
            System.out.println("(4) Sair");
            System.out.print("Escolha uma opcao: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Digite o elemento a ser inserido: ");
                    String element = scanner.nextLine();
                    pilha.push(element);
                    break;

                case 2:
                    String removedElement = pilha.pop();
                    if (removedElement != null) {
                        System.out.println("Elemento removido: " + removedElement);
                    } else {
                        System.out.println("A pilha esta vazia.");
                    }
                    break;

                case 3:
                    List<String> elements = pilha.getList();
                    if (!elements.isEmpty()) {
                        System.out.println("Elementos na pilha:");
                        for (String elem : elements) {
                            System.out.println(elem);
                        }
                    } else {
                        System.out.println("A pilha esta vazia.");
                    }
                    break;

                case 4:
                    System.out.println("Salvando elementos no arquivo...");
                    pilha.saveToFile("pilha.dat");
                    System.out.println("Saindo do programa.");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }
}
