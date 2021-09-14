package packtpub;

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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class PacktDataLongDesc {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver","C:\\Users\\premachandras\\Documents\\chromedriver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.packtpub.com/");
		Thread.sleep(3000);
		/*driver.get("https://account.packtpub.com/login?returnUrl=referrer");
		Thread.sleep(3000);
		driver.findElement(By.name("email")).sendKeys("premachandras@packtpub.com");
		driver.findElement(By.name("password")).sendKeys("Kingdom1914");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Log in')]")).click();
		
		Thread.sleep(100000);*/
String docLoc="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\iSource\\Metadata and files\\Files\\Batch8\\Book8fetch.xlsx";
driver.get("https://www.packtpub.com/");
		
		File file=new File(docLoc);
		FileInputStream fls=new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(0);
		int rownum=sheet1.getLastRowNum();
		
		
		Thread.sleep(3000);		
		
		DataFormatter formatter = new DataFormatter();
		for(int i=2;i<=rownum;i++)
		{
			
			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));
			
			driver.findElement(By.id("search")).clear();
		
		
			
			driver.findElement(By.id("search")).sendKeys(kk);
			
			Thread.sleep(2000);
			
			
			driver.findElement(By.xpath("//button[@class='magnifying-glass']")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//h5[@class='card-title mt-0']")).click();
			Thread.sleep(2000);
	
			Thread.sleep(10000);			
			
				System.out.println(driver.findElement(By.xpath("//td[@data-th='about']")).getAttribute("innerHTML"));	
				Thread.sleep(2000);
				sheet1.getRow(i).createCell(18).setCellValue(driver.findElement(By.xpath("/html/body/div[2]/main/div[3]/div/div[6]/div[2]/label[1]/div/div[2]/div/div/span/span/span")).getText());
				Thread.sleep(2000);
				sheet1.getRow(i).createCell(20).setCellValue(driver.findElement(By.xpath("//p[@class='length page-count']")).getText());
				Thread.sleep(2000);
				
			
				sheet1.getRow(i).createCell(22).setCellValue(driver.findElement(By.xpath("//td[@data-th='about']")).getAttribute("innerHTML").toString());
				Thread.sleep(2000);
				sheet1.getRow(i).createCell(5).setCellValue(driver.findElement(By.xpath("//div[@itemprop='description']")).getText());
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@id='tab-label-product.info.authors.tab-title']")).click();
				Thread.sleep(2000);
				sheet1.getRow(i).createCell(8).setCellValue(driver.findElement(By.xpath("//div[@class='authors inline']")).getText());
				Thread.sleep(2000);
				sheet1.getRow(i).createCell(10).setCellValue(driver.findElement(By.xpath("//div[@class='authors-tab']")).getText());
			FileOutputStream frt=new FileOutputStream(docLoc);
			wb.write(frt);
			
		
				
			
			
	}
		
		driver.quit();
		
	}
	

	}


