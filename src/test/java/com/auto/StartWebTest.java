package com.auto;

import org.testng.annotations.Test;

import com.seleniumFunction.Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.seleniumFunction.Driver;
import com.pagesElements.*;


public class StartWebTest {
  @Test
  public void f() {  

	 // WebDriver driver = new FirefoxDriver();
//      ChromeOptions options = new ChromeOptions();
//      options.setBinary("D:\\tools\\chromedriver_win32\\chromedriver.exe");
//      options.addArguments("start-maximized");

      WebDriver driver = new ChromeDriver();
	  Driver.set_driver(driver);
	  Driver.go_to_url("https://www.baidu.com");
	  Element el = new Element("xpath;//input[@id='su']");
      el.wait_element_present();
      System.out.println("Page title is: " + driver.getTitle());

	  el = new Element(BAIDUPAGE.INPUTSTR);
	  el.wait_element_present();
      el.input("黄勇m");

      el = new Element(BAIDUPAGE.SEARCHBUTTON);
      el.click();

  }
 
  public void beforeMethod() {
  }

  
  public void afterMethod() {
	
  }

  public void beforeClass() {
  }


  public void afterClass() {
  }

  
  public void beforeTest() {
  }


  public void afterTest() {
	  Driver.quit_driver();
  }


  public void beforeSuite() {
  }

 
  public void afterSuite() {
  }

}
