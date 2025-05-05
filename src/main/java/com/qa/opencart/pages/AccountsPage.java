package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.ArrayList;
import java.util.List;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;


public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	private final By headers = By.xpath("//div[@id='content']/h2");
	
	private final By search = By.xpath("//input[@type='text']");
	private final By searchicon = By.xpath("(//button[@type='button'])[4]");
	
	private static final Logger log = LogManager.getLogger(AccountsPage.class);
	
	
	public AccountsPage(WebDriver driver)
	{
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	@Step("getting acc page title")
	public String getAccPageTitle()
	{
		String title = util.waitForTitleIs(DEFAULT_TIMEOUT, HOME_PAGE_TITLE);
		log.info("Home page title: " + title);
		return title;
	}
	
	@Step("getting acc page url")
	public String getAccPageUrl()
	{
		String url = util.waitForURLContains(DEFAULT_TIMEOUT, HOME_PAGE_FRACTION_URL);
		log.info("Home page url: " + url);
		return url;
	}
	
	@Step("getting acc page headers")
	public List<String> getAccPageHeaders()
	{
		List<WebElement> ele = util.getElements(headers);
		System.out.println("Size is " + ele.size());
		
		List<String> list = new ArrayList<String>();
		
		for(WebElement e : ele)
		{
		   String s = e.getText();
		   list.add(s);
		}
		// System.out.println(list);
		log.info(list);		
		return list;
	}
	
	@Step("perform search: {0}")
	public ResultsPage doSearch(String value)
	{
		log.info("search key: " + value);
		
	    util.doSendKeys(search, value);
		//util.sendKeysWithWait(DEFAULT_TIMEOUT, search, value);
		util.doclick(searchicon);
		
		return new ResultsPage(driver);		
	}
	
	
	

}
