import java.util.*;

/**
 * Represents the player in the game.
 */

class Player {

  public int hp, items, round;

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
    items = 0;
    round = 1;
  }
}

/**
 * Represents the weapon (shotgun) in the game.
 */

class Shotgun {

  Stack<Integer> shotgun;
  int[] shotgunArray;
  int size;
  boolean isSawed;

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

  public static int[] controlledBulletGen(int size) {
    int[] result = new int[size];
    int lives = size / 2;
    int blanks = size - lives;

    return result;
  }

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
}

public class Game {

  public static void choice(int action) {
    System.out.println(
      "\tCONTROLS:\n1 - SHOOT PLAYER 2\n2 - SHOOT YOURSELF\n3 - USE ITEM\n9 - END GAME"
    );
    switch (action) {
      case 1:
        System.out.println("Shooting player 2...");
        // Add logic for shooting player 2
        break;
      case 2:
        System.out.println("Shooting yourself...");
        // Add logic for shooting yourself
        break;
      case 3:
        System.out.println("Using item...");
        // Add logic for using item
        break;
      case 9:
        System.out.println("Ending game...");
        // Add logic for ending the game
        break;
      default:
        System.out.println("Invalid action");
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int action = 0;

    Shotgun weapon = new Shotgun();
    // Player play1 = new Player();
    // Player play2 = new Player();

    // action = sc.nextInt();

    choice(action);

    sc.close();
  }
}
