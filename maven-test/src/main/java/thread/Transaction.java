package thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 异步回调回滚问题（分布式事务）
 */
public class Transaction {
    public static void main(String[] args) throws IOException {
        Coordinator coordinator = new Coordinator();
        Task t1 = new Task(coordinator, "t1", 5, false);
        Task t2 = new Task(coordinator, "t2", 3, true);
        Task t3 = new Task(coordinator, "t3", 1, true);

        coordinator.addTask(t1);
        coordinator.addTask(t2);
        coordinator.addTask(t3);

        coordinator.start();

        System.in.read();
    }

    /**
     * TC协调者
     */
    private static class Coordinator extends Thread {
        List<Task> tasks = new ArrayList<>();

        public void addTask(Task task) {
            tasks.add(task);
        }

        @Override
        public void run() {
            tasks.stream().forEach(t -> {
                System.out.println(t.name + "任务开始执行。。。");
                t.start();
            });
        }

        public void end(Task task) {
            if (task.result == Result.FAILED) {
                System.out.println(task.name + "任务执行失败，全局事务开始回滚。。。");
                tasks.stream().filter(t -> t != task).forEach(t -> {
                    t.interrupt();
                    t.rollback();
                });
            } else {
                System.out.println(task.name + "任务执行成功。。。");
            }
        }
    }

    /**
     * 任务线程
     */
    private static class Task extends Thread {
        Coordinator coordinator;
        String name;
        long sec;
        boolean successed;
        Result result;

        public Task(Coordinator coordinator, String name, long sec, boolean successed) {
            this.coordinator = coordinator;
            this.name = name;
            this.sec = sec;
            this.successed = successed;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sec * 1000);
            } catch (InterruptedException e) {
                return;
            }

            result = successed ? Result.SUCCESSED : Result.FAILED;
            coordinator.end(this);
        }

        public void rollback() {
            System.out.println(name + "任务已回滚。。。");
        }
    }

    private enum Result {
        SUCCESSED,
        FAILED
    }
}
