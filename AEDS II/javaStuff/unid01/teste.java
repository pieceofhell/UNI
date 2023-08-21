public class teste {
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (mid > 0 && arr[mid - 1] == target) {
            return mid - 1;
        } else if (arr[mid] == target) {
            return mid;
        }

        return -1;
    }
    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 };
        int target = 1;

        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("Elemento encontrado na posicao: " + index);
        } else {
            System.out.println("Elemento nao encontrado na array.");
        }
    }
}