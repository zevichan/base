package com.czw.base.classloader;

import com.czw.util.ComUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author ZeviChen , 2017/3/22 0022 上午 10:38
 */
public class CustomAppClassLoader extends ClassLoader {


    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = null;
        try {
            b = loadClassData(getClassName(name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return defineClass(name, b, 0, b.length);
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("start-custom loadClass "+name);
        if (name.startsWith("java.")) {
            return super.loadClass(name, false);
        }
        byte[] b = null;
        try {
            System.out.println("8");
            b = loadClassData(getClassName(name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("end-custom loadClass");
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String filepath) throws Exception {
        System.out.println("start-loadClassData "+filepath);
        int n = 0;
        filepath = ComUtils.getFilePath(filepath,".java",true);
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(filepath)));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((n = br.read()) != -1) {
            bos.write(n);
        }
        br.close();
        System.out.println("end-loadClassData");
        return bos.toByteArray();

    }

    /**
     * 获取类名及其路径
     *
     * @param name
     * @return
     */
    public String getClassName(String name) {
        return ComUtils.getFilePath("com.czw.base.classloader", name, true) + ".java";
    }

}
