package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	// 1. declare private WebDriver
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 2. page constructor is used to intialize the driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.cssSelector("input.btn.btn-primary");

	private By registerLink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");

	private By loginErrorMesg = By.cssSelector("div.alert.alert-danger.alert-dismissible ");

	// 4. page actions

	@Step("getting the login page title value....")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_TITLE);
	}

	@Step("getting the login page url....")
	public String getLoginPageUrl() {
		return eleUtil.waitForUrl(Constants.DEFAULT_TIME_OUT, Constants.LOGIN_PAGE_URL_FRACTION);
	}
	
	@Step("checking the forgot password link exist or not ....")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("checking the register link exist or not....")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	
	@Step("do login with username: {0} and password: {1}....")
	public AccountsPage doLogin(String un , String pwd) {
		System.out.println("login with : " + un + " : " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
	
	@Step("do login with wrong username: {0} and wrong password: {1}....")
	public boolean doLoginWithWrongCred(String un , String pwd) {
		System.out.println("try to login with wrong credentials: " + un + " : " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		String errorMesg = eleUtil.doElementGetText(loginErrorMesg);
		System.out.println(errorMesg);
		if(errorMesg.contains(Constants.LOGIN_ERROR_MESSAGE)) {
			System.out.println("login is not working");
			return false;
		}
		return true;
	}
	
	@Step("navigating to the register page")
	public RegistrationPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
}
