package reusablePackage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.CartSectionPage;
import pageobjects.OrdersSectionPage;

public class ReusableCode {
	
	WebDriver driver;
	public ReusableCode(WebDriver driver){
		this.driver = driver;
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cart_icon;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orders_icon;
	
	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));

}	
	public void waitForWebElementToAppear(WebElement findBy) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));

}	
	
	public void WaitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	public CartSectionPage goToCart() {
		CartSectionPage cartsectionpage = new CartSectionPage(driver);
		cart_icon.click();
		return cartsectionpage;
	}
	
	public OrdersSectionPage goToOrders() {
		orders_icon.click();
		OrdersSectionPage orderssectionpage = new OrdersSectionPage(driver);
		return orderssectionpage;
	}
}
