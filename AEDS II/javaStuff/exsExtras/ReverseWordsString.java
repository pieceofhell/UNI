class ReverseWordsString extends Solution {

  public String reverseWords(String s) {
    s += '\0';
    StringBuilder strings = new StringBuilder();
    StringBuilder finalString = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) != ' ') {
        strings.append(s.charAt(i));
      }
      if (s.charAt(i) == ' ' || s.charAt(i) == '\0') {
        finalString.append(strings.reverse() + " ");
        strings.setLength(0);
      }
    }
    finalString.deleteCharAt(finalString.length() - 1);

    return finalString.toString().replace("\0", "");
  }
}
