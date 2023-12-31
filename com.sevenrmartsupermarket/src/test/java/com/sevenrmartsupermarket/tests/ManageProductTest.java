package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.ManagePaymentPage;
import com.sevenrmartsupermarket.pages.ManageProductPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;

public class ManageProductTest extends Base {
	LoginPage loginPage;
	ManageProductPage manageProductPage;
	ExcelReader excelReader = new ExcelReader();

	@Test
	public void verifySearchProduct() {
		loginPage = new LoginPage(driver);
		manageProductPage = new ManageProductPage(driver);
		loginPage.login();
		manageProductPage.manageProductMenu();
		excelReader.setExcelFile("ManageProduct", "SearchProduct");
		String product = excelReader.getCellData(0, 0);
		boolean result = manageProductPage.searchByTitle(product);
		Assert.assertFalse(result, "Searched item is not in the list.");
	}
}
