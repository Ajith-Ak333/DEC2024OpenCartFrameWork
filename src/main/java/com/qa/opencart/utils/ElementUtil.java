package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {

	  private WebDriver driver;
	  private Actions act;
	  private JavaScriptUtil util;
	
	public ElementUtil(WebDriver driver)
	{
		this.driver=driver;
		act = new Actions(driver);
		util = new JavaScriptUtil(driver);
	}

	@Step("Finding the element using:{0}")
	public WebElement getElement(By locator)
	{
		ChainTestListener.log("locator : "+ locator.toString());
		WebElement element = driver.findElement(locator);
		highlightElement(element);
		return element;
	}
	
	private void highlightElement(WebElement element)
	{
		if(Boolean.parseBoolean(DriverFactory.highelement))
		{
		   util.flash(element);			
		}
		
	}
	
	public WebElement getElement(String loctype,String locvar)
	{
		return driver.findElement(getBy(loctype, locvar));
	}
	
	public WebElement getElement(int timeout,By locator)
	{
		return waitForElementVisible(timeout, locator);
	}
	
	
	public By getBy(String loctype,String locvar)
	{
		By locator=null;
		
		switch (loctype.toUpperCase()) {
		case "ID":
			locator = By.id(locvar);
			break;
		case "NAME":
			locator = By.name(locvar);
			break;
		case "CLASS":
			locator = By.className(locvar);
			break;
		case "XPATH":
			locator = By.xpath(locvar);
			break;
		case "CSS":
			locator = By.cssSelector(locvar);
			break;
		case "LINKTEXT":
			locator = By.linkText(locvar);
			break;
		case "PARTIAL LINKTEXT":
			locator = By.partialLinkText(locvar);
			break;
		case "TAGNAME":
			locator = By.tagName(locvar);
			break;

		default:
			System.out.println("Plzz pass the right locator type " + loctype);
			break;
		}
		return locator;
	}
	
	 private void nullCheck(CharSequence...value)
	 {
		 if(value==null)
		 {
			 throw new BrowserException("Value cannot be null");
		 }	 
	 }
	
	@Step("Entering value :{1} into element:{0}") 
	public void doSendKeys(By locator , String value)
	{
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public void doSendKeys(String loctype,String locvar, String value)
	{
		nullCheck(value);
		getElement(loctype, locvar).clear();
		getElement(loctype, locvar).sendKeys(value);
	}
	
	public void doSendKeys(By locator , CharSequence... value)
	{
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	@Step("Cliking on element using :{0}")
	public void doclick(By locator)
	{
		getElement(locator).click();
	}
	
	public void doclick(String loctype,String locvar)
	{
		getElement(loctype, locvar).click();
	}
	
	@Step("Fetching the element text using:{0}")
	public String doElementGetText(By locator)
	{
		String text = getElement(locator).getText();
		System.out.println("element text =>" + text);
		return text;
	}
	
	public String getElementDomAttributeValue(By locator,String value)
	{
		nullCheck(value);
		return getElement(locator).getDomAttribute(value);
	}
	
	public String getElementPropAttribute(By locator,String value)
	{
		nullCheck(value);
		return getElement(locator).getDomProperty(value);
	}
	
	public boolean isElementdisplayed(By locator)
	{
		try
		{
		 return getElement(locator).isDisplayed();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		    System.out.println("Element is not present on the page" + locator);
			return false;
		}
	}
	
	public boolean elementDisplayCount(By locator)
	{
		if(getElements(locator).size()==1)
        {
			System.out.println("element : " + locator + " is displayed on the page one time");	
			return true;
        }
		return false;	
	}
	
	public boolean elementDisplayCount(By locator,int exptime)
	{
		if(getElements(locator).size()==exptime)
        {
			System.out.println("element : " + locator + " is displayed on the page " + exptime + " time");	
			return true;
        }
		return false;	
	}
	
 // ************************FindElementsUtils***********************************************
	
	public List<WebElement> getElements(By locator)
	{
		 return driver.findElements(locator);
		
	}
	
	public int getElementscount(By locator)
	{
		  int size = getElements(locator).size();
		  System.out.println("Element count is : " + size);
		  return size;	
	}
	
	public List<String> getElementTextlist(By locator)
	{
		List<WebElement> ele = getElements(locator);
		
		List<String> list = new ArrayList<String>();
		
		for(WebElement e : ele)
		{
			String s = e.getText();
			if(s.length()!=0)
			{
				System.out.println(s);
				list.add(s);
			}
		}
		return list;
	}
	
	public void elementsClick(By locator,String value)
	{
		List<WebElement> ele = getElements(locator);
		for(WebElement e : ele)
		{
		  String st = e.getText();
		  System.out.println(st);
     	  if(st.equals(value))
		   {
       	  e.click();
    	  break;
		  }
		}
	}
	
 // ************************DropdownUtils***********************************************
	
	public  boolean doSelectDropdownByIndex(By locator,int index)
	{
		Select select = new Select(getElement(locator));
		try
		{
			select.selectByIndex(index);
			return true;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			System.out.println(index + "is not presemt in the dropdown");
			return false;
		}		
	}
	
	public  boolean doSelectDropdownByValue(By locator,String value)
	{
		Select select = new Select(getElement(locator));
		try 
		{
		    select.selectByValue(value);
		    return true;
		}
		catch(NoSuchElementException e)
		{
		    e.printStackTrace();
			System.out.println(value + "is not presemt in the dropdown");
			return false;
		}
	}
	
	public boolean doSelectDropdownByVisibleText(By locator,String visible)
	{
		Select select = new Select(getElement(locator));
		try
		{
			select.selectByVisibleText(visible);
			return true;
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			System.out.println(visible + "is not presemt in the dropdown");
			return false;
		}		
	}
	
	public boolean selectDropdownValue(By locator,String value)
    {
		Select select = new Select(getElement(locator));
					
			List<WebElement> list = select.getOptions();
			System.out.println("List size : " + list.size());
			
			boolean flag = false;
			
			for(WebElement e : list)
			{
				String s = e.getText();
				System.out.println(s);
				if(s.equals(value)) 
				{
					e.click();
					flag=true;
					break;
				}				
			}  
			if(flag)
			{
				System.out.println(value + " is Selected");
				return true;
			}
			else
			{
				System.out.println(value + " is not selected");
				return false;
			}
    }
	
	public List<String> getDropdownValueList(By locator)
	{		   
		   Select select = new Select(getElement(locator));
		   List<WebElement> list = select.getOptions();
		   System.out.println(list.size());
		   
		   List<String> str = new ArrayList<String>();	   
		   for(WebElement e : list)
		   {
			   String s = e.getText();
			   System.out.println(s);
			   str.add(s.trim());
		   }
		    return str;	
	}
	
	public boolean getDropdownValueList(By locator,List<String> explist)
	{		   
		   Select select = new Select(getElement(locator));
		   List<WebElement> list = select.getOptions();
		   System.out.println(list.size());
		   
		   List<String> actlist = new ArrayList<String>();	   
		   for(WebElement e : list)
		   {
			   String s = e.getText();
			   System.out.println(s);
			   actlist.add(s.trim());
		   }
		   
		   if(actlist.containsAll(explist))
		   {
			   return true;
		   }
		   else
		   {
			   return false;
		   }
	}
	
  //*******************drop down utils --non select based**************************//
	
	public void selectChoice(By webelement,By webelements,String... choicevalue) throws InterruptedException
	{
		   doclick(webelement);
		   Thread.sleep(3000);

		   List<WebElement> list = getElements(webelements);
		   System.out.println(list.size());
		   
		   if(choicevalue[0].equalsIgnoreCase("all"))
		   {
			for(WebElement e : list)
			{
				e.click();
			}
		   }
		   else
		   {
			   
		   for(WebElement e : list)
		   {
			   String s = e.getText();
			   System.out.println(s);
		    for(String value : choicevalue)
		    {
		     if(s.trim().equals(value))
		     {
		    	 e.click();
		    	 break;
		     }		    	
		    }
			   
		   }
		   }}
		   
	// *************************Action utils **************************************************
	
	 public void handleParentSubMenu(By parentmenu,By submenu) throws InterruptedException
	 {				
		 moveToElementmenu(parentmenu);	   
		 doclick(submenu);			
				
	}
	 
	 public void moveToElementmenu(By locator) throws InterruptedException
	 {
		 act.moveToElement(getElement(locator)).build().perform();
		 Thread.sleep(2000);
	 }
	 
	 public void handle4LevelmenuHandle(By level1,By level2,By level3,By level4) throws InterruptedException
	 {		 
		 doclick(level1);	  
		 Thread.sleep(2000);
		  
		 moveToElementmenu(level3);
		 Thread.sleep(2000);

		 moveToElementmenu(level3);
		 Thread.sleep(2000);

		 doclick(level4);	  
		   
	 } 
	 
	 public void doActionsSendKeys(By locator,String value)
	 {
		    act.sendKeys(getElement(locator),value).build().perform();
	 }

	public void doActionsClick(By locator)
	 {
			act.click(getElement(locator)).build().perform();
	 }
	
	public void doSendKeysWithPause(By locator,String value,long pause)
	{
		  char c[] = value.toCharArray();	  
		  for(char ch : c)
		  {
			  act.sendKeys(getElement(locator), String.valueOf(ch)).pause(pause).build().perform();
		  }		
	}
	
   // ********************************Wait for element/All Elements Utils *********************************************************
	
	
	 public WebElement waitForElementPresence(int timeout,By locator)
     {
     	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	   	  return wait.until(ExpectedConditions.presenceOfElementLocated(locator));        	 	
     }
	 public List<WebElement> waitForAllElementPresence(int timeout,By locator)
     {
     	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	   	  return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));        	 	
     }
	 
	 @Step("Waiting for element using:{0} and timeout:{1}")
	 public WebElement waitForElementVisible(int timeout,By locator)
     {
     	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	   	  WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));   
	   	  highlightElement(element);
	   	  return element;
     }
	 public List<WebElement> waitForAllElementVisible(int timeout,By locator)
     {
     	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	   	  try 
	   	  {
     	      return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));   
	   	  }
	   	  catch(TimeoutException e)
	   	  {
	   		  return Collections.EMPTY_LIST;
	   	  }
     }
	 
	 @Step("Waiting for element and clicking on it using:{0} and timeout:{1}")
	 public WebElement waitForElementToBeClick(int timeout,By locator)
     {
     	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	   	  return wait.until(ExpectedConditions.elementToBeClickable(locator));        	 	
     }
	 
	 public void clickWithWait(int timeout,By locator)
     {
		 waitForElementVisible(timeout, locator).click();   
     }
	 
	 public void sendKeysWithWait(int timeout,By locator,String value)
     {
		 waitForElementVisible(timeout, locator).clear();      	 	
         waitForElementVisible(timeout, locator).sendKeys(value);       	 	
     }
	 
  // ******************************************Wait for Alerts ************************************************
	 
	 public  Alert waitForAlert(int timeout)
	 {
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		  return wait.until(ExpectedConditions.alertIsPresent());
	 }
		
	 public void alertForAccept(int timeout)
	 {
		   waitForAlert(timeout).accept();
	 }
		
	 public void alertForDismiss(int timeout)
	 {
		   waitForAlert(timeout).dismiss();
	 }
		
	 public String waitForgetText(int timeout)
	 {
		   return waitForAlert(timeout).getText();
	 } 
	 
	 public void sendKeysAlert(int timeout,String value)
	 {
		   waitForAlert(timeout).sendKeys(value);
	 } 
	 
   // ************************************************ Wait for title ************************************************
	 public String waitForTitleContains(int timeout,String value)
		{
			   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			   try {
				   wait.until(ExpectedConditions.titleIs(value));	
	               return driver.getTitle();
			   }
			   catch (TimeoutException e) {
				   e.printStackTrace();
			}
			   return null;
		}
		
		public String waitForTitleIs(int timeout,String value)
		{
			   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			   try {
				   wait.until(ExpectedConditions.titleIs(value));	
	               return driver.getTitle();
			   }
			   catch (TimeoutException e) {
				   e.printStackTrace();
				   return null;
			}
		}
		
  // **************************************************** Wait For URLs **************************************************
		
		public String waitForURLContains(int timeout,String value)
		{
			   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			   try {
				   wait.until(ExpectedConditions.urlContains(value));	
	              return driver.getCurrentUrl();
			   }
			   catch (TimeoutException e) {
				   e.printStackTrace();
			}
			   return null;
		}
		
		public String waitForURLIs(int timeout,String value)
		{
			   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			   try {
				   wait.until(ExpectedConditions.urlToBe(value));	
	              return driver.getCurrentUrl();
			   }
			   catch (TimeoutException e) {
				   e.printStackTrace();
				   return null;
			}
		}	
		
 // ******************************************************* Wait for Frame *********************************************
		
		 public void waitForFrameAndToBeAvailable(By locator,int timeout)
		   {
			      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));	   
		   }
		   
		   public void waitForFrameAndToBeAvailable(String frameNameorId,int timeout)
		   {
			      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameorId));	   
		   }
		   
		   public void waitForFrameAndToBeAvailable(int frameIndex,int timeout)
		   {
			      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));	   
		   }
