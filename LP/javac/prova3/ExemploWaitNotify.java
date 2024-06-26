class Processador {
    private boolean pronto = false;

    public synchronized void produzir() throws InterruptedException {
        System.out.println("Produzindo...");
        Thread.sleep(1000); // Simula tempo de produção
        pronto = true;
        notify(); // Notifica a thread consumidora
        System.out.println("Produção completa!");
    }

    public synchronized void consumir() throws InterruptedException {
        while (!pronto) {
            System.out.println("Aguardando produção...");
            wait(); // Aguarda até que seja notificado
        }
        System.out.println("Consumo iniciado!");
    }
}

public class ExemploWaitNotify {
    public static void main(String[] args) {
        Processador processador = new Processador();

        Thread produtor = new Thread(() -> {
            try {
                processador.produzir();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumidor = new Thread(() -> {
            try {
                processador.consumir();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        consumidor.start();
        produtor.start();
    }
}
