package com.sevenrmartsupermarket.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.ManagePaymentPage;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class ManagePaymentTest extends Base {

	LoginPage loginPage;
	ManagePaymentPage managePaymentPage;

	@Test
	public void verifyEditPaymentMethod() {
		loginPage = new LoginPage(driver);
		managePaymentPage = new ManagePaymentPage(driver);
		loginPage.login();
		managePaymentPage.managePaymentMenuClick();
		boolean resultMessage = managePaymentPage.editPayment("Credit card", "100000");
		Assert.assertTrue(resultMessage, "Edit payment was not successful..!");

	}
}
