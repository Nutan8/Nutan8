package rise360;

import java.awt.AWTException;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.xml.txw2.Document;

public class UploadContent {
	static WebDriver driver;
	static HashMap<String,String> toc=new HashMap<String,String>(); 
	public String uplodContentRun(String userName,String productId,String videoPath, int Sec) throws IOException, InterruptedException, AWTException
	{
		String re="";
		try {
			
		
		System.setProperty("webdriver.gecko.driver","C:\\automationMetadata\\webdriver\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		System.out.println("call java script executor method");
		Thread.sleep(5000);
	driver.get("https://rise.articulate.com/");		
	System.out.println("wait for website");
		driver.manage().window().maximize();
		getData(productId,userName);
		
		
		Thread.sleep(4000);
		//***************Login credentils**************************//
		driver.findElement(By.name("email")).sendKeys("smital@packt.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		Thread.sleep(2000);
		System.out.println("clik on continue button");
		Thread.sleep(3000);
		driver.findElement(By.name("password")).sendKeys("Packt123!");
		driver.findElement(By.xpath("//button[@class=\"Signin_submitButton__2BJ4N\"]")).click();
		Robot robot = new Robot();
		
		Thread.sleep(7000);
		WebDriverWait waitW=new WebDriverWait(driver, 36000);
		if(Sec>1)
		{
			
			for (int k = 0; k < 7; k++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_MINUS);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_MINUS);
			}
			waitW.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(),'"+toc.get("Title Name")+"')]")));
			driver.findElement(By.xpath("//h2[contains(text(),'"+toc.get("Title Name")+"')]")).click();
			Thread.sleep(3000);
		}
		
