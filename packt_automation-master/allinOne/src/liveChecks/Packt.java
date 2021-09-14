package liveChecks;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Packt {

	public static void main(String[] args) throws InterruptedException, IOException, AWTException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver","C:\\Users\\premachandras\\Documents\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();

	/*	System.setProperty("webdriver.chrome.driver", "C:\\Users\\premachandras\\Documents\\chromedriver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("https://www.udemy.com/");
		options.addArguments("--disable-extensions");
		System.out.println("chrome drivers disibale extension");
		options.addArguments("user-data-dir=C:\\Users\\premachandras\\AppData\\Local\\Google\\Chrome\\User Data");	
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		*/
		driver.get("https://www.packtpub.com/in/");
		Thread.sleep(3000);
		/*driver.get("https://account.packtpub.com/login?returnUrl=referrer");
		Thread.sleep(3000);
		driver.findElement(By.name("email")).sendKeys("premachandras@packtpub.com");
		driver.findElement(By.name("password")).sendKeys("Kingdom1914");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
		
		Thread.sleep(100000);*/
String docLoc="\\\\192.168.0.200\\Products\\Executives\\kalpak\\LS\\Batch 7\\datafetch-7.xlsx";
		
		File file=new File(docLoc);
		FileInputStream fls=new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(0);
		int rownum=sheet1.getLastRowNum();
		
		
		Thread.sleep(3000);		
		
		Robot robot = new Robot();
		 for (int k = 0; k < 6; k++) {
			 robot.keyPress(KeyEvent.VK_CONTROL);
			 robot.keyPress(KeyEvent.VK_SUBTRACT);
			 robot.keyRelease(KeyEvent.VK_SUBTRACT);
			 robot.keyRelease(KeyEvent.VK_CONTROL);
		 }
		 Thread.sleep(5000);	
		DataFormatter formatter = new DataFormatter();
		for(int i=0;i<=rownum;i++)
		{
			driver.get("https://www.packtpub.com/in/");
			Thread.sleep(5000);
			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));
			
				driver.findElement(By.id("search")).clear();
				driver.findElement(By.id("search")).sendKeys(kk);
				//driver.findElement(By.xpath("//button[@class='magnifying-glass']")).click();
			
				driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
			
			
			Thread.sleep(4000);
			
			try {
				
			
			//driver.findElement(By.xpath("//button[@class='magnifying-glass']")).click();
			
			Thread.sleep(5000);
			
			
			driver.findElement(By.xpath("//h5[@class='card-title mt-0']")).click();
			
			//driver.findElement(By.xpath("//h5[@class='card-title mt-0']")).sendKeys(Keys.ENTER);
			System.out.println("clcikcedlkljk");
			Thread.sleep(10000);	
			
			
				//driver.findElement(By.id("tab-label-table.of.contents")).click();
				Thread.sleep(3000);
				String toc=driver.findElement(By.id("style-white")).getText().toString();
				System.out.println(toc);
				driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.END);
				
				
//******************** Descritpon ended and Author bio started ***************************//
				String authorName=driver.findElement(By.xpath("//a[@data-target='#author-collapse0']/h5")).getText();
				String authorBio=driver.findElement(By.id("author-collapse0")).getText();
				
			//******************** Author bio ended and TOC stared***************************/
				sheet1.getRow(i).createCell(5).setCellValue(textToHtml(toc));	
				sheet1.getRow(i).createCell(6).setCellValue(textToHtml(authorName));	
				sheet1.getRow(i).createCell(7).setCellValue(textToHtml(authorBio));
				Thread.sleep(3000);
			FileOutputStream frt=new FileOutputStream(docLoc);
			wb.write(frt);
			
			} catch (Exception e) {
				sheet1.getRow(i).createCell(14).setCellValue("not found");	
				FileOutputStream frt=new FileOutputStream(docLoc);
				wb.write(frt);
			}
				
			
			
	}
		
		driver.quit();
		
	}
	
	
	//stirng split to html tag
	
	public static String textToHtml(String s)
	{
		String outPut="<ol>";
		
		String[] lines = s.split("\\r?\\n");
		int count=1;
        for (String line : lines) {
        	if(line.length()<3)
        	{
        		
        	}
        	else
        	{
        		outPut+="<li>"+line+"</li>\n";
        		count++;
        	}
            
        }
        outPut+="</ol>";
		return outPut;
	}
	

	}


