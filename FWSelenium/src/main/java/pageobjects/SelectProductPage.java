package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusablePackage.ReusableCode;

public class SelectProductPage extends ReusableCode{

	WebDriver driver;
	WebElement filteredproduct;
	
	public SelectProductPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);// findby annotation will refer this line and takes driver reference 
	}
	
	By products = By.cssSelector(".mb-3");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector("#toast-container");
	
	
	//Pagefactory method - findby is the shortcut of List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css=".mb-3")
	List<WebElement> productsList; // here driver we are not mentioning but commonly we mention inside the constructor
	
	@FindBy(css=".ng-animating")
	WebElement load;
	
	public List<WebElement> productList() {
		waitForElementToAppear(products);
		return productsList;
	}
	
	public void getProductByName(String prodname) {
	 filteredproduct = productsList.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(prodname)).findFirst().orElse(null);
	
	}
	
	public CartSectionPage addToCart() {
	 filteredproduct.findElement(addtocart).click();
	 waitForElementToAppear(toastmessage);
	 WaitForElementToDisappear(load);
	 CartSectionPage cartsectionpage = goToCart();
	 return cartsectionpage;
}
	

	}
