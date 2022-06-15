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

	@Test
	public void productHeaderTest() {
		searchResultsPage = accountsPage.doSearch("Samsung");
		productInfoPage = searchResultsPage.selectProduct("Samsung Galaxy Tab 10.1");
		Assert.assertEquals(productInfoPage.getProductHeader(), "Samsung Galaxy Tab 10.1");
	}

	@Test
	public void productImagesCountTest() {
		searchResultsPage = accountsPage.doSearch("Apple");
		productInfoPage = searchResultsPage.selectProduct("Apple Cinema 30\"");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), Constants.APPLE_IMAGES_COUNT);
	}

	@Test
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

}
