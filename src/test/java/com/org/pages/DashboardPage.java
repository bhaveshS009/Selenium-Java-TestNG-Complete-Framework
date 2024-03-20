package com.org.pages;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.org.utility.ExcelReader;

public class DashboardPage {
	WebDriver driver;
	ExcelReader ExcelRead = new ExcelReader();
	//Input Data Excel Sheet Name
	
	
	public DashboardPage(WebDriver ldriver) 
	{
		
		this.driver = ldriver;	
	}
	
	@FindBy(xpath = "//div[@class='content-header']/h1") WebElement DashboardHeader;
	@FindBy(xpath = "//img[@class = 'brand-image-xl logo-xl']") WebElement CompanyLogo;
	@FindBy(xpath = "//ul[@class='navbar-nav ml-auto pl-2']/li[2]") WebElement userName;
	@FindBy(xpath = "//ul[@class='navbar-nav ml-auto pl-2']/li[3]") WebElement logOut;
	@FindBy(xpath = "//ul[@class='nav nav-pills nav-sidebar flex-column nav-legacy']/li")
	List <WebElement> dashboardElements;

	
	public void loginVerification() throws EncryptedDocumentException, IOException 
	{
		String ExcelSheet = "Dashboard";
		
		String ExpectedBrowserTitle = ExcelRead.getExcelCellData(ExcelSheet, 0, 1);
		String ExpectedBrowserUrl = ExcelRead.getExcelCellData(ExcelSheet, 1, 1);

		Assert.assertEquals(driver.getTitle(), ExpectedBrowserTitle);
		Assert.assertEquals(driver.getCurrentUrl(), ExpectedBrowserUrl);
		
		//Dashboard available on Header
		Assert.assertTrue(DashboardHeader.isDisplayed());
		Assert.assertEquals(userName.getText(),"John Smith");
		//Logout Button Available
		Assert.assertEquals(logOut.getText(),"Logout");
		//Username name admin logged in
		Assert.assertEquals(userName.getText(),"John Smith");
			
		
		//Verifing Company Logo on page
		List<String> dashboardData = ExcelRead.getExcelRowList(ExcelSheet, 2);
		
		if (dashboardData.size()==dashboardElements.size())
		{
			//System.out.println("Same Dashboard Element Size");
			
			for (int i=0;i<dashboardData.size();i++)
			{
				Assert.assertEquals(dashboardData.get(i), dashboardElements.get(i).getText());
			
			}
		}
		else {
			System.out.println(dashboardData.size());
			for( String dashElement:dashboardData) {
				System.out.println(dashElement);
				
			}
			
			for( WebElement Element:dashboardElements) {
				System.out.println(Element.getText());
				
			}
			
			
			System.out.println(dashboardElements.size());
			
			Assert.fail("Desired Dashboard Element Missing");
		}
		
		
		
		
	
	}
	
}
