package learn.thread.reentrantReadWrite;

import java.util.Random;

public class ReadWriteLockTest {

    public static void main(String[] args) {
        ReadWrite readWrite = new ReadWrite();

        for (int i = 0; i < 3; i++) {
            ThreadWrite write = new ThreadWrite(readWrite, new Random().nextInt(100));
            write.setName("write" + i + " : ");
            write.start();
        }

        for (int i = 0; i < 3; i++) {
            ThreadRead read = new ThreadRead(readWrite);
            read.setName("read" + i + " : ");
            read.start();
        }
    }
}
