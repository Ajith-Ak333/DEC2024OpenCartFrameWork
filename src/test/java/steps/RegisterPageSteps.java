package steps;

import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;
import io.cucumber.java.en.*;

import org.junit.Assert;
import utils.ScenarioContext;

public class RegisterPageSteps {

    private Hooks hooks;
    private final LoginPage loginPage;
    private RegisterPage registerPage;
    private final ScenarioContext scenario;

    //private boolean regSuccess;

    public RegisterPageSteps(Hooks hooks, ScenarioContext scenarioContext) {
        this.hooks = hooks;
        this.loginPage = hooks.getLoginPage();
        this.scenario = scenarioContext;
    }

    @Given("the user navigates to the registration page")
    public void the_user_navigates_to_the_registration_page() {
        registerPage = loginPage.navigateToRegisterPage();
    }

    @When("the user enters {string}, {string}, {string}, {string} and subscribes {string}")
    public void the_user_enters_and_subscribes(String firstName, String lastName, String telephone, String password, String subscribe) {

        boolean regSuccess = registerPage.navigateToRegisterPage(firstName, lastName,telephone, password,subscribe);
        scenario.setContext("REG_SUCCESS", regSuccess);
    }

    @Then("the user registration should be successful")
    public void the_user_registration_should_be_successful() {
        Assert.assertTrue((Boolean) scenario.getContext("REG_SUCCESS"));
    }

}