		else
		{
		//******************* creating course ****************************************//
		
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[text()='New Course']")).click();
		driver.findElement(By.xpath("//*[text()='Blank course']")).click();
	// Course tiltes //
		System.out.println("Title name :"+toc.get("Title Name"));
		Thread.sleep(15000);
		//je.executeScript("document.body.style.zoom = '30%';")
		for (int k = 0; k < 7; k++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_MINUS);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_MINUS);
			 
		 }
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@placeholder='Course Title']")).sendKeys(toc.get("Title Name").toString());
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[@class='cookieBanner__dismiss']")).click();
		}
	int c=0;
	int l=0;
	WebDriverWait wait = new WebDriverWait(driver,360000);

	//********************************deleted automaticaly generated course close *********************************//
	int count=0;
	Thread.sleep(2000);
	int i=Sec;
	for(;i<=100;i++)
	{ 
		if(toc.get("Section "+i)==null)
		{
			break;
		}
		else
		{
			try {
				if(Sec>1)driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.END);
				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).click();
				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(toc.get("Section "+i).toString());
				//driver.findElement(By.xpath("//input[@placeholder='Add a lesson title...']")).sendKeys(Keys.SHIFT);
				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(Keys.SHIFT,Keys.ENTER);
				
			} catch (Exception e) {
				// TODO: handle exception
			Thread.sleep(10000);
				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).click();
				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(toc.get("Section "+i).toString());
				//driver.findElement(By.xpath("//input[@placeholder='Add a lesson title...']")).sendKeys(Keys.SHIFT);
				driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(Keys.SHIFT,Keys.ENTER);
			}
			
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
				DataFormatter formatter = new DataFormatter();
				try {
					WebElement subInSec=driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input"));
					//je.executeScript("arguments[0].scrollIntoView();"+"window.scrollBy(0,500)",subInSec);
					je.executeScript("window.scrollTo(0, document.body.scrollHeight)");	
					Thread.sleep(2000);
					driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.END);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).click();
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(toc.get(i+"."+j).toString());
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.END);
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//div[text()='Add content'])[last()]")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//div[text()='Lesson'])[last()]")).click();
					
				} catch (Exception e) {
					Thread.sleep(10000);
					WebElement subInSec=driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input"));
					//je.executeScript("arguments[0].scrollIntoView();"+"window.scrollBy(0,500)",subInSec);
					je.executeScript("window.scrollTo(0, document.body.scrollHeight)");	
					Thread.sleep(2000);
					driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.END);
					Thread.sleep(2000);
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).click();
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(toc.get(i+"."+j).toString());
					driver.findElement(By.xpath("//div[@class='course-outline-lesson course-outline-lesson--new']/div[1]//input")).sendKeys(Keys.ENTER);
					Thread.sleep(2000);
					driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.END);
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//div[text()='Add content'])[last()]")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//div[text()='Lesson'])[last()]")).click();
				}
			StringBuilder data=new StringBuilder();
			System.out.println("section button close");
			if(count==100||count==150||count==200||count==250||count==300||count==350||count==400||count==500)
				Thread.sleep(2400000);
			int listele=0;
			for(int k=1;k<=100;k++)
			{	
				count++;
				
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
						if(k==2)data.append("\n");
						
						data.append("\n"+"•	"+toc.get("Clip Section "+i+"."+j+":"+k));
						listele=k;
					}
				}
				
			}
			//****************** this for adding clip description ******************************
			Thread.sleep(2000);
			try {
				driver.findElement(By.xpath("//span[text()='Text']")).click();
				
			} catch (Exception e) {
				// TODO: handle exception
				Thread.sleep(6000);
				driver.findElement(By.xpath("//span[text()='Text']")).click();
			}
			
			Thread.sleep(3000);
			try {
				driver.findElement(By.xpath("//div[text()='Paragraph with heading']")).click();
				
			} catch (Exception e) {
				// TODO: handle exception
				Thread.sleep(6000);
				driver.findElement(By.xpath("//div[text()='Paragraph with heading']")).click();
			}
			
			Thread.sleep(1500);	
			try {
				driver.findElement(By.xpath("//div[text()='Paragraph']")).click();
				
			} catch (Exception e) {
				// TODO: handle exception
				Thread.sleep(6000);
				driver.findElement(By.xpath("//div[text()='Paragraph']")).click();
			}
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='fr-element fr-view']")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='fr-element fr-view']")).sendKeys(data.toString().trim());
			//System.out.println(data);
			
			Thread.sleep(2000);
		
			System.out.println("description in sent");
			
			//**************Replacing paragraph to list **************************//
		/*	if(listele!=0)
			{
			for (int list=2;i<=listele+1;list++)
			{
				String xpathset="//div[@class='fr-element fr-view']/p["+list+"]";
				driver.findElement(By.xpath(xpathset)).sendKeys(Keys.CONTROL+"a");
				driver.findElement(By.xpath("//i[@class='fa fa-list-ul']")).click();				
			}
			}
			*/
			
			Thread.sleep(2000);
			//**************************** for uploading videos using robot method ***************************
			driver.findElement(By.xpath("//span[text()='Video']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[text()='Edit'])[last()]")).click();
			Thread.sleep(2000);
		     
			driver.findElement(By.xpath("(//button[text()='Edit'])[last()]")).click();
			Thread.sleep(2000);
			
			//driver.findElement(By.xpath("//span[text()='Replace video']")).click();
			Thread.sleep(2000);
			String vPath =videoPath+"\\video"+i+"_"+j+".mp4";
			
			WebElement videoFile1=driver.findElement(By.xpath("//input[@type='file']"));
			WebElement videoFile=driver.findElement(By.xpath("//input[@type='file']"));
			//je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-50)",videoFile);
			je.executeScript("arguments[0].readonly = false;arguments[0].disabled = false;",videoFile);
			videoFile1.sendKeys(vPath);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='blocks-sidebar__close']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='authoring-header__menu-close']")).click();
			l++;
			}
		
		Thread.sleep(3000);
		
		}
		
		c+=2;
		
		re= "video upload sucessfully";
		
	}
		
		} catch (Exception e) {
			re= e.getMessage();
		}
		return re;
		
		
		
	}
	
	
	
	
	 protected static void getData(String productId,String userName) throws IOException
	 {
		 File file=new File("C:\\automationMetadata\\excelMetadata\\"+productId+".xlsx");
		 	//File file=new File("C:\\Users\\"+userName+"\\Packt\\Premachandra Saka - AutomationMetadata\\"+productId+".xlsx");
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
