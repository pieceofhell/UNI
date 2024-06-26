class MinhaThread extends Thread {
    public void run() {
        for(int i = 0; i <= 5; i++) {
            System.out.println(Thread.currentThread().getId() + " Valor: " + i);
        }
    }
}

public class ExemploThread {
    public static void main(String[] args) {
        MinhaThread t1 = new MinhaThread();
        MinhaThread t2 = new MinhaThread();
        
        System.out.println("ID da thread t1: " + t1.getId());
        System.out.println("ID da thread t2: " + t2.getId());
        t1.start(); // Inicia a execução da thread t1
        t2.start(); // Inicia a execução da thread t2
    }
}
