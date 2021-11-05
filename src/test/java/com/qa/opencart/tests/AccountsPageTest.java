package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utlis.Constants;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
@Epic("Epic 100: account cart app - Design login page" ) //copy from jira 
@Story("US 101 : account page features with soe basic modules   ")//copy from jira 
public class AccountsPageTest extends BaseTest {

	@BeforeClass     
	
	public void accPageSetUp() {
		
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password")); 
		
	}
	
	@Test
	public void accPageTitleTest() {
		String Title = accPage.accPageTile();
		System.out.println("account page title is:" +Title);
		Assert.assertEquals(Title, Constants.ACC_PAGE_TITLE);
		
	}
	
	@Test
	public void accPageLogoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
	@Test
	public void accPageSearchTest() {
		Assert.assertTrue(accPage.issearchFieldExist());
		
	}
	
	@Test
	public void accPageSecHeaderTest() {
	
		List<String> actSecList =accPage.getAccountsecList(); //returns list f Strings hence storing in the List<String> 
		System.out.println(actSecList);
		Assert.assertEquals(actSecList, Constants.EXP_ACCOUNTS_SECTIONS_LIST);
		
	}
	// Parameterize test with data driven approach. 
	//Data driven can be achieved without excel sheet as well.   
	//Data driven is driving data from external sources . 
	
	@DataProvider //return type of DataProvider is 2D object array 
	public Object [][] productdata() {
		return new Object [][] {
			{"macbook"}, // row no 1 
			{"macbook pro"}, //row no 2 
			{"Apple"}// row no 3
			// three rows and 1 column 
			
			
		};
	}
		
	@Test (dataProvider= "productdata")
	
	public void searchTest(String productname ) { //connect dataProvider with this 
		
		resultPage = accPage.doSearch(productname);//hard coding removed. 
	
		Assert.assertTrue(resultPage.getSearchProductListCount()>0);
		
	}
	
	//create a new data provider for below method as it helps in easy maintenance 
	@DataProvider //
	public Object [][] productSelectdata() {
		return new Object [][] {
			{"macbook","macbook pro"}, 
			{"iMac","iMac"}
		
			
		};
	}
	
	@Test (dataProvider="productSelectdata")
	public void selectProductTest(String prooductName, String mainProoductName ) {
		resultPage =accPage.doSearch(prooductName); //hard coding removed 
		resultPage.selectProduct(mainProoductName); //hard coding removed 
		
	}
	
	
	
	
	
	
}
