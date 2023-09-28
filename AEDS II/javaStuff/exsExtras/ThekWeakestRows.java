import java.util.Arrays;

class ThekWeakestRows extends Solution {

  static int[] kWeakestRows(int[][] mat, int k) {
    int row = mat.length;
    int col = mat[0].length;

    // Use a custom class to store the row index and soldier count
    class RowPower implements Comparable<RowPower> {

      int index;
      int power;

      public RowPower(int index, int power) {
        this.index = index;
        this.power = power;
      }

      @Override
      public int compareTo(RowPower other) {
        // Compare by power, if powers are equal, then by row index
        if (this.power == other.power) {
          return this.index - other.index;
        }
        return this.power - other.power;
      }
    }

    // Store the row index and soldier count in RowPower objects
    RowPower[] rowPowers = new RowPower[row];
    for (int i = 0; i < row; i++) {
      int soldiers = 0;
      for (int j = 0; j < col; j++) {
        if (mat[i][j] == 1) {
          soldiers++;
        } else {
          break; // Since 0's will appear to the right of 1's, we can break early
        }
      }
      rowPowers[i] = new RowPower(i, soldiers);
    }

    // Sort the RowPower objects based on power and row index
    Arrays.sort(rowPowers);

    // Extract the k weakest row indices
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      result[i] = rowPowers[i].index;
    }

    return result;
  }

  public static void main(String[] args) {
    int[][] matrix = {
      { 1, 1, 0, 0, 0 },
      { 1, 1, 1, 1, 0 },
      { 1, 0, 0, 0, 0 },
      { 1, 1, 0, 0, 0 },
      { 1, 1, 1, 1, 1 },
    };
    int[] weakestRows = kWeakestRows(matrix, 3);

    // Print the result
    System.out.println("The k weakest rows are:");
    for (int row : weakestRows) {
      System.out.println(row);
    }
  }
}
