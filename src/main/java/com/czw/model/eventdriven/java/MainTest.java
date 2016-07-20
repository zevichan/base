package com.czw.model.eventdriven.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Zevi Chan
 * @Date 2016-07-20 15:06:53
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.czw.model.eventdriven")
//@ImportResource("classpath:spring-eventdriven.xml")
@ContextConfiguration(locations={"classpath*:spring-eventdriven.xml"}) 
//@ContextConfiguration(classes={CDPlayer.class,CDPlayerConfig.class})
//@ActiveProfiles({"magic"})
public class MainTest {
	
	@Autowired(required=true)
	private ApplicationContext  applicationContext;
	@Before
	public void init(){
		System.out.println("init");
	}
	
	@Test
	public void test(){
		applicationContext.publishEvent(new MyEvent("哈哈，售你麻痹!"));
	}
	@After
	public void end(){
		System.out.println("end");
	}
	
	
	
	
}
