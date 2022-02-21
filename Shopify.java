package myMiniProject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Shopify  {
	
static  WebDriver driver ;
String URL ;

/*
public WebDriver setDrivers(){
	//Open chrome browser 
	System.setProperty("webdriver.chrome.driver", "C://Users//mayur//workspace//First_Selenium_Project//driver//chromedriver.exe"); 
		  driver = new ChromeDriver(); 
	
   ///open FireFox browser
	System.setProperty("webdriver.gecko.driver","C://Users//mayur//workspace//First_Selenium_Project//driver//geckodriver.exe");
	driver = new FirefoxDriver();
	
		  driver.manage().window().maximize(); 
		  return driver; 
}*/

@Parameters("browser")
@BeforeMethod
public WebDriver setDrivers(String browser){
	
	if (browser.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver","C://Users//mayur//workspace//First_Selenium_Project//driver//chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
		}
	else {
		System.setProperty("webdriver.gecko.driver","C://Users//mayur//workspace//First_Selenium_Project//driver//geckodriver.exe");
		driver = new FirefoxDriver();
		return driver;
		
		}
	}



	
	public void openURL() {
		
	String URL	= "https://www.shopify.in//";
	driver.get(URL);	
	}


	public void locate(){
	//Locate and Click on “Start free trial”
	driver.findElement(By.id("MainNavSignupButton")).click();
	
	//Enter an Email address “myemail@abc.com”
	driver.findElement(By.id("0_signup_email")).sendKeys("myemail@abc.com");
	
	//Enter the password “password@123”
	driver.findElement(By.id("0_signup_password")).sendKeys("password@123");
	
	//Click on “Create your store”
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	try {
		Thread.sleep(5000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	//take screenshot---->Verify and capture the error message displayed for not entering your store name
	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(screenshotFile, new File ("C://Users//mayur//workspace//Mini_Project//src//myMiniProject//screenshot/screen.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	//Locate “Free tools” option under 3“Learn” menu and click it
	driver.findElement(By.id("CloseIcon")).click();
	driver.findElement(By.xpath("//button[text()='Learn']")).click();
	
	//Get the names of the available tools and display on the console
	driver.findElement(By.xpath("//a[text()='Free tools']")).click();
	List<WebElement> tools = driver.findElements(By.tagName("h3"));
	for(int i=0 ;i<25;i++){
	   System.out.println(tools.get(i).getText());
	}
}

public void close(){
	//close browser
	driver.close();
}


	
	public static void main(String[] args) {
		
		
		
		Shopify shopify = new Shopify();
		shopify.setDrivers("browser");
		
		shopify.openURL();
		shopify.locate();
	    shopify.close();
	}

}
