package org.Pages;


import org.GenericUtils.ReuseableMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JobLeverPage {


	WebDriver driver=null;

	@FindBy(xpath="//a[normalize-space()='Apply for this job']")
	private WebElement applyForThisJobButton;
	

	public JobLeverPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}

	public String redirectToJobLeverPage() {
		return ReuseableMethods.switchingToAnotherWindow(driver, applyForThisJobButton);	
	}

	

}
