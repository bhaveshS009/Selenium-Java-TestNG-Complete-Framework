package com.org.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;

import com.org.pages.BaseClass;
import com.org.pages.loginPage;

public class loginTest extends BaseClass {
	

	@Test
	public void loginApp() {		
		System.out.println("Inside LoginApp Method");
		logger = report.createTest("Login to CRM");
		logger.info("Starting Application");
		loginPage loginPage = PageFactory.initElements(driver, loginPage.class);
		
		//Login web

		try {
			loginPage.lauchedUrlVerification();

			loginPage.logintoCRM();
			logger.pass("Web Page launched correctly.. ");
		} 
		catch (Exception e) {
		
			e.printStackTrace();
			logger.fail("Issue in Web Page launch...!!!");
		
		} 
	
	}

			
}


		

