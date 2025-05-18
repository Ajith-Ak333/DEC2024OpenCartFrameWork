package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

import io.qameta.allure.Step;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionManager op;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	public static String highelement;
	public static final Logger log = LogManager.getLogger(DriverFactory.class);

	/**
	 * This method is used to init the driver on the basis of given browser name
	 * 
	 * @param browserName
	 */

	@Step("init the driver with properties:{0}")
	public WebDriver initDriver(Properties prop) {
		
		log.info("properties:" + prop);
		
		ChainTestListener.log("Properties used:" + prop);
		
		String browserName = prop.getProperty("browser");
		//System.out.println("Browser name is " + browserName);
		log.info("browser name : " + browserName);
		
		ChainTestListener.log("browser name:" + browserName);
		
		op = new OptionManager(prop);

		highelement = prop.getProperty("highelement");

		switch (browserName.toLowerCase()) {
		case "chrome":
			tldriver.set(new ChromeDriver(op.getChromeOptions()));
			break;
		case "firefox":
			tldriver.set(new FirefoxDriver());
			break;
		case "edge":
			tldriver.set(new EdgeDriver(op.getEdgeOptions()));
			break;
		case "safari":
			tldriver.set(new SafariDriver());
			break;
		default:
			log.error("Plz pass the valid browser name " + browserName);
			throw new BrowserException("This is not valid browser");
		}

		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		return getDriver();

	}
	
	
	/**
	 * getDriver: get the local thread copy of the driver
	 */
	
	public static WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * This method is used to init the config properties
	 * 
	 * @return
	 */

	public Properties initProp() {
		prop = new Properties();

		String envname = System.getProperty("env");
		FileInputStream ip = null;

		try {
			if (envname == null) {
				//System.out.println("env is null, hence running the tests on QA env");
				log.warn("env is null, hence running the tests on QA env");
				
				ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			} else {
				//System.out.println("Running tests on env:" + envname);
				log.info("Running tests on env:" + envname);
				switch (envname.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
					break;

				default:
					log.error("----Invalid env name----" + envname);
					throw new FrameworkException("==INVALID ENV NAME==" + envname);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	
	/**
	 * takescreenshot
	 */

	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);

	}
}
