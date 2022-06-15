package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] { 
			{ "dinneqer@gmail.com", "hyderabad" }, 
			{ " ", "toronto" }, 
			{ "", "" } 
		};
	}

//	"" -- empty character
	
	@Test(dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String username, String password) {
		Assert.assertFalse(loginPage.doLoginWithWrongCred(username, password));
	}
}
