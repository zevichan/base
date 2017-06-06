package com.czw.concurrent;


import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZeviChen , 2017/6/2 15:21
 */
public class TestCount {

    //int count = 0;//多运行几次会出现999,998的情况
    //volatile int count = 0;//对于并发并没有作用,仍然会有999,998的情况
    AtomicInteger count = new AtomicInteger(0);//原子类型是线程安全,可以实现count多线程并发统计

    @Test(threadPoolSize = 100, invocationCount = 1000)
    public void countInt() throws InterruptedException {
        Thread.sleep(10);
        //++count;
        count.addAndGet(1);
    }

    @AfterTest
    public void printCount() {
        System.out.println("count : " + count);
    }


}
