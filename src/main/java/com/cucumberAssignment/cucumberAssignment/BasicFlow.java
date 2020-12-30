package com.cucumberAssignment.cucumberAssignment;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasicFlow {
public static void main(String[] args) throws InterruptedException {
	WebDriver driver;
	ChromeOptions ops = new ChromeOptions();
	ops.addArguments("--disable-notifications");
	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	driver = new ChromeDriver(ops);
	driver.get("https://www.91mobiles.com/ ");
	driver.findElement(By.id("autoSuggestTxtBox")).sendKeys("apple mobile");
	driver.manage().window().maximize();
	driver.findElement(By.id("main_auto_search")).click();
	Thread.sleep(4000);
	String text = driver.findElement(By.id("productCount")).getText();
	String[] textarray = text.split(" ");
	String productCount = textarray[1].toString();
	int pcount = Integer.parseInt(productCount);
	assertTrue(pcount >= 1);
	
	
	
			
}
}
