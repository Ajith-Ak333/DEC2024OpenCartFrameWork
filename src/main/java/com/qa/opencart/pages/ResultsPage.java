package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	private final By resultsProduct = By.xpath("//div[contains(@class,'product-layout')]//img");	
	
	public ResultsPage(WebDriver driver)
	{
		this.driver = driver;
		util = new ElementUtil(driver);	
	}
	
	@Step("Getting the product count on results page")
	public int getResultsProductCount()
	{
		int count = util.waitForAllElementVisible(DEFAULT_TIMEOUT, resultsProduct).size();
		System.out.println("Total number of Search Product " + count);
		return count;
	}
	
	public ProductInfoPage selectProduct(String productname)
	{
		System.out.println("Product name: " + productname);
		util.clickWithWait(LONG_DEFAULT_TIMEOUT, By.linkText(productname));
		//util.doclick();
		
		return new ProductInfoPage(driver);
	}
	
	

}
