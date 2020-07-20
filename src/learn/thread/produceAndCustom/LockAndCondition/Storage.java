package learn.thread.produceAndCustom.LockAndCondition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {

    private final int MAX_SIZE = 10;

    private LinkedList<Object> sources = new LinkedList<>();
    // 锁
    private final Lock lock = new ReentrantLock();
    // 满
    private final Condition full = lock.newCondition();
    // 空
    private final Condition empty = lock.newCondition();

    public void produce() {
        lock.lock();

        while (sources.size() == MAX_SIZE) {
            System.out.println("【生产者" + Thread.currentThread().getName()
                    + "】仓库已满");
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sources.add(new Object());
        System.out.println("【生产者" + Thread.currentThread().getName()
                + "】生产一个产品，现库存" + sources.size());
        empty.signalAll();
        lock.unlock();
    }

    public void custom() {
        lock.lock();
        while (sources.size() == 0) {
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】仓库为空");
            try {
                empty.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sources.remove();
        System.out.println("【消费者" + Thread.currentThread().getName()
                + "】消费一个产品，现库存" + sources.size());
        full.signalAll();
        lock.unlock();
    }
}
