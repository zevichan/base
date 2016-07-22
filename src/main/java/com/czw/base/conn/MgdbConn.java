package com.czw.base.conn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/** 
 * 
 * @author Zevi Chan
 * @date 2016年6月2日 下午5:01:28 
 * 
 */
public class MgdbConn {
	
	protected static Logger log = LoggerFactory.getLogger(MgdbConn.class);
	protected static MongoClient mClient = null;
	protected static MongoDatabase mDatabase = null;
	protected static MongoCollection<Document> mColl = null;
	static{
		log.info("start to connect mongodb:");
		mClient = new MongoClient();
		mDatabase = mClient.getDatabase("test");
	}
	public MgdbConn() {}
	/** 
	 * 连接mongodb
	 */
	public static void main(String[] args)throws Exception {
		//		insertData();	//插入数据
		//		searchData();
		//EnumSet<Enum<E>>.of(e)
		
//		Set<Integer> data = new HashSet<>();
		List<String> intList = Arrays.asList("fjweof");
		Object[] objects = new Object[10];
		objects[0] = intList;
		
		
		
		
		
		
		
	}
	
	public static void searchData() {
		//		mDatabase.getCollection("user");
		//		mColl.find("{}");
		
	}
	public static void insertData(){
		//1.集合
		if(mDatabase.getCollection("user") == null)
			mDatabase.createCollection("user");
		MongoCollection<Document> col = mDatabase.getCollection("user");
		for(int i = 0;i<10000;i++){
			Random r = new Random();
			Document doc1 = new Document("name","zhangsan").append("random", r.nextInt(11)).append("age", 11).append("address", "浙江杭州滨江");
			Document doc2 = new Document("name","lisi").append("random", r.nextInt(11)).append("age", 20).append("address", "浙江杭州西湖");
			List<Document> docs = new ArrayList<Document>();
			docs.add(doc1);
			docs.add(doc2);
			col.insertMany(docs);
		}
//		FindIterable<Document> fi = col.find();
//		Iterator<Document> it = fi.iterator();
//		while(it.hasNext()){
//			log.info("document: {}",it.next());
//		}
	}

}
