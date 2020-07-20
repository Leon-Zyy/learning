package learn.thread.threadVsRunnable;

public class RunnableDemo {

    public static void main(String[] args) {
        MyRunnable myr1 = new MyRunnable("Runnable1");
        MyRunnable myr2 = new MyRunnable("myr2");
        MyRunnable myr3 = new MyRunnable("myr3");

        Thread t1 = new Thread(myr1);
        Thread t2 = new Thread(myr2);
        Thread t3 = new Thread(myr3);

        t1.start();
        t2.start();
        t3.start();
    }
}
