package com.czw.spring.context;

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

    public Object getBean(String name){
//        String beanName = BeanFactoryUtils.transformedBeanName(name);
//        Object bean = this.beans.get(beanName);
//
//        if (bean == null) {
//            throw new NoSuchBeanDefinitionException(beanName,
//                    "Defined beans are [" + StringUtils.collectionToCommaDelimitedString(this.beans.keySet()) + "]");
//        }
//
//        // Don't let calling code try to dereference the
//        // bean factory if the bean isn't a factory
//        if (BeanFactoryUtils.isFactoryDereference(name) && !(bean instanceof FactoryBean)) {
//            throw new BeanIsNotAFactoryException(beanName, bean.getClass());
//        }
//
//        if (bean instanceof FactoryBean && !BeanFactoryUtils.isFactoryDereference(name)) {
//            try {
//                return ((FactoryBean) bean).getObject();
//            }
//            catch (Exception ex) {
//                throw new BeanCreationException(beanName, "FactoryBean threw exception on object creation", ex);
//            }
//        }
//        else {
//            return bean;
//        }

        return this.beanDefinitionMap.get(name);
    }

    public void registerBeanDefinition(String beanName,BeanDefinition beanDefinition){
        this.beanDefinitionMap.put(beanName,beanDefinition);
    }

}
