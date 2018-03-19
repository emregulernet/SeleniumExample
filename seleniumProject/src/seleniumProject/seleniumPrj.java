package seleniumProject;

public class seleniumPrj {

	public static String dbAddress = "localhost";
	public static String dbPort = "3306";
	public static String dbUserName = "root";
	public static String dbPassword = "12345678";
	public static String dbName = "mesaage";
	public static String message = "abicim kolay gelsin, ilaný fiyatý olarak #carPrice# yazmýþsýnýz ama o kadar nakitim yok, banka ile görüþtüm #discountPrice# kredi veriyor, olur mu abi";
	public static int discountPrice = 5;
	public static String chromeDriver = "D:\\\\chromedriver.exe";
	public static int implictyWaitTime = 1;
	public static boolean maximizeWindows = true;
	public static String siteUserName = "eguler35@gmail.com";
	public static String sitePassword = "Emre1231";
	public static String siteLoginAddress = "https://secure.sahibinden.com/giris/";
	public static String siteSeachAdress = "https://www.sahibinden.com/otomobil/izmir/dizel?a4_max=180000&a5_min=2010&price_max=35000";

	public static void main(String[] args) {

		mysql dbProcess = new mysql();
		try {
			Object connectionDb = dbProcess.dbConnection(dbAddress, dbPort, dbUserName, dbPassword, dbName);
			selenium seleniumProject = new selenium();
			seleniumProject.driverCreate(chromeDriver, implictyWaitTime, maximizeWindows);
			try {
				seleniumProject.userLogin(siteUserName, sitePassword, siteLoginAddress);
			} catch (Exception e) {
				System.out.println("Err: System login error.");
				System.exit(0);
			}
			try {
				String[] carIds = new String[100];
				carIds = seleniumProject.searchCar(siteSeachAdress);
				seleniumProject.messageSend(carIds, connectionDb);
			} catch (Exception e) {
				System.out.println("Err: System search error.");
			}
		} catch (Exception e) {
			System.out.println("Err : Databese connection error.");
		}

	}

}
