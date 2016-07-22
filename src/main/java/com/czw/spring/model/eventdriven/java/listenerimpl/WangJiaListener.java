package com.czw.spring.model.eventdriven.java.listenerimpl;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.czw.spring.model.eventdriven.java.MyEvent;
import com.czw.spring.model.eventdriven.java.PeopleListener;


/**
 * 王家也听到了这个劲爆消息
 * 
 * @author Zevi Chan
 * @Date 2016-07-20 14:42:45
 */
@Component
public class WangJiaListener implements PeopleListener<MyEvent> {

	public void onApplicationEvent(final ApplicationEvent event) {
		if(event instanceof MyEvent)
			System.out.println("老李家出动了：" + event.getSource() + ",datetime:"
				+ DateFormatUtils.format(event.getTimestamp(), "yyyy-MM-dd HH:mm:ss"));
	}

}
