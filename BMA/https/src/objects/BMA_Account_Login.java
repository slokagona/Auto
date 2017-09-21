package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BMA_Account_Login {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	String exePath = "C:\\Selenium Jars\\chromedriver.exe";
	WebDriver driver = new ChromeDriver();
	System.setProperty("webdriver.chrome.driver",exePath);
	//WebDriver driver = new ChromeDriver();
	driver.get("http://OMZP1LHAQPA01.verizon.com:2040/App%20Web%20Pages/Generic/bmalogin.aspx");
	}
	private static WebElement element = null;

	 public static WebElement UserName(WebDriver driver){
		 
		 element = driver.findElement(By.id("logInBMA_UserName"));
		  return element;
		  
		 
	 }
public static WebElement PassWord(WebDriver driver){
		 
		 element = driver.findElement(By.id("logInBMA_Password"));
		  return element;
		 
		 

}
}


	


