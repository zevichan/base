package com.czw.elastic;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.Client;
import org.junit.Ignore;
import org.junit.Test;
import static com.czw.util.ESUtils.*;

/**
 * @author Zevi Chan
 * @Date 2016-07-26 17:40:48
 */
public class ESGet {

	@Test
	@Ignore
	public void getTest() {
		Client client = initClient();
		// System.out.println(client.prepareGet("megacorp","employee","2").get().getSourceAsString());
		//另起线程执行
		//		System.out.println(
		//				client.prepareGet("megacorp", "employee", "2").setOperationThreaded(true).get().getSourceAsString());
		
		printGetResponse(client.prepareGet("people", "person", "1").setOperationThreaded(true).get());
	}
	
	
	@Test
	public void mutiGetTest(){
		Client client = initClient();
		MultiGetResponse multiGetItemResponses = client.prepareMultiGet()
			    .add("megacorp", "employee", "1")           
			    .add("megacorp", "employee", "2", "3") 
			    .add("people", "person", "1")          
			    .get();

			for (MultiGetItemResponse itemResponse : multiGetItemResponses) { 
			    GetResponse response = itemResponse.getResponse();
			    if (response.isExists()) {                      
			    	printGetResponse(response);
			    }
			}
		
		
	}
	

}
