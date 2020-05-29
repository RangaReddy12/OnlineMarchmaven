package DriverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.BranchDetailsPage;
import CommonFunLibrary.BranchUpdatePage;
import CommonFunLibrary.LoginPage;
import CommonFunLibrary.LogoutPage;
import CommonFunLibrary.NewBranchPage;
import Constant.PBConstant;
import Utilities.ExcelFileUtil;

public class DriverScript extends PBConstant {
String inputpath="C:\\MarchOnlineBtach\\Selenium_Frameworks\\TestInput\\Controller.xlsx";
String outputpath="C:\\MarchOnlineBtach\\Selenium_Frameworks\\TestOutput\\KeywordResults.xlsx";
String TCSheet="TestCases";
String TSSheet="TestSteps";
ExtentReports report;
ExtentTest test;
@Test
public void startTest()throws Throwable
{
	report=new ExtentReports("./ExtentReports/keyword.html");
	boolean res=false;
	String tcres=null;
	//call page classes into driver script
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	BranchDetailsPage branches=PageFactory.initElements(driver, BranchDetailsPage.class);
	NewBranchPage branchcreation=PageFactory.initElements(driver, NewBranchPage.class);
	BranchUpdatePage updatebranch=PageFactory.initElements(driver, BranchUpdatePage.class);
	LogoutPage logout=PageFactory.initElements(driver, LogoutPage.class);
	//access excel methods
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no of rows in TCsheet
	int TCCount=xl.rowCount(TCSheet);
	//count no of rows in TSSheet
	int TSCount=xl.rowCount(TSSheet);
	Reporter.log("No of row are::"+TCCount+"    "+"No of rows ::"+TSCount,true);
for(int i=1;i<=TCCount;i++)
{
	//start test case here
			test=report.startTest("Keyword Framework");
	//read execute column from TCSheet
	String execute=xl.getCellData(TCSheet, i, 2);
	if(execute.equalsIgnoreCase("Y"))
	{
		//read tcid column from TSsheet	
		String tcid=xl.getCellData(TCSheet, i, 0);
		for(int j=1;j<=TSCount;j++)
		{
			//read description column from TSSheet
			String Description=xl.getCellData(TSSheet, j, 3);
			//read tsid column from TSsheet
			String tsid=xl.getCellData(TSSheet, j, 0);
			if(tcid.equalsIgnoreCase(tsid))
			{
				//read keyword column from TSsheet
				String keyword=xl.getCellData(TSSheet, j, 3);
			//call methods
				if(keyword.equalsIgnoreCase("AdminLogin"))
				{
				res=login.verifyLogin("Admin", "Admin");	
				test.log(LogStatus.INFO, Description);
				}
				else if(keyword.equalsIgnoreCase("NewBranchCreation"))
				{
					branches.navigateBranches();
					res=branchcreation.verifyNewBranch("ameerpet12", "Hyderabad", "kadiri", "kukatpalli", "srnagar", "123435", 1, 1, 1);
					test.log(LogStatus.INFO, Description);
				}
				else if(keyword.equalsIgnoreCase("UpdateBranch"))
				{
					branches.navigateBranches();
					res=updatebranch.verifyBranchUpdate("Kadiri", "madanapalli");
					test.log(LogStatus.INFO, Description);
				}
				else if(keyword.equalsIgnoreCase("AdminLogout"))
				{
					res=logout.verifyLogout();
					test.log(LogStatus.INFO, Description);
				}
				String tsres=null;
				if(res)
				{
					//if it true
					tsres="Pass";
				xl.setCellData(TSSheet, j, 4, tsres, outputpath);
				test.log(LogStatus.PASS, Description);
				}
				else
				{
					//if it fail
					tsres="Fail";
xl.setCellData(TSSheet, j, 4, tsres, outputpath);
test.log(LogStatus.FAIL, Description);
				}
			if(!tsres.equalsIgnoreCase("Fail"))
			{
				tcres=tsres;
			}
			}
			report.endTest(test);
			report.flush();
		}
		xl.setCellData(TCSheet, i, 3, tcres, outputpath);
	}
	else
	{
		//write as Not Executed into results column which are flag into N
		xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
	}
}
}
}














