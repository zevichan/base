package com.czw.spring.demo1;


import com.czw.util.ComUtils;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author ZeviChen , 2017/2/16 0016 上午 11:19
 */
public class FsacDemo1 {

    @Test
    public void test(){
        String path = ComUtils.getFilePath("com.czw.spring.demo1","demo1.xml",true);
        FileSystemXmlApplicationContext fsac = new FileSystemXmlApplicationContext(path);
        Thing1 t1 = (Thing1) fsac.getBean("thing1");
        t1.method1();
    }
}
