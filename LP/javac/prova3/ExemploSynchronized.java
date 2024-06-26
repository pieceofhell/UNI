class Contador {
    private int count = 0;
    
    // Método sincronizado
    public synchronized void incrementar() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class ExemploSynchronized {
    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador();

        Runnable tarefa = () -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
            }
        };

        Thread t1 = new Thread(tarefa);
        Thread t2 = new Thread(tarefa);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Valor final do contador: " + contador.getCount());
    }
}
