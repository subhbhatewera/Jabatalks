package utills;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Yopmail {

	WebDriver driver ;
	WebDriverWait myWait ;
	JavascriptExecutor js ;

	@FindBy(id = "login")
	WebElement emailField ;
	
	@FindBy(id = "refresh")
	WebElement refreshBtn ;

	@FindBy(xpath = "//button[contains(@title,'Check Inbox')]")
	WebElement checkInboxButton ;

	@FindBy(xpath = "(//span[contains(text(),'JabaTalks')])[1]")
	WebElement mailLink ;

	@FindBy(xpath = "//div//p[contains(text(),'Thanks for signing up')]")
	WebElement emailBody ;

	public Yopmail (WebDriver driver) {
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}

	public void customWait(WebElement element) {
		myWait = new WebDriverWait(driver, 20);
		myWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void enterEmail(String email) {
		customWait(emailField);
		emailField.sendKeys(email);
	}

	public void clickOnCheckInboxButton() {
		customWait(checkInboxButton);
		checkInboxButton.click();
	}

	public void openEmail() {
		driver.switchTo().frame("ifinbox");		
		customWait(mailLink);
		mailLink.click();
		driver.switchTo().defaultContent();
	}

	public boolean checkEmailContent() {
		boolean status = false;
		driver.switchTo().frame("ifmail");
		customWait(emailBody);
		String text = emailBody.getText();
		if(text.contains("Thanks for signing up for JabaTalks.")) {
			status = true ;
		}
		return status;
	}
	
	public void gotoYopmail() {
		js = (JavascriptExecutor)driver;
		js.executeScript("window.open('https://yopmail.com');");
	}

	public boolean checkWelcomeEmail(String email) {		
		enterEmail(email);
		clickOnCheckInboxButton();
		openEmail();
		boolean status = checkEmailContent();
		return status;
	}

}
