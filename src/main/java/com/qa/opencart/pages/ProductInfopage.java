package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utlis.Constants;
import com.qa.opencart.utlis.ElementUtil;

public class ProductInfopage {
	
	private WebDriver driver; 
	private ElementUtil elementutil; 
	
	private By productHeader =By.xpath("//div[@id='content']//h1"); 
	private By productImages = By.xpath("//a[@class='thumbnail']");
	private By quantity =By.id("input-quantity");
	private By addToCart =By.id("button-cart"); 
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li"); 
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li"); 
	
	
	
	public ProductInfopage(WebDriver driver) {
		this.driver= driver; 
		elementutil = new ElementUtil(driver) ;
		
	}

	public String getProductHeaderText() {
		return elementutil.doGetText(productHeader);
	}
	
	public int getProductImagesCount() {
		
		return elementutil.waitforElementsVisible(productImages, Constants.DEFAULT_TIME_OUT).size(); 
	
	}

	public Map<String,String> getProductMtadata() {
		Map<String,String> prodMap = new HashMap<String,String>(); 
		//want to store the product name in Hashmap as we can see a key and value pair such as "brand : apple "
		String productname=elementutil.doGetText(productHeader); 
		prodMap.put("Productname", productname) ;// to add the values inkey value pair 
		//this is for Macbook Pro 
		getproductMetaData(prodMap);
		getpriceMetaData(prodMap); 
		// so the hashmap stores  all the attributes   in key value pair. 
		
		return prodMap; 
		
	}

   private void getproductMetaData(Map<String,String> prodMap ) {//this functionwill start storing the metadata
	   List<WebElement> metaList= elementutil.getElements(productMetaData); 
	   
	   for (WebElement e : metaList) {
		String metaText=   e.getText();    // data like "brand : apple" will be coming 
		// key -> "brand" :  value --> "apple"
		String metakey = metaText.split(":")[0].trim(); //brand 
		String metaValue = metaText.split(":")[1].trim(); //apple  
		prodMap.put(metakey, metaValue);
	}
   }
	   
	   private void getpriceMetaData(Map<String,String> prodMap ) {//this functionwill start storing the metadata
		   List<WebElement> priceList= elementutil.getElements(productPriceData); 
		   String actPrice =priceList.get(0).getText().trim(); 
		   String exTaxPrice =priceList.get(1).getText().trim(); 
		   //create own  custom key below
		   prodMap.put("price", actPrice); //$2,000.00
		   prodMap.put("ExTax", exTaxPrice.split(":")[1].trim()); //Ex Tax: $2,000.00
		   
	  
		}   
   }



