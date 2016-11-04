package com.auto;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.Header;
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
	 private  String request_type;
	 
	 
	 public HttpHandle(){
		 this.httpclient = HttpClients.createDefault();
	     this.httpGet = null;
	     this.response_str = null;
	     this.repnse_entity = null;
	     this.response_map = null;
	     this.request = null;
	     this.post_payLoad = null;
	     this.request_type = null;
	     this.builder = EntityBuilder.create().setParameters(new ArrayList<NameValuePair>());
	 }
	 
	public  void do_get(String url){
		this.request_type = "get";
		this.request = new HttpGet(url);
		try {
			this.reponse = this.httpclient.execute(this.request);
//			this.repnse_entity = this.reponse.getEntity();  
//			this.response_str =  EntityUtils.toString(this.repnse_entity, "utf-8");
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	public HttpEntity get_response_entity(){
		return  this.reponse.getEntity();  
	}
	
	public  Header[] get_reponse_header(){
		  return  this.reponse.getAllHeaders();
	}
	
	public void set_post_playLoad(String payload){
		if (this.request_type.equals("post")){
			this.post_payLoad = payload;
			try{
				((HttpPost)this.request).setEntity(new StringEntity(this.post_payLoad));
			}
			catch(Exception e){
				e.printStackTrace();
		    }
		}
	}
	
   public void execute(){
		try{
			this.request.setHeader("Content-Type", "application/json");
			this.reponse = httpclient.execute(((HttpPost)this.request));
		}
		catch(Exception e){
			e.printStackTrace();
		}
   }
	
	public  void do_post(String url){
		this.request_type = "post";
		this.request = new HttpPost(url);
	
		
	}
	

}
