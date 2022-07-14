package thread.kill;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class RespositoryService {
    private BlockingQueue<Promise> queue;

    public RespositoryService(BlockingQueue<Promise> queue) {
        this.queue = queue;
    }

    public Result deduction(UserRequest userRequest) throws InterruptedException {
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
    }
}
