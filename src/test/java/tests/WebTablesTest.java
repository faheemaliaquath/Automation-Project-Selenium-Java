package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.WebTablesPage;
import testdata.TestDataProvider;

import java.util.Arrays;
import java.util.List;

public class WebTablesTest extends BaseTest {

    @Test(priority = 1)
    public void testStaticTableStructure() {
        WebTablesPage tablesPage = new WebTablesPage(driver, wait);

        Assert.assertEquals(tablesPage.getBookRowCount(), 6, "BookTable should have 6 data rows");

        // Checks both the number of columns (4) and their names
        Assert.assertEquals(tablesPage.getBookHeaders(),
                Arrays.asList("BookName", "Author", "Subject", "Price"),
                "BookTable headers do not match");
    }

    @Test(dataProvider = "staticTableBooksData", dataProviderClass = TestDataProvider.class, priority = 2)
    public void testStaticTableRecordDetails(String bookName, String expectedAuthor,
                                             String expectedSubject, String expectedPrice) {
        WebTablesPage tablesPage = new WebTablesPage(driver, wait);

        // Row order: [BookName, Author, Subject, Price]
        List<String> book = tablesPage.getBookRow(bookName);
        Assert.assertNotNull(book, "Book not found in table: " + bookName);

        Assert.assertEquals(book.get(1), expectedAuthor, "Author mismatch for: " + bookName);
        Assert.assertEquals(book.get(2), expectedSubject, "Subject mismatch for: " + bookName);
        Assert.assertEquals(book.get(3), expectedPrice, "Price mismatch for: " + bookName);
    }

    @Test(priority = 3)
    public void testDynamicProcessTable() {
        WebTablesPage tablesPage = new WebTablesPage(driver, wait);

        Assert.assertTrue(tablesPage.getProcessRowCount() >= 4, "taskTable should have at least 4 processes");

        List<String> chrome = tablesPage.getProcessRow("Chrome");
        Assert.assertNotNull(chrome, "Chrome process should be present in the dynamic table");
        Assert.assertFalse(chrome.isEmpty(), "Chrome process metrics should not be empty");
    }

    @Test(dataProvider = "paginationTableData", dataProviderClass = TestDataProvider.class, priority = 4)
    public void testPaginationTableRecordSelection(int pageNumber, String productName, String expectedPrice) {
        WebTablesPage tablesPage = new WebTablesPage(driver, wait);

        tablesPage.goToPage(pageNumber);
        Assert.assertTrue(tablesPage.getProductRowCount() > 0, "No rows shown on page " + pageNumber);

        // Row order: [ID, Name, Price]
        List<String> product = tablesPage.getProductRow(productName);
        Assert.assertNotNull(product, "Product '" + productName + "' not found on page " + pageNumber);
        Assert.assertEquals(product.get(2), expectedPrice, "Price mismatch for product: " + productName);

        tablesPage.selectProduct(productName);
        Assert.assertTrue(tablesPage.isProductSelected(productName),
                "Checkbox for '" + productName + "' should be checked");
    }
}