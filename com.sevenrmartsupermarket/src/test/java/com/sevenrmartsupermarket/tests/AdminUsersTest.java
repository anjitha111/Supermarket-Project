package com.sevenrmartsupermarket.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
	SoftAssert softassert = new SoftAssert(); 

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
		Assert.assertFalse(adminUsersPage.isMessageAppeared(), "User status got changed successfully.");
	}
	@Test(groups = "Regression Test",priority=2)
	public void verifyUserActivation() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		loginPage.login();
		adminUsersPage.adminUsersMenuClick();
		excelReader.setExcelFile("AdminUserData", "Admin User Input"); 
		adminUsersPage.activateUser(excelReader.getCellData(3, 0));		
		Assert.assertFalse(adminUsersPage.isMessageAppeared(), "User status got changed successfully.");
	}
	@Test(groups = "Sanity Test",priority = 3) 
	 public void verifyEditUserName()
	 { 
		loginPage= new LoginPage(driver); 
		loginPage.login(); 
		adminUsersPage= new AdminUsersPage(driver); 
		adminUsersPage.adminUsersMenuClick();
		excelReader.setExcelFile("AdminUserData", "Admin User Input");
		adminUsersPage.clickOnEditUser(excelReader.getCellData(0, 0));
		Assert.assertTrue(adminUsersPage.isEditMessageAppeared(), "User edit was not successful.");
		boolean isUpdatedUserPresent = adminUsersPage.searchUser("Janaki");
		Assert.assertTrue(isUpdatedUserPresent, "User details are not updated.");
	 }

	@Test(groups = "Smoke Test",priority=4) 
	public void verifySearchUser() 
	 { 
	 loginPage = new LoginPage(driver); 
	 loginPage.login(); 
	 adminUsersPage = new AdminUsersPage(driver);
	 adminUsersPage.adminUsersMenuClick(); 
	 excelReader.setExcelFile("AdminUserData", "Admin User Input"); 
	 boolean isUpdatedUserPresent = adminUsersPage.searchUser(excelReader.getCellData(1, 0)); 
	 Assert.assertTrue(isUpdatedUserPresent,"User does not exist!!!");
	 }
	
	@Test(groups = "Sanity Test",priority=5)
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
