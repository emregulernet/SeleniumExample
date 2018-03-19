package seleniumProject;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class selenium {

	WebDriver driver;

	public void driverCreate(String chromeDriver, int implictyWaitTime, boolean maximizeWindows) {
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		driver = new ChromeDriver();
		if (maximizeWindows) {
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(implictyWaitTime, TimeUnit.SECONDS);

	}

	public void userLogin(String siteUserName, String sitePassword, String siteLoginAddress) {
		driver.manage().deleteAllCookies();
		driver.get(siteLoginAddress);
		Cookie cookies = new Cookie.Builder("qrPopup", "true").domain("secure.sahibinden.com").build();
		driver.manage().addCookie(cookies);
		driver.get(siteLoginAddress);
		driver.findElement(By.id("username")).sendKeys(siteUserName);
		driver.findElement(By.id("password")).sendKeys(sitePassword);
		driver.findElement(By.id("userLoginSubmitButton")).click();
	}

	public String[] searchCar(String siteSeachAdress) {
		driver.get(siteSeachAdress);
		List<WebElement> carList = driver.findElements(By.xpath("//tbody[@class='searchResultsRowClass']//tr"));

		String[] carIds = new String[100];
		int i = 0;

		for (WebElement option : carList) {
			try {
			String carLink = "";
			String carId = "";
			carLink = option.findElement(By.className("classifiedTitle")).getAttribute("href");
			String[] carIdSearch1 = carLink.split("/");
			String[] carIdSearch2 = carIdSearch1[4].split("-");
			carId = carIdSearch2[carIdSearch2.length - 1];
			carIds[i] = carId;
			i++;}
			catch(Exception e)
			{}
		}
		return carIds;

	}

	public void messageSend(String[] carIds, Object connectionDb) throws ClassNotFoundException, SQLException {

		mysql dbProcess = new mysql();
		
		String carLink = "https://www.sahibinden.com/ilan/vasita-#LINK#/detay";
		for (int x = 0; x < 100; x++) {
			if (carIds[x] != null) {
				int carCount = 0;
				String carId = carIds[x];
				carCount = dbProcess.carDbCount(carId, connectionDb);

				if (carCount < 1) {
					String goLink = carLink.replace("#LINK#", carId);
					driver.get(goLink);
					try {
					String carMessageLink = driver.findElement(By.id("askQuestionLink")).getAttribute("href");
					driver.get(carMessageLink);
					String carPrice = driver.findElement(By.className("cl-date-column")).getText();
					driver.findElement(By.id("messageContent"))
					.sendKeys("abicim kolay gelsin, ilaný fiyatý olarak " + carPrice
							+ " yazmýþsýnýz ama o kadar nakitim yok, banka ile görüþtüm " + priceCalc(carPrice)
							+ " kredi veriyor, olur mu abi");
					int aa = dbProcess.carDbSave(carId, connectionDb);
					System.out.println(aa);
					}catch(Exception e) {}
				}

			}
		}

		
				
	}

	public static String priceCalc(String carPrice) {
		String price = carPrice.replace(" TL", "");
		Double dPrice = Double.parseDouble(price);
		dPrice = dPrice - 5;
		DecimalFormat df = new DecimalFormat("0");
		String formate = df.format(dPrice);
		return formate;
	}

}
