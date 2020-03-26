package com.google.emrpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenEMRDashboardPage {
	private By billyLoc = By.xpath("//*[text()='Billy']");
	private By logOutLoc = By.xpath("//li[text()='Logout']");
	private By patClientLoc=By.xpath("//div[text()='Patient/Client']");
	private By newSearchLoc=By.xpath("//div[text()='New/Search']");
	
	

	private WebDriver driver;
	private WebDriverWait wait;

	public OpenEMRDashboardPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public void waitForBillyToPresent() {
		wait.until(ExpectedConditions.presenceOfElementLocated(billyLoc));
	}

	public String getCurrentTitle() {
		String actualTitle = driver.getTitle();
		return actualTitle;
	}

	public void mouseHoverOnBilly() {
		WebElement patientclient = driver.findElement(billyLoc);
		Actions cursor = new Actions(driver);
		cursor.moveToElement(patientclient).build().perform();
	}

	public void clickOnLogOut() {
		driver.findElement(logOutLoc).click();
	}

	public void ClickOnNewOrSearchPatientMenu() {
		WebElement patEle = driver.findElement(patClientLoc);
		Actions actions = new Actions(driver);
		actions.moveToElement(patEle).build().perform();
		WebElement newEle = driver.findElement(newSearchLoc);
		newEle.click();
	}

}
