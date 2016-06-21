package com.czw.test.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.czw.test.conditon.impl.MagicExistsCondition;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月17日 下午5:11:46 
 * 
 */
@Component
public class CDPlayer {
	public CompactDisc compactDisc;
	
	public CDPlayer(CompactDisc compactDisc) {
		this.compactDisc = compactDisc;
	}
	
	@Bean
	public CDPlayer cdPlayer(CompactDisc compactDisc){
		return new CDPlayer(compactDisc);
	}
	
	@Conditional(MagicExistsCondition.class)
	@Bean
	public MagicBean magicBean(){
		return new MagicBean();
	}

}
