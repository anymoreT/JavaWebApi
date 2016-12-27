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
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.client.config.RequestConfig.Builder;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.BasicHttpContext;

import java.security.cert.CertificateException;  
import java.security.cert.X509Certificate;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.X509TrustManager;  


import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;



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
	 
	 private HttpClientContext context;//保存上下文
	 private String post_payLoad;
	 private  String request_type;
	 private Builder  http_config;
	 
	 
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
	     this.http_config  = RequestConfig.custom();
	     HttpContext localContext = new BasicHttpContext();
	     this.context = HttpClientContext.adapt(localContext);
	 
	 }
	 
	public  void do_get(String url){
		this.request_type = "get";
		this.request = new HttpGet(url);
		try {
			this.reponse = this.httpclient.execute(this.request, this.context);
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
	
	
	public Header[] getRequestHeads(){
		 Header[] headers =  this.request.getAllHeaders();
	     return headers;
	}
	
	
	
	public  Header[] get_reponse_header(){
		  return  this.reponse.getAllHeaders();
	}
	
	public int get_reponse_status_code(){
		return this.reponse.getStatusLine().getStatusCode();
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
	
	public void  set_header(BasicNameValuePair namePair){
		this.request.setHeader(namePair.getName(), namePair.getValue());
	}
	
	public void  set_header(String name, String value ){
		this.request.setHeader(name, value);
	}
	
  public Header[] getHeader(final String name) {
	    Header[] header;
	    header =  this.request.getHeaders(name);
	    return header;
	   }
  
  public Header[] getAllHeader() {
	    Header[] header;
	    header =  this.request.getAllHeaders();
	    return header;
	   }
	
	public void set_header(ArrayList<BasicNameValuePair> headerArray){
		for(BasicNameValuePair namePair: headerArray){
			this.request.setHeader(namePair.getName(), namePair.getValue());
		}
	}
	
	public  void removeHeader(String name){
		this.request.removeHeaders(name);
		
	}
	
	public void set_config(boolean allowed_redirect){
		this.http_config.setRedirectsEnabled(allowed_redirect);
		this.http_config.setRelativeRedirectsAllowed(allowed_redirect);
		this.request.setConfig(http_config.build());
	}
	
	
   public void execute(){
		try{
			if (this.request_type.equals("post")){
					this.reponse = httpclient.execute(((HttpPost)this.request), this.context);
					}
			else if (this.request_type.equals("get")){ 
		    	this.reponse = httpclient.execute(((HttpGet)this.request), this.context);
		    }
			else{
				this.reponse = httpclient.execute(((HttpGet)this.request), this.context);
			}
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
	
	
	

