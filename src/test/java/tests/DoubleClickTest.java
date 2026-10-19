package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import page.DoubleClickPage;

public class DoubleClickTest extends BaseTest{
	
	@Test
	
	public void DoubleTest() {
		
		DoubleClickPage dcp=new DoubleClickPage(driver,wait);
		dcp.doubleClick();
	}
	

}
