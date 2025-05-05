package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

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
		return option ;
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
		
		return op ;
	}

}
