package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	
	public static final String LOGIN_PAGE_URL_FRACTION ="route=account/login";
	
	public static final int DEFAULT_TIME_OUT = 7;
	
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	
	public static final int  APPLE_IMAGES_COUNT = 6;
	
	public static final String LOGIN_ERROR_MESSAGE = "No match for E-Mail Address and/or Password.";
	
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	
	public static final String TEST_DATA_SHEET_PATH = "C:\\Users\\dinne\\eclipse-workspace\\Framework_EcommerceApp\\src\\test\\resources\\testdata\\RegPageData.xlsx";
	
	public static final String REGISTER_SHEET_NAME = "registrationdata";
	
	public static final String PRODUCT_SHEET_NAME = "accountPageSearch";

	public static final String SAMSUNG_CART_SUCCESS_MESG = "Success: You have added Samsung Galaxy Tab 10.1 to your shopping cart!";
	
	
	public static List<String> getExpectedAccSectionsList() {
		// top-casting
		List<String> expSecList = new ArrayList<String>();
		expSecList.add("My Account");
		expSecList.add("My Orders");
		expSecList.add("My Affiliate Account");
		expSecList.add("Newsletter");
		
		return expSecList;
	}
	
	

}
