package com.czw.base.test;

import org.junit.Test;

/**
 * @author ZeviChen , 2017/5/15 16:09
 */
public class SystemMethod {
    
    @Test
    public void test(){
        System.out.println(System.getProperty("port","8081"));
        System.out.println(System.getProperty("ssl"));
        
        
    }
    
}
