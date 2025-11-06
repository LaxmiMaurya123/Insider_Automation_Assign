package org.Pages;


import org.GenericUtils.ReuseableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {


	WebDriver driver=null;

	@FindBy(xpath="//a[normalize-space()='Accept All']")
	private WebElement acceptAllbuttonForCookies;

	@FindBy(xpath="//a[normalize-space()='Company']")
	private WebElement companyDropdown;

	@FindBy(xpath="//a[normalize-space()='Careers']")
	private WebElement careers;


	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void clickOnAcceptAllButtonDisplayedOnCookies() {
		ReuseableMethods.scrollToElement(driver, acceptAllbuttonForCookies);
		ReuseableMethods.highlightElement(driver,acceptAllbuttonForCookies);
		acceptAllbuttonForCookies.click();	
	}

	public void howerTheMoveOverCompanyDropdown() {
		Actions act = new Actions(driver);
		ReuseableMethods.highlightElement(driver,companyDropdown);
		act.moveToElement(companyDropdown).perform();
	}

	public void clickOnCareerOption() {
		ReuseableMethods.highlightElement(driver,careers);
		careers.click();	
	}


}
