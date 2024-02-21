import java.util.Arrays;

public class Game {

  public static int randomGen() {
    return (int) (Math.random() * 10) + 2;
  }

  public static int generateRandomZeroOrOne() {
    // Generate a random floating-point number between 0 (inclusive) and 1 (exclusive)
    double randomValue = Math.random();

    // Convert the random value to an integer (0 or 1)
    int result = (int) Math.round(randomValue);

    return result;
  }

  public Game() {
    // Initialize shotgun array with random size
    int[] shotgun = new int[randomGen()];

    // Fill the shotgun array with the value 0
    Arrays.fill(shotgun, 0);
  }

  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      System.out.println(generateRandomZeroOrOne());
    }
  }
}
