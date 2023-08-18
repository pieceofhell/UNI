public class MultiplicacaoComSoma {

    public static int multSoma(int a, int b) {
        int result = 0;
        for (int i = 0; i < b; i++) {
            result += a;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(multSoma(3, 4));
    }    
}
