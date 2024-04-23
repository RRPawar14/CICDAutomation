package SeleniumFramework.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	 
//	WebElement userEmail= driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
//	ng-tns-c4-9 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue LoginAction(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatlogue= new ProductCatalogue(driver);
		return productCatlogue;
	}
	
	public void Goto() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	 public String getErrorMessage() {
		 
		 waitForWebElementToAppear(errorMessage);
		 return errorMessage.getText();
		  
		 
	 }
	 
	
	
	
//	driver.findElement(By.id("login"))
//	driver.findElement(By.id("userPassword"))
	
	
	

}
