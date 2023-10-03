class OPTIMIZEDReverseWordsString extends Solution {

  public String reverseWords(String s) {
    char[] chars = (s + '\0').toCharArray();
    int start = 0;

    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == ' ' || chars[i] == '\0') {
        reverse(chars, start, i - 1);
        start = i + 1;
      }
    }

    return new String(chars).trim();
  }

  private void reverse(char[] chars, int start, int end) {
    while (start < end) {
      char temp = chars[start];
      chars[start] = chars[end];
      chars[end] = temp;
      start++;
      end--;
    }
  }
}
