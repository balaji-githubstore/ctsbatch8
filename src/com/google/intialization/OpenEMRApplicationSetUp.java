package com.google.intialization;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.utilities.PropUtils;

public class OpenEMRApplicationSetUp {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@BeforeMethod
	@Parameters({"browser"})
	public void intialization(@Optional("ch")  String browserName)  throws IOException
	{
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
		System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
		
		
		
		//String browserName=PropUtils.getValueUsingKey("TestUtils/data.properties", "browsername");
		
		
		launchBrowser(browserName);
		
		wait =new WebDriverWait(driver,50);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        String browseUrl = PropUtils.getValueUsingKey("TestUtils/data.properties", "url");
		
		System.out.println(browseUrl);
        
        driver.get(browseUrl);

	}
	
	
	
	public void launchBrowser(String browserName)
	{
		if(browserName.equalsIgnoreCase("ie") || browserName.equalsIgnoreCase("internet explorer"))
		{
			driver=new InternetExplorerDriver();
		}
		//ie
		else if(browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
	}
	
	@AfterMethod
	public void quitBrowser()
	{
		driver.quit();
	}
}
