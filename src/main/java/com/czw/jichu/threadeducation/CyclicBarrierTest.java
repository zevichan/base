package com.czw.jichu.threadeducation;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计数器:每有NUM_BLOCK线程阻塞,CyclicBarrier就唤醒NUM_BLOCK线程运行
 */
public class CyclicBarrierTest {

    static int NUM_BLOCK = 2;
    static int NUM_THREAD = 8;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        final CyclicBarrier cb = new CyclicBarrier(NUM_BLOCK);
        for (int i = 0; i < NUM_THREAD; i++) {
            Thread.sleep(new Random().longs(1000).count());
            Runnable runnable = () -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting");
                    cb.await();
                    Thread.sleep(new Random().longs(1000).count());
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " worked");

            };
            service.execute(runnable);
        }
        service.shutdown();
    }

}

