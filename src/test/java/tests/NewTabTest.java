package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.NewTabPage;

public class NewTabTest extends BaseTest {

    @Test(priority = 1)
    public void testOpenNewTabViaWikipediaLink() {
        NewTabPage newTabPage = new NewTabPage(driver, wait);
        String parentHandle = driver.getWindowHandle();

        newTabPage.clickWikipediaLink();
        newTabPage.switchToNewTab(parentHandle);

        // Check the new tab
        String url = newTabPage.getUrlWhenLoaded("wikipedia.org");
        Assert.assertTrue(url.contains("wikipedia.org"), "New tab should open wikipedia.org, got: " + url);
        Assert.assertFalse(newTabPage.getTitle().isEmpty(), "New tab title should not be empty");

        // Close the new tab and check we are back on the original page
        newTabPage.closeNewTabAndGoBack(parentHandle);
        Assert.assertEquals(driver.getWindowHandle(), parentHandle, "Should be back on the parent window");
        Assert.assertTrue(driver.getCurrentUrl().contains("testautomationpractice.blogspot.com"),
                "Parent page URL should be the original site");
    }

    @Test(priority = 2)
    public void testOpenNewTabViaWikipediaSearch() {
        NewTabPage newTabPage = new NewTabPage(driver, wait);
        String parentHandle = driver.getWindowHandle();

        newTabPage.searchAndClickFirstResult("Selenium");
        newTabPage.switchToNewTab(parentHandle);

        String url = newTabPage.getUrlWhenLoaded("wikipedia.org");
        Assert.assertTrue(url.contains("wikipedia.org"), "Search result should open wikipedia.org, got: " + url);

        newTabPage.closeNewTabAndGoBack(parentHandle);
        Assert.assertEquals(driver.getWindowHandle(), parentHandle, "Should be back on the parent window");
    }
}