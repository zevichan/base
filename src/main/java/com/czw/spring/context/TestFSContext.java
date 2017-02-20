package com.czw.spring.context;

import com.czw.util.ComUtils;
import org.junit.Test;

/**
 * @author ZeviChen , 2017/2/16 0016 下午 5:18
 */
public class TestFSContext {

    @Test
    public void test1(){
        String path = ComUtils.getFilePath("com.czw.spring.context","FSContext.xml",true);
        FSContext fsContext = new FSContext(path);

//        Merchant merchant = (Merchant) fsContext.getBean("merchant");
//        merchant.getMerchantName();

        

    }
}
