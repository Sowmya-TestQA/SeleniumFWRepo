package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablePackage.ReusableCode;

public class OrdersSectionPage extends ReusableCode {

	WebDriver driver;
	public OrdersSectionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderprodlist;
	
	public Boolean verifyOrderMatch(String productname) {
		Boolean value = orderprodlist.stream().anyMatch(p->p.getText().equalsIgnoreCase(productname));
		return value;
	}

}
