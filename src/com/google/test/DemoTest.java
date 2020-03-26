package com.google.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.utilities.ExcelUtils;

public class DemoTest {
	
//	@Test
//	public void method1() throws IOException
//	{
//		//type, location, read or write
//		
//		FileInputStream file=new FileInputStream("TestUtils/data.properties");
//		
//		Properties prop=new Properties();
//		
//		prop.load(file);
//		
//		String browserUrl = prop.getProperty("url");
//		
//		System.out.println(browserUrl);
//		
//		
//		
//	}
	
	
//	@DataProvider
//	public String[][] invalidData()
//	{
//		String[][] main=new String[3][3];
//		//i --> number of testcase
//		//j--> number of parameters
//		
//		
//		main[0][0]="bala";
//		main[0][1]="bala123";
//		main[0][2]="invalid";
//		
//		main[1][0]="john";
//		main[1][1]="john123";
//		main[1][2]="invlaid john";
//		
//		main[2][0]="king";
//		main[2][1]="king123";
//		main[2][2]="invalid king";
//		
//		return main;
//		
//	}
	@DataProvider
	public String[][] invalidData() throws IOException
	{
		String[][] main = ExcelUtils.getSheetintoDataProvider("TestUtils/EMRData.xlsx", "invalidCredentialTest");

		return main;
	
	}
	
	//bala , bala123 , invalid
	//john , john123 , invalid
	//king, king123, invalid king
	
	@Test(dataProvider = "invalidData")
	public void invalid(String userName,String password,String expected)
	{
		System.out.println(userName+password+expected);
	}
	
	
	
	
	
}






