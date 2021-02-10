package test.selenium.dbs;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import bsh.This;


/**
 * 
 * @author suresh
 * 
 * We can do code optimization and can also create wrapper methods which will be called across multiple pages
 * 
 *
 */

public class DbsPage extends BasePage {
	
	private static String cards_tab = "//div[@class='navbar-links-left hidden-xs ']/descendant::li/a[text()='Cards']";
	private static String creditcards_tab = "//a[@href='/personal/cards/cards-comparator.page']";
	
	//DBS Altitude Visa Signature Card
	private static String compare_btn = "cardCompareBtn";
	
    private static String compareCards_1 = "//div[@id='card0']/descendant::div[@class='section-seperator']/div[@class='sub-header']";
    private static String compareCards_2 = "//div[@id='card1']/descendant::div[@class='section-seperator']/div[@class='sub-header']";
    
    
    WebDriver driver;
           
    public void driverCreation() {
    	driver = initialiseDriver();
    }
    
    public void navigateToDbsApp() {
    	navigateToApplication();
    }
    
    public void clickOnCardTab() {
    	waitElementDisplayed(driver.findElement(By.xpath(cards_tab)));
    	driver.findElement(By.xpath(cards_tab)).click();
    }
    
    public void clickOnCreditCards() {
    	Reporter.log("click on credit cards tab");
    	waitElementDisplayed(driver.findElement(By.xpath(creditcards_tab)));
    	driver.findElement(By.xpath(creditcards_tab)).click();
    	 
    }
    
    /**
     *  This method we can use as generic method to use any number of cards since card name is parameterised.
     *  
     * @param cardName - we can pass this from global as well.
     */
    // Since here it is taking more time to load, time being i have used Thread.sleep
    public void selectCard(String cardName) { 
    	System.out.println("Select card to compare");
    	 String card_compare_link = "//input[@data-title='"+cardName+"']/parent::div/descendant::label/div";
    	 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 WebElement element = driver.findElement(By.xpath(card_compare_link));
//    	 pageScrollDown(element);
    	 element.click();
    }
    
    public void clickCompareCards() {
    	Reporter.log("click on compare cards link to compare selected cards");
    	waitElementDisplayed(driver.findElement(By.id(compare_btn)));
    	driver.findElement(By.id(compare_btn)).click();
    }
    
    /**
     * This is going to validate all the fields displayed over here.
     * Assertion will fail if it finds any difference between two card features.
     */
    public void validateCardComparisions() {
    	
    	
    	List<WebElement> card1_features = driver.findElements(By.xpath(compareCards_1));
    	List<WebElement> card2_features = driver.findElements(By.xpath(compareCards_2));
    	
    	for(int i = 1 ; i < card1_features.size(); i++) {
    		String card1FeatureName = card1_features.get(i).getText();
    		System.out.println(card1FeatureName);
    		System.out.println("********");
    		String card2FeaturName = card2_features.get(i).getText();
    		System.out.println(card2FeaturName);
    		Assert.assertEquals(card1FeatureName, card2FeaturName);
    	}
    	
    }
    
    public void closeDriver() {
    	driver.close();
    }
}
