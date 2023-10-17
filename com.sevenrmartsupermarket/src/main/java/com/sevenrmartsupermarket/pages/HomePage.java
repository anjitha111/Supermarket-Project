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

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getProfileName() {
		generalUtility = new GeneralUtility();
		return generalUtility.getTextOfElement(profileName);

	}
}
