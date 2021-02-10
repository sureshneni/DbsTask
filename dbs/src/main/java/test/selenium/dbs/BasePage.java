package test.selenium.dbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.log4testng.Logger;


/**
 * 
 * @author suresh
 * 
 * *
 */
public class BasePage {
	
	public WebDriver driver;
	public WebElement element;
	private BaseUtil baseUtil;
	
	public String browser = "chrome";
	public String rootDriverPath = System.getProperty("user.dir");
	WebDriverWait wait;
		
	protected static final Logger logger = Logger.getLogger(BasePage.class);
	
	/**
	 * Initialise the browser as per configurations we send.
	 * 
	 */
	public WebDriver initialiseDriver() {
	
		logger.info("browser name: "+browser);
			
		switch(browser) {
		case "chrome" :
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		}
		return driver;
	}
	
	/**
	 * This will navigate to our application
	 */
	public void navigateToApplication() {
		baseUtil = new BaseUtil();
		String appURL = baseUtil.getConfigValueFromEnvFile("appUrl");
		logger.info("App url: "+appURL);
		driver.navigate().to(appURL);
		
	}
	
	/**
	 * 
	 * @param locatorType what type of locator we are using
	 * @param locator - actual element
	 * @return the WebElement
	 */
	public WebElement getLocator(String locatorType, String locator) {
		
		switch(locatorType) {
		case "id":
			element = driver.findElement(By.id(locator));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(locator));
			break;
	   default:
		   System.out.println("no locator matched");
		
		}
		return element;
	}
	
	public void pageScrollDown(WebElement element) {
		System.out.println("page is scrolling down");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void waitElementDisplayed(WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
}
