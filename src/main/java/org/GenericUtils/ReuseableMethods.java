package org.GenericUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReuseableMethods {

	public static WebDriver driver=null;

	public static WebDriver launchDriver (String browserName,String url) {
		if(browserName.equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver=new ChromeDriver(options);
		}else if(browserName.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);

		return driver;
	}

	public static String getPropertyValue(String key) throws IOException {
		FileInputStream fis = new FileInputStream(".//PropertiesFile/credentials.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}

	public static void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);

		try {
			Thread.sleep(200); // Just to make the highlight visible for a short time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		js.executeScript("arguments[0].style.border=''", element); // remove highlight
	}

	public static void scrollToElement(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
	}

	public static void clickByJS(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void waitForTheVisibilityOfElement(WebDriver driver,WebElement element,String actionType) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		switch(actionType) {
		case "waitForVisibilityOfElement" : wait.until(ExpectedConditions.visibilityOf(element));
		break;
		case "waitForElementToBeClickable": wait.until(ExpectedConditions.elementToBeClickable(element));
		break;
		
		default : System.out.println("Incorrect Action type");
		}	
	}

	public static String switchingToAnotherWindow(WebDriver driver,WebElement element) {
		String parentId = driver.getWindowHandle(); 
		Set<String>  allWinIds= driver.getWindowHandles(); 
		String actualURL=null;

		for(String id : allWinIds) 
		{
			if(id!=parentId) {
				driver.switchTo().window(id); 
				actualURL = driver.getCurrentUrl();
				System.out.println(actualURL);
			}else {
				continue;
			}
			
		}
		
		return actualURL;
	}
}
