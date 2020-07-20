package learn.thread.reentrantReadWrite;


import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWrite {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Object data = null;

    public void read() {
        lock.readLock().lock();
        try {
            try {
                System.out.println(Thread.currentThread().getName() + "is ready to read");
                Thread.sleep(new Random().nextInt(100));
                System.out.println(Thread.currentThread().getName() + "have write read " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(Object data) {
        try {
            lock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "is ready to write");
                this.data = data;
                Thread.sleep(new Random().nextInt(100));
                System.out.println(Thread.currentThread().getName() + "have write data " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }
}
