package com.czw.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * CreateAnno	1.注解在运行时保留
 * 				2.在方法上声明
 * 				3.无参的静态方法
 * 
 * @author Zevi Chan
 * @date 2016年6月14日 下午3:44:39 
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CreateAnno {

}
