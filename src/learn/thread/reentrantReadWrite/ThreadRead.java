package learn.thread.reentrantReadWrite;

public class ThreadRead extends Thread {
    private ReadWrite readWrite;

    public ThreadRead(ReadWrite readWrite) {
        this.readWrite = readWrite;
    }

    @Override
    public void run() {
        super.run();
        readWrite.read();
    }
}
