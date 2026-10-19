package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewTabPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By wikipediaLink = By.xpath("//div[@id='Wikipedia1']//a[contains(@class,'wikipedia-search-wiki-link')]");
    By searchInput = By.id("Wikipedia1_wikipedia-search-input");
    By searchButton = By.className("wikipedia-search-button");
    By firstResult = By.xpath("//div[@id='Wikipedia1_wikipedia-search-results']//a");

    public NewTabPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Waits until the element is clickable, scrolls to it, then clicks
    private void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        element.click();
    }

    // Opens Wikipedia in a new tab (the link uses target='_blank')
    public void clickWikipediaLink() {
        click(wikipediaLink);
    }

    // Searches Wikipedia and clicks the first result (it opens in a new tab)
    public void searchAndClickFirstResult(String searchTerm) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(searchTerm);
        click(searchButton);
        click(firstResult);
    }

    // Switches to the tab that is not the parent tab
    public void switchToNewTab(String parentHandle) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    // A new tab starts as about:blank, so wait for the real URL before reading it
    public String getUrlWhenLoaded(String expectedPart) {
        wait.until(ExpectedConditions.urlContains(expectedPart));
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void closeNewTabAndGoBack(String parentHandle) {
        driver.close();
        driver.switchTo().window(parentHandle);
    }
}