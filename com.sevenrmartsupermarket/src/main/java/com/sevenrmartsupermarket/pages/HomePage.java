package com.sevenrmartsupermarket.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class HomePage {

	GeneralUtility generalUtility;
	WebDriver driver;
	@FindBy(xpath = "//a[@class='d-block']")
	private WebElement profileName;
	@FindBy(xpath = "//a[@class='small-box-footer'][1]")
	WebElement managePageMoreInfo;
	@FindBy(xpath = "//h1[@class='m-0 text-dark']")
	WebElement listPages;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getProfileName() {
		generalUtility = new GeneralUtility();
		return generalUtility.getTextOfElement(profileName);

	}

	public String clickOnManagePageMoreInfoLinkFromHome() {
		managePageMoreInfo.click();
		return listPages.getText();
	}
}
