package com.org.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.org.utility.BrowserFactory;
import com.org.utility.ConfigDataProvider;
import com.org.utility.Helper;


public class BaseClass {
	//Not static because sperate instance for each test class execution
	public WebDriver driver;
	public ConfigDataProvider config;
	public ExtentTest logger;
	//Static because common across all test class execution
	public static ExtentSparkReporter spark;
	public static ExtentReports report;


	
	
	public BaseClass() {
	report = new ExtentReports();
	try {
		config = new ConfigDataProvider();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
//		Reporter.log("Setting up reports and test is getting ready", true);
//		//config = new ConfigDataProvider();
//		//report = new ExtentReports();
//		
//		//We have to define the location for the reporter
//		
//		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/TestReport_"+Helper.getCurrentDateTime()+".html");
//		report.attachReporter(spark);
//		//logger = report.createTest("Search For Product");
//		Reporter.log("Setting Done Test Can be started", true);
	}
}
	
	
//	public BaseClass() {
//		report = new ExtentReports();
//		try {
//			config = new ConfigDataProvider();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
	@BeforeSuite
	public void setUpSuite() throws IOException
	{
		Reporter.log("Setting up reports and test is getting ready", true);
		//config = new ConfigDataProvider();
		//report = new ExtentReports();
		
		//We have to define the location for the reporter
		
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/TestReport_"+Helper.getCurrentDateTime()+".html");
		report.attachReporter(spark);
		//logger = report.createTest("Search For Product");
		Reporter.log("Setting Done Test Can be started", true);
	}
	
	
	@BeforeClass
	public void setup()
	{
		Reporter.log("Trying to start browser and getting application ready", true);
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());
		Reporter.log("Browser and application is up and running", true);
	}
	
	@AfterClass
	public void tearDown()
	{		
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void teardownMethod(ITestResult result) {
		Reporter.log("Test about to end", true);
		if(result.getStatus()==ITestResult.FAILURE) {
			//Helper.captureScreenshot(driver);
			logger.fail("Test Failed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		/*
		else if(result.getStatus()==ITestResult.SUCCESS) {
			//Helper.captureScreenshot(driver);
			logger.pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		*/
		else if(result.getStatus()==ITestResult.SKIP) {
			//Helper.captureScreenshot(driver);
			logger.skip("Test Skipped",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} 
		report.flush();
		Reporter.log("Test Completed - Report Generated", true);
	}
	
}
