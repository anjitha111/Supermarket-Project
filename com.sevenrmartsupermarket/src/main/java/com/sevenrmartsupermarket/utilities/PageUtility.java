package com.sevenrmartsupermarket.utilities;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

//Page Factory is not needed in Utility class.

public class PageUtility {

	WebDriver driver;
	JavascriptExecutor js;

	public PageUtility(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public void select_ByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	public void select_ByVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);

	}

	public void select_ByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public void JsClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public void JsScrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void elementRightClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).build().perform();

	}

	public void elementContextClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick().build().perform();
	}

	public void elementDoubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	public void dragAndDrop(WebElement element1, WebElement element2) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(element1, element2);
	}

	public String getAlertText() {
		return driver.switchTo().alert().getText();

	}

	public void alertAccept() {
		driver.switchTo().alert().accept();

	}

	public void alertDismiss() {
		driver.switchTo().alert().dismiss();

	}

	public void switchToWindow() {

		String parent_window = driver.getWindowHandle();
		Set<String> childWindows = driver.getWindowHandles();

		for (String id : childWindows) {
			if (!parent_window.equals(id)) {
				driver.switchTo().window(id);
			}
		}
	}

	public void scrollAndClick(WebElement element) {
		int y = 0;
		while (!isClicked(element)) {
			js.executeScript("window.scrollBy(0," + y + ")");
			y = y + 5;
		}
	}

	public boolean isClicked(WebElement element) {
		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
