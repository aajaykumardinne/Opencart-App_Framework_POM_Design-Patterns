package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	// declare WebDriver as private
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// private By locators
	private By image = By.cssSelector("div#logo img");
	private By accountsSection = By.cssSelector("div#content h2");
	
	private By searchField = By.cssSelector("div#search input");
	private By searchButton = By.cssSelector("div#search button");
	
	private By logoutLink = By.cssSelector("div.list-group a:last-of-type");
	
	
	// intializing the driver by declaring the page constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	// page actions
	@Step("Getting the Accounts Page Title...")
	public String getAccountsPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Step("Checking Image Exist or not...")
	public boolean isaccPageImageExist() {
		return eleUtil.doIsDisplayed(image);
	}
	
	@Step("Checking Logout Link Exist or not...")
	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutLink);
	}
	
	/*
	 * 
	 */
	@Step("Log out from the opencart application...")
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	@Step("Getting the Accounts Page Sections List")
	public List<String> getAccountsSectionList() {
		List<WebElement>	accSecList = eleUtil.getElements(accountsSection);
		//top-casting
		List<String> accSecValList = new ArrayList<String>();
		for(WebElement e : accSecList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		
		return accSecValList;
	}
	
	@Step("Checking the search field exist or not")
	public boolean isSearchFieldExist() {
		return eleUtil.doIsDisplayed(searchField);
	}

	@Step("Searching the product")
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching for the product: " +  productName);
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
		
	}
	
}
