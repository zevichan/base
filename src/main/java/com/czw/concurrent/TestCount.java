package com.czw.concurrent;


import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile将线程缓存中的数据更改实时写入主存中,便于其他CPU上的线程读取.
 * 使用volatile定义的数据结构,每个volatile定义的字段只由一个线程进行写操作,
 * 可以实现非阻塞的数据结构,所以该关键字并不是用来定义原子操作的.
 *
 *
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
