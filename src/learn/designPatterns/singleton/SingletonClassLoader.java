package learn.designPatterns.singleton;

public class SingletonClassLoader {

    private static class SingletonClassHolder{
        private static final SingletonClassLoader INSTANCE = new SingletonClassLoader();
    }

    private SingletonClassLoader(){}

    private static final SingletonClassLoader getInstance() {
        return SingletonClassHolder.INSTANCE;
    }
}
