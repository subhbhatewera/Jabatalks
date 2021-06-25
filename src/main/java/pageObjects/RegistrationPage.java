package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	public WebDriver driver ;
	
	@FindBy(css = "div[placeholder='Choose Language']")
	WebElement languageDropDown;
	
	@FindBy(css = "div[class='ng-binding ng-scope']")
	List<WebElement> languageList ;
	
	@FindBy(css = "#name")
	WebElement fullNameField ;
	
	@FindBy(css = "#orgName")
	WebElement organizationNameField ;
	
	@FindBy(css = "#singUpEmail")
	WebElement emailField ;
	
	@FindBy(css = "label[class='ui-checkbox'] span")
	WebElement termsAndConditionsCheckBox ;
	
	@FindBy(css = "button[type='submit']")
	WebElement getStartedBtn ;
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<String> getLanguages() {
		List<String> actualList  = new ArrayList<String>();
		languageDropDown.click();
		for(WebElement itr : languageList) {
			actualList.add(itr.getText());
		}
		return actualList;
	}
	
	public void enterFullName(String fullName) {
		fullNameField.sendKeys(fullName);
	}
	
	public void enterOrganizationName(String organizationName) {
		organizationNameField.sendKeys(organizationName);
	}
	
	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void clickOnTermsCheckBox() {
		termsAndConditionsCheckBox.click();
	}
	
	public void clickOnGetStartedBtn() {
		getStartedBtn.click();
	}
	
	

}
