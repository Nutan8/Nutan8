package sharePoint;

import java.security.Provider.Service;

import org.omg.CORBA.ServiceDetail;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SharePointFileTesting {
	static WebDriver driver;
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\premachandras\\Documents\\chromedriver\\chromedriver.exe");
		driver=new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("https://www.udemy.com/");
		options.addArguments("--disable-extensions");
		System.out.println("chrome drivers disibale extension");
		String userName="premachandras";
		options.addArguments("user-data-dir=C:\\Users\\premachandras\\AppData\\Local\\Google\\Chrome\\User Data");	
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.get("https://packtservices-my.sharepoint.com/personal/premachandras_packt_com/_layouts/15/onedrive.aspx?skipSignal=true&id=%2Fpersonal%2Fpremachandras%5Fpackt%5Fcom%2FDocuments%2FTestingSaharePointProject");
		driver.findElement(By.xpath("//button[@data-automationid='FieldRenderer-name' and contains(text(),'V00140.xlsx')]")).sendKeys(Keys.SHIFT,Keys.F10);
	
	
	}
		}