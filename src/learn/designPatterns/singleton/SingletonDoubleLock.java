package learn.designPatterns.singleton;

public class SingletonDoubleLock {

    private static volatile SingletonDoubleLock singletonDoubleLock;

    private SingletonDoubleLock() {
    }

    public static SingletonDoubleLock getInstance() {
        if (singletonDoubleLock == null) {
            synchronized (SingletonDoubleLock.class) {
                if (singletonDoubleLock == null) {
                    singletonDoubleLock = new SingletonDoubleLock();
                }
            }
        }
        return singletonDoubleLock;
    }
}
