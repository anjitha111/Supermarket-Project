package com.sevenrmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class ManageProductPage {
	PageUtility pageUtility;
	WebDriver driver;
	GeneralUtility generalUtility;
	WaitUtility waitUtility;

	@FindBy(xpath = "//i[@class='nav-icon fas fa-tasks']//following-sibling::p")
	private WebElement manageProductMenu;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	private WebElement searchIcon;
	@FindBy(xpath = "//input[@name='un']")
	private WebElement titleField;
	@FindBy(xpath = "//button[@name='Search']")
	private WebElement searchButton;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr//td[1]")
	private WebElement titleName;

	public ManageProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void manageProductMenu() {
		manageProductMenuClick();
		searchIconClick();
	}

	public void searchIconClick() {
		searchIcon.click();
	}

	public void manageProductMenuClick() {
		manageProductMenu.click();
	}

	public boolean searchByTitle(String title) {
		titleField.clear();
		titleField.sendKeys(title);
		searchButton.click();
		if (titleName.getText().equals(title)) {
			return true;
		} else {
			return false;
		}
	}
}
