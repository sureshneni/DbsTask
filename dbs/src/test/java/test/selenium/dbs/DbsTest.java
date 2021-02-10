package test.selenium.dbs;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;


public class DbsTest {
	
	WebElement element;
	DbsPage dbsPage;
	
	public String card1 = "DBS Altitude Visa Signature Card";
	public String card2 = "DBS Black Visa Card";
	
	@Test (priority = 0)
	public void navigateToCards() {
		dbsPage = new DbsPage();
		dbsPage.driverCreation();
		dbsPage.navigateToDbsApp();
		dbsPage.clickOnCardTab();
		
	}
	
	@Test(priority = 1)
	public void navigateToCreditCards() {
		dbsPage.clickOnCreditCards();
	}
	
	@Test(priority = 2)
	public void selectCards() {
		dbsPage.selectCard(card1);
		dbsPage.selectCard(card2);
		dbsPage.clickCompareCards();
	}
	
	@Test (dependsOnMethods = "selectCards")
	public void compareTwoCreditCards() {
		
		dbsPage.validateCardComparisions();
	}
	
	@AfterSuite
	public void tearDown() {
		dbsPage.closeDriver();
	}

}
