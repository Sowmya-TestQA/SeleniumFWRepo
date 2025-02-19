package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablePackage.ReusableCode;

public class PaymentPage extends ReusableCode {

	WebDriver driver;
	By countrylist = By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button");
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@placeholder='Select Country']")
	WebElement countrydropdown;
	
	@FindBy(xpath="//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> countrylisttoselect;
	
	@FindBy(css=".action__submit")
	WebElement submit;

	public void fillTheDetails() {
		countrydropdown.sendKeys("India");
		waitForElementToAppear(countrylist);
		countrylisttoselect.stream().filter(f->f.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);
	}
	
	public OrderConfirmationPage submitPayment() {
		submit.click();
		OrderConfirmationPage orderconfirmationpage = new OrderConfirmationPage(driver);
		return orderconfirmationpage;
}}
