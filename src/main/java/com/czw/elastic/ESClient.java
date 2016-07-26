package com.czw.elastic;

import java.net.InetAddress;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.Test;

/**
 * @author Zevi Chan
 * @Date 2016-07-26 14:05:36
 */
public class ESClient {

	@Test
	public void test() throws Exception {
		Settings settings = Settings.settingsBuilder()
		        .put("cluster.name", "zevichan").put("client.transport.sniff", true).build();
		Client client = TransportClient.builder().settings(settings).build()
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

		GetResponse response;
		response = client.prepareGet("megacorp","employee","1").execute().actionGet();
		System.out.println();
		System.out.println("索引："+response.getIndex()+"/"+response.getType()+"/"+response.getId()+",结果:"+response.getSourceAsString());
		System.out.println();
		client.close();
	}

}
