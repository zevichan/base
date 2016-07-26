package com.czw.elastic;

import org.elasticsearch.client.Client;
import org.junit.Test;

import com.czw.util.ESUtils;

/**
 * @author Zevi Chan
 * @Date 2016-07-26 17:40:48
 */
public class ESGet {

	@Test
	public void getTest() {
		Client client = ESUtils.initClient();
		// System.out.println(client.prepareGet("megacorp","employee","2").get().getSourceAsString());
		//另起线程执行
		System.out.println(
				client.prepareGet("megacorp", "employee", "2").setOperationThreaded(true).get().getSourceAsString());

	}

}
