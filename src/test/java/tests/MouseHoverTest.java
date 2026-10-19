package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import page.MouseHoverPage;

public class MouseHoverTest extends BaseTest {
	
	@Test
	public void HoverTest() {
		
		 MouseHoverPage mhp=new MouseHoverPage(driver,wait);
			
		 mhp.mouseHover();
	}
	
	 
	
}

