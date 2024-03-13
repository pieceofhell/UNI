import java.util.*;

/**
 * Represents the player in the game.
 */

class Player {

  public int hp, items, round;

  /**
   * Uses cigarette (heals 1 HP per smoked fag)
   */

  public void useCigarette() {
    if (hp <= 5) {
      hp++;
    }
  }

  public void displayLives() {
    System.out.println("HP: " + hp);
  }

  public Player() {
    hp = 6;
    items = 0;
    round = 1;
  }
}

/**
 * Represents the weapon (shotgun) in the game.
 */

class Shotgun {

  private Stack<Integer> shotgun;
  private int[] shotgunArray;
  private int size;

  @SuppressWarnings("unused")
  private boolean isSawed;

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

  public int controlledRandomGen() {
    return (int) (Math.random() * 9) + 3;
  }

  /**
   * Generates a random number between 0 and 1 (live or blank bullet).
   * @return 0 or 1 integer value
   */
  public int bulletGen() {
    // Generate a random floating-point number between 0 (inclusive) and 1 (exclusive)
    double randomValue = Math.random();

    // Convert the random value to an integer (0 or 1)
    int result = (int) Math.round(randomValue);

    return result;
  }

  /** Checks if the bullets generated are badly seeded. If they are, the stack is restructured to have a more defined proportion between the number of live and blank bullets.
   * @return 0 or 1 integer value
   */

  public void controlledBulletGen() {
    int lives = countLives(), diff = 0;
    int blanks = size - lives;
    if (lives > blanks) diff = lives - blanks; else if (lives < blanks) diff =
      blanks - lives;
    // System.out.println("Difference is: " + diff);

    if (diff > 2) {
      // System.out.println("Bad Seed Detected! Reloading...");
      // Calculate the number of 0s and 1s needed for a better seed
      int targetZeros = (shotgun.size() + 2) / 2;
      int targetOnes = shotgun.size() - targetZeros;

      // Re-seed the shotgun with the required number of 0s and 1s
      shotgun.clear();

      for (int i = 0; i < targetZeros; i++) {
        shotgun.push(0);
      }

      for (int i = 0; i < targetOnes; i++) {
        shotgun.push(1);
      }

      Collections.shuffle(shotgun);
    }
  }

  /**
   * Loads the shotgun with the bullets being generated and pushes them into the stack.
   * @param size
   */

  public void loadShotgun(int size) {
    for (int i = 0; i < size; i++) {
      shotgun.push(bulletGen());
    }
  }

  /**
   * Loads the shotgun with the bullets being generated and pushes them into the array.
   */

  public void loadShotgun() {
    for (int i = 0; i < shotgunArray.length; i++) {
      shotgunArray[i] = bulletGen();
    }
  }

  /**
   * Reloads the shotgun due to a bad seed being generated (all blanks/lives in the stack).
   */

  public void reloadShotgunSt() {
    shotgun.clear();
    int size = randomGen();
    for (int i = 0; i < size; i++) {
      shotgun.push(bulletGen());
    }
  }

  /**
   * Reloads the shotgun due to a bad seed being generated (all blanks/lives in the array).
   */

  public void reloadShotgunAr() {
    for (int i = 0; i < shotgunArray.length; i++) {
      shotgunArray[i] = bulletGen();
    }
  }

  public int countBlanks() {
    int blanks = 0;
    for (int i = shotgun.size() - 1; i >= 0; i--) {
      int bullet = shotgun.get(i);
      if (bullet == 0) {
        blanks++;
      }
    }
    return blanks;
  }

  public int countLives() {
    int lives = 0;
    lives = shotgun.size() - countBlanks();
    return lives;
  }

  /**
   * Counts the number of live and blank bullets in the shotgun stack and prints it.
   */

  public void displayBulletsSt() {
    int bullets = shotgun.size(), lives = 0, blanks = 0;

    // Create a copy of the stack to iterate over without modifying the original
    blanks = countBlanks();

    lives = bullets - blanks;
    System.out.println(lives + " LIVE(S)\t" + blanks + " BLANK(S)");
  }

  /**
   * Counts the number of live and blank bullets in the shotgun array and prints it.
   */

  public void displayBulletsAr() {
    int bullets = shotgunArray.length, lives = 0;
    long blanks = 0;

    blanks = Arrays.stream(shotgunArray).filter(value -> value == 0).count();

    lives = bullets - (int) blanks;
    System.out.println(lives + " LIVE(S)\t" + blanks + " BLANK(S)");
  }

  /**
   * Checks if the shotgun stack has all live or all blank bullets (bad seed).
   * @return true (bad seed found) or false
   */

  public boolean isDogshitSt() {
    Stack<Integer> tempStack = new Stack<>();
    tempStack.addAll(shotgun);

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
   * @return true or false
   */

  public boolean isDogshitAr() {
    int referenceValue = shotgunArray[0];

    for (int i = 1; i < shotgunArray.length; i++) {
      if (shotgunArray[i] != referenceValue) {
        return false;
      }
    }

    // All elements matched, the array has identical values
    return true;
  }

  /** Shoots given player.
   * @param pl
   */

  public void shoot(Player pl) {
    // System.out.println("Shooting specified player...");
    if (isSawed = true) {
      // System.out.println("Shotgun is powered up.");
      pl.hp -= 2;
      isSawed = false;
      return;
    }
    pl.hp--;
  }

  /**
   * Uses Hand Saw (makes the next live bullet deal 2 DMG)
   */

  public void useHandSaw() {
    isSawed = true;
    // System.out.println("Shotgun sawed.");
  }

  /**
   * Uses Beer (skips the currently loaded bullet)
   */

  public void useBeer() {
    // System.out.println("Skipped bullet was: " + shotgun.pop());
  }

  /**
   * Uses magnifying glass (allows players to see currently loaded bullet)
   */

  public void useMagnifyingGlass() {
    // System.out.println("Currently loaded bullet is: " + shotgun.peek());
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
    loadShotgun(size);

    while (isDogshitSt()) {
      // System.out.println("Displaying first shotgun gen:");
      displayBulletsSt();
      // System.out.println("Bad seed generated. Reloading shotgun...");
      reloadShotgunSt();
    }

    controlledBulletGen();

    displayBulletsSt();
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

    // choice(action);

    sc.close();
  }
}
