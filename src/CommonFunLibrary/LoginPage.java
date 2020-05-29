package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class LoginPage {
WebDriver driver;
Actions ac;
public LoginPage(WebDriver driver)
{
	this.driver=driver;
}
//Store Locators for Login
@FindBy(name="txtuId")
WebElement username;
@FindBy(name="txtPword")
WebElement password;
@FindBy(name="login")
WebElement clicklogin;
//developing boolean type method
public boolean verifyLogin(String username,String password)throws Throwable
{
	ac=new Actions(driver);
	this.username.sendKeys(username);
	this.password.sendKeys(password);
	ac.moveToElement(clicklogin).click().perform();
	Thread.sleep(5000);
	String expected="adminflow";
	String actual=driver.getCurrentUrl();
	if(actual.toLowerCase().contains(expected.toLowerCase()))
	{
		Reporter.log("Valid Login Details",true);
		return true;
	}
	else
	{
		Reporter.log("InValid Login Details",true);
		return false;	
	}
}
}









