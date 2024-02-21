import java.util.*;

class Player {

  public int hp, items, round;

  public Player() {
    hp = 3;
    items = 0;
    round = 1;
  }
}

class Shotgun {

  // Declare shotgun as an instance variable
  Stack<Integer> shotgun = new Stack<Integer>();
  int size;

  // private boolean isPoweredUp;

  public static int randomGen() {
    return (int) (Math.random() * 7) + 2;
  }

  public static int bulletGen() {
    // Generate a random floating-point number between 0 (inclusive) and 1 (exclusive)
    double randomValue = Math.random();

    // Convert the random value to an integer (0 or 1)
    int result = (int) Math.round(randomValue);

    return result;
  }

  public static void loadShotgun(Stack<Integer> x, int size) {
    for (int i = 0; i < size; i++) {
      x.push(bulletGen());
    }
  }

  public static void countBullets(Stack<Integer> x, int size) {
    int bullets = size, lives = 0, blanks = 0;

    // Create a copy of the stack to iterate over without modifying the original
    Stack<Integer> tempStack = new Stack<>();
    tempStack.addAll(x);

    for (Integer bullet : tempStack) {
      System.out.println(bullet);
      if (bullet == 0) {
        blanks++;
      }
    }

    lives = bullets - blanks;
    System.out.println(lives + " LIVE(S)\t" + blanks + " BLANK(S)");
  }

  public static boolean isDogshit(Stack<Integer> x) {
    Stack<Integer> tempStack = new Stack<>();
    tempStack.addAll(x);

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

  public Shotgun() {
    // Initialize shotgun stack with random size
    Stack<Integer> shotgun = new Stack<Integer>();
    size = randomGen();

    // Fill the shotgun array
    loadShotgun(shotgun, size);

    while (isDogshit(shotgun)) {
      countBullets(shotgun, size);
      System.out.println("Bad seed generated. Reloading shotgun...");
      loadShotgun(shotgun, size);
    }

    countBullets(shotgun, size);
  }
}

public class Game {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int action = 0;
    // Create a new game
    Shotgun weapon = new Shotgun();
    Player play1 = new Player();
    Player play2 = new Player();

    System.out.println(
      "\tCONTROLS:\n1 - SHOOT PLAYER 2\n2 - SHOOT YOURSELF\n3 - USE ITEM\n9 - END GAME"
    );
    // action = sc.nextInt();

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
    sc.close();
  }
}
