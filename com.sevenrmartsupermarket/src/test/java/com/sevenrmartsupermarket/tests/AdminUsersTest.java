package com.sevenrmartsupermarket.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.listeners.RetryAnalyser;
import com.sevenrmartsupermarket.pages.AdminUsersPage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class AdminUsersTest extends Base {

	AdminUsersPage adminUsersPage;
	LoginPage loginPage;
	ExcelReader excelReader = new ExcelReader();

	@Test
	public void createUser() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		loginPage.login();
		excelReader.setExcelFile("CreateUserData", "Create User");
		adminUsersPage.adminUsersMenuClick();
		String username = excelReader.getCellData(0, 0);
		String password = excelReader.getCellData(0, 1);
		String usertype = excelReader.getCellData(0, 2);
		adminUsersPage.createAdminUser(username, password, usertype);
		Assert.assertTrue(adminUsersPage.isMessageAppeared(), "User is not created");
	}

	@Test(groups = "Smoke Test", retryAnalyzer = RetryAnalyser.class)

	public void verifyUserDeactivation() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		loginPage.login();
		adminUsersPage.adminUsersMenuClick();
		adminUsersPage.deActivateUser("Switha");
	}

	@Test
	public void verifyGetUserDetails() {
		System.out.println(GeneralUtility.getRandomFullName());
		System.out.println(GeneralUtility.getRandomFirstName());
		System.out.println(GeneralUtility.getRandomLastName());
		System.out.println(GeneralUtility.getRandomAddress());
	}

	@Test(groups = "Sanity Test")

	public void verifyUserDeletion() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		loginPage.login();
		adminUsersPage.adminUsersMenuClick();
		adminUsersPage.deleteUser("Joykw");
		Assert.assertFalse(adminUsersPage.isDeleteMessageAppeared());
	}

	@Test(dataProvider = "User Login", dataProviderClass = Constants.class)
	public void verifyUser(String username, String password) {
		System.out.println(GeneralUtility.getRandomFullName());
		System.out.println(username);
		System.out.println(password);
	}

}
