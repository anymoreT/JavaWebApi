package com.config;
import org.yaml.snakeyaml.*;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Config {

	 public static void  load_yaml(String filePath){
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
	    	 System.out.println(result.getClass());
	    	 System.out.println( ((HashMap)result_map.get("Login")).get("Demo"));
	     }catch (Exception e){
	    	 System.out.printf("cant read the file: %s", path.toString());
	     }
		 
	 }


}
