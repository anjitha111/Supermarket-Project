package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class PushNotificationsPage {

	GeneralUtility generalUtility;
	WebDriver driver;

	@FindBy(xpath = "//p[text()='Push Notifications']")
	private WebElement pushNotificationsMenu;
	@FindBy(xpath = "//input[@id='title']")
	private WebElement titleField;
	@FindBy(xpath = "//input[@id='description']")
	private WebElement descriptionField;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement sendButton;

	@FindBy(xpath = "//h5[text()=' Alert!']")
	WebElement successMessage;

	public PushNotificationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void clickOnPushNotifications() {
		pushNotificationsMenu.click();
	}

	public void enterTitle(String title) {
		titleField.sendKeys(title);
	}

	public void enterDescription(String description) {
		descriptionField.sendKeys(description);
	}

	public void clickOnSendButton() {
		sendButton.click();
	}

	public void sendNotifications(String title, String description) {
		enterTitle(title);
		enterDescription(description);
		clickOnSendButton();
	}

	public String getSuccessMessage() {
		generalUtility = new GeneralUtility();
		return generalUtility.getTextOfElement(successMessage);
	}
}
