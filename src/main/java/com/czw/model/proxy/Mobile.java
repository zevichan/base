package com.czw.model.proxy;

import java.io.Serializable;

/**
 *
 * @author ZeviChen
 * @Date 2016-09-10 15:57:21
 */
public interface Mobile extends Serializable {

	/**
	 * 充电
	 */
	void charge();

	/**
	 * 接电话
	 */
	void answer();
	
}
