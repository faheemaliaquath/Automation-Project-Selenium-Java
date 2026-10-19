package page;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	By simple=By.id("alertBtn");
	By confirmation=By.id("confirmBtn");
	By prompt=By.id("promptBtn");
	
	
	
	public AlertsPage(WebDriver driver,WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	
	public void simpleAlert() {
		WebElement simpleAlertButton=wait.until(ExpectedConditions.elementToBeClickable(By.id("alertBtn")));

		simpleAlertButton.click();
		
		Alert simple=driver.switchTo().alert();
		simple.accept();
	
	}

	
	public void confirmAlert() {
		WebElement confirmationAlertButton=wait.until(ExpectedConditions.elementToBeClickable(confirmation));

		confirmationAlertButton.click();
		
		Alert confirm=driver.switchTo().alert();
		confirm.dismiss();
	
	}
	
	
	public void promptAlert() {
		WebElement promptAlertButton=wait.until(ExpectedConditions.elementToBeClickable(prompt));

		promptAlertButton.click();
		
		
		Alert prompt=wait.until(ExpectedConditions.alertIsPresent());
		prompt.sendKeys("Ricky Martin");
		prompt.accept();
	
	}
	
	
	
	
}
