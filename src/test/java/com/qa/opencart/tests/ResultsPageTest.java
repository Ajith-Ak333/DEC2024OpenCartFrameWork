package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import static com.qa.opencart.constants.AppConstants.*;

public class ResultsPageTest extends BaseTest
{
	
	@BeforeClass
	public void searchSetup()
	{
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));		
	}
	
	@Description("Check search feature test...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Ajith")
	@Test
	public void searchTest()
	{
		resultspage = accpage.doSearch("macbook");
		int count = resultspage.getResultsProductCount();
		Assert.assertEquals(count, 3);
	}
	
	@Description("Check search feature click test...")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Ajith")
	@Test
	public void clickTest()
	{
		resultspage = accpage.doSearch("macbook");
        productinfopage = resultspage.selectProduct("MacBook Pro");
		Assert.assertEquals(productinfopage.getProductInfoPageTitle(), RESULTS_PAGE_TITLE);
	}

}
