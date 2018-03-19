# Basic Java Selenium Example 

Dear developer,

It's a example code block for  Login to github.com  site with selenium. 

## Download Files :

- [Selenium Client V3.11.0 R2018-03-11](https://goo.gl/Us5DnZ)

- [Chrome WebDriver](https://sites.google.com/a/chromium.org/chromedriver/)

Define variables :

```
    public static String chromeDriver = "D:\\\\chromedriver.exe";
    public static int implictyWaitTime = 4;
    public static boolean maximizeWindows = true;
    public static String siteUserName = "githubUserNameOrMailAdress";
    public static String sitePassword = "githubPassword";
```

Create WebDriver :

```
    System.setProperty("webdriver.chrome.driver", chromeDriver);
    WebDriver driver = new ChromeDriver();
    if (maximizeWindows) {
      driver.manage().window().maximize();
    }
    driver.manage().timeouts().implicitlyWait(implictyWaitTime, TimeUnit.SECONDS);
```

WebDriver to github.com :

```
    driver.get("https://github.com/");
```

Search "Sign In" link :

```
    String loginLink = driver.findElement(By.xpath("//a[@class='text-bold text-white no-underline'][1]")).getAttribute("href");
		
```

Go to "Sign In" link and filling user field  :

```
    driver.get(loginLink);
    driver.findElement(By.id("login_field")).sendKeys(siteUserName);
    driver.findElement(By.id("password")).sendKeys(sitePassword);
    driver.findElement(By.name("commit")).click();
```

Login status control :

```
    String loginStatus = driver.findElement(By.id("js-flash-container")).getText();

    if(loginStatus.length() > 0)
    {
      System.out.println("Login Error : Incorrect username or password.");
      driver.close();
    }
```

### Support or Contact
For your questions  you can email eguler35@gmail.com or www.emreguler.org visit web site.

