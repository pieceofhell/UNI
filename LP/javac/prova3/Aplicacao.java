package app;

/**
 * Este exemplo foi adaptado do Java Concurrency Tutorial, da Oracle
 * 
 * {@link https://docs.oracle.com/javase/tutorial/essential/concurrency/}
 *
 * Exemplo de aplicativo para ilustrar m�ltiplas threads em execu��o com Join
 * 
 * O comando Join aguarda a morte de uma Thread, para ent�o prosseguir com a
 * execu��o. � uma forma de sincronizar a execu��o de algumas threads. Neste
 * exemplo, as threads t2 e t3 s� se�o iniciadas ap�s o t�rmino de t1. A
 * execu��o de t2 e t3, no entanto, acontecer� de forma concorrente.
 * 
 * @author Hugo de Paula
 *
*/

class Aplicacao implements Runnable {

	public void run() {
		for (int i = 0; i < 5; i++) {
			
			// O objetivo de sleep � garantir que ocorra altern�ncia 
			// de execu��o de Threads.
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
			
			System.out.println(Thread.currentThread().getName() + "  " + i);
		}
	}

	public static void main(String args[]) {

		Thread t1 = new Thread(new Aplicacao(), "T1");
		Thread t2 = new Thread(new Aplicacao(), "T2");
		Thread t3 = new Thread(new Aplicacao(), "T3");

		try {

			t1.start();

			// Join aguarda a thread t1 terminar.

			t1.join();

			// t2 e t3 sao executadas concorrentemente.
			
			t2.start();

			t3.start();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}