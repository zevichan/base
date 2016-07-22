package com.czw.base.enu;

/**
 * 
 * @author Zevi Chan
 * @Date 2016-07-05 18:28:40
 */
public enum EnumOrdinal {
	ONE,FOUR,TWO,THREE,FIVE;
	
	/**
	 * 所有的枚举都有个默认的ordinal()方法
	 */
	public int numberOfEnum(){
		return ordinal();
	}
}
enum EnumOrdinal1{
	ONE(10),
	TWO(11),
	THREE(12),
	FOUR(13),
	FIVE(14);
	
	private int num;
	private EnumOrdinal1(int num) {this.num = num;}
	public int getNum() {
		return num;
	}
	public int numberOfEnum(){
		return ordinal();
	}
	
}