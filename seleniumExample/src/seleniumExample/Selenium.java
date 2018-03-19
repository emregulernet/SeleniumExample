package seleniumExample;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {

	WebDriver driver;

	public void driverCreate(String chromeDriver, int implictyWaitTime, boolean maximizeWindows) {
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		driver = new ChromeDriver();
		if (maximizeWindows) {
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(implictyWaitTime, TimeUnit.SECONDS);

	}

	public void userLogin(String siteUserName, String sitePassword) throws InterruptedException {
		String loginLink = driver.findElement(By.xpath("//a[@class='text-bold text-white no-underline'][1]")).getAttribute("href");
		driver.get(loginLink);
		driver.findElement(By.id("login_field")).sendKeys(siteUserName);
		driver.findElement(By.id("password")).sendKeys(sitePassword);
		driver.findElement(By.name("commit")).click();
		Thread.sleep(3000);


		String loginStatus = driver.findElement(By.id("js-flash-container")).getText();

		if(loginStatus.length() > 0)
		{
			System.out.println("Login Error : Incorrect username or password.");
			driver.close();
		}
	}
}
