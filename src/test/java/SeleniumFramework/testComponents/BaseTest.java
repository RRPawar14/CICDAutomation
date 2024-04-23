package SeleniumFramework.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFramework.PageObject.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LoginPage LoginPage;
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {
		
	prop = new Properties();
	FileInputStream file= new FileInputStream(System.getProperty("user.dir")
			+"\\src\\main\\java\\SeleniumFramework\\resources\\GlobalData.properties");
	prop.load(file);
	
	String browserName= System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
//	 prop.getProperty("browser") ------------java ternary operator used 
//	mvn test -PRegression -Dbrowser=firefox
	
	if(browserName.equalsIgnoreCase("chrome")){
	WebDriverManager.chromedriver().setup();
	driver= new ChromeDriver();
	
	}else if (browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver= new FirefoxDriver();

	}else if (browserName.equalsIgnoreCase("Edge")) {
		
	}
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	return driver;
	
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(Source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		
		driver= initializeDriver();
		LoginPage= new LoginPage(driver);
		LoginPage.Goto();
		return LoginPage;
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
}
















