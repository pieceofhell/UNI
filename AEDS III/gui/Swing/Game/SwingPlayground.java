import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Represents the player in the game.
 */

class Player {

  public int hp;
  public static int[] inventory;

  // public static boolean hasItem(){
  //   return true;
  // }

  public void addItem(int itemId) {
    switch (itemId) {
      case 1:
        inventory[0]++;
        inventory[1]++;
        break;
      case 2:
        inventory[0]++;
        inventory[2]++;
        break;
      case 3:
        inventory[0]++;
        inventory[3]++;
        break;
      case 4:
        inventory[0]++;
        inventory[4]++;
        break;
      default:
        break;
    }
  }

  public static boolean hasItem(int itemId) {
    if (inventory[0] < 1) {
      return false;
    }
    if (inventory[itemId] > 0) {
      return true;
    }
    return false;
  }

  @SuppressWarnings("static-access")
  public static void useItem(Player pl, Shotgun weapon, int itemId) {
    if (hasItem(itemId)) {
      pl.inventory[itemId]--;
      switch (itemId) {
        case 1:
          useCigarette(pl);
          break;
        case 2:
          useHandSaw(weapon);
          break;
        case 3:
          useBeer(weapon);
          break;
        case 4:
          useMagnifyingGlass(weapon);
        default:
          break;
      }
    }
  }

  /**
   * Uses cigarette (heals 1 HP per smoked fag)
   * @param player
   */

  public static void useCigarette(Player player) {
    player.hp++;
  }

  /**
   * Uses Hand Saw (makes the next live bullet deal 2 DMG)
   * @param weapon
   */

  public static void useHandSaw(Shotgun weapon) {
    weapon.isSawed = true;
  }

  /**
   * Uses Beer (skips the currently loaded bullet)
   * @param weapon
   */

  public static void useBeer(Shotgun weapon) {
    System.out.println("Skipped bullet was: " + weapon.shotgun.pop());
  }

  /**
   * Uses magnifying glass (allows player to see currently loaded bullet)
   * @param weapon
   */

  public static void useMagnifyingGlass(Shotgun weapon) {
    System.out.println("Currently loaded bullet is: " + weapon.shotgun.peek());
  }

  public Player() {
    hp = 3;
    inventory = new int[4];
    for (int i : inventory) {
      inventory[i] = 0;
    }
  }
}

/**
 * Represents the weapon (shotgun) in the game.
 */

class Shotgun {

  public Stack<Integer> shotgun;
  public int[] shotgunArray;
  public int size;
  public boolean isSawed;

  /**
   * Generates a random number between 2 and 8 (number of shells in the shotgun).
   * @return int
   */

  public static int randomGen() {
    return (int) (Math.random() * 7) + 2;
  }

  /**
   * Generates a random number between 3 and 10 (number of shells in the shotgun).
   * @return int
   * @see https://google.com
   */

  public static int controlledRandomGen() {
    return (int) (Math.random() * 9) + 3;
  }

  /**
   * Generates a random number between 0 and 1 (live or blank bullet).
   * @return 0 or 1 integer value
   */
  public static int bulletGen() {
    // Generate a random floating-point number between 0 (inclusive) and 1 (exclusive)
    double randomValue = Math.random();

    // Convert the random value to an integer (0 or 1)
    int result = (int) Math.round(randomValue);

    return result;
  }

  /**  Future function that will be worked on where the bullets generated MUST have a defined proportion of live and blank bullets.
   * @return 0 or 1 integer value
   */

  /* public static int[] controlledBulletGen(int size) {
     int[] result = new int[size];
     int lives = size / 2;
     int blanks = size - lives;

     return result;
    } */

  /**
   * Loads the shotgun with the bullets being generated and pushes them into the stack.
   * @param stack
   * @param size
   */

  public static void loadShotgun(Stack<Integer> stack, int size) {
    for (int i = 0; i < size; i++) {
      stack.push(bulletGen());
    }
  }

  /**
   * Loads the shotgun with the bullets being generated and pushes them into the array.
   * @param array
   */

  public static void loadShotgun(int[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = bulletGen();
    }
  }

  /**
   * Reloads the shotgun due to a bad seed being generated (all blanks/lives in the stack).
   * @param stack
   * @param size
   */

  public static void reloadShotgun(Stack<Integer> stack) {
    stack.clear();
    int size = randomGen();
    for (int i = 0; i < size; i++) {
      stack.push(bulletGen());
    }
  }

  /**
   * Reloads the shotgun due to a bad seed being generated (all blanks/lives in the array).
   * @param array
   */

  public static void reloadShotgun(int[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = bulletGen();
    }
  }

  /**
   * Counts the number of live and blank bullets in the shotgun stack.
   * @param stack
   */

  public static void countBullets(Stack<Integer> stack) {
    int bullets = stack.size(), lives = 0, blanks = 0;

    // Create a copy of the stack to iterate over without modifying the original
    for (int i = stack.size() - 1; i >= 0; i--) {
      int bullet = stack.get(i);
      System.out.println(bullet);
      if (bullet == 0) {
        blanks++;
      }
    }

    lives = bullets - blanks;
    System.out.println(lives + " LIVE(S)\t" + blanks + " BLANK(S)");
  }

