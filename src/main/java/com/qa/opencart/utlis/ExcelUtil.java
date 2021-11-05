package com.qa.opencart.utlis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static Workbook book; 
	private static Sheet sheet; 
	
	public static final String TEST_DATA_SHEET ="./src/test/resources/testData/OpenKartTestData.xlsx"; 
	
	
	public static Object[][] getTestData(String sheetName) {
		
		Object data[] [] =null; 
	
		try {
			FileInputStream ip =new FileInputStream(TEST_DATA_SHEET);
			
			book = WorkbookFactory.create(ip);
			
			sheet =book.getSheet(sheetName);
		
//now we need to read the data row by row and col by col . 
			//So we will use collection /Data structure - 2D array
			// so we create a 2d object array 
			
	data =new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()]; 
	//need to pass the size of the array
	//getLastRowNum - gives the last row number 
	//sheet.getRow(0).getLastCellNum()- the last cell of 1st row will be the col no. 
	//Col no is usually fixed and we keep n adding rows 
			
	//now we need to fill the 2D array hence 2 loops will bde required to fill rows and cols 
	//1st row -entire col, 2nd row entire col
			
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				 for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
		// the value from excel needs to go to the "data" array. 
					
					 data[i][j]=sheet.getRow(i+1).getCell(j).toString()   ;      
// the first row has fields like username,password ,tell no etc .
//Hence the actual data starts from 2nd row hence i+1 
					 
	 //2nd row will be filled first , then 3rd and then 4th  
		//tostring - that is your excel string and converting to pure java string 
		// time complexity - 2 for loops - O(n) square.  
				}			
			}
			 			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();	
		} 
		
		return data; 
	}

}
