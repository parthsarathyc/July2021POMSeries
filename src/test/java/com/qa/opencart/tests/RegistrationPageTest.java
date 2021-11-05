package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utlis.Constants;
import com.qa.opencart.utlis.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() {
		registerationPage=loginpage.navigatetoRegistrationPage(); 
	}	
//Data driven approach from excel sheet 
//this method will return 2-D object array with data coming from excel sheet 

	
	
	@DataProvider
	public Object[][] getRegTestData() {
		
	Object data[][]=ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME); //pass sheet name 
	//this returns a 2 D object array hence can be stored in 2D array
		
		return data; 
		
	}
	
	
	
	@Test (dataProvider ="getRegTestData")  //mapping with data providor 
	public void registrationTest(String firstname ,String lastname,
			String email,String tel, String pwd,String Subsribe ) {		
		//In the same sequence as in the excel sheet 
		Assert.assertTrue(registerationPage.registration( firstname , lastname, email, 
				tel,  pwd, Subsribe));
	}
	
	



}




