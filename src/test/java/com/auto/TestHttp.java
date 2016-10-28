package com.auto;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;  
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.protocol.HTTP;

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

//import net.sf.json.JSONObject;




public class TestHttp {
     @Test
	 public void test_get_http(){
    	  HttpHandle httpHandle =  new HttpHandle();
    	  String url = "https://drac-demo.dianrong.com/adminconsole/api/actor/11335844/investSummary";
    	  Map respnse_map = httpHandle.do_get(url);
    	  System.out.println(respnse_map);    
	 }
     
     @Test
     public void test_post(){
    	 	CloseableHttpClient httpclient = HttpClients.createDefault();
    	 	HttpPost httpPost = new HttpPost("http://uniauth-demo.sl.com/ws/rs/user/usedetailinfo");
		    try{
		        List params=new ArrayList();
		        params.add(new BasicNameValuePair("account", "yong.huang@dianrong.com"));
		        EntityBuilder entity_post =  EntityBuilder.create().setParameters(new ArrayList<NameValuePair>());
		        entity_post.setContentType(ContentType.APPLICATION_JSON);
//		        entity_post.setParameters(new BasicNameValuePair("account", "yong.huang@dianrong.com"));
		        entity_post.setText("{ \"account\": \"yong.huang@dianrong.com\"}");
		      
		        httpPost.setEntity(entity_post.build());
		        
		        
		        CloseableHttpResponse response = httpclient.execute(httpPost);
		        HttpEntity entity = response.getEntity();  
		        String response_str =  EntityUtils.toString(entity, "utf-8");
		        System.out.print(response.getStatusLine());
		        System.out.println(response_str);
		        EntityUtils.consume(entity);
		     
		    }
		   catch(Exception e){
			   e.printStackTrace();
		        	
		        }
     }
     
     
     @Test
     public void test_post1(){
    	 	CloseableHttpClient httpclient = HttpClients.createDefault();
    	 	HttpPost httpPost = new HttpPost("http://uniauth-demo.sl.com/ws/rs/user/usedetailinfo");
		    try{
		    	httpPost.setHeader("Content-Type", "application/json");
//		        List params=new ArrayList();
//		        params.add(new BasicNameValuePair("account", "yong.huang@dianrong.com"));
               httpPost.setEntity(new StringEntity("{ \"account\": \"yong.huang@dianrong.com\"}"));
		
		        
		        CloseableHttpResponse response = httpclient.execute(httpPost);
		        HttpEntity entity = response.getEntity();  
		        String response_str =  EntityUtils.toString(entity, "utf-8");
		        System.out.print(response.getStatusLine());
		        System.out.println(response_str);
		        EntityUtils.consume(entity);
		     
		    }
		   catch(Exception e){
			   e.printStackTrace();
		        	
		        }
     }
     
     
     
     @Test
     public void  test_httpUtil(){
    	 String url = "https://drac-demo.dianrong.com/adminconsole/api/actor/11335844/investSummary";

    	 HttpUtils http = HttpUtils.get(url);
    	// http.addParameter("wd", "java 核心技术"); //搜索关键字
    	http.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
    	
    	// http.setProxy("10.10.12.62", 3128); //设置代理
    	 ResponseWrap response = http.execute(); //执行请求
    	 System.out.println(response.getString()); //输出内容
    	 
     }
     
     
     @Test
     public void  test_httpUtil_post(){
    	 String url = "http://uniauth-demo.sl.com/ws/rs/user/usedetailinfo";
    	 HttpUtils http = HttpUtils.post(url);
    	// http.addParameter("wd", "java 核心技术"); //搜索关键字
    	// http.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
        http.addHeader("Content-Type", "application/json");
        String json = "{ \"account\": \"yong.huang@dianrong.com\"}";
        ObjectMapper mapper = new ObjectMapper();  
     //   Map json_map =  mapper.readValue(json, Map.class);  
        
        http.setParameter(json);

    	 // http.setProxy("10.10.12.62", 3128); //设置代理
    	 ResponseWrap response = http.execute(); //执行请求
    	 System.out.println(response.getString()); //输出内容
    	 response.transferTo("./post_json.txt"); //输出到文件
    	 http.shutdown();
     }
      
     
     
     
}
