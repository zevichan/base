package com.czw.spring.aop.act.impl;

import org.springframework.stereotype.Component;

import com.czw.spring.aop.act.Performance;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月21日 下午5:12:01 
 * 
 */
@Component
public class DancePerformance implements Performance {

	public void perform() {
		System.out.println("dancing pf");
	}

}
