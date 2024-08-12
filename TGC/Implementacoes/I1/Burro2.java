import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;

public class Burro {

  int numVertices;
  int numEdges;

  ArrayList<Integer> firstColumn;
  ArrayList<Integer> secondColumn;

  Burro() {
    this.numVertices = 0;
    this.numEdges = 0;
    this.firstColumn = new ArrayList<>();
    this.secondColumn = new ArrayList<>();
  }

  public static void main(String[] args) {
    String filePath =
      "C:/Users/henri/code/github/UNI/TGC/Implementacoes/I1/graph-test-100-1.txt";
    Burro g = new Burro();
    try {
      g.read(filePath);

      int[] tails = g.firstColumn.stream().mapToInt(i -> i).toArray();

      int[] heads = g.secondColumn.stream().mapToInt(i -> i).toArray();

      int num = 8;

      int targetIndex = findTargetIndex(num, tails);

      int numSuccessors = findNumSuccessors(targetIndex, tails);

      System.out.println(
        "Existem " + numSuccessors + " sucessores ao vertice de numero " + num
      );

      int[] successors = findSuccessors(
        targetIndex,
        numSuccessors,
        tails,
        heads
      );

      System.out.println("Seus sucessores sao:");
      System.out.println("Sucessor 1 : " + heads[targetIndex]);
      for (int i = 0; i < numSuccessors; i++) {
        System.out.println(successors[i]);
      }

      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Método gerado com ChatGPT. Usado para extração de números do arquivo .txt
   * @param filePath
   * @throws IOException
   */
  public void read(String filePath) throws IOException {
    try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
      String line;

      // Read the first line separately
      if ((line = file.readLine()) != null) {
        String[] firstLineNumbers = line.trim().split("\\s+");
        if (firstLineNumbers.length == 2) {
          numVertices = Integer.parseInt(firstLineNumbers[0]);
          numEdges = Integer.parseInt(firstLineNumbers[1]);
        }
      }

      // Read the remaining lines and store in columns
      while ((line = file.readLine()) != null) {
        String[] numbers = line.trim().split("\\s+");
        if (numbers.length == 2) {
          firstColumn.add(Integer.parseInt(numbers[0]));
          secondColumn.add(Integer.parseInt(numbers[1]));
        }
      }
    }
  }

  public static int findTargetIndex(int target, int array[]) {
    int index = -1;

    for (int i = 0; i < array.length; i++) {
      if (target == array[i]) {
        index = i;
        return index;
      }
    }

    return index;
  }

  public static int findNumSuccessors(int targetIndex, int array[]) {
    int numSuccessors = 1;

    for (int i = targetIndex; i < array.length; i++) {
      if (array[i] != array[i + 1]) {
        return numSuccessors;
      } else {
        numSuccessors++;
      }
    }

    return numSuccessors;
  }

  public static int[] findSuccessors(
    int index,
    int numSuccessors,
    int[] tails,
    int[] heads
  ) {
    int[] succ = new int[numSuccessors];

    int j = 0;
    for (int i = index; j < numSuccessors; i++) {
      succ[j] = heads[i];
      j++;
    }

    return succ;
  }
  //   public static void readFile(String filePath) throws IOException {
  //     int numVertices = 0;
  //     int numEdges = 0;

  //     ArrayList<Integer> firstColumn = new ArrayList<>();
  //     ArrayList<Integer> secondColumn = new ArrayList<>();

  //     try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
  //       String line;

  //       // Read the first line separately
  //       if ((line = file.readLine()) != null) {
  //         String[] firstLineNumbers = line.trim().split("\\s+");
  //         if (firstLineNumbers.length == 2) {
  //           numVertices = Integer.parseInt(firstLineNumbers[0]);
  //           numEdges = Integer.parseInt(firstLineNumbers[1]);
  //         }
  //       }

  //       // Read the remaining lines and store in columns
  //       while ((line = file.readLine()) != null) {
  //         String[] numbers = line.trim().split("\\s+");
  //         if (numbers.length == 2) {
  //           firstColumn.add(Integer.parseInt(numbers[0]));
  //           secondColumn.add(Integer.parseInt(numbers[1]));
  //         }
  //       }
  //     }

  //     // Convert ArrayLists to arrays if needed
  //     int[] tails = firstColumn.stream().mapToInt(i -> i).toArray();
  //     int[] heads = secondColumn.stream().mapToInt(i -> i).toArray();

  //     // Trecho acima gerado com ChatGPT (usado somente p/ extração de números)

  //     int targetedIndex = findTargetIndex(5, tails);
  //     System.out.println("SUCESSOR 1: " + heads[targetedIndex]);
  //   }
}
