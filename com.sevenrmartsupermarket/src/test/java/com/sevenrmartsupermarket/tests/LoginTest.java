package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.Screenshot;

public class LoginTest extends Base {

	LoginPage loginPage;
	HomePage homePage;
	ExcelReader excelReader = new ExcelReader();

	@Test
	public void verifyLoginFunctionality() {
		loginPage = new LoginPage(driver);
		homePage= new HomePage(driver);
		loginPage.login();
		String actualProfileName = homePage.getProfileName();
		String expectedProfileName = "Admin";
		Assert.assertEquals(actualProfileName, expectedProfileName, "Both are not matching.");
	}

	@Test
	public void verifyInvalidLoginErrorMessage() {
		loginPage = new LoginPage(driver);
		String expected_text = "Invalid Username/Password";
		Assert.assertTrue(loginPage.login("Devika", "adm"), "User credentials are valid.");
	}

	@Test
	public void verifyStaffLogin() {
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		excelReader.setExcelFile("LoginData", "Staff Credentials");
		String username = excelReader.getCellData(0, 0);
		String password = excelReader.getCellData(0, 1);
		loginPage.loginProfileCheck(username, password);
		Assert.assertEquals(homePage.getProfileName(), "Anjitha", "Wrong profile launched");
	}
}
