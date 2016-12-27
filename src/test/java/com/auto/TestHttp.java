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

import com.jayway.restassured.http.Method.*;





public class TestHttp {
     @Test
	 public void test_get_http(){
    	  HttpHandle httpHandle =  new HttpHandle();
    	  String url = "https://drac-demo.test.com/adminconsole/api/actor/11335844/investSummary";
  	      httpHandle.do_get(url);

	 }
     
     @Test
     public void test_post(){
    	 	CloseableHttpClient httpclient = HttpClients.createDefault();
    	 	HttpPost httpPost = new HttpPost("http://test.com/ws/rs/user/usedetailinfo");
		    try{
		        List params=new ArrayList();
		        params.add(new BasicNameValuePair("account", "yong.huang@test.com"));
		        EntityBuilder entity_post =  EntityBuilder.create().setParameters(new ArrayList<NameValuePair>());
		        entity_post.setContentType(ContentType.APPLICATION_JSON);
    	      //  entity_post.setParameters(new NameValuePair("account", "yong.huang@test.com"));
		       NameValuePair [] values = new NameValuePair[1];
		       values[0] = new BasicNameValuePair("account", "yong.huang@test.com");
		       entity_post.setParameters(values);
		       // entity_post.setText("{ \"account\": \"yong.huang@test.com\"}");
		      
		        
		        
		        
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
    	 	HttpPost httpPost = new HttpPost("http://test.com/ws/rs/user/usedetailinfo");
		    try{
		    	httpPost.setHeader("Content-Type", "application/json");
//		        List params=new ArrayList();
//		        params.add(new BasicNameValuePair("account", "yong.huang@test.com"));
             //  httpPost.setEntity(new StringEntity("{ \"account\": \"yong.huang@test.com\"}"));
               String jsonStr = "{ \"account\": \"yong.huang1@test.com\"}";
              Map json_map =  JacksonUtil.readValue(jsonStr, HashMap.class);
              json_map.put("account", "yong.huang@test.com");
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
    	 String url = "https://drac-demo.test.com/api/actor/11335844/investSummary";

    	 HttpUtils http = HttpUtils.get(url);
    	// http.addParameter("wd", "java 核心技术"); //搜索关键字
    	http.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
    	
    	// http.setProxy("10.10.12.62", 3128); //设置代理
    	 ResponseWrap response = http.execute(); //执行请求
    	 System.out.println(response.getString()); //输出内容
    	 
     }
     
     
     @Test
     public void  test_httpUtil_post(){
    	 String url = "http://test.com/ws/rs/user/usedetailinfo";
         String jsonStr = "{ \"account\": \"yong.huang@test.com\"}";
    	 HttpHandle httpHandle =  new HttpHandle();
    	 httpHandle.set_post_playLoad(jsonStr);
    	 httpHandle.do_post(url);
     }
      
   
     @Test
     public void  test_login_crm(){
    	 String url = "https://test.com/login?service=https%3A%2F%2Fcrm-demo.test.com%2Flogin%2Fcas";
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
    	  	 Map json_map =  new HashMap();
        	 json_map.put("username", "sales_cdu_manager1@test.com");
        	 json_map.put("password", "$cdu1234R");
        	 json_map.put("lt", lt_value);
        	 json_map.put("execution", execution_value);
        	 json_map.put("tenancyCode", "test-WEBSITE");
         	 json_map.put("domain", "https%3A%2F%2Fcrm-demo.test.com%2Flogin%2Fcas");
        	 json_map.put("_eventId", "submit");
        	 json_map.put("submit", "登录");
        	 String jsonStr = JacksonUtil.toJSon(json_map);
        	 System.out.println(jsonStr);
        	 url = "https://passport-demo.test.com/login?service=https%3A%2F%2Fcrm-demo.test.com%2Flogin%2Fcas";
        	 httpHandle.do_post(url);
        	 httpHandle.set_header("Content-Type", "application/x-www-form-urlencoded");
        	 httpHandle.set_header("Referer",  "https://passport-demo.test.com/login?service=https%3A%2F%2Fcrm-demo.test.com%2Flogin%2Fcas");
        	 httpHandle.set_header("Host", "passport-demo.test.com");
        	 httpHandle.set_header("Accept-Encoding","gzip, deflate");
        	 httpHandle.set_config(true);
        	 
        	 
        	 jsonStr = "domain=https%253A%252F%252Fcrm-demo.test.com%252Flogin%252Fcas&username=sales_cdu_manager0%40test.com&password=%24cdu1234R&lt=LT-9165-52fIEy6ct5tSffYd7BxzBVgdqPdhYo-cas01.test.com&execution=a5743510-ec39-4a61-95a0-216c987280f0_AAAAIgAAABB9q97DOCt1m%2Bh%2BvRP8KUy%2BAAAABmFlczEyOEzcbNqZK0WaeiiBn2Oz%2FxSdkl6WseEkVvsGnIXbX2chK6YhDBwhfkisq9F42JBa4vORAvfXUY%2B2RPMug3Gr5dc7cBIXZ05LiMHUDqGS%2F9Jx5BrQP8K%2BaaSWRwU%2F849bvP0QuIj0XZIf9OuDxsbLbyZJ2R457KtW%2BXZAd2RPCtGJxgoQqZZ5Z1XWyI2elD8o2s5wAnAqDUzF1JVtmZqszhJITBV8uIYTEn5vjdwFqc5sNCQt07%2BdGqRLHnG%2FdlKY3S4hb5QAwG9izRfyntyt6W6KFEVvZXdNiz0QC38ydg3OaJOeiFCu78QIC5LKjltFv0361egTmABiomTIQl6oZXi%2FzastpDRQSTDVJjRtSu3po8VW9FuzQjfR42H6MT6JUC3wWVDr5XvpQs07ZKwSoTneEkwMebcNnoGeTNlCm3EsKKpCwl0uws%2B9FavrHKMOV2Io8GJRRaKanp9nuUKzfpI14kNNyyE4xIauuGuhSVinkUXJrq%2B3dG0g%2FukRbipZGtwftfK7BwtZDdZiMAp3ZRVyxnJk4x48nQvw73pmNzX1CD9oM0FQycI00bbJrsUszpK9LEq01gLt9jfvgB06nQ2w5lmDHsbYcMSQ3tNpi8Som29i0eTQkOrFagi4NORxp8alJEJ5Kiyrp5byCiN3d%2BFguH%2FqGdTh1saCYm4%2B%2Bts3A5QQn8fCdJ1gE8FqeAUGvCv9OgWJ9bCcR7NjC7vHLGbepxHKdSYu7UC7iPjW2X4tJrudD81YAq2wB3fdEpfRpsfgoh2jrPuqrtw90iBgRu5Xk5q6Pqv1hRF0uDaoCDstncNv87twR8LSC%2F8OlycAKKypOjeW9WYbfZm4YZnpLNcBGmC2dBnm5YA1duEdXjQa02iSCSz7Bkw0kDvTJy930aGvE8Cv9v%2FZI1B6pS%2BMpUEzM90W0FwrC%2F2FgmQ6yG%2FVBbYr2KjQwLTbxZovca9d3IiJFvPu%2B1bPMBP%2BENtWdLcVzU%2Bx2RpGbxmPlZazCUdYI%2FNJ70cnNBOMs1tz2Dp7nIvz2LzqmgaK9CuviRTvizxQOVEaFuZe5794Sp71JIQM8RASPFDstTU7ctH%2FsS89DglH5aFgI8zjQA5LEewaOFdksDNDrTfgXqR3OHuk3IrHCowqrAUlEpz9ippHbX75HTOB9QpFdCPG%2FzUbxXHtpyeHr%2BBatqrAVF%2F2xvzBbcfgHH8ByVnXZErS7at03SaChaMpF%2FzYJFGDpNyKBD3jhfC6gZRFXd2ogasazYBscFkraTpoyF5NngvmuCsvC31EVO%2FZ1kfMsqjd%2BDe3pi%2FXbJtmuEBYQA0CDZPR3S3%2FpY%2BsczdJuiDupLxerMRqtIJyN7bcNH63oP6ZY2dRDDHIX%2FuR2Cn9zofKnbbGrlSdyQEQEbT1CzwEgCROiprRiX7kveyLi9fpfvnr8Gni9wn6Tm7mZOdVN5GNozm6GhCIThSl8Yg81Z6Mpol%2Bsl0nXqwh%2B5cQ90FX9P6JNcldSl5BnV0aCvJiMPz%2BTqExP0JOdPeidw4iJIKXXAsS2OmU4aAfAghzUf94XnNCLm7KlEGDrrL3QKX0bH9PzwguSW0iBDT4%2BMpe7yOuqOucS8JQLFK7oUPupcQuAek2g27GdR4iutTTN%2B7s5sHIgk5WBXr5yc%2F7soGPzM0%2FUI5oVt9JINKBHJpdklbFXsPLxjOsczy6iufgFPQBZXaCw1EbKRIhdz3GvnjIYGfqDuBPKwj8vWZsQcuVvzHXv2Xv9pF%2BbhUS7Gg69TeKN3L6Wj6wYVyivR555aM8cjwpfpk%2BCtWN1trvqWYrWKxE5PGIEDaljRl34LpRYhzVd%2FxZ8EVnlygdKeShiQOgsjzlOUnhek1pKlN0yMK6aRFQg9Rc0vKrfu4apGt0Xj5C8t50HkvAvoe0bXgrWwrCJjfjyQo6at79C80d%2BhsI7Sx%2FmyXlSjP8q5xKQ2v7RyITOdQpgJEILKT4SRyjFQnFMIxtHxyejnxPo48dF7xFDZ%2B3KLeSXKqwFB3Rpbi2H%2FJdnIeFnrieWDOTAdAU%2Bj%2BuGvDPZxYi21JUoOlcP%2FDllr0DiXYMiU60%2BaEZVcswbTqWAt5KoCUGir5Ymtng8U1WbXALACY7rhmThdxlA34teBQoaYGmcvW0Je2DF2B19i1CuaeByiav2xY6Cy2gvxzvRyrMLf1YA2BsOr9YlzBEm%2BH0qjO%2BBmkBJoeKrtdmj%2BUd10cZAowN0naZr89sq%2BMxuv9mqFRjHX6%2B0zbjbvI%2F3ujXGJxc8yfa4r9J%2FNlxW1qkg%2Fde%2B19gKkOkvKEs1xBVTKaBxpSE5wA%2FYt8flcp32w%2FiQgAEzNR1qX0tb2mKZQLzfonHxoVMjvqH6IXvd7V4Q9TY5wsN7DHxcGbr6js3erRp0ytE4wTG8NpLKlybPQeuNMtdi7OB3EU7CY2M3urfmOOCuerzCBzubeaI5xPQwdd6VWZhChNcr%2B1AXKCL4j34i2vEvBQUvLlEn2ViJAzEqld0WUE4nyvDL7oQGvXypktj6S89Ve2jRTaApCuTTRPNrQ79OPzeAd%2FBJV3mSOOa4v0VsR6Z6a4n3qV4J34Yx8CEQoGJnd%2FydD1qecKH7NoWDZxDLb3A7oJ5P0gXjsn3%2FTrp%2BUdA1PBdwhRMf0v2VgMk2%2By6CIId6hmUXomE%2BQooHKqgC4l2vrhJH3J5Uo45tcggrDIRsV7j9raLgXal23LDNBstPjXOPsEs0%2Bjweg923yYhkaEKRSK1EtovpgHMb4rkQTleZIzlVYaF5Wk9Mk%2BCbXGzkMQjARcdsHBDLp2b23BsiN5ma0IMvqd6xWkSoCkPCn8smej4YYOOKB1e8moUsQj79mwXBSB2GPwwWRaliuqnGYVIAhTw02heCaRYBBmo65ZPomQXUUD%2BPRd7l0xZhejF%2FEiFz%2FbfXgMHM%2FXl7VqjyBQR6u%2Bv1Y1uN0sRSpRse2xpyjlkItfmOUHfdbw4DpVs%2BWaJMEGHHlB0VYnOWhiQrcVHpGoYxdQ%2ByOcMWm1sKKv5H776Wt93hmPhbpGlRZx6%2BZS%2BOpfvtJ9mCdLYjMU%2FTKRT082jZ1wS15Zg3QqKxa4XByO%2FqwEhS%2BZ5zWmpaA1xEkqPPMcez5IB8KUg%2Bkw23SAZtHPZ4l1GFrzi9DQs4%2BBW1qN%2FYabk%2BjI6jo4f8sauaEQ%2FnohVmt4a74nShCHX%2FF0n%2FXQEjEQz9OPo326wcE9l67Nk9RS3yQboYaVzUjqZIaTDHOEzPugbS3%2FPej1B08WBy1Of6PL6WfS5Fazxz8IDrHRL7gt3bmQNlJv0zuQ5F8TH98YJJpAHjsCwQbwcpAO4EBoLqdoSSqv7B%2FKmuTugApAmcV9dnikwuDNe%2B99TlLfc4ikItzMiS%2Fvon6xX8MajGqlRHn5QTbg3IjGwo2QXqTmbsFqECYz9MdIvZPNUdBZtlAYxpjekKgcOt6ax7xwmQu1nhqK3DWQB8nW%2BIi%2BS4miH%2FKtR2W4XI%2FkJsR9paz1LUQnozaLhoTAh1XjWjrBE6IdM76dcGQCaGpzKAD4Pxitmz8fZuaKphfA%2BaRoU%2FzflbmlccnxtG5ziHe4%2FH2yfVGfm6Roo6EeCDlR%2FxTop%2BFwSU3%2FHPl0%2F%2FrJATNTMYkJ%2FeuCt9dck8be9EeW9pYDuaYBzp5sTsR9493BEr4Wh6ZgwqBz1T4ca1SULezFVf8eMmxTe%2F1xJGCtAIJE06Gl57zsO8TRKbyGYVhwOhPyxwwAD3T%2BqYecef6TaBzgDzjvok94F7nGHBrznoeZjmQgmmTLrVdWU30Rw5M92a%2BCIlCtCN%2Ff%2BnehfOzUnDrv1g1qTxjIcnmqid9wCELeOoBBQeqVH6RtFA1a42nEM9FeHJzVuCibjtF%2B8DRbpxuvz6MQ%2Fc2zMC0VzMO8T3Pcd3MUrWaM2p6rw1wnLJ9FrO2R9GJpHGY2f4sPoLIvGQe6CkikwfZaHJ48fWug2uoeljAJlGy%2BZrXBOc8VJMLmyJbBiUfxnzaVUSA%3D%3D&_eventId=submit&tenancyCode=test-WEBSITE";
        	 
        	 httpHandle.set_post_playLoad(jsonStr);
        	 httpHandle.execute();
        	entity = httpHandle.get_response_entity();
        	Header[] headers = httpHandle.get_reponse_header();
			   System.out.println("======request headers");
			    for(Header header: headers){
	        		System.out.println(header.toString());
	        	}
        	
        	int status_code = httpHandle.get_reponse_status_code();
        	System.out.printf("status code:%d" , status_code);
        	
         httpHandle.removeHeader("Content-Type");	
         url = "https://crm-demo.test.com/customerdetail?customerId=4";
    	 httpHandle.do_get(url);
    	 entity = httpHandle.get_response_entity();
    	 str =  EntityUtils.toString(entity, "utf-8");
        System.out.println(str);
    	 status_code = httpHandle.get_reponse_status_code();
        System.out.printf("status code:%d" , status_code);
        headers = httpHandle.get_reponse_header();
        headers = httpHandle.getRequestHeads();
		   System.out.println("======get request headers");
		    for(Header header: headers){
     		System.out.println(header.toString());
     	}
       	   
    	 }
    	 catch(Exception e){
    		 e.printStackTrace();
    	 }
     }
      
     
     
}
