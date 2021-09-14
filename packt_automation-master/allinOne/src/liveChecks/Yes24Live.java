package liveChecks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Yes24Live {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver","C:\\Users\\premachandras\\Documents\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.get("https://www.rapddl.com/");
		Thread.sleep(10000);
		
String docLoc="C:\\Users\\premachandras\\Documents\\yes24133Pulldown.xlsx";
		
		
		File file=new File(docLoc);
		FileInputStream fls=new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(1);
		int rownum=sheet1.getLastRowNum();
		
		
		Thread.sleep(2000);
		
		
		
		
		DataFormatter formatter = new DataFormatter();
		for(int i=1;i<=rownum;i++)
		{
			// for put coloum 1 value to hash map
			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(2));
			// for puting coloum 2 value to hash map
			//kk.replace(" - Second Edition", ", Second Edition");
			driver.findElement(By.id("query")).clear();
		
		
			driver.findElement(By.id("query")).sendKeys(kk);
			//
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"yesSForm\"]/fieldset/span[2]/button")).click();
			Thread.sleep(2000);
			String k="";
			try
			{
				WebElement ele=driver.findElement(By.xpath("//p[@class='goods_name goods_icon']/a/strong"));
				System.out.println(ele.isEnabled());
				if(ele.isEnabled())
				{
				k=driver.findElement(By.xpath("//p[@class='goods_name goods_icon']/a/strong")).getText();
				}
				else
				{
					k="title not avaliable";
				}
				sheet1.getRow(i).createCell(6).setCellValue(k);
				FileOutputStream frt=new FileOutputStream(docLoc);
				wb.write(frt);
			}
			catch (Exception e)
			{
				k="title not avaliable";
				
				sheet1.getRow(i).createCell(6).setCellValue(k);
				FileOutputStream frt=new FileOutputStream(docLoc);
				wb.write(frt);
			}
			
			
			
	}
		
		driver.quit();
		
	}
	

	}


