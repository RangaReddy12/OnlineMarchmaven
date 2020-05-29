package Constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class PBConstant {
public static Properties p;
public static FileInputStream fi;
public static WebDriver driver;
@BeforeMethod
public void setUp()throws Throwable
{
	p=new Properties();
	fi=new FileInputStream("C:\\MarchOnlineBtach\\Selenium_Frameworks\\PropertFile\\Environment.properties");
	p.load(fi);
	if(p.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
	System.setProperty("webdriver.chrome.driver", "C:\\MarchOnlineBtach\\Selenium_Frameworks\\Drivers\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get(p.getProperty("Url"));
	driver.manage().window().maximize();
	}
	else if(p.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "C:\\MarchOnlineBtach\\Selenium_Frameworks\\Drivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get(p.getProperty("Url"));
	}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}











