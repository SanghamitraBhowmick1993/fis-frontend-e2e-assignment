package parallel;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.factory.DriverFactory;
import com.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class AppHooks {

	private ConfigReader configreader;
	Properties prop;
	public DriverFactory driverfactory;
	public WebDriver driver;

	private static ExtentReports extent; // Static for shared access
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // Thread-local for parallel execution
	private static ExtentSparkReporter sparkReporter;

	@Before(order = 0)
	public void setupExtentReports() {
		if (extent == null) { // Initialize only once
			sparkReporter = new ExtentSparkReporter("test-output/SparkReport/Spark.html");
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

			// Add system info to the report
			extent.setSystemInfo("OS", "Mac");
			extent.setSystemInfo("User", "Sanghamitra");
			extent.setSystemInfo("Build", "1.1");
			extent.setSystemInfo("App Name", "AutomationPractice");
		}
	}

	@Before(order = 1)
	public void getProperty() {
		configreader = new ConfigReader();
		prop = configreader.init_prop();
	}

	@Before(order = 2)
	public void launchBrowser() {
		try {
			String browserName = prop.getProperty("browser");
			driver = DriverFactory.init_driver(browserName);
			driver.get("https://example.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before(order = 3)
	public void initializeTest(Scenario scenario) {
		ExtentTest test = extent.createTest(scenario.getName());
		extentTest.set(test); // Store the test instance in ThreadLocal
	}

	@AfterStep
	public void logStepResult(Scenario scenario) throws IOException {
		WebDriver driver = DriverFactory.getDriver();
		String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

		if (scenario.isFailed()) {
			extentTest.get().fail("Step failed").addScreenCaptureFromBase64String(base64Screenshot,
					"Failed Screenshot for:" + scenario.getName());
		} else {
			extentTest.get().pass("Step passed").addScreenCaptureFromBase64String(base64Screenshot,
					"Passed Screenshot for:" + scenario.getName());
		}
	}

	@After(order = 1)
	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	@After(order = 2)
	public void flushReports() {
		if (extent != null) {
			synchronized (extent) { // Synchronize to prevent race conditions
				extent.flush();
			}
		}
	}
}
