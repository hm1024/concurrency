package com.minghai.concurrency.example.concurrent;

import com.minghai.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentSkipListMapExample {
    // 请求总数
    public static int clientTotal = 5000;
    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public  static Map<Integer,Integer> map = new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            final int a = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(a);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",map.size());

    }

    public static void update(int i){
        map.put(i,i);
    }
}
