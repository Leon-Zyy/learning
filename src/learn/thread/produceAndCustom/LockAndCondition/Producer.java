package learn.thread.produceAndCustom.LockAndCondition;

import learn.thread.produceAndCustom.LockAndCondition.Storage;

public class Producer implements Runnable {

    private Storage storage;

    public Producer() {
    }

    public Producer(Storage storage, String name) {
        this.storage = storage;
        Thread.currentThread().setName(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                storage.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
