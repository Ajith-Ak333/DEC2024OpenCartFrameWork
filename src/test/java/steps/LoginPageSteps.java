package steps;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.junit.Assert;
import org.testng.Assert;
import utils.ScenarioContext;

public class LoginPageSteps {

    private final LoginPage loginPage;
    private AccountsPage accPage;
    private final ScenarioContext scenario;

   // String actTitle;

    public LoginPageSteps(Hooks hooks,ScenarioContext scenariocontext) {
        this.loginPage = hooks.getLoginPage();
        this.scenario = scenariocontext;
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Assuming the driver is already on the login page
    }

    @When("the user fetches the page title")
    public void the_user_fetches_the_page_title() {
        String actTitle = loginPage.loginPageTitle();
        scenario.setContext("LOGIN_PAGE_TITLE",actTitle);
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        Assert.assertEquals(scenario.getContext("LOGIN_PAGE_TITLE"), expectedTitle);
    }

    @Then("the page title should not be empty")
    public void the_page_title_should_not_be_empty() {
        Assert.assertFalse(scenario.getContext("LOGIN_PAGE_TITLE").toString().isEmpty(), "Page title should not be empty");
    }

    @When("the user checks the page URL")
    public void the_user_checks_the_page_url() {
        String actURL = loginPage.loginPageUrl();
        scenario.setContext("LOGIN_PAGE_URL",actURL);
        Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
    }

    @Then("the URL should contain {string}")
    public void the_url_should_contain(String fractionURL) {
        Assert.assertTrue(scenario.getContext("LOGIN_PAGE_URL").toString().contains(fractionURL));
    }

    @When("the user checks the forgot password link")
    public void the_user_checks_the_forgot_password_link() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @Then("the forgot password link should be displayed")
    public void the_forgot_password_link_should_be_displayed() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        accPage = loginPage.doLogin(username, password);
        scenario.setContext("ACC_PAGE", accPage);
    }

    @Then("the user should be redirected to the accounts page with title {string}")
    public void the_user_should_be_redirected_to_the_accounts_page_with_title(String expectedTitle) {
        AccountsPage accPage = (AccountsPage) scenario.getContext("ACC_PAGE");
        String actualTitle = accPage.getAccPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

    }
}