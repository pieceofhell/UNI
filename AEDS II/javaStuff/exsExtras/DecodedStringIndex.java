class DecodedStringIndex extends Solution {

  public String decodeAtIndex(String s, int k) {
    long decodedLength = 0;

    for (char c : s.toCharArray()) {
      if (Character.isDigit(c)) {
        decodedLength *= c - '0';
      } else {
        decodedLength++;
      }
    }

    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        decodedLength /= c - '0';
        k %= decodedLength;
      } else {
        if (k == 0 || k == decodedLength) {
          return String.valueOf(c);
        }
        decodedLength--;
      }
    }

    return null;
  }
}
