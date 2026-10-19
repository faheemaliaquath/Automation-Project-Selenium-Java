package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinksLabelsPage;

import java.util.List;

public class LinksLabelsTest extends BaseTest {

    @Test(priority = 1)
    public void testCollectValidPageLinks() {
        LinksLabelsPage linksPage = new LinksLabelsPage(driver, wait);

        List<String> urls = linksPage.getAllLinkUrls();
        System.out.println("Total valid links found: " + urls.size());

        Assert.assertFalse(urls.isEmpty(), "The page should have at least one valid link");

        for (String url : urls) {
            Assert.assertTrue(url.startsWith("http://") || url.startsWith("https://"),
                    "Link should start with http or https: " + url);
        }
    }

    @Test(priority = 2)
    public void testDetectBrokenLinks() {
        LinksLabelsPage linksPage = new LinksLabelsPage(driver, wait);

        List<String> urls = linksPage.getBrokenSectionLinkUrls();
        Assert.assertFalse(urls.isEmpty(), "The Broken Links section should have links");

        int brokenCount = 0;
        for (String url : urls) {
            int statusCode = linksPage.getStatusCode(url);
            boolean broken = linksPage.isBroken(statusCode);
            System.out.println(url + " -> status " + statusCode + (broken ? " (BROKEN)" : " (OK)"));
            if (broken) {
                brokenCount++;
            }
        }

        System.out.println("Links checked: " + urls.size() + ", broken: " + brokenCount);
        Assert.assertTrue(brokenCount > 0, "The Broken Links section should contain at least one broken link");
    }
}