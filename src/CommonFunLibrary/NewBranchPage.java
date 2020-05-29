package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class NewBranchPage {
WebDriver driver;
public NewBranchPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//input[@id='BtnNewBR']")
WebElement clicknewbranch;
@FindBy(xpath="//input[@id='txtbName']")
WebElement bname;
@FindBy(xpath="//input[@id='txtAdd1']")
WebElement address1;
@FindBy(xpath="//input[@id='Txtadd2']")
WebElement address2;
@FindBy(xpath="//input[@id='txtadd3']")
WebElement address3;
@FindBy(xpath="//input[@id='txtArea']")
WebElement area;
@FindBy(xpath="//input[@id='txtZip']")
WebElement zcode;
@FindBy(xpath="//select[@id='lst_counrtyU']")
WebElement selectcountry;
@FindBy(xpath="//select[@id='lst_stateI']")
WebElement selectstate;
@FindBy(xpath="//select[@id='lst_cityI']")
WebElement selectcity;
@FindBy(xpath="//input[@id='btn_insert']")
WebElement clicksubmit;
public boolean verifyNewBranch(String bname,String address1,String address2,String address3,
	String area,String zcode,int country,int state,int city)throws Throwable
{
this.clicknewbranch.click();
Thread.sleep(5000);
this.bname.sendKeys(bname);
this.address1.sendKeys(address1);
this.address2.sendKeys(address2);
this.address3.sendKeys(address3);
this.area.sendKeys(area);
this.zcode.sendKeys(zcode);
new Select(selectcountry).selectByIndex(country);
Thread.sleep(3000);
new Select(selectstate).selectByIndex(state);
Thread.sleep(3000);
new Select(selectcity).selectByIndex(city);
Thread.sleep(3000);
this.clicksubmit.click();
Thread.sleep(5000);
//get alert message
String alertmessage=driver.switchTo().alert().getText();
System.out.println(alertmessage);
Thread.sleep(5000);
driver.switchTo().alert().accept();
Thread.sleep(5000);
String expected="New Branch with";
if(alertmessage.toLowerCase().contains(expected.toLowerCase()))
{
	Reporter.log("Branch Created Successfully",true);
	return true;
}
else
{
	Reporter.log("Branch Not Created Successfully",true);
	return false;
}
}
}













