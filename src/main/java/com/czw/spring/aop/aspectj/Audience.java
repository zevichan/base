package com.czw.spring.aop.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月21日 下午4:58:14 
 * 
 */
//@Aspect
public class Audience {
	
	@Before("execution(** com.czw.act.Performance.perform(..))")
	public void silenceCellPhones(){
		System.out.println("before Silencing cell phones");
	}
	@After("execution(** com.czw.act.Performance.perform(..))")
	public void after(){
		System.out.println("after ");
	}
	
	@AfterReturning("execution(** com.czw.act.Performance.perform(..))")
	public void applause(){
		System.out.println("afterreturning CLAP CLAP CLAP");
	}
	
	@AfterThrowing("execution(** com.czw.act.Performance.perform(..))")
	public void demandRefund(){
		System.out.println("AfterThrowing demanding a refund");
	}
	
	
}
