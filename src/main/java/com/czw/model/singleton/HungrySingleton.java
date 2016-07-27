package com.czw.model.singleton;

/**
 * 
 * @author Zevi Chan
 * @Date 2016-07-27 10:59:22
 */
public class HungrySingleton {
	
	private final static HungrySingleton hungrySingleton = new HungrySingleton();
	
	private HungrySingleton(){}
	
	public static HungrySingleton getInstance(){
		return hungrySingleton;
	}
	
	
	
	
	
	
}
