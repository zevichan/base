package com.czw.spring.context;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZeviChen , 2017/2/16 0016 下午 12:48
 */
public class BeanDefinition {

    private final Map<String, Object> beans = new LinkedHashMap<>();

    public Map<String, Object> getBeans() {
        return beans;
    }
}
