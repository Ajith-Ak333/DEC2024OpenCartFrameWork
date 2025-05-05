package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;



@Feature("F 60: Open Cart - Login Feature")
@Epic("Epic 200: design pages for open cart application")
@Story("US 201: implement Accounts page for open cart application")
public class AccountsPageTest extends BaseTest{
	
	
	@BeforeClass
	public void accSetup()
	{
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));		
	}
	
	@Description("checking open cart Acc page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Ajith")
	@Test
	public void accPageTitleTest()
	{
		String title = accpage.getAccPageTitle();
		Assert.assertEquals(title, HOME_PAGE_TITLE);
	}
	
	@Description("checking open cart acc page url ...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Ajith")
	@Test
	public void accPageUrlTest()
	{
		String url = accpage.getAccPageUrl();
		Assert.assertTrue(url.contains(HOME_PAGE_FRACTION_URL));
	}
	
	@Description("checking open cart acc page headers...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Ajith")
	@Test
	public void accPageHeadersTest()
	{
		List<String> list = accpage.getAccPageHeaders();
		Assert.assertEquals(list, expheaderlist);
	}

}
