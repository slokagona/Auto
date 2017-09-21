package testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import objects.Accnt_User_Login;

public class Login_Test_Case {
	
	   public static void main(String[] args) {
	 
		   String exePath = "C:\\Selenium Jars\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver",exePath);
			WebDriver driver = new ChromeDriver();
			driver.get("http://OMZP1LHAQPA01.verizon.com:2040/App%20Web%20Pages/Generic/bmalogin.aspx");
			driver.manage().window().maximize();
	 
	     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
// Use page Object library now
	 
	     Accnt_User_Login.txtbx_UserName(driver).sendKeys("accnt");
	 
	     Accnt_User_Login.txtbx_Password(driver).sendKeys("1234");
	 
	     Accnt_User_Login.btn_LogIn(driver).click();
	 
	     System.out.println(" Login Successfull");	     
	 
	     driver.quit();
	   }

}
