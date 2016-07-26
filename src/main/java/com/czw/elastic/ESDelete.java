package com.czw.elastic;

import org.elasticsearch.client.Client;
import org.junit.Test;

import com.czw.util.ESUtils;

/**
 * @author Zevi Chan
 * @Date 2016-07-26 18:00:59
 */
public class ESDelete {
	
	
	@Test
	public void deleteTest(){
		Client client = ESUtils.initClient();
//		DeleteResponse deleteResponse = client.prepareDelete("megacorp", "employee", "2").get();
		
		
	}
	
	
	
}
