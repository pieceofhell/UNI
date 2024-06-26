package javac;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

    private List<T> queue = new LinkedList<>();
    private int limit = 10;
    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public BlockingQueue(int limit) {
        this.limit = limit;
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == limit) {
                notFull.await();
            }
            queue.add(item);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            T item = queue.remove(0);
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }
}
