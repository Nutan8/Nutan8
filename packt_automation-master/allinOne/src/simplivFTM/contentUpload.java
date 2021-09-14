package simplivFTM;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class contentUpload {

	WebDriver driver;
	JavascriptExecutor je = (JavascriptExecutor) driver;
	public static HashMap TitleDesc(String meta,String user) throws IOException
	{
		HashMap<String,String> hm=new HashMap<String,String>();
		//File file=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\Udemy Automation\\"+meta+".xlsx");
		File file=new File("C:\\automationMetadata\\excelMetadata\\"+meta+".xlsx");
		
		Boolean pathCheck=file.exists();
		if(pathCheck==false)
		{

			System.out.println("Foler is not available on you local onedrive \n Please sysc the folder using following steps: \n 1.Goto online onddrive"
					+ "\n 2. If the folder is shared by anyone then goto shraed with me \n 3.open the folder \n 4. above you get sync click on it the folder is sysc to you ondrive");
			
		}
		else
		{
		FileInputStream fls=new FileInputStream(file);
		
		 int se=1;
		 
		XSSFWorkbook wb=new XSSFWorkbook(fls);

		XSSFSheet sheet1= wb.getSheetAt(0);

		int rownum=sheet1.getLastRowNum();

		DataFormatter formatter = new DataFormatter();
		
		
		for(int i=0;i<=rownum;i++)

		{		

			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));// for put coloum 1 value to hash map
		
			String kk1=formatter.formatCellValue(sheet1.getRow(i).getCell(1));		// for puting coloum 2 value to hash map		

			String a=kk.trim();

			String b=kk1.trim();

			hm.put(a,b); // maping the excel value to hash map

	}
		}
		
		return hm;
	}
	
	public void uploadSection(String dataPath,String videoPath,String url,String userName,int secId) throws InterruptedException, IOException, Exception
	{
		
		   
		HashMap titleToc=TitleDesc(dataPath,userName);
		
			driverWebChrome(userName,url);
			System.out.println("navigate to cource url");
			Thread.sleep(3000);
			//System.out.println(url);
			


			driver.get(url);
		
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver,3600000);
			
		 //  Thread.sleep(4000);
			
			
		//Create new section...(i.e each and every title name of the VLP)
			int i=secId;
		for(;i<=100;i++)
		{ 
			
			if(titleToc.get("Section "+i)==null)
			{
				break;
			}
			else
			{
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.END);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-block add-session']")));
				Thread.sleep(2000);
					driver.findElement(By.xpath("//button[@class='btn btn-block add-session']")).click();
					Thread.sleep(2000);
					driver.findElement(By.xpath("//input[@placeholder='Session title']")).sendKeys(titleToc.get("Section "+i).toString());
					Thread.sleep(2000);
					driver.findElement(By.xpath("(//div[@class='session']/form/div[2]/a[contains(text(),'Save')])[last()]")).click();
			
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

						
						Thread.sleep(2000);
						WebElement elemntsLecBtn=driver.findElement(By.xpath("(//div[@class='session']/div/div/div[4]/button[1])[last()]"));
					
					System.out.println(titleToc.get("Title Name"));
					
					if(i==1 && j<10)
						driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.HOME);
					else
						driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.END);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='btn add-session add-lecture'])[last()-1]")));
					driver.findElement(By.xpath("(//button[@class='btn add-session add-lecture'])[last()-1]")).click();
					
					/*if (j==1)
					{
						Thread.sleep(500);
						driver.findElement(By.xpath("//input[@placeholder='Lecture title']")).sendKeys(titleToc.get(i+"."+j).toString()+" - "+titleToc.get("Section "+i).toString());
					}
					else
					{*/
						Thread.sleep(500);
						driver.findElement(By.xpath("//input[@placeholder='Lecture title']")).sendKeys(titleToc.get(i+"."+j).toString());
					//}
					
					
					
					
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
				Thread.sleep(2000);
				
			 
				
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
			        robot.delay(50);
			        robot.keyRelease(KeyEvent.VK_ENTER);
			    //Thread.sleep(10000);
				    
			        
			   
			       // uploadLecVideo.sendKeys(ss.toString());
					
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
							
							String againtitle=driver.findElement(By.xpath("//div[@class='text-error']")).getText();
							
							if(againtitle.length()>0)
							{
							driver.findElement(By.xpath("//input[@placeholder='Lecture title']")).sendKeys(titleToc.get(i+"."+j).toString()+"-"+titleToc.get("Section "+i).toString());
							lecSaveBtn.click();
							}
						} catch (Exception e) {
							continue;
						}						
		
					
				}
				catch (NoSuchElementException d) {
					// TODO: handle exception
					j=j-1;
					//driver.navigate().refresh();
					driver.findElement(By.xpath("//button[@class='btn text-uppercase']")).click();
					Thread.sleep(3000);
				}
				
			}
				
			
		}
		
	}
	}
	
	//Web driver class for details and re-upload
	public void driverWebChrome(String userName, String url) throws InterruptedException, AWTException
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
		driver.get(url);
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[2]/header/div[2]/a[2]")).click();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"auth-form\"]/div[1]/input")).sendKeys("products@packt.com");
		driver.findElement(By.xpath("//*[@id=\"auth-form\"]/div[2]/input")).sendKeys("packt@123");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"auth-form\"]/div[4]/button")).click();
		Thread.sleep(2000);
		//driver.get("https://www.simpliv.com/courses/prem-testing-title-demo/edit/?step=2");
		
		for(int t=0;t<6;t++){
			 robot1.keyPress(KeyEvent.VK_CONTROL);
			 robot1.keyPress(KeyEvent.VK_MINUS);
			 robot1.keyRelease(KeyEvent.VK_CONTROL);
			 robot1.keyRelease(KeyEvent.VK_MINUS);
				Thread.sleep(1000);
				}
		
	}
	
	
}
