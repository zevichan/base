package com.czw.protocol.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZeviChen , 2017/3/27 0027 下午 3:48
 */
public class BarterHttpClient {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpHead httpHead;

    @Test
    public void barter() throws IOException {
        HttpPost httppost = new HttpPost("http://test.shopwall.cn/wxpay/wxcallback");
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("LoginName", "admin"));
        formparams.add(new BasicNameValuePair("passWord", "123456"));
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);


        System.out.println("executing request " + httppost.getURI());
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            System.out.println("--------------------------------------");
            System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
            System.out.println("--------------------------------------");
        }
        response.close();
        httpclient.close();

    }

    @Before
    public void login() throws UnsupportedEncodingException {
        HttpPost httppost = new HttpPost("http://test.shopwall.cn/wxpay/user/login");
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("LoginName", "lklklk"));
        formparams.add(new BasicNameValuePair("passWord", "lklklk"));
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);


    }


}
