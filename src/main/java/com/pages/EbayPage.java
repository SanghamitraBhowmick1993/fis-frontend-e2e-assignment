package com.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.util.ConfigReader;

public class EbayPage {
	private WebDriver driver;
	static ConfigReader reader = new ConfigReader();

	// 1. By locators or OR
	private By searchTxt = By.id("gh-ac");
	private By searchBtn = By.id("gh-btn");
	private By clickFirstBook = By.xpath("(//li[contains(@id, 'item')])[1]//div//div[1]//div[@class='s-item__image']");
	private By addToCart = By.id("atcBtn_btn_1");

	// 2. Constructor of page class
	public EbayPage(WebDriver driver) {
		this.driver = driver;
	}

	// 3. Page Actions
	public void login_ebayApp() {
		try {
			String url = reader.getEnvironmentProperty();
			driver.get(url);
			driver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchFor(String searchString) {
		driver.findElement(searchTxt).sendKeys(searchString);
		driver.findElement(searchBtn).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
		wait.until(ExpectedConditions.titleContains("Book for sale | eBay"));
	}

	public void clickOnFirstBook() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(clickFirstBook).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void addToCart() {
		Set<String> windowHandles = driver.getWindowHandles();
		String currentWindowHandle = driver.getWindowHandle();
		String newWindowHandle = null;
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(currentWindowHandle)) {
				newWindowHandle = windowHandle;
				break;
			}
		}
		driver.switchTo().window(newWindowHandle);
		driver.findElement(addToCart).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public int getCartCount() {
		return Integer.parseInt(driver.findElement(By.id("gh-cart-n")).getText());

	}
}
