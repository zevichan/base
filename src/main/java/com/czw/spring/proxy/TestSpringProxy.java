package com.czw.spring.proxy;

import com.czw.util.ComUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Created by zevi on 2017/7/14.
 */
public class TestSpringProxy {


    @Test
    public void proxyFactoryBean() {

        String path = ComUtils.getFilePath("com.czw.spring.proxy", "spring.xml", true);
        System.out.println("path: " + path);
        ApplicationContext app = new FileSystemXmlApplicationContext("file:" + path);
        
        System.out.println("\n--------------------ProxyFactoryBean手动代理配置------------------------------\n");

        IUserInfoService IUserInfoService = (IUserInfoService) app.getBean("customUserInfoLogProxy");
        IUserInfoService.getUsername("1111");

        System.out.println("\n--------------------BeanNameAutoProxyCreator自动代理------------------------------\n");

        IGoodsService goodsService = (IGoodsService) app.getBean("goodsService");
        goodsService.getGoodsInfo("2222");

        System.out.println("\n--------------------DefaultAdvisorAutoProxyCreator自动代理------------------------------\n");

        IMerchantService merchantService = (IMerchantService) app.getBean("merchantService");
        merchantService.getMerchant("3333");


    }


}
