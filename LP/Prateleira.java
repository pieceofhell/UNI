// Crie uma Prateleira genérica que seja capaz de armazenar qualquer tipo de item. A ordem de inserção/remoção dos itens funciona da mesma maneira que uma fila; A Prateleira deve guardar o nome do item e sua descrição. A Prateleira deve ter um método para adicionar um item, um método para remover um item e um método para buscar um item (com base na sua descrição). Por fim, ela deve ter um método toString para imprimir todos os seus itens em ordem.

import java.util.List;

public class Prateleira<T> {
  private List<T> items;

  public Prateleira(List<T> items) {
    this.items = items;
  }

  public void addItem(T item) {
    items.add(item);
  }

  public void removeItem(T item) {
    items.remove(item);
  }

  public T searchItem(String description) {
    for (T item : items) {
      if (item.getDescription().equals(description)) {
        return item;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (T item : items) {
      sb.append(item.toString());
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Prateleira<Item> shelf = new Prateleira<>();
    Item item1 = new Item("Item 1", "Description 1");
    Item item2 = new Item("Item 2", "Description 2");
    Item item3 = new Item("Item 3", "Description 3");

    shelf.addItem(item1);
    shelf.addItem(item2);
    shelf.addItem(item3);

    System.out.println(shelf.toString());
  }
}

class Item {
  private String name;
  private String description;

  public Item(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "Name: " + name + ", Description: " + description + "\n";
  }
}
