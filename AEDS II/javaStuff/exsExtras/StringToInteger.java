class StringToInteger extends Solution {

  public int myAtoi(String s) {
    int number = 0;
    int sIndex = 0;
    while (!Character.isLetter(s.charAt(sIndex)) && sIndex < s.length()) {
        // if (s.charAt(sIndex) == '-') {
        // }
        number = Integer.parseInt(s);
        sIndex++;
    }
    System.out.println(number);

    return number;
  }
}
