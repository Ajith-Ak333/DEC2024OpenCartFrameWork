package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;

@Feature("F 50: Open Cart - Login Feature")
@Epic("Epic 100: design pages for open cart application")
@Story("US 101: implement login page for open cart application")
public class LoginPageTest extends BaseTest{
	
	@Description("Checking open cart login page title...")
    @Severity(SeverityLevel.MINOR)
	@Owner("Ajith")
	@Test(description = "checking login page title")
	public void loginPageTitleTest()
	{
		System.out.println("Starting test case");
		String title = loginpage.loginPageTitle();
		ChainTestListener.log("Checking on loginpage : " + title);
		Assert.assertEquals(title, LOGIN_PAGE_TITLE);
		System.out.println("Ending test case");
	}
	
	@Description("Checking open cart login page url...")
    @Severity(SeverityLevel.NORMAL)
	@Owner("Ajith")
	@Test(description = "checking login page url")
	public void loginPageUrlTest()
	{
		String url = loginpage.loginPageUrl();
		Assert.assertTrue(url.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Description("Checking open cart login page has forgot pwd link...")
    @Severity(SeverityLevel.CRITICAL)
	@Owner("Ajith")
	@Test(description = "ForgetPwdLinkTest")
	public void loginPageForgetLinkTest()
	{
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Description("Check user able to check valid user credentials..")
    @Severity(SeverityLevel.BLOCKER)
	@Owner("Ajith")
	@Test(priority = Short.MAX_VALUE,description = "login with valid credentials")
	public void doLoginTest()
	{
		accpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accpage.getAccPageTitle(), HOME_PAGE_TITLE);
	}
	
	@Test(enabled=false,description = "WIP -- forgot pwd check")
	public void forgotPwd()
	{
		System.out.println("This is still inprogress");
	}

}
