package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GUIElementsPage {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	
	//locators
	
	By nameInput=By.id("name");
	By emailInput=By.id("email");
	By phoneInput=By.id("phone");
	By addressTextarea=By.id("textarea");
	By maleRadio=By.id("male");
	By femaleRadio=By.id("female");
	By monday=By.id("monday");
	By friday=By.id("friday");
	By saturday=By.id("saturday");
	By countryDropdown=By.id("country");
	By colorsDropdown=By.id("colors");
	By animals=By.id("animals");
	By datePickerInput=By.id("datepicker");
	
			
	
	//Constructor
	public GUIElementsPage(WebDriver driver,WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
		
	}
	private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
	
	
	public void enterName(String name) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        scrollIntoView(element);
        element.clear();
        element.sendKeys(name);
    }
    public String getNameValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
        return element.getAttribute("value");
    }
    
    
    public void enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        scrollIntoView(element);
        element.clear();
        element.sendKeys(email);
    }
    public String getEmailValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        return element.getAttribute("value");
    }
    
    public void enterPhone(String phone) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        scrollIntoView(element);
        element.clear();
        element.sendKeys(phone);
    }
    public String getPhoneValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        return element.getAttribute("value");
    }
    public void enterAddress(String address) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addressTextarea));
        scrollIntoView(element);
        element.clear();
        element.sendKeys(address);
    }
    public String getAddressValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addressTextarea));
        return element.getAttribute("value");
    }
    
    
    public void selectGender(String gender) {
        By genderLocator = gender.equalsIgnoreCase("female") ? femaleRadio : maleRadio;
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(genderLocator));
        scrollIntoView(element);
        if (!element.isSelected()) {
            element.click();
        }
    }
    public boolean isGenderSelected(String gender) {
        By genderLocator = gender.equalsIgnoreCase("female") ? femaleRadio : maleRadio;
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(genderLocator));
        return element.isSelected();
    }
    
    
    public void selectDay(String day) {
        By dayLocator = By.id(day.trim().toLowerCase());
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        scrollIntoView(element);
        if (!element.isSelected()) {
            element.click();
        }
    }
    public void unselectDay(String day) {
        By dayLocator = By.id(day.trim().toLowerCase());
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        scrollIntoView(element);
        if (element.isSelected()) {
            element.click();
        }
    }
    public boolean isDaySelected(String day) {
        By dayLocator = By.id(day.trim().toLowerCase());
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(dayLocator));
        return element.isSelected();
    }
    
    
    public void selectCountry(String country) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown));
        scrollIntoView(element);
        Select select = new Select(element);
        try {
            select.selectByValue(country.toLowerCase());
        } catch (Exception e) {
            select.selectByVisibleText(country);
        }
    }
    public String getSelectedCountry() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown));
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText().trim();
    }
    public String getSelectedCountryValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdown));
        Select select = new Select(element);
        return select.getFirstSelectedOption().getAttribute("value").trim();
    }
    public void selectColor(String color) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(colorsDropdown));
        scrollIntoView(element);
        Select select = new Select(element);
        try {
            select.selectByValue(color.toLowerCase());
        } catch (Exception e) {
            select.selectByVisibleText(color);
        }
    }
    
    public String getSelectedColor() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(colorsDropdown));
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText().trim();
    }
    public String getSelectedColorValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(colorsDropdown));
        Select select = new Select(element);
        return select.getFirstSelectedOption().getAttribute("value").trim();
    }
    public void setDate(String date) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerInput));
        scrollIntoView(element);
        element.clear();
        element.sendKeys(date);
    }
    public String getDateValue() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerInput));
        return element.getAttribute("value");
    }
    
//	public void FillForm() {
//		WebElement Name=wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
//		Name.sendKeys("Faheema Liaquath Ali");
//		
//		WebElement Email=wait.until(ExpectedConditions.visibilityOfElementLocated(email));
//		Email.sendKeys("faheemaliaquathali@gmail.com");
//		
//		WebElement Phone=wait.until(ExpectedConditions.visibilityOfElementLocated(phone));
//		Phone.sendKeys("9876543210");
//		
//		WebElement Address=wait.until(ExpectedConditions.visibilityOfElementLocated(address));
//		Address.sendKeys("A city, Florida");
//		
//		WebElement Female=wait.until(ExpectedConditions.visibilityOfElementLocated(genderFemale));
//		Female.click();
//		
//		WebElement Monday=wait.until(ExpectedConditions.visibilityOfElementLocated(monday));
//		Monday.click();
//		
//		WebElement Friday=wait.until(ExpectedConditions.visibilityOfElementLocated(friday));
//		Friday.click();
//		
//		
//		Select dd=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(country)));
//		dd.selectByIndex(9);
//		
//		Select cc=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(colors)));
//		cc.selectByContainsVisibleText("Green");
//		
//		Select aa=new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(animals)));
//		aa.selectByIndex(4);
//	
//		
		
		
//	}
	
	
	
}
