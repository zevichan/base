package com.czw.model.singleton;

/**
 * @author Zevi Chan
 * @Date 2016-07-27 11:05:24
 */
public class VolatileSingleton {
	
	private volatile static VolatileSingleton volatileSingleton = null;
	
	private VolatileSingleton(){}
	
	public static VolatileSingleton getInstance(){
		if(volatileSingleton == null)
			synchronized (VolatileSingleton.class) {
				if(volatileSingleton == null)
					volatileSingleton = new VolatileSingleton();
			}
		return volatileSingleton;
	}
	
	
}
