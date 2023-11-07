package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.ManagePaymentPage;
import com.sevenrmartsupermarket.pages.ManageProductPage;

public class ManageProductTest extends Base {
	LoginPage loginPage;
	ManageProductPage manageProductPage;

	@Test
	public void verifySearchProduct() {
		loginPage = new LoginPage(driver);
		manageProductPage = new ManageProductPage(driver);
		loginPage.login();
		manageProductPage.manageProductMenu();
		boolean result = manageProductPage.searchByTitle("Product1");
		Assert.assertTrue(result, "Searched item is not in the list.");
	}
}
