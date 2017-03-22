package com.czw.base.classloader;

import org.relaxng.datatype.Datatype;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.util.HashMap;

/**
 * http://stackoverflow.com/questions/1771679/difference-between-threads-context-class-loader-and-normal-classloader
 *
 * spring.DefaultResouceLoader.getClassLoader()
 *
 * 可以从多个地方加载类，比如网络上，数据库中，甚至即时的编译源文件获得类文件；
 * 个性化后类加载器可以在运行时原则性的加载某个版本的类文件；
 * 个性化后类加载器可以动态卸载一些类；
 * 个性化后类加载器可以对类进行解密解压缩后再载入类。
 *
 * @author ZeviChen , 2017/3/21 0021 下午 5:02
 */
public class TestClassLoader1 {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
        ClassLoader classLoader1 = TestClassLoader1.class.getClassLoader();
        ThreadClassLoader tcl = new ThreadClassLoader();
        ClassLoader extClassLoader = DNSNameService.class.getClassLoader();

        tcl.start();
        Thread.sleep(100);
        
        ObjClassLoader ocl = new ObjClassLoader();
        Class cls = Class.forName(HashMap.class.getName());


        System.out.println("sun.boot.class.path:" + System.getProperty("sun.boot.class.path"));
        System.out.println("java.ext.dirs : " + System.getProperty("java.ext.dirs"));
        System.out.println("java.class.path : " + System.getProperty("java.class.path"));
        System.out.println("----------------------------------------------------");
        System.out.println("classpath loader : "+Datatype.class.getClassLoader());
        System.out.println("ext loader : "+extClassLoader);
        System.out.println("boot loader : "+extClassLoader.getParent());
        System.out.println("class loader : "+cls.getClassLoader());
        System.out.println("obj loader : "+ocl.getClassLoader());
        System.out.println("java loader : "+ String.class.getClassLoader());
        System.out.println("local loader : "+classLoader1);
        System.out.println("thread loader : "+tcl.getClassLoader());
        System.out.println("system loader : "+ClassLoader.getSystemClassLoader());
        System.out.println("----------------------------------------------------");
        System.out.println("current classloader init : ");
        TestClassLoader1.class.getClassLoader().loadClass("com.czw.base.classloader.TestObjInit");
        System.out.println("classforname classloader init : ");
        Class.forName("com.czw.base.classloader.TestObjInit");


    }
}

class TestObjInit{

    static{
        System.out.println("TestObjInit static init!");
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

class TestClass{
    private String password;
    public String username;
    public static int age;
    public final static String phone = "123456";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        TestClass.age = age;
    }

    public static String getPhone() {
        return phone;
    }
}
