package learn.thread.produceAndCustom.waitAndNotyfy;

import java.util.LinkedList;

public class Storage {

    private final int MAX_SIZE = 10;

    private LinkedList<Object> sources = new LinkedList<>();

    public void produce() {
        synchronized (sources) {
            while (sources.size() == MAX_SIZE) {
                System.out.println("【生产者" + Thread.currentThread().getName()
                        + "】仓库已满");
                try {
                    sources.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            sources.add(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName()
                    + "】生产一个产品，现库存" + sources.size());
            sources.notifyAll();
        }
    }

    public void custom() {
        synchronized (sources) {
            while (sources.size() == 0) {
                System.out.println("【消费者" + Thread.currentThread().getName()
                        + "】仓库为空");
                try {
                    sources.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            sources.remove();
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】消费一个产品，现库存" + sources.size());
            sources.notifyAll();
        }
    }
}
