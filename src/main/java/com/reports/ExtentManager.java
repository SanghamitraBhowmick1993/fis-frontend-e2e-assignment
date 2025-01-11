package com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	public static ExtentReports report;
	public static ExtentReports getInstance() {
		if(report == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("./test-output/SparkReport/Spark.html");
			report = new ExtentReports();
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("TestAutomationXX");
			spark.config().setEncoding("utf-8");
			spark.config().setReportName("Demo CI/CD");
			report.attachReporter(spark);
		}
		return report;
	}

}
