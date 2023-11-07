package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;

public class HomeTest extends Base {

	HomePage homePage;
	LoginPage loginPage;

	@Test
	public void verifyManagePageLinkClick() {
		loginPage = new LoginPage(driver);
		loginPage.login();
		homePage = new HomePage(driver);
		String result = homePage.clickOnManagePageMoreInfoLinkFromHome();
		String expectedText = "List Pages";
		Assert.assertEquals(expectedText, result, "Expected and actual data are not matching.");
	}

}