  /**
   * Counts the number of live and blank bullets in the shotgun array.
   * @param array
   */

  public static void countBullets(int[] array) {
    int bullets = array.length, lives = 0, blanks = 0;

    for (int bullet : array) {
      if (bullet == 0) {
        blanks++;
      }
    }

    lives = bullets - blanks;
    System.out.println(lives + " LIVE(S)\t" + blanks + " BLANK(S)");
  }

  /**
   * Checks if the shotgun stack has all live or all blank bullets (bad seed).
   * @param stack
   * @return true (bad seed found) or false
   */

  public static boolean isDogshit(Stack<Integer> stack) {
    Stack<Integer> tempStack = new Stack<>();
    tempStack.addAll(stack);

    int referenceValue = tempStack.pop();

    while (!tempStack.isEmpty()) {
      int currentValue = tempStack.pop();

      if (currentValue != referenceValue) {
        return false;
      }
    }

    // All elements matched, the stack has identical values
    return true;
  }

  /**
   * Checks if the shotgun array has all live or all blank bullets (bad seed).
   * @param x
   * @return
   */

  public static boolean isDogshit(int[] array) {
    int referenceValue = array[0];

    for (int i = 1; i < array.length; i++) {
      if (array[i] != referenceValue) {
        return false;
      }
    }

    // All elements matched, the array has identical values
    return true;
  }

  /**
   *
   * @param shotgun
   * @param pl
   */

  public static void shoot(Shotgun shotgun, Player pl) {
    if (shotgun.isSawed = true) {
      pl.hp -= 2;
      shotgun.isSawed = false;
      return;
    }
    pl.hp--;
  }

  /**
   * Constructor that initializes the shotgun stack and array with random size and fills them with bullets.
   */

  public Shotgun() {
    isSawed = false;
    size = randomGen();
    // Initialize shotgun stack with random size
    shotgun = new Stack<Integer>();

    // shotgunArray = new int[size];

    // Fill the shotgun stack with bullets
    loadShotgun(shotgun, size);

    while (isDogshit(shotgun)) {
      countBullets(shotgun);
      System.out.println("Bad seed generated. Reloading shotgun...");
      reloadShotgun(shotgun);
    }

    countBullets(shotgun);
  }

  public static void bark() {
    System.out.println("Woof!");
  }
}

public class SwingPlayground extends JFrame {

  private JPanel mainPanel, subPanel;
  private CardLayout cardLayout;
  private JButton btn1, btn2, btn3, btn4;
  private JButton subBtn1, subBtn2, subBtn3, subBtn4, subBtnBack;

  public void bark() {
    System.out.println("Woof!");
  }

  public SwingPlayground() {
    Container cp = getContentPane();

    // Create the main panel with CardLayout
    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);

    // Panel with the original 4 buttons
    JPanel originalPanel = createOriginalPanel();
    mainPanel.add(originalPanel, "original");

    // Panel with the additional 4 buttons and a back button
    subPanel = createSubPanel();
    mainPanel.add(subPanel, "sub");

    cp.add(mainPanel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("GUI Testing");
    setSize(350, 120);
    setVisible(true);
  }

  private JPanel createOriginalPanel() {
    JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

    btn1 = new JButton("Shoot yourself");
    btn2 = new JButton("Shoot other player");
    btn3 = new JButton("Use item");
    btn4 = new JButton("End Game");

    panel.add(btn1);
    panel.add(btn2);
    panel.add(btn3);
    panel.add(btn4);

    // btn1.addActionListener(
    //   new ActionListener() {
    //     @Override
    //     public void actionPerformed(ActionEvent evt) {
    //       bark();
    //     }
    //   }
    // );
    btn1.addActionListener(e -> showMessage("Button 1 clicked"));
    btn2.addActionListener(e -> showMessage("Button 2 clicked"));
    btn3.addActionListener(e -> showSubPanel());
    btn4.addActionListener(e -> showMessage("Button 4 clicked"));

    return panel;
  }

  private JPanel createSubPanel() {
    JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

    subBtn1 = new JButton("Sub Button 1");
    subBtn2 = new JButton("Sub Button 2");
    subBtn3 = new JButton("Sub Button 3");
    subBtn4 = new JButton("Sub Button 4");
    subBtnBack = new JButton("Go Back");

    panel.add(subBtn1);
    panel.add(subBtn2);
    panel.add(subBtn3);
    panel.add(subBtn4);
    panel.add(subBtnBack);

    subBtn1.addActionListener(e -> showMessage("Sub Button 1 clicked"));
    subBtn2.addActionListener(e -> showMessage("Sub Button 2 clicked"));
    subBtn3.addActionListener(e -> showMessage("Sub Button 3 clicked"));
    subBtn4.addActionListener(e -> showMessage("Sub Button 4 clicked"));
    subBtnBack.addActionListener(e -> showOriginalPanel());

    return panel;
  }

  private void showMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  private void showSubPanel() {
    cardLayout.show(mainPanel, "sub");
  }

  private void showOriginalPanel() {
    cardLayout.show(mainPanel, "original");
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(SwingPlayground::new);
  }
}
