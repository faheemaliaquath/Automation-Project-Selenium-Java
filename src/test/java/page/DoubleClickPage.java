package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DoubleClickPage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	By button=By.xpath("//*[@id=\"HTML10\"]/div[1]/button");
	
	public DoubleClickPage(WebDriver driver, WebDriverWait wait) 
	{
		this.driver=driver;
		this.wait=wait;
	}
	
	public void doubleClick() {
		Actions double_click=new Actions(driver);
		
		WebElement btn=driver.findElement(button);
		double_click.doubleClick(btn).perform();
		
	}

	
	
}
