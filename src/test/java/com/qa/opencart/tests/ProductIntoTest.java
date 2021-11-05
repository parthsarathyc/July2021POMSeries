package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utlis.Constants;

public class ProductIntoTest extends BaseTest {

@BeforeClass     
	
	public void productInfoPageSetUp() {		
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password")); 
	
	}

	@Test
	public void productHeaderTest() {
	
		resultPage=accPage.doSearch("macbook"); 
		productInfoPage =resultPage.selectProduct("macbook pro"); 
		String actHeader= productInfoPage.getProductHeaderText(); 
		Assert.assertEquals(productInfoPage, "macbook pro");//to validate 
	}

	@DataProvider //
	public Object [][] getImageData() {
		return new Object [][] {
			{"macbook", "macbook Pro",4 }, //4 images for macbook pro 
			{"iMac","iMac",3} ////3 images for iMAC 
		
			
		};
	}
	
	
@Test(dataProvider= "getImageData")
public void productImageCountTest(String productname,String mainProductName, int imageCount ) {
	resultPage=accPage.doSearch(productname); 
	productInfoPage =resultPage.selectProduct(mainProductName); 
	Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
	
}

@Test
public void productMetaDataTest() {
	
	resultPage=accPage.doSearch("macbook"); 
	productInfoPage =resultPage.selectProduct("macbook pro"); 
	Map<String, String> actProdmap =productInfoPage.getProductMtadata();  
	//returns hashmap having all the key value attributes
	//string the same in another hashmap 
	
	//to print the hashmap values we do as below:
	
	actProdmap.forEach((k,v) -> System.out.println(k +":" +v)); //lambdra function 
	//hard assertions 
//	Assert.assertEquals(actProdmap.get("Productname"), "MacBook Pro");
//	Assert.assertEquals(actProdmap.get("Brand"), "Apple");
//	Assert.assertEquals(actProdmap.get("Product Code"), "Product 18");

	//	soft asserting - even if the first assert is getting failed 
	// even then it will give chance to 2nd ,3rd and so on...
	//in short it will not terminate the program. 
	//change assert to softassert in --thats the only change 
	
	softAssert.assertEquals(actProdmap.get("Productname"), "MacBook Pro");
	softAssert.assertEquals(actProdmap.get("Brand"), "Apple");
	softAssert.assertEquals(actProdmap.get("Product Code"), "Product 18");
	softAssert.assertAll(); //this is written after all soft asserts are written
	 //then only it willl give you the proper report. 
	
	
}

















}
