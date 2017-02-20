package com.czw.spring.context;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZeviChen , 2017/2/16 0016 下午 5:25
 */
public interface BeanFactory {

    Object getBean(String name);
}
