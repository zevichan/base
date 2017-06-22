package com.czw.base.classloader;

import org.eclipse.jetty.webapp.WebAppClassLoader;
import sun.misc.Launcher;

/**
 * @author ZeviChen , 2017/6/22 09:55
 */
public class TestClassLoader3 {

    public static void main(String[] args) {
        System.out.println("bootstrap path : " + Launcher.getBootstrapClassPath());

    }


}

class Test3ClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
