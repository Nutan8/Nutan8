package udemyLive;

import java.awt.AWTException;
import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.Select;

public class UdemyLiveC extends MutableCapabilities {
	static WebDriver driver;
	static HashMap<String,String> hm=new HashMap<String,String>();  
	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\premachandras\\Documents\\chromedriver\\chromedriver.exe");
		//System.setProperty("webdriver.opera.driver", "C:\\Users\\premachandras\\Downloads\\operadriver_win64 (4)\\operadriver_win64\\operadriver.exe");
		
//		OperaOptions options = new OperaOptions();
//		options.addExtensions(new File("/path/to/extension.crx"));
//	    options.setBinary(new File("C:\\Users\\premachandras\\AppData\\Local\\Programs\\Opera\\53.0.2907.68\\opera.exe"));
//	    driver=new OperaDriver(options);
		driver=new ChromeDriver();
		UdemyLiveC.getData();

		JavascriptExecutor je = (JavascriptExecutor) driver;
		
		driver.get("https://www.udemy.com/");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='btn btn-quaternary']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("id_email")).sendKeys("udemy@packtpub.com");
		Thread.sleep(1000);
		driver.findElement(By.id("id_password")).sendKeys("C0llab1357");
		Thread.sleep(1000);
		driver.findElement(By.id("submit-id-submit")).click();
		Thread.sleep(5000);
		driver.get("https://www.udemy.com/course/create/1");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='create-course-flow--buttonPanel--PDCNH radio-button radio']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		Thread.sleep(2000);
		System.out.println(hm.get("Title Name"));
		driver.findElement(By.xpath("//div[@class='form-control-counter-container']//input")).sendKeys(hm.get("Title Name"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		Thread.sleep(3000);
		Select catDrop=new Select(driver.findElement(By.id("courseCategory")));
		Thread.sleep(1000);
		catDrop.selectByVisibleText("IT & Software");
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[@title][position()]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@data-purpose='right-button']")).click();
		Thread.sleep(6000);
		String url=driver.getCurrentUrl();
		String t=filtered(url);
		String urlConternt="https://www.udemy.com/course/"+t+"/manage/curriculum/";
		driver.get(urlConternt);
		Thread.sleep(2000);
		int c=0;
		int l=0;
		//******************************delete automatically generated courese ******************************************//
		driver.findElement(By.xpath("//button[@data-purpose='lecture-delete-btn']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='modal-footer']//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@data-purpose='section-delete-btn']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='modal-footer']//button[@type='submit']")).click();
		//********************************deleted automaticaly generated course close *********************************//
		Thread.sleep(3000);
		for(int i=1;i<=hm.size();i++)
		{ 
			if(hm.get("Section "+i)==null)
				break;
			else
			{
			// for find and add section
			driver.findElement(By.xpath("//button[@class='ellipsis btn btn-default btn-block\' and @ data-purpose='add-section-btn\']")).click();
			driver.findElement(By.xpath("//input[@id='title' and @placeholder='Enter a Title' and @data-purpose='section-title']")).sendKeys(hm.get("Section "+i));
			Thread.sleep(1000);
			WebElement subSec=driver.findElement(By.xpath("//button[@type='submit' and @data-purpose='submit-section-form']"));
			je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-50)",subSec);
			subSec.click();
			}
			//driver.findElement(By.xpath("(//button[@type='submit' and @data-purpose='submit-section-form'])[last()]")).click();
			Thread.sleep(3000);
			if(c<1)
			{
					driver.findElement(By.xpath("//button[@data-purpose='add-lecture-btn']")).click();	
			}
			for(int j=1;j<=hm.size();j++)
			{
				Thread.sleep(3000);
				if(hm.get(i+"."+j)==null)
				{
					//driver.findElement(By.xpath("//button [@data-purpose='cancel-section-form' and @type='button']")).click();
				break;
				}
				else
				{
				WebElement subInSec=driver.findElement(By.xpath("//input[@data-purpose='lecture-title' and @id='title' and @type='text']"));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-50)",subInSec);
				subInSec.sendKeys(hm.get(i+"."+j));
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@type='submit' and @data-purpose='submit-lecture-form']")).click();
				
				StringBuilder data=new StringBuilder();
				
				for(int k=1;k<=hm.size();k++)
				{
					
					Thread.sleep(1000);
					if(hm.get("Clip Section "+i+"."+j+":"+k)==null)
					{
						break;
					}
					else
					{
						if(k==1)
						{
						data.append(hm.get("Clip Section "+i+"."+j+":"+k));
						}
						else
						{
							data.append("\n   •  "+hm.get("Clip Section "+i+"."+j+":"+k));
						}
					}
					
				}
				//****************** this for adding clip description ******************************
				WebElement lecColBut=driver.findElement(By.xpath("(//button[@data-purpose='lecture-collapse-btn' and @type='button'])[last()]"));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-200)",lecColBut);
				Thread.sleep(5000);
				lecColBut.click();
				WebElement lecDecBut=driver.findElement(By.xpath("(//button[@data-purpose='add-desc-btn' and @type='button'])[last()]"));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-200)",lecDecBut);
				Thread.sleep(3000);
				lecDecBut.click();
				Thread.sleep(3000);
				WebElement textArea=driver.findElement(By.id("redactor-uuid-"+l));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-200)",textArea);
				Thread.sleep(2000);
				textArea.sendKeys(data);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
				Thread.sleep(2000);
				//**************************** for uploading videos using robot method ***************************
				driver.findElement(By.xpath("(//button[@data-purpose='lecture-add-content-btn'])[last()]")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("(//a[@data-purpose='select-video'])[last()]")).click();
				Thread.sleep(4000);
			     
				Thread.sleep(3000);
				//driver.findElement(By.xpath("(//div[@data-purpose='asset-uploader-input'])[last()]")).click();
				String vPath = "\\\\192.168.4.61\\Photon\\Video\\2018\\9864_Implementing Serverless Microservices Architecture Patterns\\19] Final Package\\9781788839570\\Package\\videos\\video"+i+"_"+j+".mp4";
				
				WebElement videoFile=driver.findElement(By.xpath("(//div[@data-purpose='asset-uploader-input'])[last()]//input[@type='file']"));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-50)",videoFile);
				videoFile.sendKeys(vPath);
				
				l++;
				}
			
			Thread.sleep(3000);
			
			}
			
			c+=2;
		}
		
		// for method call for verifying all the videos are uploaded or not 
		
		while(uploadStatus()>0)
		{
			uploadStatus();
		}
		
		// for transering to other section in url
		
		driver.get("https://www.udemy.com/course/"+t+"/manage/basics/");
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
		 File file=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\Udemy Automation\\V09864.xlsx");
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
				hm.put(a,b);
		}
			
		
	 }
}
	
