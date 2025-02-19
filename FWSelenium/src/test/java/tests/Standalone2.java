package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageobjects.LoginPage;

public class Standalone2 {
	
	public static void main(String[] args){
		String prodname = "IPHONE 13 PRO";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
//		driver.findElement(Username).sendKeys("sowmya64@gmail.com");
//		driver.findElement(Password).sendKeys("Sowmya@123");
//		driver.findElement(LoginButton).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement filteredproduct = products.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(prodname)).findFirst().orElse(null);
		filteredproduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> prodnames = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean value = prodnames.stream().anyMatch(p->p.getText().equalsIgnoreCase(prodname));
		Assert.assertTrue(value);
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		driver.findElement(By.xpath("//*[@placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button")));
		List<WebElement> country = driver.findElements(By.xpath("//section[@class='ta-results list-group ng-star-inserted']/button"));
		country.stream().filter(f->f.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);
		driver.findElement(By.cssSelector(".action__submit")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String 	confirmatortText = driver.findElement(By.cssSelector(".hero-primary")).getText();	
		Assert.assertEquals(confirmatortText, "THANKYOU THE ORDER.");
		System.out.println("Test is completed"  + confirmatortText);
		driver.close();
	}
	
}
