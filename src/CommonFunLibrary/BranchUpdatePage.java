package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class BranchUpdatePage {
WebDriver driver;
public BranchUpdatePage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//tr//tr[2]//td[7]//a[1]//img[1]")
WebElement clickedit;
@FindBy(name="txtbnameU")
WebElement bname;
@FindBy(name="txtadd1u")
WebElement address1;
@FindBy(name="btnupdate")
WebElement clickupdate;
public boolean verifyBranchUpdate(String bname,String address1)throws Throwable
{
	this.clickedit.click();
	Thread.sleep(5000);
	this.bname.clear();
	this.bname.sendKeys(bname);
	this.address1.clear();
	this.address1.sendKeys(address1);
	this.clickupdate.click();
	Thread.sleep(5000);
	String alertmessage1=driver.switchTo().alert().getText();
	Reporter.log(alertmessage1,true);
	Thread.sleep(5000);
	driver.switchTo().alert().accept();
	Thread.sleep(5000);
	String expected="Branch updated";
	if(alertmessage1.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Branch Updated Success",true);
		return true;
	}
	else
	{
		Reporter.log("Branch Not Updated Success",true);
		return false;
	}
		
}
}
