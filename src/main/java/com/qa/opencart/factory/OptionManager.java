package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {
	
	private Properties prop;
	
	public OptionManager(Properties prop)
	{
		this.prop = prop;	
	}	
	
	public ChromeOptions getChromeOptions()
	{
		ChromeOptions option = new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			System.out.println("Running in the headless mode");
			option.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			System.out.println("Running in the incognito mode");
			option.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			option.setCapability("browserName", "chrome");
			option.setBrowserVersion(prop.getProperty("browserversion").trim());

			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			option.setCapability("selenoid:options", selenoidOptions);

		}
		return option ;
	}
	
	public FirefoxOptions getFirefoxoptions()
	{
		FirefoxOptions optfir = new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			System.out.println("Running in the headless mode");
			optfir.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			System.out.println("Running in the incognito mode");
			optfir.addArguments("--incognito");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			optfir.setCapability("browserName", "firefox");
			optfir.setBrowserVersion(prop.getProperty("browserversion").trim());

			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			optfir.setCapability("selenoid:options", selenoidOptions);

		}
		return optfir ;
	}
	
	public EdgeOptions getEdgeOptions()
	{
		EdgeOptions op = new EdgeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			System.out.println("Running in the headless mode");
			op.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			System.out.println("Running in the incognito mode");
			op.addArguments("--inprivate");
		}
		if(Boolean.parseBoolean(prop.getProperty("remote")))
		{
			op.setCapability("browserName", "edge");
		}
		
		return op ;
	}

}
