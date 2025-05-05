package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	private final By productHeaders = By.tagName("h1");
	private final By productImages = By.cssSelector("ul.thumbnails img");
	private final By productmetadata = By.xpath("(//ul[@class='list-unstyled'])[8]/li");
	private final By productpricedata = By.xpath("(//ul[@class='list-unstyled'])[9]/li");
	
	private Map<String,String> hashmap;
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver = driver;
		util = new ElementUtil(driver);	
	}
	
	public String getProductInfoPageTitle()
	{
		String title = util.waitForTitleIs(DEFAULT_TIMEOUT,RESULTS_PAGE_TITLE);
		System.out.println("Product InfoPage is " + title);
		return title;	
	}
	
	public String getProductHeader()
	{
		String text = util.waitForElementVisible(DEFAULT_TIMEOUT, productHeaders).getText();
		System.out.println("Header Name is " + text);
		return text;	
	}
	
	public int getProductImagesCount()
	{
		int size = util.waitForAllElementVisible(DEFAULT_TIMEOUT, productImages).size();
		System.out.println("Images count is " + size);		
		return size;		
	}
	
	public Map<String, String> getProductDetailsMap()
	{
		hashmap = new HashMap<String,String>();
		
		hashmap.put("productheader", getProductHeader());
		hashmap.put("productimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return hashmap;
	}
	
	private void getProductMetaData()
	{
		List<WebElement> ele = util.waitForAllElementVisible(DEFAULT_TIMEOUT, productmetadata);
		for(WebElement e : ele)
		{
			String s = e.getText();
			String meta[] = s.split(":");
			String metakey = meta[0].trim();
			String metavalue = meta[1].trim();
			hashmap.put(metakey, metavalue);
		}
	}
	
	private void getProductPriceData()
	{
		List<WebElement> ele = util.waitForAllElementVisible(DEFAULT_TIMEOUT, productpricedata);
		String productprice = ele.get(0).getText();
		String extaxprice = ele.get(1).getText().split(":")[1].trim();
		hashmap.put("productprice", productprice);
		hashmap.put("extaxprice", extaxprice);
	}
}
