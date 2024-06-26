class MinhaRunnable implements Runnable {
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getId() + " Valor: " + i);
        }
    }
}

public class ExemploRunnable {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MinhaRunnable());
        Thread t2 = new Thread(new MinhaRunnable());
        
        t1.start(); // Inicia a execução da thread t1
        t2.start(); // Inicia a execução da thread t2
    }
}
