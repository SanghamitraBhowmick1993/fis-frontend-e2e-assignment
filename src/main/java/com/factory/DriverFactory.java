package com.factory;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public static WebDriver driver;
	Properties prop;

	/**
	 * This method is used to initialize the threadlocal driver on the basis of
	 * given browser
	 * 
	 * @param browser
	 * @return this will return tldriver.
	 */
	public static WebDriver init_driver(String browser) {
		if (browser.equals("chrome")) {
			
			//runs tests in GUI mode
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-debugging-port=9222"); // Use an open port
			options.addArguments("--disable-web-security"); // Disable web security for testing
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--remote-allow-origins=*");

			// enable this to run tests in headless
			options.addArguments("--headless");

			tlDriver.set(new ChromeDriver(options));
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			return getDriver();
			
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/MicrosoftEdgeDriver");
			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--remote-debugging-port=9222");
			edgeOptions.addArguments("--disable-web-security");
			edgeOptions.addArguments("--allow-running-insecure-content");
			edgeOptions.addArguments("--remote-allow-origins=*");
			tlDriver.set(new EdgeDriver(edgeOptions));
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			return getDriver();

		} else if (browser.equals("safari")) {
			System.setProperty("webdriver.safari.driver", System.getProperty("user.dir") + "/safaridriver");
			SafariOptions safariOptions = new SafariOptions();
			tlDriver.set(new SafariDriver(safariOptions));
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180));
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			return getDriver();
		}
		return null;
	}

	/**
	 * this is used to get the driver with ThreadLocal
	 * 
	 * @return
	 */
	public static synchronized WebDriver getDriver() {
		if (tlDriver == null) {
			System.out.println("driver is null");
			return tlDriver.get();
		}
		return tlDriver.get();
	}

}