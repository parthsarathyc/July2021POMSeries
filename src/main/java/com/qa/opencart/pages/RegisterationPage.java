package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utlis.Constants;
import com.qa.opencart.utlis.ElementUtil;

public class RegisterationPage {
	
	private ElementUtil elemenetutil; 
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public RegisterationPage(WebDriver driver) {
		elemenetutil = new ElementUtil(driver); 	
	}
	
	public boolean registration (String firstName, String lastName, String email, 
			String telephone,String password,String subsribe) {
		
fillRegForm( firstName,  lastName,  email,  telephone, password);  // fill reg method 

selectSubscriptionOtion(subsribe); 	// call the method - for subscription 

selectAgreementAndContinue();  
return getResistrationStatus(); 
	}
	
	//Making below private - private method used by public internally 
	//because i dont want to give the access to fill Reg Form to testNG class/user class
	//kind of encapsulation whre we are abstracting the data like this 
	
private void fillRegForm(String firstName, String lastName, String email, 
		String telephone,String password) {
	
	elemenetutil.dosendkey(this.firstName, firstName);
	
	elemenetutil.dosendkey(this.lastName, lastName);
	elemenetutil.dosendkey(this.email, email);
	elemenetutil.dosendkey(this.telephone, telephone);
	elemenetutil.dosendkey(this.password, password);
	elemenetutil.dosendkey(confirmpassword, password);
}
	
	private  void selectSubscriptionOtion(String subsribe) {
		
		if (subsribe.equalsIgnoreCase("yes")) {
			elemenetutil.doClick(subscribeYes);
		}else {
			elemenetutil.doClick(subscribeNo);
		}
	}
	
	
	private void selectAgreementAndContinue() {
		elemenetutil.doClick(agreeCheckBox);
		elemenetutil.doClick(continueButton);
	}
	
	private boolean getResistrationStatus() {
		String msg = 	elemenetutil.doGetText(sucessMessg);
		
		if (msg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			elemenetutil.doClick(logoutLink);
			elemenetutil.doClick(registerLink);
			
			return true; 
		}	
		
		return false; 
	}
	
}
