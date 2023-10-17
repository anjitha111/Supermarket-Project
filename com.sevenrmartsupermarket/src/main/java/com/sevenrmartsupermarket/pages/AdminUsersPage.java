package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class AdminUsersPage {

	PageUtility pageUtility;
	WebDriver driver;
	GeneralUtility generalUtility;
	WaitUtility waitUtility;

	@FindBy(xpath = "//i[@class='nav-icon fas fa-users']//following-sibling::p")
	private WebElement adminUsersMenu;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//input[@id='username']")
	private WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	private WebElement passWord;
	@FindBy(xpath = "//select[@id='user_type']")
	private WebElement userType;
	@FindBy(xpath = "//button[@name='Create']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successMessage;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private List<WebElement> namesList;

	public AdminUsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void createAdminUser(String username, String password, String usertype) {
		clickOnNewButton();
		enterUserName(username);
		enterPassword(password);
		selectUserType(usertype);
		clickOnSaveButton();
	}

	public void adminUsersMenuClick() {
		adminUsersMenu.click();
	}

	public void clickOnNewButton() {
		newButton.click();
	}

	public void enterUserName(String username) {
		userName.sendKeys(username);
	}

	public void enterPassword(String password) {
		passWord.sendKeys(password);
	}

	public void selectUserType(String usertype) {
		userType.sendKeys(usertype);
	}

	public void clickOnSaveButton() {
		generalUtility = new GeneralUtility();
		waitUtility = new WaitUtility(driver);
		waitUtility.waitForElementToBeClickable(saveButton, 10l);
		saveButton.click();
	}

	public boolean isMessageAppeared() {
		return generalUtility.isTextPresent(successMessage, "User Created Successfully");
	}

	public boolean isDeleteMessageAppeared() {
		return generalUtility.isTextPresent(successMessage, "User Status Changed Successfully");

	}
	public void deActivateUser(String personName) {
		int index = 0;
		generalUtility = new GeneralUtility();
		pageUtility = new PageUtility(driver);
		List<String> names = new ArrayList();
		names = generalUtility.getTextOfElements(namesList);
		for (String name : names) {
			if (personName.equals(name)) {
				index++;
				break;
			}
			index++;
		}
		WebElement deactivateButton = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-hover table-sm']//tr[" + index + "]//td[5]/a[1]"));
		pageUtility.scrollAndClick(deactivateButton);
	}
	public void deleteUser(String personName) {
		int index = 0;
		generalUtility = new GeneralUtility();
		pageUtility = new PageUtility(driver);
		List<String> names = new ArrayList();
		names = generalUtility.getTextOfElements(namesList);
		for (String name : names) {
			if (personName.equals(name)) {
				index++;
				break;
			}
			index++;
		}
		WebElement deleteButton = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-hover table-sm']//tr[" + index + "]//td[5]/a[3]"));
		pageUtility.scrollAndClick(deleteButton);
		driver.switchTo().alert().accept();
	}
}
