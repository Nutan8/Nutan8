package liveChecks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NookLiveChecks {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.gecko.driver","C:\\Users\\premachandras\\Documents\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.barnesandnoble.com/");
		Thread.sleep(3000);
		
String docLoc="C:\\Users\\premachandras\\OneDrive - Packt\\Live Checks B&N\\bn.xlsx";
		
		
		File file=new File(docLoc);
		FileInputStream fls=new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(1);
		int rownum=sheet1.getLastRowNum();
		
		
		Thread.sleep(2000);
		
		
		
		
		DataFormatter formatter = new DataFormatter();
		for(int i=0;i<=rownum;i++)
		{
			// for put coloum 1 value to hash map
			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(1));
			// for puting coloum 2 value to hash map
			//kk.replace(" - Second Edition", ", Second Edition");
			driver.findElement(By.id("searchBarBN")).clear();
		
		
			driver.findElement(By.id("searchBarBN")).sendKeys(kk);
			//
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='icon-search-2']")).click();
			Thread.sleep(3000);
			String fettitle="";
			String bodyDesc="";
			String detailsInfo="";
			try
			{
				WebElement titleName=driver.findElement(By.xpath("//h1[@itemprop='name']"));
				WebElement desc=driver.findElement(By.xpath("//div[@class='descriptionWrap']"));
				System.out.println(desc.isEnabled());
				if(titleName.isEnabled())
				{
					fettitle=driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
					System.out.println(fettitle);
					bodyDesc=driver.findElement(By.xpath("//div[@class='descriptionWrap']")).getText();
					System.out.println(bodyDesc);
					detailsInfo=driver.findElement(By.xpath("(//div[@class='productInfo'])[last()]")).getText();
					System.out.println(detailsInfo);
				}
				else
				{
					fettitle="title not avaliable";
				}
				sheet1.getRow(i).createCell(6).setCellValue(fettitle);
				sheet1.getRow(i).createCell(7).setCellValue(bodyDesc);
				sheet1.getRow(i).createCell(8).setCellValue(detailsInfo);
				FileOutputStream frt=new FileOutputStream(docLoc);
				wb.write(frt);
			}
			catch (Exception e)
			{
				fettitle="title not avaliable";
				
				sheet1.getRow(i).createCell(6).setCellValue(fettitle);
				FileOutputStream frt=new FileOutputStream(docLoc);
				wb.write(frt);
			}
			
			
			
	}
		
		driver.quit();
		
	}
	

	}


