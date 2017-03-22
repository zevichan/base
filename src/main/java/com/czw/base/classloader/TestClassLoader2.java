package com.czw.base.classloader;

import org.junit.Test;

/**
 * @author ZeviChen , 2017/3/22 0022 下午 4:47
 */
public class TestClassLoader2 {

    @Test
    public void customClassLoader() throws ClassNotFoundException {
        CustomAppClassLoader loader = new CustomAppClassLoader();
        ContextLoaderThread2 runner = new ContextLoaderThread2();
        runner.setClassLoader(loader);
        System.out.println("1");
        runner.start();
        System.out.println("2");
    }
}

class ContextLoaderThread2 extends Thread {

    private ClassLoader classLoader;

    @Override
    public void run() {
        System.out.println("3");
        Class cls = null;
        try {
            System.out.println("6");
            cls = classLoader.loadClass("com.czw.base.classloader.Pet");
            System.out.println("7");
        } catch (ClassNotFoundException e) {
            System.out.println("5"+e.getMessage());
        }
        System.out.println("thread class loader : " + cls);
        System.out.println("4");
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
