package com.czw.base.colls.tcollection;

import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author ZeviChen , 2017/6/28 09:11
 */
public class TestQueue {


    @Test
    public void priorityQueue() {
        Queue<Integer> qi = new PriorityQueue<>();

        qi.add(5);
        qi.add(2);
        qi.add(1);
        qi.add(10);
        qi.add(3);

        while (!qi.isEmpty()) {
            System.out.print(qi.poll() + ",");
        }
        System.out.println();
        System.out.println("-----------------------------");
        // 自定义的比较器，可以让我们自由定义比较的顺序 Comparator<Integer> cmp;
        Comparator<Integer> cmp = (e1, e2) -> e2 - e1;
        Queue<Integer> q2 = new PriorityQueue<>(5, cmp);
        q2.add(2);
        q2.add(8);
        q2.add(9);
        q2.add(1);
        while (!q2.isEmpty()) {
            System.out.print(q2.poll() + ",");
        }
    }
    
    @Test
    public void arrayQueue(){
        Deque dq = new ArrayDeque();
        
        int h = 0,l = 5;
        System.out.println((h-1)&(l-1));
        
    }

    @Test
    public void LinkedBlockingDeque(){
        LinkedBlockingDeque l = new LinkedBlockingDeque();
        BlockingDeque deque = new LinkedBlockingDeque();

    }


}
