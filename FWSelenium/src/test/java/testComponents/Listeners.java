package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = BaseTest.getReportObject();	
	ThreadLocal<ExtentTest> local = new ThreadLocal<ExtentTest>();//thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		local.set(test);	//unique thread id(ErrorValidationTest)->test
		}

	@Override
	public void onTestSuccess(ITestResult result) {
		local.get().log(Status.PASS, "Test Passed!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		local.get().log(Status.FAIL, "Test Failed!");
		local.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);//error
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		local.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		local.get().skip(result.getThrowable());
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
		extent.flush();
		
	}

}
