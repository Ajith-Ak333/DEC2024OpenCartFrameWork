package steps;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.BrowserContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Hooks {

    private  DriverFactory df;
    private  WebDriver driver;
    private  Properties prop;
    private  LoginPage loginPage;

    @Before
    public void setUp() {
        df = new DriverFactory();
        prop = df.initProp();
        driver = df.initDriver(prop);
        loginPage = new LoginPage(driver);

        String browsername = BrowserContext.getBrowser();
        if(browsername != null)
        {
            prop.setProperty("browser",browsername);
        }
    }

    @After
    public  void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public Properties getProperties() {
        return prop;
    }

    private Map<String, Object> scenarioData = new HashMap<>();

    public void setScenarioData(String key, Object value) {
        scenarioData.put(key, value);
    }

    public Object getScenarioData(String key) {
        return scenarioData.get(key);
    }
}