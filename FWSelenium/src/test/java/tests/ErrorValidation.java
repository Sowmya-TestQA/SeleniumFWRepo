package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.CartSectionPage;
import pageobjects.OrdersSectionPage;
import pageobjects.SelectProductPage;
import testComponents.BaseTest;

public class ErrorValidation extends BaseTest {
	public String prodname = "IPHONE 13 PRO"; 
	
	@Test(groups = {"Error validations"},retryAnalyzer=testComponents.RetryTest.class)
	public void loginNegativeCheck() throws IOException{
	loginpage.loginToAppln("sowmyam64@gmail.com", "Sowmya@123");
	String message = loginpage.getErrorMessage();
	Assert.assertEquals("Incorrect email or password.", message);
	}
	
	@Test
	public void productNegativeCheck() throws IOException{

		SelectProductPage selectproductpage = loginpage.loginToAppln("sowmya64@gmail.com", "Sowmya@123");
	    selectproductpage.productList();
		selectproductpage.getProductByName(prodname);
		
		CartSectionPage cartsectionpage = selectproductpage.addToCart();
		boolean value = cartsectionpage.validateCartList("IPHONE 13 PRO");
		Assert.assertFalse(value);
		System.out.print("test 1 done");
	}
	
	@Test(dependsOnMethods = {"productNegativeCheck"})
	public void validateOrders() throws IOException{
		
		SelectProductPage selectproductpage = loginpage.loginToAppln("sowmya64@gmail.com", "Sowmya@123");
		OrdersSectionPage orderssectionpage = selectproductpage.goToOrders();
		boolean value = orderssectionpage.verifyOrderMatch(prodname);
		Assert.assertTrue(value);
	}
}
