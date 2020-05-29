package DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;

public class DataDriven {
	String inputpath="C:\\MarchOnlineBtach\\Selenium_Frameworks\\TestInput\\TestData.xlsx";
	String outputpath="C:\\MarchOnlineBtach\\Selenium_Frameworks\\TestOutput\\Reults.xlsx";
WebDriver driver;
ExtentReports report;
ExtentTest test;
@BeforeTest
public void setup()
{
	report=new ExtentReports("./ExtentReports/Reports.html");
System.setProperty("webdriver.chrome.driver", "C:\\MarchOnlineBtach\\Selenium_Frameworks\\Drivers\\chromedriver.exe");
driver=new ChromeDriver();
}
@Test
public void verifyLogin()throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		//row count
	int rc=xl.rowCount("Login");
	int cc=xl.colCount("Login");
	Reporter.log("No of rows are::"+rc+"   "+"no of columns are::"+cc,true);
	for(int i=1;i<=rc;i++)
	{
	test=report.startTest("Login Test");
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
	String username=xl.getCellData("Login", i, 0);
	String password=xl.getCellData("Login", i, 1);
	driver.findElement(By.name("txtUsername")).sendKeys(username);
	driver.findElement(By.name("txtPassword")).sendKeys(password);
	driver.findElement(By.name("Submit")).click();
	if(driver.getCurrentUrl().contains("dash"))
	{
		Reporter.log("Login SUccess",true);
		test.log(LogStatus.PASS, "Login SUccess");
		xl.setCellData("Login", i, 2, "Login SUccess", outputpath);
		xl.setCellData("Login", i, 3, "Pass", outputpath);
	}
	else
	{
		String message=driver.findElement(By.id("spanMessage")).getText();
		Reporter.log(message,true);
		test.log(LogStatus.FAIL, message);
		xl.setCellData("Login", i, 2, message, outputpath);
		xl.setCellData("Login", i, 3, "Fail", outputpath);
	}
	report.endTest(test);
	report.flush();
	}
}
@AfterTest
public void teardown()
{
	driver.close();
}
}













