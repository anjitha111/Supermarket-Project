package com.sevenrmartsupermarket.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class ManageContentPage {

	PageUtility pageUtility;
	WebDriver driver;
	GeneralUtility generalUtility;
	WaitUtility waitUtility;

	@FindBy(xpath = "//i[@class='nav-icon fas fa-edit']//following-sibling::p")
	private WebElement manageContent;
	@FindBy(xpath = "//a[@class='nav-link']//following-sibling::p[text()='Manage Pages']")
	private WebElement managePages;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	private WebElement newButton;
	@FindBy(xpath = "//input[@id='title']")
	private WebElement enterTitleField;
	@FindBy(xpath = "//div[@class='note-editable card-block']")
	private WebElement descriptionField;
	@FindBy(xpath = "//input[@id='page']")
	private WebElement pageText;
	@FindBy(xpath = "//input[@name='main_img']")
	private WebElement chooseFileButton;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveButton;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successMessage;

	public ManageContentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addPages(String title, String description, String text) {
		manageContentMenuClick();
		managePagesClick();
		clickOnNewButton();
		enterTheTitle(title);
		enterTheDescription(description);
		enterThePageFieldText(text);
		chooseFileToUpload("Sebamed Rash cream.jpg");
		clickOnSaveButton();
	}

	public void manageContentMenuClick() {
		manageContent.click();
	}

	public void managePagesClick() {
		managePages.click();
	}

	public void clickOnNewButton() {
		newButton.click();
	}

	public void enterTheTitle(String title) {
		enterTitleField.sendKeys(title);
	}

	public void enterTheDescription(String description) {
		descriptionField.sendKeys(description);
	}

	public void enterThePageFieldText(String text) {
		pageText.sendKeys(text);
	}

	public void chooseFileToUpload(String imageName) {
		File file = new File(Constants.IMAGES_FILE_PATH + imageName);
		chooseFileButton.sendKeys(file.getAbsolutePath());
	}

	public void clickOnSaveButton() {

		pageUtility = new PageUtility(driver);
		waitUtility = new WaitUtility(driver);
		waitUtility.waitForElementToBeClickable(saveButton, 10l);
		pageUtility.JsScrollIntoView(saveButton);
		pageUtility.scrollAndClick(saveButton);

	}

	public boolean isSuccessMessageDisplayed() {
		return generalUtility.isTextPresent(successMessage, "Page Created Successfully");

	}
}
