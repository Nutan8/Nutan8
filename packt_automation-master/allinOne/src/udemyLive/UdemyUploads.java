package udemyLive;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class UdemyUploads {

	static WebDriver driver;
	static HashMap<String,String> toc=new HashMap<String,String>();  
	JavascriptExecutor je = (JavascriptExecutor) driver;
	public UdemyUploads() {
		

}
	
	public void  udemyLogin() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\premachandras\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		
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
				
		
	}
	
	public void uploadsVideo(String Title) throws Exception
	{
		driver.get("https://www.udemy.com/course/create/1");
		getData(Title);		
		// getting data from the excel sheet 
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='create-course-flow--buttonPanel--PDCNH radio-button radio']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='form-control-counter-container']//input")).sendKeys(toc.get("TitleName"));
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
		Thread.sleep(4000);
		int c=0;
		int l=0;
		//******************************delete automatically generated courese ******************************************//
		driver.findElement(By.xpath("//button[@data-purpose='lecture-delete-btn']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='modal-footer']//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@data-purpose='section-delete-btn']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='modal-footer']//button[@type='submit']")).click();
		//********************************deleted automaticaly generated course close *********************************//
		Thread.sleep(3000);
		for(int i=1;i<=toc.size();i++)
		{ 
			if(toc.get("Section "+i)==null)
				break;
			else
			{
			// for find and add section
			driver.findElement(By.xpath("//button[@class='ellipsis btn btn-default btn-block\' and @ data-purpose='add-section-btn\']")).click();
			driver.findElement(By.xpath("//input[@id='title' and @placeholder='Enter a Title' and @data-purpose='section-title']")).sendKeys(toc.get("Section "+i));
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
			for(int j=1;j<=toc.size();j++)
			{
				Thread.sleep(3000);
				if(toc.get(i+"."+j)==null)
				{
					//driver.findElement(By.xpath("//button [@data-purpose='cancel-section-form' and @type='button']")).click();
				break;
				}
				else
				{
				WebElement subInSec=driver.findElement(By.xpath("//input[@data-purpose='lecture-title' and @id='title' and @type='text']"));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-50)",subInSec);
				subInSec.sendKeys(toc.get(i+"."+j));
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@type='submit' and @data-purpose='submit-lecture-form']")).click();
				
				StringBuilder data=new StringBuilder();
				
				for(int k=1;k<=toc.size();k++)
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
				WebElement lecColBut=driver.findElement(By.xpath("(//button[@data-purpose='lecture-collapse-btn' and @type='button'])[last()]"));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-300)",lecColBut);
				Thread.sleep(5000);
				lecColBut.click();
				WebElement lecDecBut=driver.findElement(By.xpath("(//button[@data-purpose='add-desc-btn' and @type='button'])[last()]"));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-300)",lecDecBut);
				Thread.sleep(3000);
				lecDecBut.click();
				Thread.sleep(3000);
				WebElement textArea=driver.findElement(By.id("redactor-uuid-"+l));
				je.executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-300)",textArea);
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
				String vPath = "\\\\192.168.4.61\\Photon\\Video\\2018\\6296_Hands-on Web Development with Vue.js\\19] Final Package\\9781787283039\\Package\\videos\\video"+i+"_"+j+".mp4";
				
				driver.findElement(By.xpath("(//div[@data-purpose='asset-uploader-input'])[last()]//input[@type='file']")).sendKeys(vPath);;
				
				
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
	// ******************************* uploading fetching start  ***********************************
	 protected static int uploadStatus()
	 {
				
		java.util.List<WebElement> links =driver.findElements(By.xpath("//div[@role='progressbar']"));

		return links.size();
		 
	 }
	 
	 // ******************************** uploading status ending *********************************************
	 
	 protected static void getData(String Title) throws IOException   // getting TOC of the title
	 {
		 File file=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\Udemy Automation\\"+Title+".xlsx");
			FileInputStream fls=new FileInputStream(file);
			@SuppressWarnings("resource")
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
	 
	 protected static String filtered(String k) // for filterting the course id 
	 {
		 System.out.println(k);
			String k1=(k.substring(7));
			String array1[]= k1.split("/");
			System.out.println(array1[3]);
			return array1[3];
	 }
	 
}
