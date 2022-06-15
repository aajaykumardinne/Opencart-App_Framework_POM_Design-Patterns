package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {
		registrationPage = loginPage.goToRegisterPage();
	}

	public String getRandomEmail() {
		Random randomGenerator =  new Random();
		String email = "automation"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	
	@DataProvider
	public Object[][] getRegisterData() {
//		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return new Object[] [] {
			{"anil", "ok", getRandomEmail(), "4584212133", "automatic@12", "yes"}, 
		};
	}
	
	
	@Test(dataProvider = "getRegisterData")
	public void accountRegistrationTest(String firstName, String lastName, String email, String telephone , String password, String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firstName, lastName, email, telephone, password, subscribe));

	}

}
