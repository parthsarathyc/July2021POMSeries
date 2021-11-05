package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver driver; //this is a normal driver 
	public Properties prop ; 
	public static String highlight; 
	public OptionsManager optionsManager; 
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); 
	
	//Here  we have created driver only but this is thread local driver.
	//because i want to generate local copy of the driver for webdriver 
	//Threadlocal is having 2 methods - get() and set()
	
	public WebDriver initDriver(Properties prop) {
		
		optionsManager=new OptionsManager(prop); 
	
		highlight = prop.getProperty("highlight"); 
	
		String browser =prop.getProperty("browser").trim();
		
		System.out.println("browser name is " + browser );
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver= new ChromeDriver(optionsManager.getChromeOptions()); // not required 
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//this is equivalent to driver=new chhromedriver() but this driver we
			//are using is the thread local driver 
			//it will start creating the object of chrome driver and then threadlocal copy will be created. 
			
		}else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver= new FirefoxDriver(optionsManager.getfirefoxOptions()); 
			tlDriver.set(new FirefoxDriver(optionsManager.getfirefoxOptions()));
			//TlDriver is the ref variable of ThreadLocal class.
			//Threadlocal class generics are having webdriver -ThreadLocal<WebDriver>
		
		}
		else {
			System.out.println("please pass the the correct browser name");
		}	
//		driver.manage().window().maximize();   //driver replaced with getDriver
//		driver.manage().deleteAllCookies();
//		driver.get(prop.getProperty("url"));	 
//		
		getdriver().manage().window().maximize();// gives the local copy of the driver 
		getdriver().manage().deleteAllCookies();
		getdriver().get(prop.getProperty("url"));
		
		return getdriver(); 
	}
		

	public synchronized  WebDriver getdriver() {
		//whenever you want the local copy of a driver call this method. 
		// multiple threads are acessing the getdriver() then it happen
		//in a synchronised manner hence - synchronized
		return tlDriver.get(); 
	}
	
	
	public Properties initProp() {

		prop= new Properties(); 
		FileInputStream ip =null; // to use tthe referance for different scenarios
		
		String env = System.getProperty("env"); //help me to read the command line argument 
		
		if (env==null) { // production env 
		try {
			 ip  = new FileInputStream ("./src/test/resources/Config/config.properties");
			
			//prop.load(ip); 
		
		} catch (FileNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else {
			System.out.println("running on env: " +   env);
			try {
			switch (env.toLowerCase()) {
			case "qa":
				ip  = new FileInputStream ("./src/test/resources/Config/qa.config.properties");
				// updated url 
				break;
				
			case "dev":
				ip  = new FileInputStream ("./src/test/resources/Config/dev.config.properties");
				// updated url 
				break;
				
			case "stage":
				ip  = new FileInputStream ("./src/test/resources/Config/stage.config.properties");
				// updated url 
				break;

			default:
				System.out.println(".....Please pass the right environment....." + env);
				break;
			}
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}
			//try catch block is auto prompted .
			//once this ip in initaialised we have to load the property 	
} 
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return prop ; 
	}


//take screenshot 
	public String getScreenshot() {
		
		File srcFile= ((TakesScreenshot)getdriver()).getScreenshotAs(OutputType.FILE);
		//to take a screenshot we need driver ---> getDriver ()--> we are getting thread local driver 
		//((TakesScreenshot)getdriver())--> the entire ting will be take screenshot class 
		//OutputType.FILE-> taking file type screenshot and storing in file 
		
		String path = System.getProperty("user.dir")+ "/screenshot/" + System.currentTimeMillis() + "png"; 
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}




}
	