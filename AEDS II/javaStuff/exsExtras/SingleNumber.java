import java.util.HashSet;

class SingleNumber extends Solution {

  static int singleNumber(int[] nums) {
    HashSet<Integer> seen = new HashSet<>();

    for (int num : nums) {
      if (seen.contains(num)) {
        seen.remove(num);
      } else {
        seen.add(num);
      }
    }

    for (int num : seen) {
      return num;
    }

    return 0;
  }

  public static void main(String[] args) {
    int[] numbs = { 2, 2, 1, 3 };
    singleNumber(numbs);
  }
}