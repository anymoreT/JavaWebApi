package com.auto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.auto.Driver;
import java.lang.Throwable;
import java.lang.Exception;
import java.lang.Thread;
import java.lang.AssertionError;


public class Element {
	 public WebDriver driver;
	 public String by_way = null;
	 public String locator = null;
	 public  WebElement element = null;
	 
	 public Element(String by_way, String locator){
		 this.driver = Driver.get_driver();
		 this.by_way = by_way;
		 this.locator = locator;
	 }
	 
	 public WebElement get_element(){
		try{
			 if (this.by_way .equals("name")){
				this.element =  this.driver.findElement(By.name(this.locator));
				 }
			 if (this.by_way .equals("id")){
				 this.element =  this.driver.findElement(By.id(this.locator));
				 }
			 if (this.by_way .equals("linkText")){
				 this.element =   this.driver.findElement(By.linkText(this.locator));
				 }
			 if (by_way.equals("xpath")){
				 this.element =   this.driver.findElement(By.xpath(this.locator));
				 }
			 if (this.by_way .equals("className")){
				 this.element =   this.driver.findElement(By.className(this.locator));
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
			 throw new AssertionError("couldn't meet element present.\n");
		 }
		
	 }
	 
	 public void input(String str){
		 this.element = get_element();
		 this.element.sendKeys(str);
	 } 
}
