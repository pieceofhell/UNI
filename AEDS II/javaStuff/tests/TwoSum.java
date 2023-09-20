class TwoSum extends Solution {

  public static void twoSum(int[] nums, int target) {
    int[] foundNums = new int[2];
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if ((nums[i] + nums[j]) == target) {
          foundNums[0] = i;
          foundNums[1] = j;
        }
      }
    }
    System.out.println(foundNums[0] + " " + foundNums[1]);
  }

  public static void main(String[] args) {
    int[] arr = new int[] {3, 4, 9, 6, 4};
    twoSum(arr, 8);
  }
}
