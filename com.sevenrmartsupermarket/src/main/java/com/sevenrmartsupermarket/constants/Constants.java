package com.sevenrmartsupermarket.constants;

import org.testng.annotations.DataProvider;

import com.sevenrmartsupermarket.utilities.ExcelReader;

public class Constants {

	ExcelReader excelreader = new ExcelReader();
	public static final String CONFIG_FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\config.properties";// Project path+config.properties path.
	public static final String EXCEL_FILE_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\ExcelFiles\\";
	public static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + "\\ExtentReport";
	public static final String SCREENSHOT_FILE_PATH = System.getProperty("user.dir") + "\\screenshots\\";
	public static final String IMAGES_FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\images\\";

	/** Expected results **/
	/** Login Page **/
	public static String expected_text = "Invalid Username/Password";
	public static String userStatusAfterActivation="Active";
	public static String userStatusAfterDeactivation="Inactive";

	/** Data Provider **/
	@DataProvider(name = "User Login")
	public Object[][] dataProviderUserLogin() {
		excelreader.setExcelFile("LoginData", "Set of valid credentials");
		return excelreader.getMultidimentionalData(3, 2);
	}
}
