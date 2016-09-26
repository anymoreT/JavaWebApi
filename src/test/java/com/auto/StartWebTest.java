package com.auto;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.auto.Driver;
import com.auto.Element;
import com.config.Config;

public class StartWebTest {
  @Test
  public void f() {  
	  Config.load_yaml(".");
	  WebDriver driver = new FirefoxDriver();
	  Driver.set_driver(driver);
	  Driver.go_to_url("https://www.baidu.com");
	  Element el = new Element("name", "wd1");
      el.wait_element_present();
      System.out.println("Page title is: " + driver.getTitle());
  }
 
  @Test
  public void f1() {  
	  WebDriver driver = new FirefoxDriver();
	  Driver.set_driver(driver);
	  Driver.go_to_url("https://www.dianrong.com");
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
  }


  public void beforeSuite() {
  }

 
  public void afterSuite() {
  }

}
