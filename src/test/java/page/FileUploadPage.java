package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUploadPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By singleFileInput = By.id("singleFileInput");
    By multipleFilesInput = By.id("multipleFilesInput");
    By multipleFilesButton = By.xpath("//form[@id='multipleFilesForm']//button[@type='submit']");
    By multipleFilesStatus = By.id("multipleFilesStatus");

    public FileUploadPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Choose one file (send the full file path to the file input)
    public void uploadSingleFile(String filePath) {
        driver.findElement(singleFileInput).sendKeys(filePath);
    }

    // The browser shows the chosen file name in the input's value
    public String getSingleFileName() {
        return driver.findElement(singleFileInput).getAttribute("value");
    }

    // Choose several files (separate the paths with a new line) and click Upload
    public void uploadMultipleFiles(String filePaths) {
        driver.findElement(multipleFilesInput).sendKeys(filePaths);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(multipleFilesButton));
        // Scroll down to the button so nothing blocks the click
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        button.click();
    }

    public String getMultipleFilesStatus() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(multipleFilesStatus)).getText();
    }
}