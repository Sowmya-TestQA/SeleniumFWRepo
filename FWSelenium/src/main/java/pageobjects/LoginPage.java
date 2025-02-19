package pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablePackage.ReusableCode;

public class LoginPage extends ReusableCode {
	
		WebDriver driver;
		
		public LoginPage(WebDriver driver){
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);// findby annotation will refer this line and takes driver reference 
		}
		
		//Pagefactory method - findby is the shortcut of driver.findElement(By.cssSelector("#userEmail")) 
		@FindBy(id="userEmail")
		WebElement UserEmail; // here driver we are not mentioning but commonly we mention inside the constructor
		
		@FindBy(id="userPassword")
		WebElement UserPassword;
		
		@FindBy(id="login")
		WebElement Login;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errormessage;
		
		public SelectProductPage loginToAppln(String useremail, String password) {
			UserEmail.sendKeys(useremail);
			UserPassword.sendKeys(password);
			Login.click();
			SelectProductPage selectproductpage = new SelectProductPage(driver);
			return selectproductpage;
		}
		
		public void goTo() {
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public String getErrorMessage() {
			waitForWebElementToAppear(errormessage);
			String message = errormessage.getText();	
			return message;
			}
		
}
