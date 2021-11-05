package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop; 
	private ChromeOptions co; //this class will helpas shown in below method 
// to set the headless mode/incognio 	we need properties. 
	
	private FirefoxOptions fo;  // to use firefox options
	
	//create constructor
	
	public OptionsManager (Properties prop) {
		this.prop=prop; //this does not need a driver and requires properties. 
		}
	
	public ChromeOptions getChromeOptions(){ //return type is chromeDriver 
		co= new ChromeOptions(); 
	//	co.addArguments("--headless");//we have to pass this for headless
	//we can pass the headless argument on the basis of if condition
			
			if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
			// this will give string =headless=true. As this is if condition 
			//so by using concept of wrapper class convert that string into a boolean 		
			}
			
			//similarly for incognito 
			if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
				co.addArguments("--incognito");		
		}
		
			return co; 
	}
	
	public FirefoxOptions getfirefoxOptions(){//return type is firefox 
		fo= new FirefoxOptions(); 
	
			if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
			
			}
			
			//similarly for incognito 
			if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
				fo.addArguments("--incognito");		
		}
		
			return fo; 
	}
	
	//similarly we can write for rdge, safari etc.... 
	
	
	
	

}
