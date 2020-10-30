package learn.dataStructure.other;

/**
 * 单利模式
 * 类加载模式
 */
public class SingletonClassloader {

    private static class SingletonLoader {
        private static final SingletonClassloader INSTANCE = new SingletonClassloader();
    }

    private SingletonClassloader() {
    }

    public static final SingletonClassloader getInstance() {
        return SingletonLoader.INSTANCE;
    }
}
