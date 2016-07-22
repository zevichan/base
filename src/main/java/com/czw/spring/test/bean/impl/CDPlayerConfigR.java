package com.czw.spring.test.bean.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.czw.spring.test.bean.CompactDisc;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月17日 下午3:35:27 
 * 
 */
@Configuration
//@ComponentScan
public class CDPlayerConfigR {
	//java显式配置方式
	@Bean
	public CompactDisc sgtPeppers(){
		return new SgtPeppers();
	}
}
