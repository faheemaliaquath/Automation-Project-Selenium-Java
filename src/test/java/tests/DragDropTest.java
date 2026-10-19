package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import page.DragDropPage;

public class DragDropTest extends BaseTest{
	
	
	@Test
	public void DragNDropTest() {
		
		DragDropPage ddp=new DragDropPage(driver,wait);
		ddp.DragAndDrop();
		
	}

}
