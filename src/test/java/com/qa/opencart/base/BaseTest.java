package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfopage;
import com.qa.opencart.pages.RegisterationPage;
import com.qa.opencart.pages.Resultspage;

public class BaseTest {
	//all page class references are maintained here  
	public WebDriver driver; 
	public DriverFactory df; 
	public LoginPage loginpage; 
	public Properties prop; 
	public AccountsPage accPage; 
	public Resultspage resultPage; 
	public ProductInfopage  productInfoPage; 
	public RegisterationPage  registerationPage;
	
	
	public SoftAssert softAssert;//this soft assert can be used in child class 

	@BeforeTest
	public void setUp() {
		df= new DriverFactory(); 
		
		prop= df.initProp(); 
		driver=df.initDriver(prop);
		loginpage=new LoginPage(driver);
		
		softAssert = new SoftAssert(); 
	}

	@AfterTest
	public void tearDown() {
		driver.quit();  
		
	}




}
