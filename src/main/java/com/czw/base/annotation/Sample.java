package com.czw.base.annotation;

/** 
 * 定义的CreateAnno注解只能用于静态的方法，不能处理异常和实例化方法
 * 
 * @author Zevi Chan
 * @date 2016年6月14日 下午3:49:38 
 * 
 */
public class Sample {
	
	@CreateAnno
	public static void m1(){}		//passed
	
	public static void m2(){}		//MainTest中的处理会忽略这个方法
	
	@CreateAnno
	public static void m3(){		//fail
		throw new RuntimeException("m2");
	}
	
	public static void m4(){}		//忽略
	
	@CreateAnno
	public void m5(){}				//无效
	
	public static void m6(){}		//忽略
	
	@CreateAnno
	public static void m7(){
		throw new RuntimeException("m7");		//fail
	}
	
	public static void m8(){}		//忽略
	
	@ExceptionTest(ArithmeticException.class)
	public static void m9(){
		int i = 0;
		i = i/i;
	}
	
	@ExceptionTest(ArithmeticException.class)
	public static void m10(){}
	
	
	
	
}
