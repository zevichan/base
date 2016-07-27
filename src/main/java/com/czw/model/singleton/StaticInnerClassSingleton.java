package com.czw.model.singleton;

/**
 * @author Zevi Chan
 * @Date 2016-07-27 11:02:44
 */
public class StaticInnerClassSingleton {
	
	private static class SingletonHolder{
		private final static SingletonHolder singletonHolder = new SingletonHolder();
	}
	
	public final static SingletonHolder getInstance(){
		return SingletonHolder.singletonHolder;
	}
}
