class TwoSumOpt {

  public static void twoSum(int[] nums, int target) {
    int[] indexes = new int[2];
    int i = 0;
    boolean notFound = true;
    while (notFound) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] == target - nums[j]) {
          indexes[0] = i;
          indexes[1] = j;
          notFound = false;
        }
      }
      i++;
    }
    System.out.println(indexes[0] + " " + indexes[1]);
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 3, 4, 9, 6, 4};
    twoSum(arr, 8);
  }
}
