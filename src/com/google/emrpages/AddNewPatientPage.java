package com.google.emrpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddNewPatientPage {
	
	private WebDriver driver;
	private By firstNameLoc=By.id("form_fname");
	private By lastNameLoc=By.id("form_lname");
	private By dobLoc=By.id("form_DOB");
	private By genderLoc=By.xpath("//select[@id='form_sex']");
	private By createLoc=By.id("create");
	private By confirmLoc=By.xpath("//input[@value='Confirm Create New Patient']");
	private String addPatientFrameName ="pat";
	private String modalFrameName ="modalframe";
	
	
	public AddNewPatientPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void FillNewPatientDetails(String firstName,String lastName,String Dob,String gender)
	{
		driver.switchTo().frame(addPatientFrameName);
		
		WebElement firstNameEle= driver.findElement(firstNameLoc);
		firstNameEle.sendKeys(firstName);		

		WebElement lNameEle= driver.findElement(lastNameLoc);
		lNameEle.sendKeys(lastName);

		WebElement dobEle= driver.findElement(dobLoc);
		dobEle.sendKeys(Dob);
		
		WebElement selectGenderEle= driver.findElement(genderLoc);
		Select selectGender=new Select(selectGenderEle);
		selectGender.selectByVisibleText(gender);
		
		driver.findElement(createLoc).click();
		
		driver.switchTo().defaultContent();
		
		
		driver.switchTo().frame(modalFrameName);
		
		driver.findElement(confirmLoc).click();
		
		driver.switchTo().defaultContent();
	}

}
