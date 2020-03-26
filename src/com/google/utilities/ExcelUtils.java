package com.google.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	
	
	public static String[][] getSheetintoDataProvider(String fileDetails,String sheetName) throws IOException
	{
		FileInputStream file = new FileInputStream(fileDetails);
		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sheet = book.getSheet(sheetName);
		int rowCount =sheet.getPhysicalNumberOfRows();
		
		DataFormatter format=new DataFormatter();
		
		XSSFRow rowCheck= sheet.getRow(0);
		
		int cellCount=rowCheck.getPhysicalNumberOfCells();
		
		//2 dim - based on rowcount and cellcount
		String[][] main=new String[rowCount-1][cellCount];
				
		//row
		for(int i = 1; i<rowCount; i++)
		{
			XSSFRow row= sheet.getRow(i);
			//cell
			for(int j=0;j<cellCount;j++)
			{
				XSSFCell cell= row.getCell(j);
				String cellValue = format.formatCellValue(cell);
				main[i-1][j]=cellValue;
			}
		}
		
		
		book.close();
		file.close();

		return main;
		
	}

}
