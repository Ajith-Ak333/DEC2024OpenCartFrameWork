package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	
	  private WebDriver driver;
	  private JavascriptExecutor js;
	  
	  
	  public JavaScriptUtil(WebDriver driver)
	  {
		  this.driver=driver;
		  js = (JavascriptExecutor)this.driver;
	  }
	  
	  public String getPageTitle()
	  {
		  return js.executeScript("return document.title;").toString();
	  }
	  
	  public String getPageUrl()
	  {
		  return js.executeScript("return document.URL;").toString();
	  }	  
	  
	  public void clickJs(WebElement element)
	  {
		  js.executeScript("arguments[0].click();", element);
	  }
	  
	  public void scrollIntoView(WebElement element)
	  {
		  js.executeScript("arguments[0].scrollIntoView(true);", element);
		  
	  }
	  
	  public void scrollToUp()
	  {
		  js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	  }
	  
	  public void scrollToDown()
	  {
		  js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	  }
	  
	  public void sendKeys(String id,String value)
	  {
		  js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	  }
	  
	  public void alertJS(String message)
	  {
		  js.executeScript("alert('" + message + "')");
	  }
	  
	  public String getPageInnerText()
	  {
		  return js.executeScript("return document.documentElement.innerText;").toString();
	  }
	  
	  public void drawBorder(WebElement element)
	  {
		  js.executeScript("arguments[0].style.border='3px solid red'",element);
	  }
	  
	  public void flash(WebElement element) {
			String bgcolor = element.getCssValue("backgroundColor");//blue
			for (int i = 0; i < 5; i++) {
				changeColor("rgb(0,200,0)", element);// green
				changeColor(bgcolor, element);// blue
			}
		}

		private void changeColor(String color, WebElement element) {
			js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);//GBGBGBGBGBGB

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
	  
}
