	package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	static ExtentReports extent;
	public static ExtentReports getReportObject() {
		
	String path = ("user.dir")+ "//reports//index.html";
	
	ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	reporter.config().setReportName("Wbe Automation Result");
	reporter.config().setDocumentTitle("Test Results");
	
	extent=new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester", "Sowmya S");
	return extent;
	
	}
}
