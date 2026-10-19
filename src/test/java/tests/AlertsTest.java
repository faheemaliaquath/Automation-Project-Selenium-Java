package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import page.AlertsPage;

public class AlertsTest extends BaseTest{
	
	
	@Test
	public void SimpleAlert() {
		AlertsPage alert1=new AlertsPage(driver,wait);
		
		alert1.simpleAlert();
	}
	
	@Test
	public void ConfirmationAlert() {
		AlertsPage alert2=new AlertsPage(driver,wait);
		
		alert2.confirmAlert();
	}

	@Test
	public void PromptAlert() {
		AlertsPage alert3=new AlertsPage(driver,wait);
		alert3.promptAlert();
	}
}
