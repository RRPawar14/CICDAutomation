package SeleniumFramework.test;

import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;


import SeleniumFramework.PageObject.CartPage;
import SeleniumFramework.PageObject.CheckoutPage;
import SeleniumFramework.PageObject.ConfirmationPage;
import SeleniumFramework.PageObject.LoginPage;
import SeleniumFramework.PageObject.ProductCatalogue;
import SeleniumFramework.testComponents.BaseTest;
import SeleniumFramework.testComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest {

	
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void ErrorValidate() throws IOException, InterruptedException {
		String productName="ZARA COAT 3";
//		LoginPage LoginPage= launchApplication();
		LoginPage.LoginAction("rahulpawar1409@gmail.com", "Rohit&14");
		Assert.assertEquals("Incorrect email or password.", LoginPage.getErrorMessage());
		
	
	}
	
		
	}


