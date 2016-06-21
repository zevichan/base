package com.czw.test.bean.impl;

import org.springframework.stereotype.Component;

import com.czw.test.bean.CompactDisc;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月17日 下午3:18:00 
 * 
 */
@Component
public class SgtPeppers implements CompactDisc {

	/** 
	 *  
	 */
	public void play() {
		System.out.println("run play");
	}

}
