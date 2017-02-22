package com.czw.spring.context;

import com.czw.jichu.jdbc.Bean;
import com.czw.util.ComUtils;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ZeviChen , 2017/2/16 0016 下午 12:44
 */
public class DefBeanFactory implements BeanFactory {


    //并发bean解析后的值
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Map<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public Object getBean(String name) {

        BeanDefinition obj = this.beanDefinitionMap.get(name);
        String beanClassPath = (String) obj.getBeanClass();

        Object beanObj = null;
        try {
            Class beanClass = Class.forName(beanClassPath);
            beanObj = beanClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return beanObj;
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

}