// ******************************************************* Wait for Window ********************************************
		   
		   public Boolean waitforWindow(int timeout,int numberofwindow) {
				 
			      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			      try
			      {
			    	  return wait.until(ExpectedConditions.numberOfWindowsToBe(numberofwindow));
			      }
			      catch (Exception e) {
			    	  e.printStackTrace();
			    	  return false;
				}		 
		 }
 //******************************************************* Command Of Fluent Waits **************************************
		   public WebElement waitForFluent(int timeout,int pollingtime,By locator)
			{
				   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		                 .withTimeout(Duration.ofSeconds(timeout))
		                 .pollingEvery(Duration.ofSeconds(pollingtime))
		                 .ignoring(NoSuchElementException.class)
		                 .ignoring(StaleElementReferenceException.class)
		                 .withMessage("=====Element is not found======");
				   return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
			
			public WebElement waitForFluentPresence(int timeout,int pollingtime,By locator)
			{
				   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		                 .withTimeout(Duration.ofSeconds(timeout))
		                 .pollingEvery(Duration.ofSeconds(pollingtime))
		                 .ignoring(NoSuchElementException.class)
		                 .ignoring(StaleElementReferenceException.class)
		                 .withMessage("=====Element is not found======");
				   return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			}
//******************************************************* Command Of WedriverWait with fluent Features *************************
			public  WebElement webDriverWithFluent(int timeout,int pollingtime,By locator)
			{
				   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
				   wait.pollingEvery(Duration.ofSeconds(pollingtime));
				   wait.ignoring(NoSuchElementException.class);
				   wait.ignoring(StaleElementReferenceException.class);
			       wait.withMessage("=====Element is not found======");
				   return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
		   
	}