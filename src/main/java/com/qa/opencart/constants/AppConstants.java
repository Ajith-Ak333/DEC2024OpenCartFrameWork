package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	public static final int DEFAULT_TIMEOUT = 5;
	public static final int MEDIUM_DEFAULT_TIMEOUT = 10;
	public static final int LONG_DEFAULT_TIMEOUT = 15;

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String HOME_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_TITLE = "Search - macbook";
	public static final String RESULTS_PAGE_TITLE = "MacBook Pro";


	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String HOME_PAGE_FRACTION_URL = "route=account/account";

	public static final List<String> expheaderlist = List.of("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	//******************************** Sheet Name *********************************
	
	public static final String REGISTER_SHEET_NAME = "Register";	
	public static final String REGISTER_SHEET1_NAME = "Product";	

}
