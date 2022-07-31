package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	// pre-conditions before moving to the Product info page
	@BeforeClass
	public void ProductInfoPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultsPage = accountsPage.doSearch("Samsung");
		productInfoPage = searchResultsPage.selectProduct("Samsung Galaxy Tab 10.1");
		Assert.assertEquals(productInfoPage.getProductHeader(), "Samsung Galaxy Tab 10.1");
	}

	@Test(priority = 2)
	public void productImagesCountTest() {
		searchResultsPage = accountsPage.doSearch("Apple");
		productInfoPage = searchResultsPage.selectProduct("Apple Cinema 30\"");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.APPLE_IMAGES_COUNT);
	}

	@Test(priority = 3)
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("Mac");
		productInfoPage = searchResultsPage.selectProduct("iMac");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();

//		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));

		softAssert.assertEquals(actProductInfoMap.get("name"), "iMac");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 14");
		softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");

		softAssert.assertEquals(actProductInfoMap.get("Price"), "$122.00");
		softAssert.assertEquals(actProductInfoMap.get("Ex Tax"), "$100.00");

		softAssert.assertAll();
	}

	
	@Test(priority = 4)
	public void productQuantityTest() {
		searchResultsPage = accountsPage.doSearch("Samsung");
		productInfoPage = searchResultsPage.selectProduct("Samsung Galaxy Tab 10.1");
		Assert.assertTrue(productInfoPage.getProductQuantity("2"));
	}
	
	
	@Test(priority = 5)
	public void addToCartTest() {
		searchResultsPage = accountsPage.doSearch("Samsung");
		productInfoPage = searchResultsPage.selectProduct("Samsung Galaxy Tab 10.1");
		Assert.assertTrue(productInfoPage.getAddToCart());
	}
}
