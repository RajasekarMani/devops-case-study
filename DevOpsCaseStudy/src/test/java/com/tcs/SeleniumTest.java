package com.tcs;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTest {
	private static WebDriver driver;

	public SeleniumTest() {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
	}

	@Test
	public void isPageTitleCorrect() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://www.google.com/xhtml");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("ChromeDriver");
		searchBox.submit();
		Thread.sleep(5000); // Let the user actually see something!
		driver.quit();
	}

}
