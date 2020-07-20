package learn.thread.produceAndCustom.LockAndCondition;

import learn.thread.produceAndCustom.LockAndCondition.Storage;

public class Customer implements Runnable {

    private Storage storage;

    private Customer() {
    }

    public Customer(Storage storage, String name) {
        this.storage = storage;
        Thread.currentThread().setName(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                storage.custom();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
