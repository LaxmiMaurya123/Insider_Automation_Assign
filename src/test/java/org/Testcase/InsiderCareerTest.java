package org.Testcase;

import java.io.IOException;

import org.GenericUtils.ReuseableMethods;
import org.Pages.CareerPage;
import org.Pages.HomePage;
import org.Pages.JobLeverPage;
import org.Pages.QualityAssurancePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InsiderCareerTest {
	WebDriver driver=null;
	HomePage homepage;
	CareerPage careerpage;
	QualityAssurancePage qualityAssurancePage;
	JobLeverPage jobLeverPage;

//	Visit https://useinsider.com/ 
	@BeforeClass
	public void setup() throws IOException {
		String url = ReuseableMethods.getPropertyValue("url");
		driver = ReuseableMethods.launchDriver("Chrome", url);
	}
	
	
// check Insider home page is opened or not
	@Test(priority = 1, description = "Verify that Insider homepage opens successfully")
	public void verifyHomePageIsOpened() {
		String actualTitle = driver.getTitle().trim();
		String expectedHomePageTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";
		Assert.assertEquals(actualTitle, expectedHomePageTitle);
	}
	
//	Select the “Company” menu in the navigation bar, select “Careers” and check Career page
	@Test(priority = 2, description = "Verify navigation to Careers page is successful")
	public void verifyCareerPageIsOpened() {
		homepage=new HomePage(driver);
		homepage.clickOnAcceptAllButtonDisplayedOnCookies();
		homepage.howerTheMoveOverCompanyDropdown();
		homepage.clickOnCareerOption();
		
		String actualUrl = driver.getCurrentUrl().trim();
		String expectedCareerUrl = "https://useinsider.com/careers/";
		Assert.assertTrue(actualUrl.contains(expectedCareerUrl));
	}
	
//	and check Career page, its Locations, Teams, and Life at Insider blocks are open or not
	@Test(priority = 3, description = "Verify various sections on Career page")
	public void verifyCareerPageSections() throws InterruptedException {
		careerpage=new CareerPage(driver);
		careerpage.allTeamsAreDisplayed();
		Thread.sleep(2000);
		careerpage.locationHeaderIsDisplayed();
		Thread.sleep(2000);
		careerpage.lifeAtInsiderHeaderIsDisplayed();
		Thread.sleep(2000);
		String paraForLifeAtInsider = careerpage.lifeAtInsiderParagraph();
		System.out.println(paraForLifeAtInsider);

	}
	
//	Go to https://useinsider.com/careers/quality-assurance/
	@Test(priority = 4, description = "Verify that Quality Assurance page is opens successfully")
	public void verifyQualityAssurancePageIsOpened() throws InterruptedException {
		careerpage.clickOnSeeAllTeamsButton();
		Thread.sleep(2000);
		careerpage.clickOn("Quality Assurance");
		Thread.sleep(2000);
		
		String actualUrl = driver.getCurrentUrl().trim();
		String expectedUrl = "https://useinsider.com/careers/quality-assurance/";
		Assert.assertTrue(actualUrl.contains(expectedUrl));
	}
	
//	click “See all QA jobs”, filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check thepresence of the jobs list
//	And Check that all jobs’ Position contains “Quality Assurance”, Department contains “Quality Assurance”, and Location contains “Istanbul, Turkey”
	@Test(priority = 5, description = "Verify that all listed jobs match the selected filter criteria")
	public void verifyResultsAfterApplyingLocationAndDepartmentFilter() throws InterruptedException {
		qualityAssurancePage=new QualityAssurancePage(driver);
		qualityAssurancePage.clickOnSeeAllQAJobsButton();
		qualityAssurancePage.applyLocationFilter("Istanbul, Turkiye");
		Thread.sleep(1000);
		Assert.assertTrue(qualityAssurancePage.verifyDepartmentsOfJobAfterApplyingFilter(),"Department Filter ");
		Assert.assertTrue(qualityAssurancePage.verifyLocationsOfJobAfterApplyingFilter(),"Location Filter ");
	}
	
	
//	Click the “View Role” button and check that this action redirects us to the Lever Application form page
	@Test(priority = 6, description = "Verify 'View Role' redirects to Lever Application form")
	public void verifyViewRoleRedirection() throws InterruptedException {
		jobLeverPage=new JobLeverPage(driver);
		qualityAssurancePage.clickOnViewRoleButton();
		Assert.assertTrue((jobLeverPage.redirectToJobLeverPage()).contains("https://jobs.lever.co/"));
		
	}
	
	



}
