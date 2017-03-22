package com.czw.base.classloader;

/**
 * @author ZeviChen , 2017/3/22 0022 下午 4:49
 */
public class Pet {

    static{
        System.out.println("init");
    }

    {
        System.out.println("{}");
    }

    public Pet(){
        System.out.println("constructor");
    }


}
