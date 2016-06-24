package com.czw.aop.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月21日 下午4:58:14 
 * 
 */
@Aspect
public class AudienceImprove {
	@Pointcut("execution(* com.czw.aop.act.Performance.perform(..))")
	public void perform(){}
	
	@Before("perform()")
	public void silenceCellPhones(){
		System.out.println("before active");
	}
	@After("perform()")
	public void after(){
		System.out.println("after active");
	}
	
	@AfterReturning("perform()")
	public void applause(){
		System.out.println("afterreturning active");
	}
	
	@AfterThrowing("perform()")
	public void demandRefund(){
		System.out.println("AfterThrowing active");
	}
	
	
}
