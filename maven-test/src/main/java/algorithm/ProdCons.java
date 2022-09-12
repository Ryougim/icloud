package algorithm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者demo
 */
public class ProdCons {

    static ReentrantLock lock = new ReentrantLock();
    static Condition prodCondition = lock.newCondition();
    static Condition consCondition = lock.newCondition();
    static int count = 0; //初始消息数
    static int maxCount = 3; //最大消息数

    public static void main(String[] args) {
        new Thread(new ProdCons.Producer(), "生产者1").start();
        new Thread(new ProdCons.Producer(), "生产者2").start();

        new Thread(new ProdCons.Consumer(), "消费者1").start();
        new Thread(new ProdCons.Consumer(), "消费者2").start();
    }

    private static class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();

                try {
                    while (count >= maxCount) {
                        System.out.println("消息已满，生产者进入等待状态");
                        try {
                            prodCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println("当前容器数量为" + count + "，生产者唤醒所有消费者消费");
                    consCondition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();

                try {
                    while (count <= 0) {
                        System.out.println("消息已空，消费者进入等待状态");
                        try {
                            consCondition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println("当前容器数量为" + count + "，消费者唤醒所有生产者生产");
                    prodCondition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
