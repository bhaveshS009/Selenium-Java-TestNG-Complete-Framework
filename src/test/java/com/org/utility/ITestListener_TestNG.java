package com.org.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ITestListener_TestNG implements ITestListener{
	
		@Override
		public void onTestStart(ITestResult result) {
			System.out.println("New Test Started - "+ result.getName());
			// TODO Auto-generated method stub
			ITestListener.super.onTestStart(result);
		}

		@Override
		public void onTestSuccess(ITestResult result) {
			System.out.println("Result of the Test - "+ result.getName() + " Exceuted - Pass");
			Reporter.log("<br> Result of the Test - "+ result.getName() + " Exceuted - Pass");
			
			// TODO Auto-generated method stub
			ITestListener.super.onTestSuccess(result);
		}

		@Override
		public void onTestFailure(ITestResult result) {
			System.out.println("Result of the Test - "+ result.getName() + " Exceuted - Fail");
			// TODO Auto-generated method stub
			ITestListener.super.onTestFailure(result);
		}

		@Override
		public void onTestSkipped(ITestResult result) {
			System.out.println("Test - "+ result.getName() + " - Skipped because of method -" + result.getSkipCausedBy());
			// TODO Auto-generated method stub
			ITestListener.super.onTestSkipped(result);
		}

		@Override
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub
			ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
		}

		@Override
		public void onTestFailedWithTimeout(ITestResult result) {
			// TODO Auto-generated method stub
			ITestListener.super.onTestFailedWithTimeout(result);
		}

		@Override
		public void onStart(ITestContext context) {
			// TODO Auto-generated method stub
			ITestListener.super.onStart(context);
		}

		@Override
		public void onFinish(ITestContext context) {
			System.out.println("Test Cases Execution finished");
			// TODO Auto-generated method stub
			ITestListener.super.onFinish(context);
		}

	}
	


	
	

