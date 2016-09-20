package com.auto;
import org.openqa.selenium.WebDriver;

public class Driver
{
	public static WebDriver driver = null;
	
	public Driver(){
		
	}
	
	/*
	 *  设置driver
	 */
	static public  void set_driver (WebDriver driver){
		Driver.driver = driver;
	}
	
   /*
    * 得到driver
    */
	static public  WebDriver get_driver (WebDriver driver){
		return Driver.driver ;
	}
	
	/*
	 * 访问网页
	 */
	public static void  go_to_url(String url){
		Driver.driver.navigate().to(url);
	}
	

}
