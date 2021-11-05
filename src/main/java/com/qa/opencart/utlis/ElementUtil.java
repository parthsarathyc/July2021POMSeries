package com.qa.opencart.utlis;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsutil; 

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		jsutil= new JavaScriptUtil(driver); // to call methods of java script util 
		//same driver will fo Javascript Util and execute the required script with flash/highlight functionality 
	}
	
	
	public WebElement getElement(By locator) {

		WebElement ele= driver.findElement(locator); 
		if (Boolean.parseBoolean(DriverFactory.highlight)) {   
			//highlight is giving output as String --> "true" 
			//if condition requires boolean hence we have to use a boolean parser here.		
			jsutil.flash(ele); //if the highlight=true then only flash the element 		
		}
			
		return ele; 
	}

	
	public List<WebElement> getElements(By locator) {	
		return  driver.findElements(locator); 
	}

	public void dosendkey(By locator, String value) {
		WebElement ele= getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}
	
	public  void doActionSendkeys(By locator, String value) {
		
		Actions act =new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform(); 
		act.sendKeys(getElement(locator), value).build().perform();
	}
	
	public void doClick(By locator) {

		getElement(locator).click();

	}

	public String doGetText(By locator) {

		return getElement(locator).getText();

	}

	public boolean doIsDisplayed(By locator) {

		return getElement(locator).isDisplayed();
	}

	public boolean doIsEnabled(By locator) {

		return getElement(locator).isEnabled();
	}

	public String doGetAttributeValue(By locator, String attrName) {

		return getElement(locator).getAttribute(attrName);
	}

	/***************************
	 * dropdown utils
	 *********************************************************/

	public  void doSelectByIndex(By locator, int Index) {
		Select select = new Select(getElement(locator));

		select.selectByIndex(Index);
	}

	public  boolean doSelectByVisibleText(By locator, String text) { 
		Select select = new Select(getElement(locator));

		select.selectByVisibleText(text);
		return IsDropDoenSelected(select, text); 
		
	}

	public   boolean doSelectByvalue(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByValue(text);
		return IsDropDoenSelected(select, text); 
	}
	
	public  boolean IsDropDoenSelected(Select select, String ExpValue) {
	
		String text = select.getFirstSelectedOption().getText();
		if (text.equals(ExpValue)) {
			return true;
		}
		return false;
	}


	public void doSelectDropdown(By locator, String text) {

		Select select = new Select(getElement(locator));
		List<WebElement> optionList = select.getOptions();

		iterateDropdownandSelect(optionList, "India");

	}

	public void seletDropDownWithoutSelect(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);

		iterateDropdownandSelect(optionsList, "India");

	}

	private void iterateDropdownandSelect(List<WebElement> optionList, String value) {

		for (WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;

			}

		}

	}

	//*********************actions class **************************************************************
	
	
	public  void parentChildMenuHandle(By parent, By Child) throws InterruptedException {
		Actions act = new Actions(driver); 
		act.moveToElement(getElement(parent)).build().perform();
		Thread.sleep(2000);
		getElement(Child).click(); 
		
	}
	
	//*******************************wait utils***********************************************************
	
	public  WebElement waitForElementPresent(By locator, int timeout)
	{
		WebDriverWait wait =new WebDriverWait(driver, timeout);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public  WebElement waitForElementPresent(By locator, long timeout,long intervalTime) 
	
	{
		WebDriverWait wait =new WebDriverWait(driver, timeout, intervalTime);


		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public  Alert waitForJSAlert(int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}

	// for accept alert
	public  void acceptAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
	}

	// for dismiss alert
	public  void dismissAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
		;
	}

	// for alert sendkeys
	public  void AlertSendKeys(int timeOut, String value) {
		waitForJSAlert(timeOut).sendKeys(value);
	}

	public  String AlertGetText(int timeOut) {
		return waitForJSAlert(timeOut).getText();
	}
	
	//this function will tell the url is updated or not 
		public  boolean waitForUrlContains(int timeOut, String urlfraction) {
			WebDriverWait wait =new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.urlContains(urlfraction)) ; 	
			}
			
		public  boolean waitForUrlToBe(int timeOut, String url) {
			WebDriverWait wait =new WebDriverWait(driver, timeOut);
			return wait.until(ExpectedConditions.urlContains(url)) ; 	
			}
		
		public  String waitForTitleToBe(int timeOut, String title) {
			WebDriverWait wait =new WebDriverWait(driver, timeOut);
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle(); 
			}	
			return null; 
			}
		
		public  String waitForTitleContains(int timeOut, String titlefraction) {
			WebDriverWait wait =new WebDriverWait(driver, timeOut);
			 wait.until(ExpectedConditions.titleContains(titlefraction)) ; 	
			return  driver.getTitle(); 
			}
		
		public  List<WebElement> waitforElementsPresent(By locator,int timeOut){
			WebDriverWait wait= new WebDriverWait(driver, timeOut); 
			return	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)); 	
		}

		public  ArrayList<String>  getElementsTextList(By locator,int timeOut){
			WebDriverWait wait= new WebDriverWait(driver, timeOut); 
			List<WebElement> eleList= 	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)); 
			
			ArrayList<String> eleValList=new ArrayList<String>(); 
			
			for (WebElement e : eleList) {
				eleValList.add(e.getText()); 
			}
			return eleValList; 
		}
		public  void  printElementsTextList(By locator,int timeOut){
			WebDriverWait wait= new WebDriverWait(driver, timeOut); 
			List<WebElement> eleList= 	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)); 
		
			for (WebElement e : eleList) {
			System.out.println(e.getText());
			}
		
		}

		public  List<WebElement> waitforElementsVisible(By locator,int timeOut){
			WebDriverWait wait= new WebDriverWait(driver, timeOut); 
			return	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

		public  ArrayList<String>  getVisibleElementsTextList(By locator,int timeOut){
		
			List<WebElement> eleList= 	waitforElementsVisible( locator, timeOut); 
			
			ArrayList<String> eleValList=new ArrayList<String>(); 
			
			for (WebElement e : eleList) {
				eleValList.add(e.getText()); 
			}
			return eleValList; 
		}

		public  WebElement waitFrElementPresenceWithWebDrivertWait(By Locator, int timeOut, int pollingTime) {
			WebDriverWait wait =new WebDriverWait (driver, timeOut);
			wait.withMessage("element not found").
				pollingEvery(Duration.ofMillis(pollingTime)).
					ignoring(StaleElementReferenceException.class);
						
		
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator)); 
		}

		public  WebElement waitFrElementPresenceWithFluentWait(By Locator, int timeOut, int pollingTime) {
			Wait <WebDriver> wait =new FluentWait<WebDriver> (driver).
					withTimeout(Duration.ofSeconds(timeOut)).
						withMessage("element not found").
							pollingEvery(Duration.ofMillis(pollingTime)).	
								ignoring(StaleElementReferenceException.class);		
		
				return wait.until(ExpectedConditions.presenceOfElementLocated(Locator)); 
		}
		



}
