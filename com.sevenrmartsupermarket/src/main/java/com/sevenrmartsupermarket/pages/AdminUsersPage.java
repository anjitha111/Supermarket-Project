package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
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

	@CacheLookup
	@FindBy(xpath = "//i[@class='nav-icon fas fa-users']//following-sibling::p")
	private WebElement adminUsersMenu;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//input[@name='username']")
	WebElement enterUserName;
	@FindBy(xpath = "//input[@id='un']")
	private WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	private WebElement passWord;
	@FindBy(xpath = "//select[@id='user_type']")
	private WebElement userType;
	@FindBy(xpath = "//button[@name='Create']")
	private WebElement saveButton;
	@FindBy(xpath = "//a[@onclick='click_button(2)']")
	private WebElement searchButton;
	@FindBy(name = "Update")
	private WebElement updateButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successMessage;
	@FindBy(xpath = "//button[@name='Search']")
	private WebElement usernameSearchField;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private WebElement usernameSearchResult;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private List<WebElement> namesList;
	@FindBy(xpath = "//button[@class='close']")
	private WebElement alertCloseOption;

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
		waitUtility = new WaitUtility(driver);
		pageUtility = new PageUtility(driver);
		waitUtility.waitForElementToBeVisible(adminUsersMenu, 10l);
		pageUtility.scrollAndClick(adminUsersMenu);
	}

	public void clickOnNewButton() {
		newButton.click();
	}

	public void enterUserName(String username) {
		enterUserName.sendKeys(username);
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

	public boolean isEditMessageAppeared() {
		waitUtility = new WaitUtility(driver);
		waitUtility.waitForElementToBeVisible(successMessage, 10l);
		return generalUtility.isTextPresent(successMessage, "User Updated Successfully");
	}

	public boolean isStatusChangeMessageAppeared() {
		return generalUtility.isTextPresent(successMessage, "User Status Changed Successfully");

	}

	public boolean isDeleteMessageAppeared() {
		return generalUtility.isTextPresent(successMessage, "User Deleted Successfully");

	}

	public String deActivateUser(String personName) {
		int index = 0;
		String negativeResult;
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
		WebElement deActivateButton = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-hover table-sm']//tr[" + index + "]//td[5]/a[1]"));
		WebElement userStatus = driver.findElement(By
				.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
		if (userStatus.getText().equals(Constants.userStatusAfterActivation)) {
			pageUtility.scrollAndClick(deActivateButton);
			WebElement status = driver.findElement(By.xpath(
					"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
			return status.getText();
		} else {
			negativeResult = "User is already deactivated.";
			return negativeResult;
		}
	}

	public String activateUser(String personName) {
		int index = 0;
		String negativeResult;
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
		WebElement activateButton = driver.findElement(
				By.xpath("//table[@class='table table-bordered table-hover table-sm']//tr[" + index + "]//td[5]/a[1]"));
		WebElement userStatus = driver.findElement(By
				.xpath("//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
		if (userStatus.getText().equals(Constants.userStatusAfterDeactivation)) {
			pageUtility.scrollAndClick(activateButton);
			WebElement status = driver.findElement(By.xpath(
					"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[3]"));
			return status.getText();
		} else {
			negativeResult = "User is already activated.";
			return negativeResult;
		}
	}

	public void clickOnEditUser(String personName) {
		pageUtility = new PageUtility(driver);
		int index = 0;
		generalUtility = new GeneralUtility();
		List<String> names = new ArrayList();
		names = generalUtility.getTextOfElements(namesList);
		for (String name : names) {
			if (personName.equals(name)) {
				index++;
				break;
			}
			index++;
		}
		WebElement editButton = driver.findElement(By.xpath(
				"//table[@class='table table-bordered table-hover table-sm']//tbody//tr[" + index + "]//td[5]/a[2]"));
		pageUtility.scrollAndClick(editButton);
		editUsername("Ishaan");
	}

	public void editUsername(String username) {
		enterUserName.click();
		enterUserName.clear();
		enterUserName.sendKeys(username);
		updateButton.click();

	}

	public boolean isPersonAppearingInSearch(String user) {
		waitUtility = new WaitUtility(driver);
		searchButton.click();
		userName.click();
		userName.sendKeys(user);
		waitUtility.waitForElementToBeClickable(usernameSearchField, 10l);
		usernameSearchField.click();
		if (user.equals(usernameSearchResult.getText())) {
			return true;
		} else {
			return false;
		}
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

	public void closeAlert() {
		alertCloseOption.click();
	}
}
