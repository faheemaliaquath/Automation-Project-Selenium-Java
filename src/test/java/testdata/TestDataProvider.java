package testdata;

import java.io.File;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
	@DataProvider(name = "guiFormData")
    public static Object[][] getGuiFormData() {
        return new Object[][] {
            // name, email, phone, address, gender, days (comma-separated), country, color, date
            {"Alice Johnson", "alice@test.com", "9876543210", "123 Elm Street, Cityville", "female", "sunday,wednesday", "usa", "red", "10/25/2026"},
            {"Bob Smith", "bob.smith@example.org", "9123456780", "456 Oak Avenue, Metropolis", "male", "monday,friday", "canada", "blue", "11/15/2026"},
            {"Carlos Mendoza", "carlos@demo.net", "9988776655", "789 Pine Road, Gotham", "male", "tuesday,thursday,saturday", "germany", "green", "12/01/2026"}
        };
    }
    @DataProvider(name = "guiFormBoundaryData")
    public static Object[][] getGuiFormBoundaryData() {
        return new Object[][] {
            // Extreme/boundary values (e.g. empty fields, single char, max length)
            {"", "", "", "", "male", "monday", "japan", "yellow", "01/01/2026"},
            {"A", "a@b.co", "1", "Short", "female", "sunday", "france", "white", "02/02/2026"}
        };
    }
    
    @DataProvider(name = "dropdownAndRadioData")
    public static Object[][] getDropdownAndRadioData() {
        return new Object[][] {
            {"male", "usa", "red"},
            {"female", "canada", "blue"},
            {"male", "germany", "green"},
            {"female", "japan", "yellow"}
        };
    }
    @DataProvider(name = "staticTableBooksData")
    public static Object[][] getStaticTableBooksData() {
        return new Object[][] {
            // BookName, Author, Subject, Price
            {"Learn Selenium", "Amit", "Selenium", "300"},
            {"Learn Java", "Mukesh", "Java", "500"},
            {"Learn JS", "Animesh", "Javascript", "300"},
            {"Master In Selenium", "Mukesh", "Selenium", "3000"},
            {"Master In Java", "Amod", "JAVA", "2000"},
            {"Master In JS", "Amit", "Javascript", "1000"}
        };
    }
    
    @DataProvider(name = "paginationTableData")
    public static Object[][] getPaginationTableData() {
        return new Object[][] {
            // Page Number, Product Name, Expected Price
            {1, "Smartphone", "$10.99"},
            {1, "Laptop", "$19.99"},
            {2, "Television", "$20.99"},
            {3, "Router", "$24.99"}
        };
    }
    @DataProvider(name = "alertPromptData")
    public static Object[][] getAlertPromptData() {
        return new Object[][] {
            {"John Doe", "Hello John Doe! How are you today?"},
            {"Selenium Tester", "Hello Selenium Tester! How are you today?"},
            {"Automation Expert", "Hello Automation Expert! How are you today?"}
        };
    }
    @DataProvider(name = "doubleClickTextData")
    public static Object[][] getDoubleClickTextData() {
        return new Object[][] {
            {"Custom Test Input 123"},
            {"Selenium Automation Framework"},
            {"Data Driven Double Click Value"}
        };
    }
    
    @DataProvider(name = "fileUploadData")
    public static Object[][] getFileUploadData() {
        String basePath = System.getProperty("user.dir") + File.separator + "src" + 
                File.separator + "test" + File.separator + "resources" + 
                File.separator + "test-files" + File.separator;
        return new Object[][] {
            {basePath + "sample-upload.txt", "sample-upload.txt"},
            {basePath + "sample-second.txt", "sample-second.txt"}
        };
    }
    @DataProvider(name = "searchNewTabData")
    public static Object[][] getSearchNewTabData() {
        return new Object[][] {
            {"Selenium"},
            {"TestNG"}
        };
    }
}
