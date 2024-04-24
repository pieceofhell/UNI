public class PatternMatching {

  public static void main(String[] args) {
    String text = "abacaxicombananaelinguicaehmuitobom";
    String pattern = "banana";
    PatternMatching pm = new PatternMatching();
    System.out.println("Text: " + text);
    System.out.println("Pattern: " + pattern);
    // {
    //   System.out.println("Seção de código que apresenta o texto em bytes:");
    //   byte[] textBin = StringToByteArray(text);
    //   byte[] patternBin = StringToByteArray(pattern);
    //   for (int i = 0; i < textBin.length; i++) {
    //     System.out.print(textBin[i] + " ");
    //   }
    //   System.out.println();
    //   System.out.println("Bytes do padrão: ");
    //   for (int i = 0; i < patternBin.length; i++) {
    //     System.out.print(patternBin[i] + " ");
    //   }
    // }
    // System.out.println("\n");
    // {
    //   System.out.println("Seção de código que apresenta o texto em bits:");
    //   int[] textInt = StringToIntArray(text);
    //   int[] patternInt = StringToIntArray(pattern);
    //   for (int i = 0; i < textInt.length; i++) {
    //     System.out.print(Integer.toBinaryString(textInt[i]) + " ");
    //   }
    //   System.out.println();
    //   System.out.println("Bits do padrão: ");
    //   for (int i = 0; i < patternInt.length; i++) {
    //     System.out.print(Integer.toBinaryString(patternInt[i]) + " ");
    //   }
    // }
    pm.patternSearch(StringToByteArray(text), StringToByteArray(pattern));
  }

  public static byte[] StringToByteArray(String s) {
    byte[] result = new byte[s.length()];
    for (int i = 0; i < s.length(); i++) {
      result[i] = (byte) s.charAt(i);
    }
    return result;
  }

  public static int[] StringToIntArray(String s) {
    int[] result = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      result[i] = (int) s.charAt(i);
    }
    return result;
  }

  public void patternSearch(byte[] text, byte[] pattern) {
    int n = text.length;
    int m = pattern.length;
    int comps = 0;
    for (int i = 0; i <= n - m; i++) {
      int j;
      for (j = 0; j < m; j++) {
        if (text[i + j] != pattern[j]) {
          comps++;
          break;
        }
      }
      if (j == m) {
        System.out.println("Padrão encontrado na posição " + i);
      }
    }
    System.out.println("Comparações: " + comps);
  }
}
