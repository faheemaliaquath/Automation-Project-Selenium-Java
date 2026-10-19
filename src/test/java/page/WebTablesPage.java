package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WebTablesPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By bookRows = By.xpath("//table[@name='BookTable']//tr[position() > 1]");
    By bookHeaders = By.xpath("//table[@name='BookTable']//th");
    By taskRows = By.xpath("//table[@id='taskTable']//tbody//tr");
    By productRows = By.xpath("//table[@id='productTable']//tbody//tr");

    public WebTablesPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ---------- Helpers ----------

    // Waits for the rows to appear, then returns them
    private List<WebElement> getRows(By rowsLocator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(rowsLocator));
    }

    // Finds the row whose cell in 'nameColumn' matches 'name' and returns the text of all its cells
    private List<String> findRow(By rowsLocator, int nameColumn, String name) {
        for (WebElement row : getRows(rowsLocator)) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > nameColumn
                    && cells.get(nameColumn).getText().trim().equalsIgnoreCase(name.trim())) {
                List<String> values = new ArrayList<>();
                for (WebElement cell : cells) {
                    values.add(cell.getText().trim());
                }
                return values;
            }
        }
        return null;
    }

    // Scrolls to the element first so ads or banners do not block the click
    private void click(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        element.click();
    }

    private By checkboxFor(String productName) {
        return By.xpath("//table[@id='productTable']//tr[td[contains(normalize-space(),'"
                + productName.trim() + "')]]//input[@type='checkbox']");
    }

    // ---------- Static table (BookTable) ----------

    public int getBookRowCount() {
        return getRows(bookRows).size();
    }

    public List<String> getBookHeaders() {
        List<String> headers = new ArrayList<>();
        for (WebElement header : wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bookHeaders))) {
            headers.add(header.getText().trim());
        }
        return headers;
    }

    // Returns [BookName, Author, Subject, Price] for the book, or null if not found
    public List<String> getBookRow(String bookName) {
        return findRow(bookRows, 0, bookName);
    }

    // ---------- Dynamic table (taskTable) ----------

    public int getProcessRowCount() {
        return getRows(taskRows).size();
    }

    public List<String> getProcessRow(String processName) {
        return findRow(taskRows, 0, processName);
    }

    // ---------- Pagination table (productTable) ----------

    public void goToPage(int pageNumber) {
        By pageLink = By.xpath("//ul[@id='pagination']//a[normalize-space()='" + pageNumber + "']");
        click(wait.until(ExpectedConditions.elementToBeClickable(pageLink)));
        wait.until(ExpectedConditions.attributeContains(pageLink, "class", "active"));
    }

    public int getProductRowCount() {
        return getRows(productRows).size();
    }

    // Returns [ID, Name, Price] for the product, or null if not found
    public List<String> getProductRow(String productName) {
        return findRow(productRows, 1, productName);
    }

    public void selectProduct(String productName) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxFor(productName)));
        if (!checkbox.isSelected()) {
            click(checkbox);
        }
    }

    public boolean isProductSelected(String productName) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(checkboxFor(productName))).isSelected();
    }
}