package pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablePackage.ReusableCode;

public class OrderConfirmationPage extends ReusableCode {

	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmatorytext;
	
	public String verifyOrderConfirmation() {
		waitForWebElementToAppear(confirmatorytext);
		String 	confirmatorytmessage = confirmatorytext.getText();	
		return confirmatorytmessage;
	}
	
	
	
}
