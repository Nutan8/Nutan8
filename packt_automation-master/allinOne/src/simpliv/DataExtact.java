package simpliv;

import java.awt.AWTException;
import java.awt.KeyboardFocusManager;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JScrollBar;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rotatable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataExtact {
	
	WebDriver driver;
	JavascriptExecutor je = (JavascriptExecutor) driver;
	public static HashMap TitleDesc(String meta, String userName) throws IOException
	{
		File file=new File("C:\\automationMetadata\\excelMetadata\\"+meta+".xlsx");
		FileInputStream fls=new FileInputStream(file);
		 int se=1;

		XSSFWorkbook wb=new XSSFWorkbook(fls);

		XSSFSheet sheet1= wb.getSheetAt(0);

		int rownum=sheet1.getLastRowNum();

		DataFormatter formatter = new DataFormatter();
		HashMap<String,String> hm=new HashMap<String,String>();
		
		for(int i=0;i<=rownum;i++)

		{		

			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));// for put coloum 1 value to hash map
		
			String kk1=formatter.formatCellValue(sheet1.getRow(i).getCell(1));		// for puting coloum 2 value to hash map		

			String a=kk.trim();

			String b=kk1.trim();

			hm.put(a,b); // maping the excel value to hash map

	}
		
		return hm;
	}
	
	public void uploadSection(int titleNumber, String dataPath,String videoPath,String url,String userName) throws InterruptedException, IOException, Exception
	{
		
		   
		HashMap titleToc=TitleDesc(dataPath,userName);
		if(titleNumber==1)
		{
			driverWebChrome(userName);
			driver.get(url);
		}
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver,360000);
			
		 //  Thread.sleep(4000);
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-block add-session']")));
			Thread.sleep(4000);
				driver.findElement(By.xpath("//button[@class='btn btn-block add-session']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@placeholder='Session title']")).sendKeys(titleToc.get("Title Name").toString());
				Thread.sleep(3000);
				driver.findElement(By.xpath("(//div[@class='session']/form/div[2]/a[contains(text(),'Save')])[last()]")).click();
		//Create new section...(i.e each and every title name of the VLP)
		for(int i=1;i<=100;i++)
		{ 
			
			if(titleToc.get("Section "+i)==null)
			{
				break;
			}
			else
			{
			
			}

			for(int j=1;j<=100;j++)
			{
				System.out.println("data enters into section");
				Thread.sleep(1000);
				if(titleToc.get(i+"."+j)==null)
				{
				break;
				}
				else
				{
					
					// wait for lecture button
					if(i==1 && j<10)
					driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.HOME);

						
						Thread.sleep(3000);
						WebElement elemntsLecBtn=driver.findElement(By.xpath("(//div[@class='session']/div/div/div[4]/button[1])[last()]"));
					
					System.out.println(titleToc.get("Title Name"));
					
					if(i==1 && j<10)
						driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.HOME);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='btn add-session add-lecture'])[last()-1]")));
					((JavascriptExecutor) driver).executeScript(
			                "arguments[0].scrollIntoView();", driver.findElement(By.xpath("//button[@class='btn btn-block add-session']")));
					if(i==1 && j<10)
						driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.HOME);
					Thread.sleep(1500);
					driver.findElement(By.xpath("(//button[@class='btn add-session add-lecture'])[last()-1]")).click();
					
					
				
					
					Thread.sleep(1000);
					driver.findElement(By.xpath("//input[@placeholder='Lecture title']")).sendKeys(titleToc.get(i+"."+j).toString());
					
					
					//System.out.println(titleToc.get(i+"."+j) +"--"+i+"."+j);//Inner section name

				StringBuilder data=new StringBuilder();//for inner section description
				for(int k=1;k<=100;k++)
				{
					
					if(titleToc.get("Clip Section "+i+"."+j+":"+k)==null)
					{
						break;
					}
					else
					{
						if(k==1)
						{
						data.append(titleToc.get("Clip Section "+i+"."+j+":"+k));
						}
						else
						{
							data.append("\n   •  "+titleToc.get("Clip Section "+i+"."+j+":"+k));
						}
					}
					
				}
				
				driver.findElement(By.xpath("//div[@data-placeholder='Brief description']")).sendKeys(data);
				Thread.sleep(5000);
				
			 
				
				try {
					WebElement uploadvid=driver.findElement(By.xpath("(//form[@class='dropzone dz-clickable']/div[2]/span/i)[last()-1]"));
					
					WebElement uploadLecVideo=driver.findElement(By.xpath("(//form[@class='dropzone dz-clickable']/div[2]/span/i)[last()-1]"));
					((JavascriptExecutor) driver).executeScript(
			                "arguments[0].scrollIntoView();", uploadLecVideo);
					uploadLecVideo.click();
		
				 //   driver.findElement(By.xpath("(//form[@class='dropzone dz-clickable']/div[2]/span/i)[last()-1]")).click();
				    
				    // stringSelection = new StringSelection(videoPath+"//video"+i+"_"+j+".mp4");
			        //Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			        //clipboard.setContents(stringSelection, null);
			        Thread.sleep(500);
			        System.out.println(videoPath);
			        System.out.println(videoPath+"//video"+i+"_"+j+".mp4");
			        StringSelection ss = new StringSelection(videoPath+"\\video"+i+"_"+j+".mp4");
			        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			        Thread.sleep(700);
			        
			        Robot robot = new Robot();
			       
			        robot.keyPress(KeyEvent.VK_CONTROL);
			        robot.keyPress(KeyEvent.VK_V);
			        robot.keyRelease(KeyEvent.VK_V);
			        robot.keyRelease(KeyEvent.VK_CONTROL);
			        robot.delay(50);
			        robot.keyPress(KeyEvent.VK_ENTER);			     
			        robot.keyRelease(KeyEvent.VK_ENTER);
			    //Thread.sleep(10000);
				    

					
					System.out.println(data);		
					
				
					
					System.out.println("while loop is calling");
					
					int cout=0;
					
					
				//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='dz-preview-template']/div/div[2]")));
					wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='dz-preview-template']/div/div[2]"), "Success"));
					//wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.xpath("//*[@id='dz-preview-template']/div/div[2, value))
				 System.out.println("waiting to click on save button");
				 WebElement lecSaveBtn=driver.findElement(By.xpath("//div[@class='buttons-group buttons-left m-t-40']/button[1]"));
				 ((JavascriptExecutor) driver).executeScript(
			                "arguments[0].scrollIntoView();", lecSaveBtn);
					//driver.findElement(By.xpath("//div[@class='buttons-group buttons-left m-t-40']/button[1]")).click();
				 System.out.println("clicked on save button");
					
				 lecSaveBtn.click();
					
					Thread.sleep(3000);
					try {
						
						String kkww=driver.findElement(By.xpath("//div[@class='text-error']")).getText();
						
						if(kkww.length()>0)
						{
						driver.findElement(By.xpath("//input[@placeholder='Lecture title']")).sendKeys(titleToc.get("Title Name")+titleToc.get(i+"."+j).toString());
						lecSaveBtn.click();
						}
						
						try {
							
							String againtitle=driver.findElement(By.xpath("//div[@class='text-error']")).getText();
							
							if(againtitle.length()>0)
							{
							driver.findElement(By.xpath("//input[@placeholder='Lecture title']")).sendKeys(titleToc.get("Section "+i)+titleToc.get(i+"."+j).toString());
							lecSaveBtn.click();
							}
						} catch (Exception e) {
							continue;
						}
						
					} catch (Exception e) {
						continue;
						
					}
					
					
					
						
					
						
					
					
				}
				catch (NoSuchElementException d) {
					// TODO: handle exception
					j=j-1;
					driver.navigate().refresh();
					Thread.sleep(3000);
				}

				
			
				
			}
				
			
		}
		
	}
	}
	
	//Web driver class for details and re-upload
	public void driverWebChrome(String userName) throws InterruptedException, AWTException
	{
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\premachandras\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("https://www.udemy.com/");
		options.addArguments("--disable-extensions");
		System.out.println("chrome drivers disibale extension");
		String userName="premachandras";
		options.addArguments("user-data-dir=C:\\Users\\"+userName+"\\AppData\\Local\\Google\\Chrome\\User Data");	
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);*/
		 Robot robot1 = new Robot();
		 
		String location = "C:\\automationMetadata\\webdriver\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", location);
		driver = new FirefoxDriver();
		
		System.out.println("call java script executor method");
		driver.get("https://www.simplivlearning.com/");
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[2]/header/div[2]/a[2]")).click();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id=\"auth-form\"]/div[1]/input")).sendKeys("products@packt.com");
		driver.findElement(By.xpath("//*[@id=\"auth-form\"]/div[2]/input")).sendKeys("packt@123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"auth-form\"]/div[4]/button")).click();
		Thread.sleep(3000);
		//driver.get("https://www.simpliv.com/courses/prem-testing-title-demo/edit/?step=2");
		
		for(int t=0;t<6;t++){
			 robot1.keyPress(KeyEvent.VK_CONTROL);
			 robot1.keyPress(KeyEvent.VK_MINUS);
			 robot1.keyRelease(KeyEvent.VK_CONTROL);
			 robot1.keyRelease(KeyEvent.VK_MINUS);
				Thread.sleep(1000);
				}
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.body.style.zoom = '0.3'");
	}
	
	
}
