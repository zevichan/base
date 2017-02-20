package com.czw.spring.context;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZeviChen , 2017/2/16 0016 下午 12:48
 */
public class BeanDefinition implements Cloneable {

    //此处指定class路径
    private String beanClass;
    private String beanName;

    //常量定义在ConfigurableBeanFactory中
    private String scope = "singleton";

    private boolean singleton = true;

    private boolean prototype = false;

    private boolean lazyInit = false;

    private String factoryBeanName;

    private String factoryMethodName;

    private String initMethodName;

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    private String destroyMethodName;


    public Object getBeanClass() {
        return beanClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getFactoryMethodName() {
        return factoryMethodName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "beanClass='" + beanClass + '\'' +
                ", beanName='" + beanName + '\'' +
                ", scope='" + scope + '\'' +
                ", singleton=" + singleton +
                ", prototype=" + prototype +
                ", lazyInit=" + lazyInit +
                ", factoryBeanName='" + factoryBeanName + '\'' +
                ", factoryMethodName='" + factoryMethodName + '\'' +
                ", initMethodName='" + initMethodName + '\'' +
                ", destroyMethodName='" + destroyMethodName + '\'' +
                '}';
    }
}
