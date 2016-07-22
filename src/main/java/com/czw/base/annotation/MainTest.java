package com.czw.base.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 一般注解的定义是特定的程序员完成的工作，大部分程序员都只需要做个了结
 * 除非你做框架等开发需要用到注解
 * 
 * @author Zevi Chan
 * @date 2016年6月14日 下午3:52:49 
 * 
 */
public class MainTest {
	private static Logger log = LoggerFactory.getLogger(MainTest.class);
	
	
	
	/**
	 * 查看自定义的注解哪些可用，哪些失败，哪些忽略,捕捉异常注解
	 * @param     
	 * @return void    
	 * @throws
	 */
	@Test
//	@Ignore
	public void createAnno(){
		int tests = 0;
		int passedCreateAnno = 0;
		int passedExceptionTest = 0;
		
		for(Method m : Sample.class.getDeclaredMethods()){
			if(m.isAnnotationPresent(CreateAnno.class)){
				tests++;
				try {
					m.invoke(null);
					passedCreateAnno++;
				} catch (InvocationTargetException ite) {
					Throwable t = ite.getCause();
					Class<? extends Exception> excType = 
							m.getAnnotation(ExceptionTest.class).value();
					if(excType.isInstance(t)){
						passedExceptionTest++;
					}else{
						log.info("method name[{}]\n\texceptionTest : failed.expected={} - {} "
								+ ",passedExceptionTest={}",m.getName(),excType.getName(),t,passedExceptionTest);
					}
					
					
					log.info(m+" failed: "+t);
				} catch (Exception e) {
				
					log.info("method name[{}]\n\tcreateAnno : invalid @CreateAnno : {}",m.getName(),m);
				}
			}
			log.info("method name[{}]\n\tcreateAnno : passedCreateAnno = {} ,Failed = {}",
					m.getName(),passedCreateAnno,tests-passedCreateAnno); 
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
