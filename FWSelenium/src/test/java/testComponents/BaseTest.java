package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageobjects.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginpage;
	
	public WebDriver initializeDriver() throws IOException {
		Properties prop = 	new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") 
				+ "//src//main//java//resources//GlobalProperties.properties");
		prop.load(file);
		
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser"); // gets params from cmd prompt
				
		//String browser = prop.getProperty("browser"); //gets value from global props
		if(browser.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless"))
			{
			options.addArguments("headless");
			} // headless mode
		 driver = new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,900)); // full screen mode
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			 driver = new FirefoxDriver();
			}
		else if(browser.equalsIgnoreCase("edge")) {
			 driver = new EdgeDriver();
			}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchAppln() throws IOException {
		WebDriver driver = initializeDriver();
	    loginpage = new LoginPage(driver);
		loginpage.goTo();
		return loginpage;
	}
		
	public List<HashMap<String, String>> getJsonData(String Filepath) throws IOException {
		String str_content = FileUtils.readFileToString(new File(Filepath),StandardCharsets.UTF_8);
		//above line read and converts json to string
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(str_content, new TypeReference<List<HashMap<String,String>>>(){});
		//above line read and converts string to list(hashmap)
		return data;
}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts =  (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
		
	}
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+ "//reports//index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Wbe Automation Results of product order");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sowmya S");
		return extent;
		
		}
	
	@AfterMethod(alwaysRun = true)
	public void closeAppln() {
	 driver.close();
	}
}
