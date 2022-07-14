package thread;

/**
 * 哲学家问题
 */
public class Philosopher extends Thread {
    private int index;
    private Chopstick left, right;

    public Philosopher(int index, Chopstick left, Chopstick right) {
        this.index = index;
        this.left = left;
        this.right = right;
    }

    private static class Chopstick {

    }

    @Override
    public void run() {
        if (index % 2 == 0) {
            synchronized (left) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (right) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("哲学家" + index + "吃完了！");
                }
            }
        } else {
            synchronized (right) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (left) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("哲学家" + index + "吃完了！");
                }
            }
        }
    }

    public static void main(String[] args) {
        Chopstick c1 = new Chopstick();
        Chopstick c2 = new Chopstick();
        Chopstick c3 = new Chopstick();
        Chopstick c4 = new Chopstick();
        Chopstick c5 = new Chopstick();

        Philosopher p1 = new Philosopher(1, c1, c2);
        Philosopher p2 = new Philosopher(2, c2, c3);
        Philosopher p3 = new Philosopher(3, c3, c4);
        Philosopher p4 = new Philosopher(4, c4, c5);
        Philosopher p5 = new Philosopher(5, c5, c1);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
