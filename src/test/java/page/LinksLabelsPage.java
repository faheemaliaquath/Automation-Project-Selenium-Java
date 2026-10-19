package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class LinksLabelsPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators
    By allLinks = By.tagName("a");
    By brokenSectionLinks = By.xpath("//h4[normalize-space()='Broken Links']/following-sibling::a");

    public LinksLabelsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Returns the URL of every link on the page that starts with http or https
    public List<String> getAllLinkUrls() {
        return getUrls(allLinks);
    }

    // Returns the URLs of the links under the "Broken Links" heading
    public List<String> getBrokenSectionLinkUrls() {
        return getUrls(brokenSectionLinks);
    }

    private List<String> getUrls(By locator) {
        List<String> urls = new ArrayList<>();
        for (WebElement link : wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator))) {
            String href = link.getAttribute("href");
            // Skip empty links and links like javascript: or mailto:
            if (href != null && (href.startsWith("http://") || href.startsWith("https://"))) {
                urls.add(href);
            }
        }
        return urls;
    }

    // Opens the URL and returns the status code (200 = OK, 404 = not found, 0 = could not connect)
    public int getStatusCode(String urlString) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) URI.create(urlString).toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            return connection.getResponseCode();
        } catch (Exception e) {
            return 0;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // A link is broken if the status code is 400 or higher, or if we could not connect
    public boolean isBroken(int statusCode) {
        return statusCode >= 400 || statusCode == 0;
    }
}