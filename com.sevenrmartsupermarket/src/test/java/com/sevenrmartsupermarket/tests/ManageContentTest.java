package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.pages.AdminUsersPage;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.ManageContentPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;

public class ManageContentTest extends Base {

	ManageContentPage manageContentPage;
	LoginPage loginPage;
	AdminUsersPage adminUsersPage;
	ExcelReader excelReader = new ExcelReader();

	@Test(groups = { "Smoke Test", "Sanity Test" })
	public void managePage() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		manageContentPage = new ManageContentPage(driver);
		loginPage.login();
		excelReader.setExcelFile("CreatePage", "Page Input");
		String title = excelReader.getCellData(0, 0);
		String description = excelReader.getCellData(0, 1);
		String text = excelReader.getCellData(0, 2);
		manageContentPage.addPages(title, description, text);
		Assert.assertFalse(manageContentPage.isSuccessMessageDisplayed(), "Page got added succcessfully.");
	}
}
