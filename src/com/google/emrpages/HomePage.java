package com.google.emrpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	private By userNameLoc = By.id("authUser");
	private By passwordLoc = By.id("clearPass");
	private By selectLanguageLoc=By.xpath("//select[@name='languageChoice']");
	private By loginLoc=By.xpath("//button[@type='submit']");
	private By errorLoc=By.xpath("//div[@class='alert alert-danger login-failure m-1']");
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	
	public void enterUserName(String userName) {
		WebElement userEle = driver.findElement(userNameLoc);
		userEle.sendKeys(userName);
	}

	public void enterPassword(String password) {
		WebElement passEle = driver.findElement(passwordLoc);
		passEle.sendKeys(password);
	}

	public void selectLanguage(String language)
	{
		Select lang=new Select(driver.findElement(selectLanguageLoc));
        lang.selectByVisibleText(language);
	}
	
	public void clickOnLogin()
	{
		WebElement loginEle=driver.findElement(loginLoc);
        loginEle.click();
	}
	
	public String getErrorMessage()
	{
		 WebElement errorEle= driver.findElement(errorLoc);
	     String actualText = errorEle.getText().trim();
	       
	     return actualText;
	}
	
}
