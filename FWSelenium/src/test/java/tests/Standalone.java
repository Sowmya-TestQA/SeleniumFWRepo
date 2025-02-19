package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.CartSectionPage;
import pageobjects.OrderConfirmationPage;
import pageobjects.PaymentPage;
import pageobjects.SelectProductPage;
import testComponents.BaseTest;

public class Standalone extends BaseTest  {
	
	@Test(dataProvider = "getData", groups="ProductOrder")
	public void productOrder(HashMap<String,String> data) throws IOException{
		
		SelectProductPage selectproductpage = loginpage.loginToAppln(data.get("email"), data.get("password"));
	    selectproductpage.productList();
		selectproductpage.getProductByName(data.get("productname"));
		
		CartSectionPage cartsectionpage = selectproductpage.addToCart();
		boolean value = cartsectionpage.validateCartList((data.get("productname")));
		Assert.assertTrue(value);
		
		PaymentPage paymentpage = cartsectionpage.checkout();
		paymentpage.fillTheDetails();
		OrderConfirmationPage orderconfirmationpage= paymentpage.submitPayment();
		
		String confirmatorytmessage = orderconfirmationpage.verifyOrderConfirmation();
		Assert.assertEquals(confirmatorytmessage, "THANKYOU FOR THE ORDER.");
		
		System.out.println("*****Test is completed "  + confirmatorytmessage + " *****");
		
	}
	
	@DataProvider
	 public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+ "/src/test/java/data/ProductOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
	}//method 3
	
//	 public Object[][] getData() {
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "sowmya64@gmail.com");
//		map.put("password", "Sowmya@123");
//		map.put("productname", "IPHONE 13 PRO");
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "sowmya64@gmail.com");
//		map1.put("password", "Sowmya@123");
//		map1.put("productname", "IPHONE 13 PRO");
//		return new Object[][] {{map},{map1}};
//	
//	}//method2
//	 @DataProvider
//	 public Object[][] getData() {
//		 return new Object[][] {{"sowmya64@gmail.com", "Sowmya@123","IPHONE 13 PRO"},{"sowmya64@gmail.com", "Sowmya@123","QWERTY"}};
//	 } method 1

}


	

