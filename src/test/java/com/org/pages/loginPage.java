package com.org.pages;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.org.utility.ExcelReader;


public class LoginPage{
	WebDriver driver;
	ExcelReader ExcelRead = new ExcelReader();
	//Input Data Excel Sheet Name
	
	
	public LoginPage(WebDriver ldriver) 
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
		System.out.println("EmailValue--"+ EmailValue);
		String PasswordValue = ExcelRead.getExcelCellData(ExcelSheet, 1, 1);
		System.out.println("EmailValue--"+PasswordValue);

		loginButton.click();
		Reporter.log("Login button Sucessfully clicked",false);		
	}
	
	public void lauchedUrlVerification()  {
		String ExcelSheet = "Login";
		
		String ExpectedTitle = null;
		String ExpectedUrl = null;
		
		try {
			ExpectedTitle = ExcelRead.getExcelCellData(ExcelSheet, 2, 1);
			ExpectedUrl = ExcelRead.getExcelCellData(ExcelSheet, 3, 1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.log("Issue while fetching the excel data",false);
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
		Reporter.log("Web URL verified",false);
		
	
	}


}