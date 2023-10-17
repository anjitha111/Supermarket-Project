package com.sevenrmartsupermarket.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.sevenrmartsupermarket.constants.Constants;

public class Screenshot {
	TakesScreenshot takescreenshot; // To capture screenshots.

	public void takeScreenshot(WebDriver driver, String imageName) {
		try {
			takescreenshot = (TakesScreenshot) driver;
			File screenShot = takescreenshot.getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
			String destinationPath = Constants.SCREENSHOT_FILE_PATH + imageName + "_" + timeStamp + ".png";
			File file = new File(destinationPath);
			FileHandler.copy(screenShot, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
