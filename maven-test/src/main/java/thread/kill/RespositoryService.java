package thread.kill;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RespositoryService {
    private BlockingQueue<Promise> queue; //合并队列
    private static AtomicInteger concurCount; //接口并发计数

    static {
        concurCount = new AtomicInteger();
    }

    public RespositoryService(BlockingQueue<Promise> queue) {
        this.queue = queue;
    }

    public Result deduction(UserRequest userRequest) throws InterruptedException {
        concurCount.getAndIncrement();
        try {
            if (concurCount.get() < 3) {
                //并发数小于3不进行合并队列
                return new Result("0", "ok");
            }
            Promise promise = new Promise(userRequest);
            synchronized (promise) {
                //将请求放入队列
                boolean offer = queue.offer(promise, 100, TimeUnit.MILLISECONDS);
                if (!offer) {
                    return new Result("1", "系统繁忙");
                }
                try {
                    promise.wait(200);
                    if (promise.getResult() == null) {
                        return new Result("1", "等待超时");
                    }
                } catch (InterruptedException e) {
                    return new Result("1", "被中断");
                }
            }
            return promise.getResult();
        } finally {
            concurCount.getAndDecrement();
        }
    }
}
