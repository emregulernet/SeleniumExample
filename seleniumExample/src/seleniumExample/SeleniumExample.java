package seleniumExample;

 
public class SeleniumExample {

	public static String chromeDriver = "D:\\\\chromedriver.exe";
	public static int implictyWaitTime = 4;
	public static boolean maximizeWindows = true;
	public static String siteUserName = "yourMailAdress";
	public static String sitePassword = "yourPassword";

	public static void main(String[] args) throws InterruptedException {
		
		Selenium sampleSelenium = new Selenium();
		sampleSelenium.driverCreate(chromeDriver, implictyWaitTime, maximizeWindows);
		sampleSelenium.driver.get("https://github.com/");
		sampleSelenium.userLogin(siteUserName, sitePassword);
	}

}
