class Pow extends Solution {

  static double myPow(double x, int n) {
    if (n == 0) {
      return 1.0;
    }

    double result = 1.0;
    long absN = Math.abs((long) n); // Handle integer overflow for n

    while (absN > 0) {
      if (absN % 2 == 1) {
        result *= x;
      }
      x *= x;
      absN /= 2;
    }

    if (n < 0) {
      return 1.0 / result;
    } else {
      return result;
    }
  }

  public static void main(String[] args) {
    double result = myPow(2, -2);
    System.out.println(result);
  }
}
