package base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public class BaseTest {

	
	public WebDriver driver;
	public WebDriverWait wait;
	
	//TestNg annotation that calls this method before the test execution begins
	@BeforeMethod
	
	public void setUp() throws IOException {
		
		WebDriverManager.chromedriver().setup();
		ConfigReader config=new ConfigReader();
		driver=new ChromeDriver();
		
		driver.get(config.getProperty("url"));
		driver.manage().window().maximize();
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
	}
	
	//TestNG annotation that calls this method after the Test is executed
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
}
