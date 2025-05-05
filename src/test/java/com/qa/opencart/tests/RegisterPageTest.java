package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerInfoSetup() {
		registerpage = loginpage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] { { "vishal", "mehta", "9894708912", "vishal@123", "vishal@123", "yes" },
				{ "jyothi", "sharma", "9566779123", "jyothi@123", "jyothi@123", "no" },
				{ "archi", "verma", "9750326957	", "archi@123", "archi@123", "yes" } };
	}

	@DataProvider
	public Object[][] getUserRegData() {
		Object[][] ob = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return ob;
	}

	@Test(dataProvider = "getUserRegData")
	public void userRegisterTest(String firstname, String lastname, String telephone, String password,
			String confirmpassword, String subscribe) {
		Assert.assertTrue(registerpage.navigateToRegisterPage(firstname, lastname, telephone, password, confirmpassword,
				subscribe));
	}
}
