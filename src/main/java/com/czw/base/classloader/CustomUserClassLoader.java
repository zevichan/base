package com.czw.base.classloader;

import com.czw.util.ComUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author ZeviChen , 2017/3/22 0022 上午 10:38
 */
public class CustomUserClassLoader extends ClassLoader {
    
    String pkg = "com.czw.base.classloader";
    
    @Test
    public void test() {
        String p = "D:\\github\\base\\src\\main\\java\\com\\czw\\base\\classloader\\Pet.class";
        File f = new File(p);
        System.out.println(f.getName());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = getClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        Class clazz = defineClass(pkg+"."+name, classData, 0, classData.length);
        return clazz;
    }

    private byte[] getClassData(String name) {
        System.out.println("in - getClassData - " + name);
        try {
            System.out.println("out - getClassData - "+name);
            return FileUtils.readFileToByteArray(new File(classNameToPath(name)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String classNameToPath(String name) {
        String filepath = ComUtils.getFilePath(pkg, name, true) + ".class";
//        int i = filepath.lastIndexOf("\\");
//        char[] cs = filepath.toCharArray();
//        cs[i] = Character.MIN_VALUE;
//        String f = new String(cs);
        System.out.println("filePath : " + filepath);
        return filepath;
    }

}
