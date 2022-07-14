package thread.kill;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Client {
    private static Integer amount = 8;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        BlockingQueue<Promise> queue = new LinkedBlockingDeque();

        mergeJob(queue);

        RespositoryService respositoryService = new RespositoryService(queue);

        List<Future<Result>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserRequest userRequest = new UserRequest(i, 1);
            ExecutorService executorService = Executors.newCachedThreadPool();
            Future<Result> result = executorService.submit(() -> {
                return respositoryService.deduction(userRequest);
            });
            futureList.add(result);
        }

        for (Future<Result> future : futureList) {
            Result result = future.get(300, TimeUnit.MILLISECONDS);
            System.out.println(result);
        }
    }

    public static void mergeJob(BlockingQueue<Promise> queue) {
        new Thread(() -> {
            List<Promise> promises = new ArrayList<>();
            while (true) {
                if (queue.isEmpty()) {
                    try {
                        Thread.sleep(10);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    if (queue.peek() != null) {
                        promises.add(queue.poll());
                    }
                }
                int sum = promises.stream().mapToInt(e -> e.getUserRequest().getCount()).sum();
                System.out.println("合并队列大小：" + size);
                if (sum <= amount) {
                    amount -= sum;
                    for (Promise promise : promises) {
                        promise.setResult(new Result("0", "ok"));
                        synchronized (promise) {
                            promise.notify();
                        }
                    }
                    promises.clear();
                } else {
                    for (Promise promise : promises) {
                        int count = promise.getUserRequest().getCount();
                        if (amount >= count) {
                            amount -= promise.getUserRequest().getCount();
                            promise.setResult(new Result("0", "库存扣减成功"));
                        } else {
                            promise.setResult(new Result("1", "库存不足"));
                        }
                        synchronized (promise) {
                            promise.notify();
                        }
                    }
                    promises.clear();
                }
            }
        }, "合并队列").start();
    }
}
