package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	private By email = By.id("input-email");
	private By pwd = By.id("input-password");
	private By login = By.xpath("//input[@type='submit']");
	private By forget = By.xpath("(//a[text()='Forgotten Password'])[1]");
	
	private By register = By.linkText("Register");
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	@Step("Getting login page title")
	public String loginPageTitle()
	{
		String title = util.waitForTitleIs(DEFAULT_TIMEOUT, LOGIN_PAGE_TITLE);
		System.out.println("Title name is " + title);
		return title;
	}
	
	@Step("Getting login page url")
	public String loginPageUrl()
	{
		String url = util.waitForURLContains(DEFAULT_TIMEOUT, LOGIN_PAGE_FRACTION_URL);
		System.out.println("Url name is " + url);
		return url;
	}
	
	@Step("Checking forgot pwd link exist")
	public Boolean isForgotPwdLinkExist()
	{
		return util.isElementdisplayed(forget);
	}
	
	@Step("Login with valid username:{0} and password:{1}")
	public AccountsPage doLogin(String username,String password)
	{
		System.out.println("User credentials: " + username + ":" + password);
		util.waitForElementVisible(MEDIUM_DEFAULT_TIMEOUT, email).sendKeys(username);
		util.doSendKeys(pwd, password);
		util.doclick(login);
		
		return new AccountsPage(driver);	
	}
	
	@Step("Navigating to the registeration page")
	public RegisterPage navigateToRegisterPage()
	{
		util.clickWithWait(DEFAULT_TIMEOUT,register);
		
		return new RegisterPage(driver);
	}
	
	
}
