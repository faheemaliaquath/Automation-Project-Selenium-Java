package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MouseHoverPage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public MouseHoverPage(WebDriver driver, WebDriverWait wait) 
	{
		this.driver=driver;
		this.wait=wait;
	}
	
	public void mouseHover() {
		
		Actions hover=new Actions(driver);
		
		WebElement hoverelement=driver.findElement(By.xpath("//*[@id=\"HTML3\"]/div[1]/div/button"));
		
		hover.moveToElement(hoverelement).perform();
		
	}
}