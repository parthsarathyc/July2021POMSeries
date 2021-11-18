package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utlis.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	
	private WebDriver driver; 
	private ElementUtil elementUtil; 
	
	private By emailId= By.id("input-email"); 
	private By password = By.id("input-password"); 
	private By loginButton = By.xpath("//input[@value='Login']"); 
	private By forgotPwsLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	public LoginPage(WebDriver driver) {  
		
		this.driver=driver; 
		elementUtil =new ElementUtil(driver);
	}
	
	@Step("login page title step.....")
	public String getLoginPageTitle() {
		return driver.getTitle(); 
	}
	@Step("login page url step.....")
	public String getLoginPageurl() {
		return driver.getCurrentUrl(); 
	}
	@Step("forgot pwd link step....")
	public Boolean isForgotPWDlinkExist() {
		
		return elementUtil.doIsDisplayed(forgotPwsLink);
	}	
	public Boolean isRegisterLinkExist() {
	
		return elementUtil.doIsDisplayed(registerLink);
	}
	@Step("login with username : {0} and password : {1} ")
	public AccountsPage doLogin(String un, String pwd) {// user name willd password will be given 0 and 1 respectively 
		System.out.println("creds" + un);
		elementUtil.dosendkey(emailId, un);
		elementUtil.dosendkey(password, pwd);
		elementUtil.doClick(loginButton);
		

		
		return new AccountsPage(driver); //create accounts page object i.e AccoutsPage constructor will be called 
		//the constructor will ask for driver so the driver of Loginpage.java  will get passed there 
		//i.e the same sessions  id will be given to accounts page now. 
	}
	
	public RegisterationPage navigatetoRegistrationPage() {
		
		elementUtil.doClick(registerLink); //the next landing page is registration page 
		//now this is the methods responsibility to return the next landing page class object 
		return new RegisterationPage(driver); 
	}
	
	
	
	
	
}
