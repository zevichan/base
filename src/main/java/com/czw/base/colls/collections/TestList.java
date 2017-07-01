package com.czw.base.colls.collections;

import org.testng.annotations.Test;

import javax.management.relation.RoleList;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zevi on 2017/6/26.
 */
public class TestList {

    @Test
    public void arrayList() {

        ArrayList<Car> as = new ArrayList<>();
        as.add(new Car("aaa"));
        as.add(new Car("aaa"));
        as.add(new Car("bbb"));
        System.out.println(as.size());

    }

    /**
     * 几乎所有方法都加了synchronized，效率太低
     */
    @Test
    public void vector() {
        Vector<Car> v = new Vector<>();
        v.add(new Car("aaa"));

    }

    /**
     * 继承自Vector，虽然做到线程安全，但效率低
     */
    @Test
    public void stack() {
        Stack<Car> s = new Stack<>();
        s.push(new Car("aaa"));
        s.push(new Car("bbb"));
        s.push(new Car("aaa"));
        System.out.println(s.peek());//get
        System.out.println(s.pop());//get and remove

    }

    @Test
    public void linkedList() {
        List<Car> l = new LinkedList<>();
        l.add(new Car("aaa"));
        l.add(new Car("bbb"));
        l.add(new Car("ccc"));
        int i = l.indexOf(new Car("bbb"));
        System.out.println("b index:" + i);
        l.add(i, new Car("ddd"));
        l.remove(new Car("ccc"));

        int count = 0;
        for (Car c : l) {
            System.out.println(count + " : " + c);
            count++;
        }

    }

    @Test
    public void copyOnWriteArrayList() throws InterruptedException {
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(a);
        Thread t = new Thread(() -> {
            int count = 0;
            while (true) {
                list.add(count++ + "");
            }
        });
        t.setDaemon(true);
        t.start();
        Thread.currentThread().sleep(3);
        for (String s : list) {
            System.out.println(list.hashCode());
            System.out.println(s);
        }
    }

    @Test
    public void roleList(){
        RoleList r = new RoleList();


    }


}
