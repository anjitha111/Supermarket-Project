package com.sevenrmartsupermarket.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {

	WebDriver driver;
	WebDriverWait wait;

	public static final long IMPLICIT_WAIT = 10;
	public static final long PAGE_LOAD_WAIT = 20;

	public WaitUtility(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToBeClickable(WebElement element, Long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeVisible(String xpath, Long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void waitForElementToBeVisible(By locator, Long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForElementToBeVisible(WebElement element, Long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeInVisible(WebElement element, Long waitTime) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void fluentWaitForElementToBeInVisible(By locator, Long waitTime, Long pollTime) {
		Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
				.ignoring(Exception.class);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
}
