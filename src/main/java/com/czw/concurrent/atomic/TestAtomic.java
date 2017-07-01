package com.czw.concurrent.atomic;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zevi on 2017/7/1.
 */
public class TestAtomic {


    @Test
    public void atomicPrimitiveType(){
        new AtomicInteger();
        new AtomicLong();


    }


}
