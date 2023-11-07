package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class ManagePaymentPage {

	PageUtility pageUtility;
	WebDriver driver;
	GeneralUtility generalUtility;
	WaitUtility waitUtility;

	@FindBy(xpath = "//i[@class='nav-icon fas fa-credit-card']//following-sibling::p")
	private WebElement managePaymentMenu;
	@FindBy(xpath = "//div[@class='card-body table-responsive p-0']//tr[1]//td[4]//i[@class='fas fa-edit']")
	private WebElement editButton;
	@FindBy(xpath = "//input[@id='name']")
	private WebElement titleField;
	@FindBy(xpath = "//input[@name='limit']")
	private WebElement payLimitField;
	@FindBy(xpath = "//button[@name='Update']")
	private WebElement updateButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successMessage;

	public ManagePaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public boolean editPayment(String title, String payLimit) {
		boolean result;
		editButtonClick();
		return result = updateData(title, payLimit);
	}

	public void managePaymentMenuClick() {
		pageUtility = new PageUtility(driver);
		pageUtility.JsScrollIntoView(managePaymentMenu);
		managePaymentMenu.click();
	}

	public void editButtonClick() {
		waitUtility = new WaitUtility(driver);
		waitUtility.waitForElementToBeClickable(editButton, 50l);
		editButton.click();
	}

	public boolean updateData(String title, String payLimit) {
		titleField.clear();
		titleField.sendKeys(title);
		payLimitField.clear();
		payLimitField.sendKeys(payLimit);
		updateButton.click();
		return successMessage.getText().contains("Payment Method Updated Successfully");
	}

}
