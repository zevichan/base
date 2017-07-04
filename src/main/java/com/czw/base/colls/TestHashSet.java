package com.czw.base.colls;

import com.czw.base.colls.tcollection.Car;
import org.testng.annotations.Test;

import java.util.*;

/**
 * @author ZeviChen , 2017/6/26 09:09
 */
public class TestHashSet {


    @Test
    public void test1() {
        Set<Car> s = new HashSet<>();
        s.add(new Car("aaa"));
        s.add(new Car("bbb"));

        List<Car> l = new LinkedList<>();
        l.add(new Car("aaa"));
        l.add(new Car("ccc"));

        s.addAll(l);
        System.out.println("size : " + s.size());
        Iterator<Car> it = s.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("----------foreach-------------------");
    }

}
