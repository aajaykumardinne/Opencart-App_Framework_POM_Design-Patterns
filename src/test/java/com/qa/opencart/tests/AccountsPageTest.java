package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 200: Design Open Cart App - Accounts Page")
@Story("US 201: Open Cart AccountsPage Design with multiple features")
@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BaseTest {
	// execution will start from this test class

	// never use the driver apis in the test class.
	// Assertions are written in the test class

	// pre-conditions before moving to the AccountsPage class
	@BeforeClass
	public void accountPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("Accounts Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountsPageTitle();
		System.out.println("Accounts Page TITLE is: " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Description("ImageExist Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void isaccPageImageExistTest() {
		Assert.assertTrue(accountsPage.isaccPageImageExist());
	}

	@Description("Logout Link ExistTest")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}

	@Description("Accounts Page Sections List Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void accPageSectionsListTest() {
		List<String> actlAccSecList = accountsPage.getAccountsSectionList();
		Assert.assertEquals(actlAccSecList, Constants.getExpectedAccSectionsList());
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { 
			{ "Macbook" }, 
			{ "Apple" }, 
			{ "Samsung" }, };
//		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
			
	}

	// establish the connection between the @test and @dataprovider annotation
	// productName is the holding parameter for the Dataprovider annotation
	
	@Description("Search Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5, dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultsPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductListCount() > 0);

	}

	@DataProvider
	public Object[][] productSelectData() {
		return new Object[] [] {
			{"Mac" , "iMac"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			{"Apple", "Apple Cinema 30\""},
	//		{"Macbook", "Macbook Pro"},
		};
	}
	
	@Description("Selecting the Product Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=6 , dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchResultsPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName , Errors.MAIN_PRODUCTNAME_ERROR_MESG);
	}

	
}
