package com.org.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.org.pages.BaseClass;
import com.org.pages.DashboardPage;
import com.org.pages.LoginPage;


public class DashboardTest extends BaseClass {
	
	
	@Test
	public void DashboardVerification() {		
		
		logger = report.createTest("Verification of Dashboard");
		logger.info("Verifing Dashboard Page......");

		DashboardPage DashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		//Login web

		try {
			loginPage.logintoCRM();
			logger.pass("Web Page launched correctly.. ");
			Reporter.log("Web Page launched correctly.. ", true);
			DashboardPage.DashboardVerification();
			
			logger.pass("Dashboard elements matched, correct user logged in ");
			Reporter.log("<br>Dashboard elements matched, correct user logged in ", false);
		} 
		catch (Exception e) {
		
			e.printStackTrace();
			logger.fail("Issue in Web Page launch...!!!");
			Reporter.log("<br>Issue in Web Page launch...!!!",false);
		
		} 
	
	}

		
}