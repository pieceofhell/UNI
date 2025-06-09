public class Fibonacci {
    // fibonacci iterativo
    public static int fibonacci(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
            System.out.println("Fibonacci de " + i + " é: " + b); // Log do valor atual
        }
        
        return b;
    }

    // fibonacci recursivo
    public static int fibonacciRecursivo(int n) {
        if (n <= 1) return n;
        return fibonacciRecursivo(n - 1) + fibonacciRecursivo(n - 2);
    }


    public static void main(String[] args) {
        int n = 10; // Exemplo de entrada
        System.out.println("Fibonacci de " + n + " é: " + fibonacci(n));  
        System.out.println("Fibonacci recursivo de " + n + " é: " + fibonacciRecursivo(n));      
        
    }

}