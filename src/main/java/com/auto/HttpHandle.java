package com.auto;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import  org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class HttpHandle {
	 public CloseableHttpClient httpclient; 
	 public HttpGet httpGet;
	 public CloseableHttpResponse reponse;
	 public 	String response_str;
	 public 	Map response_map;
	 public HttpEntity repnse_entity;
	 
	 
	 public HttpHandle(){
		 this.httpclient = HttpClients.createDefault();
	     this.httpGet = null;
	     this.response_str = null;
	     this.repnse_entity = null;
	     this.response_map = null;
	 }
	public  Map do_get(String url){
		this.httpGet = new HttpGet(url);
		try {
		this.reponse = this.httpclient.execute(this.httpGet);
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
	

}
