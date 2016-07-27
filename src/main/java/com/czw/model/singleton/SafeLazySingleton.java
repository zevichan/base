package com.czw.model.singleton;

/**
 * @author Zevi Chan
 * @Date 2016-07-27 10:59:09
 */
public class SafeLazySingleton {
	private static SafeLazySingleton safeLazySingleton = null;
	
	private SafeLazySingleton(){}
	
	public static synchronized SafeLazySingleton getInstance(){
		if(safeLazySingleton == null)
			safeLazySingleton = new SafeLazySingleton();
		return safeLazySingleton;
	}
}
