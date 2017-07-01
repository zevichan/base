package com.czw.server.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * 
 * @author ZeviChen
 * @Date 2016-09-14 17:15:32
 */
public class TomcatTest {
	public final static void main(String[] args) throws LifecycleException {

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(80);
		tomcat.start();
//		tomcat.stop();
	}
	
	
	
	
}
