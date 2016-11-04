package com.auto;


import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.util.EntityUtils;  
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
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
import com.auto.JacksonUtil;
import org.apache.http.util.EntityUtils;
//import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;





public class TestHttp {
     @Test
	 public void test_get_http(){
    	  HttpHandle httpHandle =  new HttpHandle();
    	  String url = "https://drac-demo.dianrong.com/adminconsole/api/actor/11335844/investSummary";
  	      httpHandle.do_get(url);

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
    	      //  entity_post.setParameters(new NameValuePair("account", "yong.huang@dianrong.com"));
		       NameValuePair [] values = new NameValuePair[1];
		       values[0] = new BasicNameValuePair("account", "yong.huang@dianrong.com");
		       entity_post.setParameters(values);
		       // entity_post.setText("{ \"account\": \"yong.huang@dianrong.com\"}");
		      
		        
		        
		        
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
             //  httpPost.setEntity(new StringEntity("{ \"account\": \"yong.huang@dianrong.com\"}"));
               String jsonStr = "{ \"account\": \"yong.huang1@dianrong.com\"}";
              Map json_map =  JacksonUtil.readValue(jsonStr, HashMap.class);
              json_map.put("account", "yong.huang@dianrong.com");
              jsonStr = JacksonUtil.toJSon(json_map);
              httpPost.setEntity(new StringEntity(jsonStr));
              
              
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
         String jsonStr = "{ \"account\": \"yong.huang@dianrong.com\"}";
    	 HttpHandle httpHandle =  new HttpHandle();
    	 httpHandle.set_post_playLoad(jsonStr);
    	 httpHandle.do_post(url);
     }
      
   
     @Test
     public void  test_login_crm(){
  
    	 

    	 
    	 String url = "https://passport-demo.dianrong.com/login?service=https%3A%2F%2Fcrm-demo.dianrong.com%2Flogin%2Fcas";
    	 HttpHandle httpHandle =  new HttpHandle();
    	 httpHandle.do_get(url);
    	 HttpEntity entity = httpHandle.get_response_entity();
    	 try{
    		 String str =  EntityUtils.toString(entity, "utf-8");
    		 Document doc = Jsoup.parse(str);
    		 Elements lt_element = doc.select("input[name=lt]"); 
    		 Elements execution_element = doc.select("input[name=execution]"); 
    		 String lt_value = lt_element.attr("value");
    		 String execution_value = execution_element.attr("value");
    		// System.out.println(str); 
//    		 System.out.println(lt_value); 
//    		 System.out.println(execution_value); 
    		 
    	  	 Map json_map =  new HashMap();
        	 json_map.put("username", 1);
        	 json_map.put("password", true);
        	 json_map.put("lt", lt_value);
        	 json_map.put("execution", execution_value);
         	 json_map.put("domain", "https%3A%2F%2Fcrm-demo.dianrong.com%2Flogin%2Fcas");
        	 json_map.put("_eventId", "submit");
        	 json_map.put("submit", "登录");
        	 String jsonStr = JacksonUtil.toJSon(json_map);
        	 System.out.println(jsonStr);
        	 url = "https://passport-demo.dianrong.com/login?service=https%3A%2F%2Fcrm-demo.dianrong.com%2Flogin%2Fcas";
        	 httpHandle.do_post(url);
        	 httpHandle.set_post_playLoad(jsonStr);
        	 httpHandle.execute();
        	entity = httpHandle.get_response_entity();
        	Header[] headers = httpHandle.get_reponse_header();
        	for(Header header: headers){
        		System.out.println(header.toString());
        	}
//        	str =  EntityUtils.toString(entity, "utf-8");
//       	   System.out.println(str);
       	   
       	   
       	   
    	 }
    	 catch(Exception e){
    		 e.printStackTrace();
    	 }
     }
      
     
     
}
