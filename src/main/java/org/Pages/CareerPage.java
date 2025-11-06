package org.Pages;

import java.util.ArrayList;
import java.util.List;

import org.GenericUtils.ReuseableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.util.Assert;

public class CareerPage {

	WebDriver driver=null;

	@FindBy(xpath="//h3[normalize-space()='Find your calling']")
	private WebElement findYourCallingHeader;

	@FindBy(xpath="//a[normalize-space()='See all teams']")
	private WebElement seeAllTeamsButton;

	@FindBy(xpath = "//div[contains(@class,'job-title')]//h3")
	private List<WebElement> allJobTitles;

	@FindBy(xpath="//h3[normalize-space()='Our Locations']")
	private WebElement locationsHeader;

	@FindBy(xpath = "//div[@class='location-info']/p")
	private List<WebElement> allLocationNames;

	@FindBy(xpath="//h2[normalize-space()='Life at Insider']")
	private WebElement lifeAtInsiderHeader;

	@FindBy(xpath="//h2[normalize-space()='Life at Insider']/ancestor::div[@data-id=\"87842ec\"]//p")
	private WebElement lifeAtInsiderParagraph;

	@FindBy(xpath="//div[contains(@class,'job-title')]//h3[normalize-space()='Quality Assurance']")
	private WebElement qualityAssuranceButton;


	public CareerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	

	public boolean allTeamsAreDisplayed () {
		ReuseableMethods.scrollToElement(driver, findYourCallingHeader);
		ReuseableMethods.highlightElement(driver,findYourCallingHeader);
		return findYourCallingHeader.isDisplayed();
	}

	public boolean locationHeaderIsDisplayed () {
		ReuseableMethods.scrollToElement(driver, locationsHeader);
		ReuseableMethods.highlightElement(driver,locationsHeader);
		return locationsHeader.isDisplayed();
	}

	public boolean lifeAtInsiderHeaderIsDisplayed() {
		ReuseableMethods.scrollToElement(driver, lifeAtInsiderHeader);
		ReuseableMethods.highlightElement(driver,lifeAtInsiderHeader);
		return lifeAtInsiderHeader.isDisplayed();
	}

	public String lifeAtInsiderParagraph() {
		ReuseableMethods.scrollToElement(driver, lifeAtInsiderParagraph);
		ReuseableMethods.highlightElement(driver,lifeAtInsiderParagraph);
		return lifeAtInsiderParagraph.getText();
	}

	public void clickOnSeeAllTeamsButton() {
		ReuseableMethods.scrollToElement(driver, seeAllTeamsButton);
		ReuseableMethods.highlightElement(driver,seeAllTeamsButton);
		ReuseableMethods.clickByJS(driver, seeAllTeamsButton);	
	}

	public void clickOn(String jobTitleName) {
		for(WebElement jobTitle : allJobTitles) {
			ReuseableMethods.scrollToElement(driver, jobTitle);
			ReuseableMethods.highlightElement(driver,jobTitle);
			if((jobTitle.getText()).contains(jobTitleName)) {
				jobTitle.click();
				break;
			}
		}
	}

}
