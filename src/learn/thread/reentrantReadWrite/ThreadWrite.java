package learn.thread.reentrantReadWrite;

public class ThreadWrite extends Thread {

    private ReadWrite readWrite;

    private Object data;

    public ThreadWrite(ReadWrite readWrite, Object data) {
        this.readWrite = readWrite;
        this.data = data;
    }

    @Override
    public void run() {
        super.run();
        readWrite.write(data);
    }
}
