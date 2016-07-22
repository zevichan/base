package com.czw.spring.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import com.czw.spring.aop.act.Encoreable;
import com.czw.spring.aop.act.impl.DefaultEncoreable;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月22日 下午1:38:56 
 * 
 */
@Aspect
public class EncoreableIntroducer {
	
	@DeclareParents(value="com.czw.aop.act.Performance+",defaultImpl=DefaultEncoreable.class)
	public static Encoreable encoreable;
	
}
