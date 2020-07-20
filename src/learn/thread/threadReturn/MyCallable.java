package learn.thread.threadReturn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("线程执行......");
        Thread.sleep(5000);
        System.out.println("线程执行完毕......");
        return "hello world!!!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        /***
         * futureTask 实现了 Runnable接口
         * 所以新建线程的时候可以传入futureTask
         * FutureTask重写的run方法中实际是调用了Callable接口在call()方法
         * 所以执行线程的时候回执行call方法的内容
         */
        Thread thread = new Thread(futureTask);
        thread.start();
        if(!futureTask.isDone()) {
            System.out.println("task has not finished, please wait!");
        }
        System.out.println("task return: " + futureTask.get());
    }
}
