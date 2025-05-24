package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;
import com.qa.opencart.utils.LogUtil;

import io.qameta.allure.Description;

//@Listeners(ChainTestListener.class)
public class BaseTest {

	DriverFactory dr;
	WebDriver driver;
	protected LoginPage loginpage;
	protected Properties prop;
	protected AccountsPage accpage;
	protected ResultsPage resultspage;
	protected ProductInfoPage productinfopage;
	protected RegisterPage registerpage;
	
	public static final Logger log = LogManager.getLogger(BaseTest.class);

	@Description("init the driver and properties")
	@Parameters({ "browser","browserversion","testname" })
	@BeforeTest
	public void setUp(String browsername,String browserversion,String testname) {
		dr = new DriverFactory();
		prop = dr.initProp();

		if (browsername != null) {
			prop.setProperty("browser", browsername);
			prop.setProperty("browserversion", browserversion);
			prop.setProperty("testname", testname);
		}

		driver = dr.initDriver(prop);
		loginpage = new LoginPage(driver);
	}

//	@BeforeMethod
//	public void beforemethod(ITestContext result)
//	{
//		LogUtil.info("---starting test case---" + result.getName());	
//	}

	@AfterMethod // will be running after each @test method
	public void attachScreenshot(ITestResult result) {
		if (!result.isSuccess()) {// only for failure test cases -- true
			log.info("---screenshot is taken---");
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		// LogUtil.info("---starting test case---" +
		// result.getMethod().getMethodName());
		// ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
	}

	@Description("close the browser..")
	@AfterTest
	public void tearDown() {
		driver.close();
		log.error("---Closing the browser---");
	}
}