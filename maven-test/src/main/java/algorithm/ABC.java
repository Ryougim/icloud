package algorithm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ABC循环问题（线程排序） 按顺序输出10次ABC
 */
public class ABC {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cA = lock.newCondition(); //线程A所在的队列
    private static Condition cB = lock.newCondition(); //线程B所在的队列
    private static Condition cC = lock.newCondition(); //线程C所在的队列

    private static CountDownLatch latchB = new CountDownLatch(1); //门栓，控制线程B不能第一个抢到锁
    private static CountDownLatch latchC = new CountDownLatch(1); //门栓，控制线程C不能第一个抢到锁

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("A");
                    cB.signal(); //唤醒线程B
                    if (i == 0) latchB.countDown(); //打印第一个A之后去掉线程B的门栓，让线程B可以争抢锁
                    cA.await(); //线程A等待
                }
                cB.signal(); //循环结束要唤醒线程B
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread A");

        Thread threadB = new Thread(() -> {
            try {
                latchB.await(); //等待门栓打开
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("B");
                    cC.signal(); //唤醒线程C
                    if (i == 0) latchC.countDown(); //打印第一个B之后去掉线程C的门栓，让线程C可以争抢锁
                    cB.await(); //线程B等待
                }
                cC.signal(); //循环结束要唤醒线程C
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread B");

        Thread threadC = new Thread(() -> {
            try {
                latchC.await(); //等待门栓打开
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("C");
                    cA.signal(); //唤醒线程A
                    cC.await(); //线程C等待
                }
//                cA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "Thread C");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
