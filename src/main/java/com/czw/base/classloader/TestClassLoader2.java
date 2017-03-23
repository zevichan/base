package com.czw.base.classloader;

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author ZeviChen , 2017/3/22 0022 下午 4:47
 */
public class TestClassLoader2 {

    @Test
    @Ignore
    public void customClassLoader() throws ClassNotFoundException {
        CustomUserClassLoader loader = new CustomUserClassLoader();
        ContextLoaderThread2 runner = new ContextLoaderThread2();
        runner.setClassLoader(loader);
        runner.start();
    }

    @Test
    public void customClassLoader2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        CustomUserClassLoader cucl = new CustomUserClassLoader();
        Class clazz = cucl.findClass("Pet");
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.newInstance().toString());
        
    }


}

class ContextLoaderThread2 extends Thread {

    private CustomUserClassLoader classLoader;

    @Override
    public void run() {
        Class cls = null;
        try {
            cls = classLoader.findClass("Pet");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("thread class loader : " + cls.getClassLoader());
    }

    public void setClassLoader(CustomUserClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
