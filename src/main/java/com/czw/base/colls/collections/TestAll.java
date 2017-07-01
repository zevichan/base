package com.czw.base.colls.collections;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by zevi on 2017/6/30.
 */
public class TestAll {


    @Test
    public void test1() {
        new HashMap<>();
        new TreeMap<>();

        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println(i1==i2);//输出false

    }


}
