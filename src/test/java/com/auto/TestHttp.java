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
import java.util.Map;
import org.testng.annotations.Test;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.testng.Assert;

import com.auto.HttpHandle;




public class TestHttp {
     @Test
	 public void test_get_http(){
    	  HttpHandle httpHandle =  new HttpHandle();
    	 String url = "https://drac-demo.dianrong.com/adminconsole/api/actor/11335844/investSummary";
    	  Map respnse_map = httpHandle.do_get(url);
    	  System.out.println(respnse_map);
//		  CloseableHttpClient httpclient = HttpClients.createDefault();
//		    HttpGet httpGet = new HttpGet("https://drac-demo.dianrong.com/adminconsole/api/actor/11335844/investSummary");
//		    try{
//		        CloseableHttpResponse response = httpclient.execute(httpGet);
//		        HttpEntity entity = response.getEntity();  
//		        String response_str =  EntityUtils.toString(entity, "utf-8");
//		        EntityUtils.consume(entity);
//		        
//		        try {
//		        	 ObjectMapper mapper = new ObjectMapper(); 
//		        	 Map m = mapper.readValue(response_str, Map.class);
//		        	 System.out.println(m.get("content"));
//		        	 
//		        }
//		        catch (JsonParseException e) {
//		            e.printStackTrace();
//		        } catch (JsonMappingException e) {
//		            e.printStackTrace();
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//
//		    }
//		    catch(Exception e){
//		    	e.printStackTrace();
//		    }
//		    
	 }
}
