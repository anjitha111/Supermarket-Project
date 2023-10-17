package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.pages.PushNotificationsPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.Screenshot;

public class PushNotificationsTest extends Base {

	PushNotificationsPage pushNotificationsPage;
	LoginPage loginPage;
	ExcelReader excelreader = new ExcelReader();

	@Test
	public void verifyPushNotificationsSuccessMessage() {
		loginPage = new LoginPage(driver);
		loginPage.login();
		excelreader.setExcelFile("NotificationsData", "Notifications Inputs");
		pushNotificationsPage = new PushNotificationsPage(driver);
		pushNotificationsPage.clickOnPushNotifications();
		String title = excelreader.getCellData(0, 0);
		String description = excelreader.getCellData(1, 0);
		pushNotificationsPage.sendNotifications(title, description);
		Assert.assertEquals(pushNotificationsPage.getSuccessMessage(), "Alert!", "Message didn't send");
	}
}
