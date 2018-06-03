package com.seleniumFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.Throwable;
import java.lang.Exception;
import java.lang.Thread;
import java.lang.AssertionError;


public class Element {
	 public WebDriver driver;
	 public String by_way = null;
	 public String locator = null;
	 public  WebElement element = null;
	 
	 /*
	  * 使用时，需要在调用的时候，传入特定的参数
	  */
	 public Element(String common_locator_str, String special_locator){
		 this.driver = Driver.get_driver();
		 String[] locator = common_locator_str.split(";");
		 this.by_way = locator[0];
		 this.locator = String.format(locator[1], special_locator);
	 }
	 
	 /*
	  * 使用时，需要在调用的时候，传入特定的参数
	  */
	 public Element(String locator_str){
		 this.driver = Driver.get_driver();
		 String[] locator = locator_str.split(";");
		 this.by_way = locator[0];
		 this.locator = locator[1];
	 }
	 
//	 public WebElement get_element(){
//		try{
//			 if (this.by_way .equals("name")){
//				this.element =  this.driver.findElement(By.name(this.locator));
//				 }
//			 if (this.by_way .equals("id")){
//				 this.element =  this.driver.findElement(By.id(this.locator));
//				 }
//			 if (this.by_way .equals("linkText")){
//				 this.element =   this.driver.findElement(By.linkText(this.locator));
//				 }
//			 if (by_way.equals("xpath")){
//				 this.element =   this.driver.findElement(By.xpath(this.locator));
//				 }
//			 if (this.by_way .equals("className")){
//				 this.element =   this.driver.findElement(By.className(this.locator));
//				 }
//		}
//	   catch(Throwable e){
//		   this.element = null;
//	   }
//		return this.element ;
//	 }

	public WebElement get_element(){
		try{
			switch (this.by_way) {
				case "name":
					this.element = this.driver.findElement(By.name(this.locator));
					break;
				case "id":
					this.element = this.driver.findElement(By.id(this.locator));
					break;
				case "linkText":
					this.element = this.driver.findElement(By.linkText(this.locator));
					break;
				case "xpath":
					this.element = this.driver.findElement(By.xpath(this.locator));
					break;
				case "className":
					this.driver.findElement(By.className(this.locator));
					break;
				default:
					this.element = this.driver.findElement(By.xpath(this.locator));
					break;
			}
		}
		catch(Throwable e){
			this.element = null;
		}
		return this.element ;
	}
	 
	 public void click(){
		 this.element = get_element();
		 this.element.click();
	 }
	 
	 public void wait_element_present(){
		 int timeout = 30;
		 int interval = 3;
		 for(int i=0; i < timeout/interval; i++){
			 this.element = get_element();
			 if (this.element == null){
				 try{
				   Thread.sleep(3000);
				   System.out.printf("+++wait element present %d \n", i);
				   continue;
				 }
				 catch (Exception e) {
		              System.out.println(e);
		           }
			 }
		 }
		 if (! (this.element instanceof WebElement)){
			 throw new AssertionError(String.format("%s is not  present.\n", this.locator));
		 }
		
	 }
	 
	 public void input(String str){
		 this.element = get_element();
		 this.element.sendKeys(str);
	 } 
}
