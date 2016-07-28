package com.czw.elastic;

import static com.czw.util.ESUtils.initClient;
import static com.czw.util.ESUtils.printResponse;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Ignore;
import org.junit.Test;

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
		
		printResponse(client.prepareGet("goods", "info", "1").setOperationThreaded(true).get());
	}
	
	/**
	 * 多index请求
	 */
	@Test
	@Ignore
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
			    	printResponse(response);
			    }
			}
		
		
	}
	
	/**
	 * Deprecated 2.1.0
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void countTest(){
		Client c = initClient();
		CountResponse response = c.prepareCount("goods")
		        .setQuery(QueryBuilders.termQuery("_type", "info"))
		        .execute()
		        .actionGet();
			System.out.println(response == null ? "null":"count : "+response.contextSize());
	}
	

}
