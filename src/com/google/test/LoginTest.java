package com.google.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
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

import com.google.emrpages.HomePage;
import com.google.emrpages.OpenEMRDashboardPage;
import com.google.intialization.OpenEMRApplicationSetUp;
import com.google.utilities.ExcelUtils;
import com.google.utilities.PropUtils;

public class LoginTest extends OpenEMRApplicationSetUp {
	
	
	@Test(priority = 2)
	public void invalidCredentialTest()
	{
		//HomePage
		
		HomePage home=new HomePage(driver);
		
		home.enterUserName( "admin222");
		home.enterPassword("pass123");
		home.selectLanguage("English (Indian)");
		home.clickOnLogin();

        String expectedText="Invalid username or password";
        //write code to get error message
        String actualText= home.getErrorMessage();
        
        Assert.assertEquals(actualText, expectedText, "Assertion on invalid credential");
        
	}
	
	@DataProvider
	public String[][] invalidCredentialsData() throws IOException
	{
		 String[][] main=ExcelUtils.getSheetintoDataProvider("TestUtils/EMRData.xlsx", "invalidCredentialTest");
		 return main;
	}
	
	@Test(priority = 3,dataProvider = "invalidCredentialsData")
	public void invalidCredentialsTest(String userName,String password,String language,String expectedText)
	{
		//HomePage
		
		HomePage home=new HomePage(driver);
		
		home.enterUserName(userName);
		home.enterPassword(password);
		home.selectLanguage(language);
		home.clickOnLogin();

        //String expectedText="Invalid username or password";
        //write code to get error message
        String actualText= home.getErrorMessage();
        
        Assert.assertEquals(actualText, expectedText, "Assertion on invalid credential");
        
	}
	
	@Test(priority = 1)
	public void validCredentialTest() {
		
		//HomePage
		HomePage home=new HomePage(driver);
		
		home.enterUserName( "admin");
		home.enterPassword("pass");
		home.selectLanguage("English (Indian)");
		home.clickOnLogin();
		
		String expectedTitle = "OpenEMR";
        //Dashboard
		
		OpenEMRDashboardPage openEMR=new OpenEMRDashboardPage(driver, wait);
		
		
		openEMR.waitForBillyToPresent();
        String actualTitle= openEMR.getCurrentTitle();
        System.out.println(actualTitle);
       
        //Assertion
        Assert.assertEquals(actualTitle, expectedTitle,"Assertion on validCredential");
        
        openEMR.mouseHoverOnBilly();
        openEMR.clickOnLogOut();
      
	}

}
