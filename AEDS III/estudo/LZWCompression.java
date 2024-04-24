/**
 * LZWCompression
 */
public class LZWCompression {

    public static void main(String[] args) {
        String input = "TOBEORNOTTOBEORTOBEORNOT";
        LZWCompression lzw = new LZWCompression();
        lzw.compress(input);
    }

    public void compress(String input) {
        int dictSize = 256;
        String w = "";
        String result = "";
        for (char c : input.toCharArray()) {
            String wc = w + c;
            if (dictSize == 4096) {
                System.out.println("Dicionário cheio");
                break;
            }
            if (isInDictionary(wc)) {
                w = wc;
            } else {
                result += w + " ";
                addDictionary(wc);
                w = "" + c;
            }
        }
        result += w;
        System.out.println(result);
    }

    public boolean isInDictionary(String s) {
        return false;
    }

    public void addDictionary(String s) {
        System.out.println("Adicionando " + s + " ao dicionário");
    }
}