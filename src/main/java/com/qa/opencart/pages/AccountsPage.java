package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utlis.Constants;
import com.qa.opencart.utlis.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver; 
	private ElementUtil elementutil; 
	
	//create by locators
	
	private By Search = By.name("search");
	private By  searchIcon= By.xpath("//span/button[@type='button']");
	private By logoutLink= By.linkText("Logout");
	private By accSecHeaders= By.cssSelector("div#content h2")  ; 

	
	//create constructor of this class 
	public AccountsPage(WebDriver driver) {
		
		this.driver=driver; 
		elementutil= new ElementUtil(driver); //this constructor is already created in Element Util 	
	}
	//create methods etc 
	
	public String accPageTile() {
		return elementutil.waitForTitleToBe(Constants.DEFAULT_TIME_OUT, Constants.ACC_PAGE_TITLE); 
	}

	
	public boolean  isLogoutLinkExist() {
	 return	elementutil.doIsDisplayed(logoutLink); 
		
	}
	
	public boolean  issearchFieldExist() {
		 return	elementutil.doIsDisplayed(Search); 		
		}

	public List<String> getAccountsecList() {
		List<WebElement> secList =elementutil.getElements(accSecHeaders);
		
		List<String> secHeaderList= new ArrayList<String>() ; 
		
		for (WebElement e : secList) {
			secHeaderList.add(e.getText()) ;	
		}
		return secHeaderList; 
	}
	
	public Resultspage doSearch(String productName ) {  // return type changed to ResultsPage 
		System.out.println("Product name is" + productName );
		elementutil.dosendkey(Search, productName);  
		elementutil.doClick(searchIcon); 
		//now we landing on a new page.
		//so this is method responsibility to return next landing page class object. 
		//this is called test driven development approach - to write the test cases we have 
		//to do some development here on the basis of my requirement.
		//like on this case n the basis of requirement we have to create ResultsPage class. 
		
		return new Resultspage(driver); //driver as constructor  is declared at Results Page 
	}
	
}
