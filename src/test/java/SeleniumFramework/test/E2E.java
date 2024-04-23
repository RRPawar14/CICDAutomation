package SeleniumFramework.test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.PageObject.CartPage;
import SeleniumFramework.PageObject.CheckoutPage;
import SeleniumFramework.PageObject.ConfirmationPage;
import SeleniumFramework.PageObject.LoginPage;
import SeleniumFramework.PageObject.OrderPage;
import SeleniumFramework.PageObject.ProductCatalogue;
import SeleniumFramework.testComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class E2E extends BaseTest {
	String productName="ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
//		LoginPage LoginPage= launchApplication();
		ProductCatalogue productCatlogue=LoginPage.LoginAction(input.get("email"), input.get("password"));
		List<WebElement> product = productCatlogue.getProductList();
		productCatlogue.addProductToCart(input.get("product"));
		CartPage cartpage= productCatlogue.goToCartPage();
		boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkout= cartpage.goToCheckout();
		checkout.selectCountry("india");
		ConfirmationPage confirmationpage= checkout.submitOrder();
	
		String ConfirmMsg= confirmationpage.getConfirmationMessage();
		Assert.assertTrue(ConfirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistory() {
		ProductCatalogue productCatlogue=LoginPage.LoginAction("rahulpawar1409@gmail.com", "Rohit&1409");
		OrderPage orderPage= productCatlogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumFramework//data//PurchaseData.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
	
	

	
	
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//		
//	List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
//
//		return new Object[][] {{data.get(0)},{data.get(1)}};
//	
//	}
	
	
	
	
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"rahulpawar1409@gmail.com", "Rohit&1409","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	
//	}
	
//	HashMap<String, String> map = new HashMap<String,String>();
//	map.put("email","rahulpawar1409@gmail.com");
//	map.put("password", "Rohit&1409");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String,String>();
//	map1.put("email","shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
//	
//	
//	return new Object[][] {{map},{map1}};


}