package designMode.singleton;

public class SingletonStaic {
    private SingletonStaic() {
    }

    private static class Singleton {
        private static final SingletonStaic singleton = new SingletonStaic();
    }

    public static SingletonStaic getInstance() {
        return Singleton.singleton;
    }
}
