package liveChecks;

import java.awt.RenderingHints.Key;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PerlegoLiveChecks {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver","C:\\Users\\premachandras\\Documents\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.perlego.com/search?query=searchInput&tab=topResult&language=English&publicationDate=&topic=&publisher=&author=&format=");
		
		Thread.sleep(3000);
		
String docLoc="C:\\Users\\premachandras\\Downloads\\Perlego notlive - jul19-sep20.xlsx";
		
		
		File file=new File(docLoc);
		FileInputStream fls=new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(0);
		int rownum=sheet1.getLastRowNum();
		
		
		Thread.sleep(2000);
		
		
		
		
		DataFormatter formatter = new DataFormatter();
		for(int i=1;i<=rownum;i++)
		{
			// for put coloum 1 value to hash map
			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));
			// for puting coloum 2 value to hash map
			//kk.replace(" - Second Edition", ", Second Edition");
			driver.findElement(By.id("searchInput")).clear();
		
		
			driver.findElement(By.id("searchInput")).sendKeys(kk);
			//
			driver.findElement(By.id("searchInput")).sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			String k="";
			try
			{
				WebElement ele=driver.findElement(By.xpath("//div[@data-component-locator='NoResultsFound']"));
				System.out.println(ele.isEnabled());
				if(ele.isEnabled())
				{
				k="Title not live";
				}
				
				sheet1.getRow(i).createCell(6).setCellValue(k);
				FileOutputStream frt=new FileOutputStream(docLoc);
				wb.write(frt);
			}
			catch (Exception e)
			{
				k="title avaliable";
				
				sheet1.getRow(i).createCell(6).setCellValue(k);
				FileOutputStream frt=new FileOutputStream(docLoc);
				wb.write(frt);
			}
			
			
			
	}
		
		driver.quit();
		
	}
	

	}


