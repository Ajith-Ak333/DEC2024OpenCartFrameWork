package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

import static com.qa.opencart.constants.AppConstants.*;

public class ProductsPageInfoTest extends BaseTest{
	
	
	@BeforeClass
	public void productInfoSetup()
	{
		accpage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));	
	}
	
	@DataProvider
	public Object[][] getProductTestData()
	{
		return new Object[][]
		{
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}	
		};		
	}
	
	@Test(dataProvider = "getProductTestData")
	public void productHeaderTest(String searchKey,String productName)
	{
		resultspage = accpage.doSearch(searchKey);
		productinfopage = resultspage.selectProduct(productName);
		String header = productinfopage.getProductHeader();
		Assert.assertEquals(header, productName);
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData()
	{
		return new Object[][]
		{
			{"macbook","MacBook Pro",4},
			{"macbook","MacBook Air",4},
			{"imac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7}	
		};
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
	   Object[][] ob =  ExcelUtil.getTestData(REGISTER_SHEET1_NAME);
	   return ob;
		
	}
	
	@Test(dataProvider = "getProductData")
	public void productImagesCountTest(String searchKey,String productName,String expcounts)
	{
		resultspage = accpage.doSearch(searchKey);
		productinfopage = resultspage.selectProduct(productName);
		int count = productinfopage.getProductImagesCount();
		Assert.assertEquals(String.valueOf(count), expcounts);
	}
	
	@Test
	public void productInfoTest()
	{
		resultspage = accpage.doSearch("macbook");
		productinfopage = resultspage.selectProduct("MacBook Pro");
		Map<String,String> map = productinfopage.getProductDetailsMap();
		
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(map.get("Brand"),"Apple");
		softassert.assertEquals(map.get("Product Code"),"Product 18");
		softassert.assertEquals(map.get("Availability"),"Out Of Stock");	
		softassert.assertEquals(map.get("productprice"),"$2,000.00");	
		softassert.assertEquals(map.get("extaxprice"),"$2,000.00");	
		
		softassert.assertAll();
	}

}
