package com.org.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	//Screenshot,Alerts,Frames, windows, sync issues, java script executor
	public static String captureScreenshot(WebDriver driver){
		
		File Screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")+"/Screenshots/ScreenShot_"+ getCurrentDateTime()+".png";
		
		try {
			FileUtils.copyFile(Screenshot, new File(screenshotPath));
		} catch (IOException e) {
			System.out.println("Issue in screenshot capture --> "+ e.getMessage());
			
		}

		return screenshotPath;
	}
	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yy_HH_mm_ss_SSS");
		Date currentTime = new Date();
		return customFormat.format(currentTime);
	}

	public void handleAlerts() {
		//TODO
		
	}
}
