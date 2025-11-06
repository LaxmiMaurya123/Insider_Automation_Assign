package org.Pages;

import java.util.List;

import org.GenericUtils.ReuseableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class QualityAssurancePage {

	WebDriver driver=null;

	@FindBy(xpath="//a[normalize-space()='See all QA jobs']")
	private WebElement seeAllQAJobsButton;
	
	@FindBy(xpath="//span[@title='Quality Assurance']")
	private WebElement departmentFilterDisplayed;
	
	@FindBy(xpath="//span[@title='Istanbul, Turkiye']")
	private WebElement locationFilterDisplayed;
	
	@FindBy(xpath="//span[@class='select2-selection__arrow']")
	private WebElement arrowButtonOfLocationFilter;
	
	@FindBy(xpath="//select[@name='filter-by-location']")
	private WebElement locationFlterDropdown;
	
	@FindBy(xpath="//p[contains(@class,'position-title')]")
	private WebElement jobTitle;
	
	@FindBy(xpath="//span[contains(@class,'position-department')]")
	private List<WebElement> departmentsOfFilteredJobs;

	@FindBy(xpath="//div[contains(@class,'position-location')]")
	private List<WebElement> locationsOfFilteredJobs;
	
	@FindBy(xpath="//a[normalize-space()='View Role']")
	private WebElement viewRoleButton;
	

	public QualityAssurancePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public void clickOnSeeAllQAJobsButton () {
		ReuseableMethods.scrollToElement(driver, seeAllQAJobsButton);
		ReuseableMethods.highlightElement(driver,seeAllQAJobsButton);
		seeAllQAJobsButton.click();
	}
	
	public void applyLocationFilter (String locationName) {
		ReuseableMethods.scrollToElement(driver, departmentFilterDisplayed);
		ReuseableMethods.highlightElement(driver,departmentFilterDisplayed);
		ReuseableMethods.waitForTheVisibilityOfElement(driver,departmentFilterDisplayed,"waitForVisibilityOfElement");
		arrowButtonOfLocationFilter.click();
		Select sel = new Select(locationFlterDropdown);
		sel.selectByVisibleText(locationName);
		ReuseableMethods.waitForTheVisibilityOfElement(driver,locationFilterDisplayed,"waitForVisibilityOfElement");
		arrowButtonOfLocationFilter.click();
	}
	
	public boolean verifyLocationsOfJobAfterApplyingFilter() {
		boolean locationFilterIsCorrect = false;
		for(WebElement location : locationsOfFilteredJobs) {
			ReuseableMethods.scrollToElement(driver, location);
			ReuseableMethods.highlightElement(driver,location);
			if((location.getText()).contains("Turkey") || (location.getText()).contains("Istanbul")) {
				locationFilterIsCorrect = true;
			}else {
				locationFilterIsCorrect = false;
				break;
			}
		}
		
		return locationFilterIsCorrect;
	}
	
	public boolean verifyDepartmentsOfJobAfterApplyingFilter() {
		boolean departmentFilterIsCorrect = false;
		for(WebElement department : departmentsOfFilteredJobs) {
			ReuseableMethods.scrollToElement(driver, department);
			ReuseableMethods.highlightElement(driver,department);
			if((department.getText()).contains("Quality Assurance")) {
				departmentFilterIsCorrect = true;
			}else {
				departmentFilterIsCorrect = false;
				break;
			}
		}
		
		return departmentFilterIsCorrect;
	}

	
	public void clickOnViewRoleButton () {
		Actions act = new Actions(driver);
		act.moveToElement(jobTitle).perform();
		ReuseableMethods.scrollToElement(driver, viewRoleButton);
		ReuseableMethods.highlightElement(driver,viewRoleButton);
		viewRoleButton.click();
	}
	
}
