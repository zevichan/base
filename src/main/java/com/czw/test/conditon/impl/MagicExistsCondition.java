package com.czw.test.conditon.impl;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月20日 下午2:13:22 
 * 
 */
public class MagicExistsCondition implements Condition{
	public boolean matches(ConditionContext ctxt, AnnotatedTypeMetadata metadata) {
		Environment env = ctxt.getEnvironment();
		for(String s:env.getActiveProfiles())
			System.out.println(s);
		return env.containsProperty("magic");
	}
}