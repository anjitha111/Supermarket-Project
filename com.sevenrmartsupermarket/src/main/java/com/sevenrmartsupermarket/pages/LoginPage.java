package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class LoginPage {

	WebDriver driver;
	Properties properties = new Properties();
	FileInputStream ip;
	GeneralUtility generalUtility;

	@FindBy(xpath = "//input[@name='username']")
	private WebElement userName; // Encapsulation
	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordField;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInButton;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement errorMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		try {
			ip = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(ip);
		} catch (Exception e) {
			e.printStackTrace(); 
			System.out.println("File not found exception");
		}
	}

	public void enterUserName(String username) {
		userName.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void clickOnSignInButton() {
		signInButton.click();
	}

	public void login() {
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		enterUserName(username);
		enterPassword(password);
		clickOnSignInButton();

	}

	public boolean login(String username, String password) {
		generalUtility = new GeneralUtility();
		enterUserName(username);
		enterPassword(password);
		clickOnSignInButton();
		return generalUtility.isTextPresent(errorMessage, "Invalid Username/Password");
	}

	public void loginProfileCheck(String username, String password) {
		generalUtility = new GeneralUtility();
		enterUserName(username);
		enterPassword(password);
		clickOnSignInButton();

	}

}
