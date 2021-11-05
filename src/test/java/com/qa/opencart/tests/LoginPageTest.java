 package com.qa.opencart.tests;

import javax.management.DescriptorKey;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utlis.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Open cart app - Design login page" ) //copy from jira 
@Story("US 101 : login page features with soe basic modules   ")//copy from jira 
public class LoginPageTest extends BaseTest {

	//differant annotations by allure report 
	@Description("login pae title test ...")  
	//allure report annotation. Ptovide info about the test 
	@Severity(SeverityLevel.NORMAL)  // test case severity 
	@Test
	public void loginPageTitleTest() {
	String title = loginpage.getLoginPageTitle(); 
	
	System.out.println("tiltle is" + title);
	Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	
	@Description("login pae url test ...")  
	@Severity(SeverityLevel.NORMAL)  // test case severity 
	@Test
	public void loginPageUrlTest() {
	String url = loginpage.getLoginPageurl(); 
	
	System.out.println("url is" + url);
	Assert.assertTrue(url.contains((Constants.LOGIN_PAGE_URL_VALUE)));
	
	}
	@Description("forgot password link ...")  
	@Severity(SeverityLevel.CRITICAL)  // test case severity 
	@Test
	public void forgetPwdLinkTest() {

		Assert.assertTrue(loginpage.isForgotPWDlinkExist());
	}
	
	@Description("loginpage register link")  
	@Severity(SeverityLevel.CRITICAL)  // test case severity 
	@Test
	public void RegisterLinkTest() {

		Assert.assertTrue(loginpage.isRegisterLinkExist());
	}
	
	
	@Test
	public void loginTest() {
		
		loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	
	 
	
	
	
	
	
	
}
