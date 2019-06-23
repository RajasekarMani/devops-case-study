package com.tcs;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SeleniumTestIT {
	private static WebDriver driver;
	ChromeOptions chromeOptions;

	public SeleniumTestIT() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless","--no-sandbox","--disable-dev-shm-usage");
	}
	
	public void isGooglePageLoading() throws InterruptedException {
		driver = new ChromeDriver(chromeOptions);
		driver.get("https://www.google.com/xhtml");
		driver.manage().window().maximize();
		Thread.sleep(5000);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("ChromeDriver");
		searchBox.submit();
		Thread.sleep(5000); // Let the user actually see something!
		driver.quit();
	}

	@Test
	public void isPageTitleCorrect() throws InterruptedException {
		driver = new ChromeDriver(chromeOptions);		
		driver.get(System.getProperty("app.url"));
		driver.manage().window().maximize();
		Thread.sleep(5000);
		String actualTitle = driver.getTitle();	
		String expectedTitle = "DevOps Case Study - Calculator";
		assertEquals(expectedTitle, actualTitle);
		driver.quit();
	}

}
