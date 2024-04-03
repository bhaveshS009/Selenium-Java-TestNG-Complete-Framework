package com.org.testcases;

import org.testng.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.pages.BaseClass;
import com.org.pages.LoginPage;
import com.org.utility.ITestListener_TestNG;

@Listeners(ITestListener_TestNG.class)
public class LoginTest extends BaseClass {
	

	@Test
	public void loginApp() {		
		System.out.println("Inside LoginApp Method");
		logger = report.createTest("Login to CRM");
		logger.info("Starting Application");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		//Login web

		try {
			loginPage.lauchedUrlVerification();

			loginPage.logintoCRM();
			logger.pass("Web Page launched correctly.. ");
		} 
		catch (Exception e) {
		
			e.printStackTrace();
			Assert.fail("Issue in Web Page launch...!!!");
		
		} 
	
	}

			
}


		

