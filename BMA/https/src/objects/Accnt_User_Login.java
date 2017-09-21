package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Accnt_User_Login {

	
	private static WebElement element = null;
	 
    public static WebElement txtbx_UserName(WebDriver driver){
 
         element = driver.findElement(By.id("logInBMA_UserName"));
 
         return element;
 
         }
 
     public static WebElement txtbx_Password(WebDriver driver){
 
         element = driver.findElement(By.id("logInBMA_Password"));
 
         return element;
 
         }
 
     public static WebElement btn_LogIn(WebDriver driver){
 
         element = driver.findElement(By.name("logInBMA$LoginButton"));
 
         return element;
 
         }
 
}


