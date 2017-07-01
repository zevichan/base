package com.czw.base.colls.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zevi on 2017/6/29.
 */
public class TestQueue {

//    @Test
//    public void ArrayBlockingQueue() {

    public final static void main(String[] args) {

        new ArrayBlockingQueue(10);

        Thread i1 = new Thread(new RunIt3(), "aaa");
        Thread i2 = new Thread(new RunIt3(), "bbb");
        i2.start();
        i1.start();
        i2.interrupt();
    }
}
class RunIt3 implements Runnable {
    private static Lock lock = new ReentrantLock();
    public void run() {
        try {
            //---------------------------------a
//            lock.lock();
//            lock.lockInterruptibly();
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " running");
            TimeUnit.SECONDS.sleep(2);
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " finished");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted");

        }
    }
}
