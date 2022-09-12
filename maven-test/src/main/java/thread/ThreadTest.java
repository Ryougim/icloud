package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadTest {
    public static void main(String[] args) throws Exception {
        Thread t = new MyThread();
        t.start();

        Thread t2 = new Thread(new MyThread2());
        t2.start();

        Thread tt = new Thread(() -> {

        });

        Callable<String> c = new MyThread3();
        FutureTask<String> f = new FutureTask<>(c);
        Thread t3 = new Thread(f);
        t3.start();
        System.out.println(f.get());

        Callable<Object> callable = Executors.callable(new MyThread2());

    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("线程执行。。。");
        }
    }

    private static class MyThread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("线程执行。。。");
        }
    }

    private static class MyThread3 implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("线程执行。。。");
            return "执行完毕";
        }
    }
}
