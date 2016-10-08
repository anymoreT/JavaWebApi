package com.auto;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;  
import org.apache.http.util.EntityUtils;  

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;

public class TestHttp {
     @Test
	 public void test_get_http(){
		  CloseableHttpClient httpclient = HttpClients.createDefault();
		    HttpGet httpGet = new HttpGet("https://drac-demo.dianrong.com/adminconsole/api/actor/11335844/investSummary");
		    try{
		        CloseableHttpResponse response = httpclient.execute(httpGet);
		        HttpEntity entity = response.getEntity();  
//		        System.out.println(EntityUtils.toString(entity));  
//		        System.out.println(response.toString());
//		        System.out.println(response.getStatusLine().getStatusCode());
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
		    
	 }
}
