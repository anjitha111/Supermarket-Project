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
	SoftAssert softAssert = new SoftAssert();

	@Test
	public void createUser() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		loginPage.login();
		excelReader.setExcelFile("CreateUserData", "Create User");
		adminUsersPage.adminUsersMenuClick();
		String username = excelReader.getCellData(0, 0);
		String password = excelReader.getCellData(0, 1);
		String userType = excelReader.getCellData(0, 2);
		adminUsersPage.createAdminUser(username, password, userType);
		Assert.assertTrue(adminUsersPage.isMessageAppeared(), "User is not created");
	}

	@Test(groups = "Smoke Test")
	public void verifyUserDeactivation() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		loginPage.login();
		adminUsersPage.adminUsersMenuClick();
		excelReader.setExcelFile("AdminUserData", "Admin User Input");
		String existingStatus = adminUsersPage.deActivateUser(excelReader.getCellData(0, 0));
		if (existingStatus.equals("User is already deactivated")) {
			Assert.assertTrue(false, "User is already in deactivated status.");
		} else if (existingStatus.equals("Inactive")) {
			Assert.assertTrue(adminUsersPage.isStatusChangeMessageAppeared(), "User status got changed successfully.");
		}
	}

	@Test(groups = "Regression Test", priority = 2, retryAnalyzer = RetryAnalyser.class)
	public void verifyUserActivation() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		loginPage.login();
		adminUsersPage.adminUsersMenuClick();
		excelReader.setExcelFile("AdminUserData", "Admin User Input");
		String existingStatus = adminUsersPage.activateUser(excelReader.getCellData(4, 0));
		if (existingStatus.equals("User is already activated.")) {
			Assert.assertFalse(true, "User is already in activated status!!!");
		} else if (existingStatus.equals("Active")) {
			Assert.assertTrue(adminUsersPage.isStatusChangeMessageAppeared(), "User status got changed successfully.");
		}

	}

	@Test(groups = "Sanity Test", priority = 3)
	public void verifyEditUserName() {
		loginPage = new LoginPage(driver);
		loginPage.login();
		adminUsersPage = new AdminUsersPage(driver);
		adminUsersPage.adminUsersMenuClick();
		excelReader.setExcelFile("AdminUserData", "Admin User Input");
		adminUsersPage.clickOnEditUser(excelReader.getCellData(0, 0));
		softAssert.assertTrue(adminUsersPage.isEditMessageAppeared(), "User edit was not successful.");
		adminUsersPage.closeAlert();
		boolean isUpdatedUserPresent = adminUsersPage.isPersonAppearingInSearch("Ishaan");
		softAssert.assertTrue(isUpdatedUserPresent, "User details are not updated.");
		softAssert.assertAll();
	}

	@Test(groups = "Smoke Test", priority = 4)
	public void isPersonAppearingInSearch() {
		loginPage = new LoginPage(driver);
		loginPage.login();
		adminUsersPage = new AdminUsersPage(driver);
		adminUsersPage.adminUsersMenuClick();
		excelReader.setExcelFile("AdminUserData", "Admin User Input");
		boolean isUpdatedUserPresent = adminUsersPage.isPersonAppearingInSearch(excelReader.getCellData(1, 0));
		Assert.assertTrue(isUpdatedUserPresent, "User does not exist!!!");
	}

	@Test(groups = "Sanity Test", priority = 5)
	public void verifyUserDeletion() {
		loginPage = new LoginPage(driver);
		adminUsersPage = new AdminUsersPage(driver);
		loginPage.login();
		adminUsersPage.adminUsersMenuClick();
		adminUsersPage.deleteUser("Margerette");
		boolean isUpdatedUserPresent = adminUsersPage.isPersonAppearingInSearch("Margerette");
		Assert.assertFalse(adminUsersPage.isDeleteMessageAppeared());
	}
}
