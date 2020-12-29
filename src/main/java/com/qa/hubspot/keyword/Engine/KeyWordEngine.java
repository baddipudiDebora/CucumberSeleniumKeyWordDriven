package com.qa.hubspot.keyword.Engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.keyword.KeyWords.KeyWordElementActions;

public class KeyWordEngine {
	public WebDriver driver;
	public Properties prop;
	public BasePage base;

	public KeyWordElementActions keyWordEleActions;
	
	public static Workbook book;
	public static Sheet sheet;
	public static ThreadLocal<Workbook> testBook = new ThreadLocal<Workbook>();
	public static ThreadLocal<Sheet> testSheet = new ThreadLocal<Sheet>();

	public final String TESTDATA_SHEET_PATH = "C:/Users/Deborah/eclipse-workspace/KeyWordDrivenWebFramework-master/src/main/java/com/qa/hubspot/keyword/Scenarios/hubspot_scenarios.xlsx";

	public void startExecution(String sheetName) {
		String locatorValue = null;
		String locatorName = null;
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
			testBook.set(book);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		testSheet.set(sheet);
		int k = 0;
		for (int i = 0; i < testSheet.get().getLastRowNum(); i++) {

			try {
				String locatorColValue = testSheet.get().getRow(i + 1).getCell(k + 1).toString().trim();

				if (!locatorColValue.equalsIgnoreCase("NA")) {
					locatorName = locatorColValue.split("=")[0].trim();
					locatorValue = locatorColValue.split("=")[1].trim();
					System.out.println(locatorName);
				}
				String action = testSheet.get().getRow(i + 1).getCell(k + 2).toString().trim();
				System.out.println(action);
				String value = testSheet.get().getRow(i + 1).getCell(k + 3).toString().trim();
				System.out.println(value);
				switch (action) {

				case "open browser":
					base = new BasePage();
					driver = base.init_driver(value);
					keyWordEleActions = new KeyWordElementActions(driver);
					break;

				case "OPEN_URL":
					keyWordEleActions.launchUrl(value);
					break;

				case "sendKeys":
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					keyWordEleActions.sendKeys(locatorValue);
					break;

				case "click":
					System.out.println(value);
					driver.findElement(By.id(locatorValue)).click();
					break;

				case "CLOSE_BROWSER":
					keyWordEleActions.quitBrowser();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
