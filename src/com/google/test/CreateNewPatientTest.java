package com.google.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.emrpages.AddNewPatientPage;
import com.google.emrpages.HomePage;
import com.google.emrpages.OpenEMRDashboardPage;
import com.google.intialization.OpenEMRApplicationSetUp;
import com.google.utilities.ExcelUtils;
import com.google.utilities.PropUtils;

public class CreateNewPatientTest extends OpenEMRApplicationSetUp {
	
	@DataProvider
	public String[][] AddPatientData() throws IOException
	{
		 String[][] main=ExcelUtils.getSheetintoDataProvider("TestUtils/EMRData.xlsx", "AddPatientTest");
		 return main;
	}
	
	
	
	@Test(dataProvider = "AddPatientData")
	public void AddPatientTest(String firstName,String lastName,String dob, String gender,String expectedText)
	{
		//homepage
		HomePage home=new HomePage(driver);
		
		home.enterUserName("admin");
		home.enterPassword("pass");
		home.selectLanguage("English (Indian)");
		home.clickOnLogin();
		
		
		//dashboard
		OpenEMRDashboardPage dashboardPage=new OpenEMRDashboardPage(driver,wait);
		dashboardPage.ClickOnNewOrSearchPatientMenu();
		
		
		//AddPatientPage
		AddNewPatientPage addNewPatientPage=new AddNewPatientPage(driver);
		
		addNewPatientPage.FillNewPatientDetails(firstName, lastName, dob, gender);
		
		
		
		//MedicalRecordDashboardPage
		
		//explicit wait
		//wait until alert present
		
		wait.until(ExpectedConditions.alertIsPresent());
		
		
		Alert alertBox= driver.switchTo().alert();
		 
		String alertText= alertBox.getText();
		System.out.println(alertText);
		
		 alertBox.accept();
		 
		 
		 if(driver.findElements(By.className("closeDlgIframe")).size() > 0)
		 {
			 driver.findElement(By.className("closeDlgIframe")).click();
		 }
		 
		 
		 
		driver.switchTo().frame("pat");
		 
		WebElement medicalEle= driver.findElement(By.xpath("//h2[contains(text(),'Medical Record')]"));
		
		String actualText = medicalEle.getText();
		
		System.out.println(actualText);
		
		driver.switchTo().defaultContent();
		
		
//		if(actualText.contains("Bala Dina"))
//		{
//			System.out.println("pass");
//		}
//		else
//		{
//			System.out.println("fail");
//		}

		Assert.assertTrue(actualText.toLowerCase().contains(expectedText.toLowerCase()), "Assertion on checking add new patient record");
		
		
	}
	

}
