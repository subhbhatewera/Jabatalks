package TestCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.BaseClass;
import pageObjects.RegistrationPage;
import utills.Yopmail;

public class RegistrationTc extends BaseClass{

	String baseURL = prop.getProperty("url");
	String fullName = prop.getProperty("fullName");
	String organizationName = prop.getProperty("organizationName");
	String email = prop.getProperty("email");
	RegistrationPage registration ;
	Yopmail yopmail;

	@BeforeClass
	public void setUp() {
		launchBrowser();
		driver.get(baseURL);
		registration = new RegistrationPage(driver);
		yopmail = new Yopmail(driver);
		
	}
	
	@Test(priority = 1)
	public void validateLanguageDropDown() {
		List<String> expectedList = Arrays.asList("English", "Dutch");
		List<String> actualList = registration.getLanguages();
		System.out.println("Actual list "+actualList);
		System.out.println("Expected List " +expectedList);
		Assert.assertEquals(actualList, expectedList);
	}

	@Test(priority = 2)
	public void registration() {
		registration.enterFullName(fullName);
		registration.enterOrganizationName(organizationName);
		registration.enterEmail(email);
		registration.clickOnTermsCheckBox();
		registration.clickOnGetStartedBtn();
		String mainWindow = driver.getWindowHandle();
		yopmail.gotoYopmail();
		switchtoChildWindow();
		boolean status = yopmail.checkWelcomeEmail(email);
		driver.switchTo().window(mainWindow);	
		Assert.assertTrue(status);
	}

}
