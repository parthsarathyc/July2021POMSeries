package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utlis.Constants;
import com.qa.opencart.utlis.ElementUtil;

public class Resultspage {
	private By searchHeader = By.cssSelector("div#content h1");
	// how many product results are coming 
	private By productResuls = By.xpath("//div[@class='caption']/h4/a");
	private WebDriver driver; 
	private ElementUtil elementutil; 

	//create constructor 
	
	public Resultspage(WebDriver driver) {
		this.driver=driver; // driver value is passed to the above declared WebDriver driver 
		elementutil=new ElementUtil(driver); //driver value is passed here
	}
	
	public String getSearchHeaderName() {
		
		return elementutil.doGetText(searchHeader); 
		
	}
	
	public int getSearchProductListCount() {
		
		return elementutil.waitforElementsVisible(productResuls, Constants.DEFAULT_TIME_OUT).size();
		
	}
	
	//now we have to click on an product - say Macboook pro. After clicking it takes to new page 
	//POM says all respective  locators should be created inside a class only which are related to that page. 
	//the respective page actions should be created on the page class 
	
	
	public ProductInfopage selectProduct (String mainProductName) {
		List<WebElement> searchList= elementutil.waitforElementsVisible(productResuls, Constants.DEFAULT_TIME_OUT);
		
		for (WebElement e : searchList) {
			String text= e.getText().trim();
			if (text.equalsIgnoreCase(mainProductName)) {
				e.click();
				break; 			
			}		
		}
		 //returns next landing page class object
		// as part of TDD approach we write the newclass 
		//this ProductInfopage can be created with options as shown below   
		
		return new ProductInfopage(driver); 

								
	}
	
	
	
	
	
	
}
