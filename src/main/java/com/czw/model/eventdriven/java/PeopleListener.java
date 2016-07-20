package com.czw.model.eventdriven.java;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 行为监听总接口
 * @author Zevi Chan
 * @Date 2016-07-20 14:23:34
 */
public interface PeopleListener<E extends ApplicationEvent> extends ApplicationListener<ApplicationEvent> {
	
}
