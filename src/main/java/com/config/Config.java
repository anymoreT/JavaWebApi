package com.config;
import org.yaml.snakeyaml.*;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Config {
	/*
	 * 获得配置文件数据
	 */
	 public static HashMap get_yaml_config(){
		Path path;
		String root_path = System.getProperty("user.dir");
		path = Paths.get(root_path, "src/main/java", "com/config", "adminMainSite.ymal");  
		 System.out.println(path);
		 Yaml yaml = new Yaml();
	     File f=new File(path.toString());
	     //读入文件
	     try{
	    	 Object result= yaml.load(new FileInputStream(f));
	    	 HashMap result_map = (HashMap)result;
	    	 return result_map;
//	    	 System.out.println(result.getClass());
//	    	 System.out.println( ((HashMap)result_map.get("Login")).get("Demo"));
	     }catch (Exception e){
	    	 System.out.printf("cant read the file: %s", path.toString());
	    	 return null;
	     }
	     
	     }
	 
	 /*
	  * 读取yaml文件，返回Ｈashmap格式
	  */
	 public static HashMap load_yaml(String filePath){
		 Yaml yaml = new Yaml();
	     File f=new File(filePath);
	     //读入文件
	     try{
	    	 Object result= yaml.load(new FileInputStream(f));
	    	 HashMap result_map = (HashMap)result;
	    	 return result_map;
	     }catch (Exception e){
	    	 System.out.printf("cant read the file: %s", filePath);
	    	 return null;
	     }
	 }
	     
		 
     public static String  get_env(){
    	 String evn = "Demo";
    	 return evn;
     }




}
