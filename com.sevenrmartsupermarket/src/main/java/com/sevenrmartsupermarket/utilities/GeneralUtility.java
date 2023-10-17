package com.sevenrmartsupermarket.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.WebElement;
import com.github.javafaker.Faker;

public class GeneralUtility {

	static Faker faker = new Faker();

	public static String getTimeStamp() {
		return new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
	}

	public String getTextOfElement(WebElement element) {
		return element.getText();
	}

	public List<String> getTextOfElements(List<WebElement> element) {
		List<String> data = new ArrayList<String>();

		for (WebElement item : element) {
			data.add(item.getText());
		}
		return data;
	}

	public boolean is_Displayed(WebElement element) {
		return element.isDisplayed();
	}

	public boolean is_Selected(WebElement element) {
		return element.isSelected();
	}

	public boolean is_Enabled(WebElement element) {
		return element.isEnabled();
	}

	public String getElementsAttribute(WebElement element, String attribute) {
		return element.getAttribute(attribute);// Otherwise we have to write like getAttribute(id),(name)...
	}

	public boolean isTextPresent(WebElement element, String expectedText) {
		String data = element.getText();
		return data.contains(expectedText);
	}

	public static String getRandomFullName() {
		String name = faker.name().fullName();
		return name;
	}

	public static String getRandomFirstName() {
		String firstName = faker.name().firstName();
		return firstName;
	}

	public static String getRandomLastName() {
		String lastName = faker.name().lastName();
		return lastName;
	}

	public static String getRandomAddress() {
		String streetAddress = faker.address().streetAddress();
		return streetAddress;
	}
}
