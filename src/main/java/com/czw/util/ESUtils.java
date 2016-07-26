package com.czw.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author Zevi Chan
 * @Date 2016-07-26 17:41:20
 */
public class ESUtils {
	private static Settings settings;
	private static Client client;
	private static GetRequest getRequest;
	private static GetResponse getResponse;
	private static InetSocketTransportAddress inetSocketTransportAddress;
	
	public static void initSettings(){
		settings = Settings.settingsBuilder().put("cluster.name", "zevichan")
				.put("client.transport.sniff", true).build();
	}
	
	public static Client initClient(){
		initSettings();
		try {
			client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
