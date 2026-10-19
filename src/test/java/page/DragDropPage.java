package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragDropPage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	By s=By.id("draggable");
	By d=By.id("droppable");
	public DragDropPage(WebDriver driver,WebDriverWait wait){
		
		this.driver=driver;
		this.wait=wait;
		
	}
	
	public void DragAndDrop() {
		
		Actions dd=new Actions(driver);
		
		WebElement source=driver.findElement(s);
		
		WebElement destination=driver.findElement(d);
		
		dd.dragAndDrop(source, destination).perform();
	}
	
}
