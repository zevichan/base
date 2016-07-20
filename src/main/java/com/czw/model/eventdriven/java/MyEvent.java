package com.czw.model.eventdriven.java;

import org.springframework.context.ApplicationEvent;

/**
 * @author Zevi Chan
 * @Date 2016-07-20 14:15:00
 */
@SuppressWarnings("serial")
public class MyEvent extends ApplicationEvent  {
	
	public MyEvent(final String source) {
		super(source);
	}

}
