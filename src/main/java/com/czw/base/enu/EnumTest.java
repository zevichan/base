package com.czw.base.enu;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月13日 下午5:38:36 
 * 
 */
public enum EnumTest {
	MON(1,"monday"),
	TUE(2,"tuesday"),
	WED(3,"wednesday"),
	THU(4,"thursday"),
	FRI(5,"friday"),
	SAT(6,"saturday"),
	SUN(7,"sunday");
	
	private final int signum;
	private final String name;
	EnumTest(int signum,String name){
		this.signum = signum;
		this.name = name;
	}
	public int getSignum(){
		return signum;
	}
	public String getName(){
		return name;
	}
	
}
/**
 * 实现接口类,改造枚举
 * 
 * @author Zevi Chan
 * @date 2016年6月14日 下午1:43:42 
 *
 */
enum EnumOperation implements Operation{
	PLUS("+"){
		public double apply(double x,double y){return x+y;}
	},
	MINUS("-"){
		public double apply(double x,double y){return x-y;}
	},
	TIMES("*"){
		public double apply(double x,double y){return x*y;}
	},
	DIVIDE("/"){
		public double apply(double x,double y){return x/y;}
	};
	
	private final String symbol;
	private EnumOperation(String symbol) {
		this.symbol = symbol;
	}
	public String getSymbol() {
		return symbol;
	}
	@Override
	public String toString() {
		return symbol;
	}
	
	//实现接口就不需要再枚举类中定义抽象方法
//	abstract double apply(double x,double y);
	
	
	public static EnumOperation inverse(EnumOperation op){
		switch(op){
		case PLUS: return EnumOperation.PLUS;
		case MINUS: return EnumOperation.MINUS;
		case TIMES: return EnumOperation.TIMES;
		case DIVIDE: return EnumOperation.DIVIDE;
		default:throw new AssertionError("Unknown op:"+op);
		}
	}
	
	
}

interface Operation{
	double apply(double x,double y);
}
