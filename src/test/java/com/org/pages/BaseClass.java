package com.org.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
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
import com.org.utility.WebDriver_EventListener;

//import jdk.jfr.internal.Logger;

import com.org.utility.Helper;


public class BaseClass {
	//Not static because sperate instance for each test class execution
	protected WebDriver driver;
	protected ConfigDataProvider config;
	protected ExtentTest logger;
	//Static because common across all test class execution
	protected static ExtentSparkReporter spark;
	protected static ExtentReports report;
	protected static WebDriverListener eventListener;
	protected WebDriver decoratedDriver;
		
	protected BaseClass() {
	report = new ExtentReports();
	
	try {
		config = new ConfigDataProvider();
	} catch (IOException e) {
		//logger.fail("Issue in accessing the configuration of test suite");
		Reporter.log("Issue in accessing the configuration of test suite", true);
		e.printStackTrace();
		
	}
}
	
	@BeforeSuite
	public void setUpSuite() throws IOException
	{
		Reporter.log("Setting up reports and test is getting ready", true);
				
		//We have to define the location for the reporter		
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/TestReport_"+Helper.getCurrentDateTime()+".html");
		report.attachReporter(spark);
		
		Reporter.log("Setting Done Test Can be started", true);
	}
	
	
	@BeforeClass
	public void setup()
	{
		Reporter.log("Trying to start browser and getting application ready", true);
		//logger.info("Trying to start browser and getting application ready");
		WebDriverListener e_Listner = new WebDriver_EventListener();
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());
		
		WebDriver decoratedDriver = new EventFiringDecorator<WebDriver>(e_Listner).decorate(driver);
		//Assigning the event driver object back to driver object, in order to avail listener functionaliy across all test execution of any class
		driver = decoratedDriver;
		Reporter.log("Browser and application is up and running", false);
		//logger.info("Browser and application is up and running");
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
			logger.fail("Test is Failed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			//Reporter.log("Test is Failed", false);
		}
		/*
		else if(result.getStatus()==ITestResult.SUCCESS) {
			//Helper.captureScreenshot(driver);
			logger.pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		*/
		else if(result.getStatus()==ITestResult.SKIP) {
			//Helper.captureScreenshot(driver);
			logger.skip("Test is Skipped",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		} 
		report.flush();
		Reporter.log("Test Completed - Report Generated", true);
	}
	
}
