package com.czw.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.czw.test.bean.CompactDisc;
import com.czw.test.bean.impl.SgtPeppers;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月20日 下午1:11:38 
 * 
 */
@Configuration
//@Profile("test")
public class CDPlayerConfig {
	
	@Bean
	public CompactDisc sgtPeppers(){
		return new SgtPeppers();
	}
	
}
