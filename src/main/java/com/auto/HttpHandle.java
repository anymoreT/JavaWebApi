package com.auto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import  org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;



public class HttpHandle {
	 public CloseableHttpClient httpclient; 
	 public HttpGet httpGet;
	 public CloseableHttpResponse reponse;
	 public 	String response_str;
	 public 	Map response_map;
	 public HttpEntity repnse_entity;
	 public HttpRequestBase request;
	 private EntityBuilder builder; //Post, put请求的参数
	 private URIBuilder uriBuilder; //get, delete请求的参数
	 private String post_payLoad;
	 
	 
	 public HttpHandle(){
		 this.httpclient = HttpClients.createDefault();
	     this.httpGet = null;
	     this.response_str = null;
	     this.repnse_entity = null;
	     this.response_map = null;
	     this.request = null;
	     this.post_payLoad = null;
	     this.builder = EntityBuilder.create().setParameters(new ArrayList<NameValuePair>());
	 }
	 
	public  Map do_get(String url){
		this.request = new HttpGet(url);
		try {
		this.reponse = this.httpclient.execute(this.request);
		this.repnse_entity = this.reponse.getEntity();  
        this.response_str =  EntityUtils.toString(this.repnse_entity, "utf-8");
        EntityUtils.consume(this.repnse_entity);
        try {
        	ObjectMapper mapper = new ObjectMapper(); 
        	this.response_map = mapper.readValue(response_str, Map.class);
        		}
        catch (JsonParseException e) {
           e.printStackTrace();
        	}
        catch (JsonMappingException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
           e.printStackTrace();
       }
        
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return this.response_map;
	}
	
	public void set_post_playLoad(String payload){
		this.post_payLoad = payload;
	}
	
	public  void do_post(String url){
		this.request = new HttpPost(url);
		try{
			//this.reponse = this.httpclient.execute(this.request);
			this.request.setHeader("Content-Type", "application/json");
			HttpPost httPost = 	(HttpPost)this.request;
			httPost.setEntity(new StringEntity(this.post_payLoad));
	        CloseableHttpResponse response = httpclient.execute(httPost);
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
	

}
