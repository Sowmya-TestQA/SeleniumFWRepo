package pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablePackage.ReusableCode;

public class CartSectionPage extends ReusableCode {
	
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartprodlist;
	
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkout;
	
	public CartSectionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	public boolean validateCartList(String productname) {
		Boolean value = cartprodlist.stream().anyMatch(p->p.getText().equalsIgnoreCase(productname));
		return value;
	}
	
	public PaymentPage checkout() {
		checkout.click();
		PaymentPage paymentpage = new PaymentPage(driver);
		return paymentpage;
	}
}
