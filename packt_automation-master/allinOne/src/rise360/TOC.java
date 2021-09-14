package rise360;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import net.bytebuddy.asm.Advice.Enter;

public class TOC {

	static WebDriver driver;
	static HashMap<String,String> toc=new HashMap<String,String>();  
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
	/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\premachandras\\Documents\\chromedriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("https://www.udemy.com/");
		options.addArguments("--disable-extensions");
		System.out.println("chrome drivers disibale extension");
		String userName="premachandras";
		options.addArguments("user-data-dir=C:\\Users\\"+userName+"\\AppData\\Local\\Google\\Chrome\\User Data");	
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);*/
		
		System.setProperty("webdriver.gecko.driver","C:\\Users\\premachandras\\Documents\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		System.out.println("call java script executor method");
		Thread.sleep(5000);
	driver.get("https://rise.articulate.com/");		
	System.out.println("wait for website");
		driver.manage().window().maximize();
		getData();
		
			Thread.sleep(4000);
			//***************Login credentils**************************//
			driver.findElement(By.id("idp-discovery-username")).sendKeys("smital@packt.com");
			Thread.sleep(3000);
			driver.findElement(By.id("idp-discovery-submit")).click();
			System.out.println("clik on continue button");
			Thread.sleep(3000);
			driver.findElement(By.id("okta-signin-password")).sendKeys("Packt123!");
			driver.findElement(By.id("okta-signin-submit")).click();
			//******************* creating course ****************************************//
			
			Thread.sleep(20000);
			driver.findElement(By.xpath("//button[text()='New Course']")).click();
			driver.findElement(By.xpath("//*[text()='Blank course']")).click();
		// Course tiltes //
			System.out.println("Title name :"+toc.get("TitleName"));
			
			Robot robot = new Robot();
			 for (int k = 0; k < 4; k++) {
				 robot.keyPress(KeyEvent.VK_CONTROL);
				 robot.keyPress(KeyEvent.VK_SUBTRACT);
				 robot.keyRelease(KeyEvent.VK_SUBTRACT);
				 robot.keyRelease(KeyEvent.VK_CONTROL);
			 }
			Thread.sleep(10000);
			driver.findElement(By.xpath("//textarea[@placeholder='Course Title']")).sendKeys(toc.get("Title Name").toString());
		
		
		int c=0;
		int l=0;

		//********************************deleted automaticaly generated course close *********************************//
		Thread.sleep(20000);
		for(int i=1;i<=100;i++)
		{ 
			if(toc.get("Section "+i)==null)
			{
				break;
			}
			else
			{

				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).click();
				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(toc.get("Section "+i).toString()+"section "+i);
				//driver.findElement(By.xpath("//input[@placeholder='Add a lesson title...']")).sendKeys(Keys.SHIFT);
				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(Keys.SHIFT,Keys.ENTER);
			}
			Thread.sleep(1000);
			
			for(int j=1;j<=100;j++)
			{
				System.out.println("data enters into section");
				Thread.sleep(1000);
				if(toc.get(i+"."+j)==null)
				{
					//driver.findElement(By.xpath("//button [@data-purpose='cancel-section-form' and @type='button']")).click();
				break;
				}
				else
				{
//				WebElement subInSec=driver.findElement(By.xpath("//input[@data-purpose='lecture-title' and @id='title' and @type='text']"));
//				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,50)",subInSec);
//				subInSec.sendKeys(toc.get(i+"."+j));
					
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).click();
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(toc.get(i+"."+j).toString());
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//div[text()='Add content'])[last()]")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//div[text()='Lesson'])[last()]")).click();
				StringBuilder data=new StringBuilder();
				System.out.println("section button close");
				for(int k=1;k<=100;k++)
				{
					
					Thread.sleep(1000);
					if(toc.get("Clip Section "+i+"."+j+":"+k)==null)
					{
						break;
					}
					else
					{
						if(k==1)
						{
						data.append(toc.get("Clip Section "+i+"."+j+":"+k));
						}
						else
						{
							data.append("\n   •  "+toc.get("Clip Section "+i+"."+j+":"+k));
						}
					}
					
				}
				//****************** this for adding clip description ******************************
				Thread.sleep(2000);
				driver.findElement(By.xpath("//span[text()='Text']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[text()='Paragraph with heading']")).click();
				Thread.sleep(1000);	
				driver.findElement(By.xpath("//div[text()='Paragraph']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[@class='fr-element fr-view']")).clear();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//div[@class='fr-element fr-view']")).sendKeys(data);
				//System.out.println(data);
				
				Thread.sleep(2000);
			
				System.out.println("description in sent");
				
				Thread.sleep(1000);
				//**************************** for uploading videos using robot method ***************************
				driver.findElement(By.xpath("//span[text()='Video']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//button[text()='Edit'])[last()]")).click();
				Thread.sleep(1000);
			     
				driver.findElement(By.xpath("(//button[text()='Edit'])[last()]")).click();
				Thread.sleep(1000);
				
				//driver.findElement(By.xpath("//span[text()='Replace video']")).click();
				Thread.sleep(1000);
				
				Thread.sleep(1000);
				Thread.sleep(1000);
				String vPath = "\\\\192.168.4.61\\Photon\\Video\\2018\\12023_Hands-on PowerShell for Active Directory\\19] Final Package\\9781789616385\\Package\\videos\\video"+i+"_"+j+".mp4";
				
				WebElement videoFile1=driver.findElement(By.xpath("//input[@type='file']"));
				WebElement videoFile=driver.findElement(By.xpath("//input[@type='file']"));
				//je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-50)",videoFile);
				je.executeScript("arguments[0].readonly = false;arguments[0].disabled = false;",videoFile);
				videoFile1.sendKeys(vPath);
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[@class='blocks-sidebar__close']")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[@class='authoring-header__menu-close']")).click();
				l++;
				}
			
			Thread.sleep(3000);
			
			}
			
			c+=2;
		}
		
		// for method call for verifying all the videos are uploaded or not 
		
		// for transering to other section in url
		

}
	
	 protected static String filtered(String k)
	 {
		 System.out.println(k);
			String k1=(k.substring(7));
			String array1[]= k1.split("/");
			System.out.println(array1[3]);
			return array1[3];
	 }
	 
	 
	 protected static int uploadStatus()
	 {
		//String g=driver.findElement(By.xpath("//div[@class='progress-sm progress']")).getText();
		
		java.util.List<WebElement> links =driver.findElements(By.xpath("//div[@role='progressbar']"));

		return links.size();
		 
	 }
	 
	 protected static void getData() throws IOException
	 {
		 File file=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\Udemy Automation\\V12023.xlsx");
			FileInputStream fls=new FileInputStream(file);
			XSSFWorkbook wb=new XSSFWorkbook(fls);
			XSSFSheet sheet1= wb.getSheetAt(0);
			int rownum=sheet1.getLastRowNum();
			
			DataFormatter formatter = new DataFormatter();
			for(int i=0;i<=rownum;i++)
			{
				// for put coloum 1 value to hash map
				String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));
				// for puting coloum 2 value to hash map
				String kk1=formatter.formatCellValue(sheet1.getRow(i).getCell(1));				
				String a=kk.trim();
				String b=kk1.trim();
				// maping the excel value to hash map
				toc.put(a,b);
		}
			
		
	 }
}
	
