package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import page.GUIElementsPage;
import testdata.TestDataProvider;

public class GUIElementsTest extends BaseTest {
	
    @Test(dataProvider = "guiFormData", dataProviderClass = TestDataProvider.class, priority = 1)
    public void testCompleteGUIFormSubmission(String name, String email, String phone, String address,
                                               String gender, String days, String country, String color, String date) {
        GUIElementsPage guiPage = new GUIElementsPage(driver, wait);
        // Text input fields
        
        guiPage.enterName(name);
        Assert.assertEquals(guiPage.getNameValue(), name, "Name field value mismatch");
        guiPage.enterEmail(email);
        Assert.assertEquals(guiPage.getEmailValue(), email, "Email field value mismatch");
        guiPage.enterPhone(phone);
        Assert.assertEquals(guiPage.getPhoneValue(), phone, "Phone field value mismatch");
        guiPage.enterAddress(address);
        Assert.assertEquals(guiPage.getAddressValue(), address, "Address textarea value mismatch");
        //  Radio buttons
        
        guiPage.selectGender(gender);
        Assert.assertTrue(guiPage.isGenderSelected(gender), "Selected gender radio button should be checked");
//         Check-boxes
        
        String[] dayList = days.split(",");
        for (String day : dayList) {
            guiPage.selectDay(day);
            Assert.assertTrue(guiPage.isDaySelected(day), "Day checkbox should be checked: " + day);
        }
        //  Drop-downs
        guiPage.selectCountry(country);
        Assert.assertTrue(guiPage.getSelectedCountryValue().equalsIgnoreCase(country) ||
                          guiPage.getSelectedCountry().toLowerCase().contains(country.toLowerCase()) ||
                          guiPage.getSelectedCountry().equalsIgnoreCase("United States") && country.equalsIgnoreCase("usa"),
                "Selected country dropdown mismatch");
        guiPage.selectColor(color);
        Assert.assertTrue(guiPage.getSelectedColorValue().equalsIgnoreCase(color) ||
                          guiPage.getSelectedColor().toLowerCase().contains(color.toLowerCase()),
                "Selected color dropdown mismatch");
        //  Date picker
        guiPage.setDate(date);
        Assert.assertEquals(guiPage.getDateValue(), date, "Date picker value mismatch");
    }
    
    @Test(dataProvider = "guiFormBoundaryData", dataProviderClass = TestDataProvider.class, priority = 2)
    public void testGUIFormBoundaryValues(String name, String email, String phone, String address,
                                           String gender, String day, String country, String color, String date) {
        GUIElementsPage guiPage = new GUIElementsPage(driver, wait);
        guiPage.enterName(name);
        Assert.assertEquals(guiPage.getNameValue(), name, "Boundary name mismatch");
        guiPage.enterEmail(email);
        Assert.assertEquals(guiPage.getEmailValue(), email, "Boundary email mismatch");
        guiPage.selectGender(gender);
        Assert.assertTrue(guiPage.isGenderSelected(gender), "Boundary gender radio should be selected");
        guiPage.selectDay(day);
        Assert.assertTrue(guiPage.isDaySelected(day), "Boundary day checkbox should be selected");
        guiPage.unselectDay(day);
        Assert.assertFalse(guiPage.isDaySelected(day), "Unselected day checkbox should not be checked");
    }
}
    
