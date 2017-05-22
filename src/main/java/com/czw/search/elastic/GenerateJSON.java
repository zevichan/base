//package com.czw.search.elastic;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.czw.search.elastic.entity.Person;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * @author Zevi Chan
// * @Date 2016-07-27 09:14:52
// */
//public class GenerateJSON {
//
//	public static String doItYouself(){
//
//		String json = "{" +
//		        "\"user\":\"kimchy\"," +
//		        "\"postDate\":\"2013-01-30\"," +
//		        "\"message\":\"trying out Elasticsearch\"" +
//		    "}";
//		return json;
//	}
//
////	public static Map<String,Object> usingMap(){
//		Map<String, Object> json = new HashMap<String, Object>();
//		json.put("user","kimchy");
//		json.put("postDate",new Date());
//		json.put("message","trying out Elasticsearch");
//
//		return json;
//	}
//
//	public static String serializeBean(){
//		Person p = new Person();
//		p.setId(1);
//		p.setName("张三");
//		p.setAge(20);
//		p.setEmail("857465231@qq.com");
//		p.setAddress("杭州滨江");
//
//		// instance a json mapper
//		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
//
//		// generate json
//		String json = "";
//		try {
//			json = mapper.writeValueAsString(p);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		return json;
//	}
//
//	public static String esHelpers(){
//		String json = "";
//		try {
//			XContentBuilder builder = XContentFactory.jsonBuilder()
//					.startObject()
//					.field("user", "kimchy")
//					.field("postDate", new Date())
//					.field("message", "trying out Elasticsearch")
//					.endObject();
//			json = builder.string();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		return json;
//	}
//
//
//
//}
