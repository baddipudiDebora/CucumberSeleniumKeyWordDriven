package com.qa.hubspot.keyword.KeyWords;

import static org.testng.Assert.assertTrue;

import javax.validation.constraints.NotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.TestException;

import com.qa.hubspot.keyword.Engine.BasePage;

public class KeyWordElementActions extends BasePage {

	public WebDriver driver;
	public WebElement ele;
	public String textDisplayed;

	public KeyWordElementActions(WebDriver driver) {
		this.driver = driver;
	}

	public void launchUrl(@NotNull String URL) {
		try {
			driver.get(URL);
		} catch (Exception e) {
			throw new TestException("URL did not load");
		}

	}

	public WebElement getElement(String locatorvalue) throws InterruptedException {
		WebElement ele = driver.findElement(By.id(locatorvalue));
		return ele;

	}

	public void click(WebElement webele) {
		try {
			if (webele != null)
				webele.click();
		} catch (Exception e) {
			throw new TestException(String.format("The following element is not clickable: [%s]", webele));
		}
	}

	public void sendKeys(WebElement ele, String value) {
		try {
			ele.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyCount(WebElement ele) throws InterruptedException {
		Thread.sleep(4000);
		String text = ele.getText();
		String[] textarray = text.split(" ");
		String productCount = textarray[1].toString();
		int pcount = Integer.parseInt(productCount);
		assertTrue(pcount >= 1);
	}
	public String verifyMobileName(WebElement mobilename) throws InterruptedException {
		Thread.sleep(2000);
		String text = mobilename.getText();
		String[] textarray = text.split(" ");
		String MobileName = textarray[4].toString().toUpperCase();
		return MobileName;
	}
	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
		}
	}
}
