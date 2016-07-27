package com.czw.elastic;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.junit.Ignore;
import org.junit.Test;

import com.czw.util.ESUtils;

/**
 * @author Zevi Chan
 * @Date 2016-07-26 18:03:25
 */
public class ESUpdate {

	@Ignore
	@Test
	public void updateTest() {
		Client client = ESUtils.initClient();
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index("people");
		updateRequest.type("person");
		updateRequest.id("1");
		try {
			updateRequest.doc(XContentFactory.jsonBuilder()
					.startObject()
					.field("gender", "male")
					.field("name", "张三")
					.field("age", 20).endObject());

			UpdateResponse response = client.update(updateRequest).get();
			System.out.println("结果：" + response.getGetResult().sourceAsString());
		} catch (InterruptedException | IOException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void preparedUpdateTest() {
		Client client = ESUtils.initClient();
		/*client.prepareUpdate("ttl", "doc", "1")
				.setScript(new Script("ctx._source.gender = \"male\"", ScriptService.ScriptType.INLINE, null, null))
				.get();*/
		
		try {
			UpdateResponse response = client.prepareUpdate("people", "person", "1")
				.setDoc(XContentFactory.jsonBuilder()
						.startObject()
						.field("_id",1)
						.field("gender", "male")
						.field("name", "张三")
						.field("age", 20)
						.endObject())
				.get();
			System.out.println("hahah");
			System.out.println("结果："+response.getGetResult().sourceAsString());
			System.out.println("hahah");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
