package com.org.pages;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.org.utility.ExcelReader;


public class loginPage{
	WebDriver driver;
	ExcelReader ExcelRead = new ExcelReader();
	//Input Data Excel Sheet Name
	
	
	public loginPage(WebDriver ldriver) 
	{
		
		this.driver = ldriver;

		
	}

	@FindBy(xpath = "//input[@class ='email valid']") WebElement Email;
	
	@FindBy(xpath = "//input[@class ='password valid']") WebElement Password;
	
	@FindBy(xpath = "//button[@type = 'submit']") WebElement loginButton;
	
	public void logintoCRM() throws EncryptedDocumentException, IOException 
	{
		String ExcelSheet = "Login";
		
		String EmailValue = ExcelRead.getExcelCellData(ExcelSheet, 0, 1);
//		System.out.println("EmailValue--"+ EmailValue);
		String PasswordValue = ExcelRead.getExcelCellData(ExcelSheet, 1, 1);
//		System.out.println(PasswordValue);
		//logger.log(Status.INFO,EmailValue);


//		Email.clear();
//		Email.sendKeys(EmailValue);
//		Password.clear();
//		Password.sendKeys(PasswordValue);

		loginButton.click();
		Reporter.log("Login button Sucessfully clicked",true);
		
	}
	
	public void lauchedUrlVerification()  {
		String ExcelSheet = "Login";
		
		String ExpectedTitle = null;
		String ExpectedUrl = null;
		//logger.info("Executing Lauched URL verification");
		
		//logger.pass("web Lauching Verified");
		try {
			ExpectedTitle = ExcelRead.getExcelCellData(ExcelSheet, 2, 1);
			ExpectedUrl = ExcelRead.getExcelCellData(ExcelSheet, 3, 1);
			//logger.pass("Excel Data Fetched sucessfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.fail("Issue while fetching the excel data");
			
		} 
	
		System.out.println("ExpectedTitle-->"+ExpectedTitle);
		System.out.println("ExpectedUrl-->"+ExpectedUrl);
		System.out.println(driver.getCurrentUrl());
		Assert.assertEquals(driver.getTitle(), ExpectedTitle);
		Assert.assertEquals(driver.getCurrentUrl(), ExpectedUrl);
	
		//Soft Assert
		SoftAssert s1 = new SoftAssert();
		s1.assertEquals(driver.getTitle(), ExpectedTitle);
		s1.assertAll();
		Reporter.log("Web URL verified",true);
	
	}


}