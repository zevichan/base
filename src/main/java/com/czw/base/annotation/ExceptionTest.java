package com.czw.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月14日 下午4:25:18 
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
	//@ExceptionTest(ArithmeticException.class)
	Class<? extends Exception> value();
	
	//@ExceptionTest({IndexOutOfBoundsException.class,NullPointerException.class})
//	Class<? extends Exception>[] value();
	
	
	
}
