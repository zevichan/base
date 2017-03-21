package com.czw.base.classloader;

import java.util.HashMap;

/**
 * @author ZeviChen , 2017/3/21 0021 下午 5:02
 */
public class TestClassLoader1 {

    public static void main(String[] args) throws InterruptedException {
        ClassLoader classLoader1 = TestClassLoader1.class.getClassLoader();
        ThreadClassLoader tcl = new ThreadClassLoader();
        tcl.start();
        Thread.sleep(100);
        
        ObjClassLoader ocl = new ObjClassLoader();
        
        
        System.out.println("obj loader : "+ocl.getClassLoader());        
        System.out.println("java loader : "+new HashMap<>().getClass().getClassLoader());
        System.out.println("local loader : "+classLoader1);
        System.out.println("thread loader : "+tcl.getClassLoader());
        System.out.println("system loader : "+ClassLoader.getSystemClassLoader());


    }
}

class ObjClassLoader {
    
    public ClassLoader getClassLoader(){
        return this.getClass().getClassLoader();
    }
    
}

class ThreadClassLoader extends Thread{

    private ClassLoader classLoader;

    @Override
    public void run() {
        classLoader = Thread.currentThread().getContextClassLoader();
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }
}
