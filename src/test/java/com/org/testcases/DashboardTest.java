package com.org.testcases;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.Test;

import com.org.pages.BaseClass;
import com.org.pages.DashboardPage;
import com.org.pages.loginPage;


public class DashboardTest extends BaseClass {
	
	
	@Test
	public void DashboardVerification() {		
		
		logger = report.createTest("Verification of Dashboard");
		logger.info("Verifing Dashboard Page......");

		DashboardPage DashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		loginPage loginPage = PageFactory.initElements(driver, loginPage.class);
		
		//Login web

		try {
			loginPage.logintoCRM();
			DashboardPage.loginVerification();

			
			logger.pass("Web Page launched correctly.. ");
		} 
		catch (Exception e) {
		
			e.printStackTrace();
			logger.fail("Issue in Web Page launch...!!!");
		
		} 
	
	}

		
}