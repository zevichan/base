package com.czw.spring.model.eventdriven.java.listenerimpl;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.czw.spring.model.eventdriven.java.MyEvent;
import com.czw.spring.model.eventdriven.java.PeopleListener;


/**
 * 老李家听到了这个消息
 * 
 * @author Zevi Chan
 * @Date 2016-07-20 14:40:49
 */
@Component
public class LaoliJiaListener implements PeopleListener<MyEvent> {
	
	public void onApplicationEvent(final ApplicationEvent event) {
		if(event instanceof MyEvent)
			System.out.println("老李家出动了：" + event.getSource() + ",datetime:"
				+ DateFormatUtils.format(event.getTimestamp(), "yyyy-MM-dd HH:mm:ss"));
		
	}
	
}
