package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtils;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	private By firstname = By.name("firstname");
	private By lastname = By.name("lastname");
	private By regemail = By.name("email");
	private By telephone = By.name("telephone");
	private By password = By.name("password");
	private By password_confirm = By.name("confirm");
	private By subscribeyes = By.xpath("(//label[@class='radio-inline'])[1]/input[@type='radio']");
	private By subscribeno = By.xpath("(//label[@class='radio-inline'])[2]/input[@type='radio']");
	private By privacy = By.name("agree");
	private By button = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");
	
	private By regtitle = By.xpath("//div[@id='content']/h1");
	
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		util = new ElementUtil(driver);
	}
	
	public boolean navigateToRegisterPage(String firstname,String lastname,String telephone,String password,String subscribe)
	{		
		util.waitForElementVisible(DEFAULT_TIMEOUT, this.firstname).sendKeys(firstname);
		util.doSendKeys(this.lastname, lastname);
		String email = StringUtils.getRandomEmailId();
		util.doSendKeys(this.regemail, email);
		util.doSendKeys(this.telephone, telephone);
		util.doSendKeys(this.password, password);
		util.doSendKeys(this.password_confirm, password);
		
		if(subscribe.equalsIgnoreCase("yes"))
		{
		  util.doclick(subscribeyes);
		}
		else
		{
		  util.doclick(subscribeno);
		}
		
		util.doclick(privacy);
		util.doclick(button);
		
		if(util.waitForElementVisible(DEFAULT_TIMEOUT,regtitle).getText().contains(REGISTER_SUCCESS_MESSG))
		{
		  util.doclick(logout);
		  util.doclick(register);
		  return true;
		}
		else
		{
			return false;
		}
	}	

}
