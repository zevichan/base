package com.czw.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateResponse;
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
	private static StringBuilder sb = new StringBuilder();
	
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
	
	/**
	 * 打印GetResponse结果
	 * @param response
	 */
	public static void printGetResponse(GetResponse response){
		if(response == null)
			System.out.println("\nGetResponse is null !");
		else
			System.out.println("\nURL:"+getResURL(response)+",结果:"+response.getSourceAsString());
	}
	/**
	 * 打印GetResponse结果
	 * @param response
	 */
	public static void printUpdateResponse(UpdateResponse response){
		if(response == null || response.getGetResult() == null)
			System.out.println("\nUpdateResponse or getresult is null !");
		else
			System.out.println("\nURL:"+updateResURL(response)+",结果:"+response.getGetResult().sourceAsString());
	}
	
	public static String getResURL(GetResponse response){
		return spliceURL(response.getIndex())
					.append("/")
					.append(response.getType())
					.append("/")
					.append(response.getId()).toString();
	}
	public static String updateResURL(UpdateResponse response){
		return spliceURL(response.getIndex())
				.append("/")
				.append(response.getType())
				.append("/")
				.append(response.getId()).toString();
	}
	public static StringBuilder spliceURL(String str){
		sb.delete(0, sb.length());
		return sb.append(str);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
