package com.czw.base.classloader;

import java.io.Serializable;
import java.util.HashMap;

/**
 * http://stackoverflow.com/questions/15202997/what-is-the-difference-between-canonical-name-simple-name-and-class-name-in-jav/15203417#15203417
 *
 * @author ZeviChen , 2017/3/22 0022 下午 2:22
 */
public class ClassXXXName {

    public static void main(String[] args){
        //primitive
        System.out.println("基本类型：int");
        showClass(int.class);

        //class
        System.out.println("基本类型：String");
        showClass(String.class);

        //inner class
        System.out.println("内部类：HashMap.SimpleEntry");
        showClass(HashMap.SimpleEntry.class);

        //anonymous inner class
        System.out.println("匿名内部类：Serializable");
        showClass(new Serializable(){}.getClass());

        {
            //primitive Array
            System.out.println("基本类型数组：int[]");
            int demo[] = new int[5];
            Class<? extends int[]> clzz = demo.getClass();
            showClass(clzz);
        }

        {
            //Object Array
            System.out.println("基本类型包装类数组：Integer[]");
            Integer demo[] = new Integer[5];
            Class<? extends Integer[]> clzz = demo.getClass();
            showClass(clzz);
        }

    }

    private static void showClass(Class<?> c) {
        System.out.println("getName(): \t\t\t\t" + c.getName());
        System.out.println("getCanonicalName(): \t" + c.getCanonicalName());
        System.out.println("getSimpleName(): \t\t" + c.getSimpleName());
        System.out.println("toString(): \t\t\t" + c.toString());
        System.out.println();
    }

}
