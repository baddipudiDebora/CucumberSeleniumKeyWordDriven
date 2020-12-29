package com.qa.hubspot.keyword.KeyWords;

import javax.validation.constraints.NotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import com.qa.hubspot.keyword.Engine.BasePage;

public class KeyWordElementActions extends BasePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public Actions actions;
	public Select select;

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
	
	public WebElement getElement(@NotNull By selector) {
		try {
			WebElement ele = driver.findElement(selector);
			return ele;
		} catch (Exception e) {
		}
		return null;
	}

	public String getElementText(@NotNull By selector) {
		try {
			return getElement(selector).getText().trim();
		} catch (Exception e) {
		}
		return null;
	}

	public void click(@NotNull By selector) {
		WebElement element = getElement(selector);
		try {
			if (element != null)
				element.click();
		} catch (Exception e) {
			throw new TestException(String.format("The following element is not clickable: [%s]", selector));
		}
	}


	public void quitBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
		}
	}

	public void sendKeys(String value) {
	
		WebElement element = getElement(By.id(value));
		
		try {
			Thread.sleep(5000);
			System.out.println("waiting for textbox");
			element.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
