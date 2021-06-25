package pageObjects;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

	public WebDriver driver ;
	public String path = System.getProperty("user.dir");
	public Properties prop ;

	public BaseClass() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(path+"/config.properties");
			prop.load(ip);
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", path+"/drivers/chrome/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", path+"/drivers/firefox/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
	}

	public void closeBrowser() {
		driver.quit();
	}
	
	public void switchtoChildWindow() {
		try {
			String mainWindow = driver.getWindowHandle();		
			Set<String> set = driver.getWindowHandles();
			Iterator<String> itr = set.iterator();
			while(itr.hasNext()) {
				String childWindow = itr.next();
				if(!mainWindow.equals(childWindow)) {
					driver.switchTo().window(childWindow);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
