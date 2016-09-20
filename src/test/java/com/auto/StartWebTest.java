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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartWebTest {
  @Test
  public void f() {
//	  WebDriver driver = null;
//	  WebElement element = null;
	  System.out.println("++++test21中国1321312");
	  WebDriver driver = new FirefoxDriver();

      // And now use this to visit Google
      driver.get("https://www.baidu.com");
      // Alternatively the same thing can be done like this
      // driver.navigate().to("http://www.google.com");

      // Find the text input element by its name
      WebElement element = driver.findElement(By.name("wd"));

      // Enter something to search for
      element.sendKeys("点融网!");

      // Now submit the form. WebDriver will find the form for us from the element
      element.submit();

      // Check the title of the page
      System.out.println("Page title is: " + driver.getTitle());
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
