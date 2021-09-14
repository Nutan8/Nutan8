package packtpub;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PactMetadata {

		public static void main(String[] args) throws InterruptedException, AWTException, IOException {
			
			System.setProperty("webdriver.gecko.driver","C:\\Users\\premachandras\\Documents\\geckodriver-v0.26.0-win64\\geckodriver.exe");
			WebDriver driver=new FirefoxDriver();
			driver.manage().window().maximize();
			
			driver.get("https://www.packtpub.com/");
			Thread.sleep(3000);
			String docLoc="C:\\Users\\premachandras\\OneDrive - Packt\\RNIB\\RPS Solution - Copy.xlsx";
			
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
			
			for(int i=1;i<=rownum;i++)
			{
				String htmlDetailDescription="<p><strong>";
				String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(4));
				System.out.println(" here is hte link");
				System.out.println(sheet1.getRow(i).getCell(4));
				driver.get(sheet1.getRow(i).getCell(4).toString());
				
				try {
					
		
				Thread.sleep(10000);			
				//metadescripton or ta
				System.out.println("clcikcedlkljk");
				Thread.sleep(10000);	
				
					String titleName=driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']")).getText();
					Thread.sleep(1000);
					sheet1.getRow(i).createCell(3).setCellValue(titleName);
					String isbn=driver.findElement(By.xpath("//td[@data-th='isbn']")).getText();
					Thread.sleep(1000);
					sheet1.getRow(i).createCell(2).setCellValue(isbn.toString());
					String date=driver.findElement(By.xpath("//td[@data-th='date_of_publication']")).getText();
					sheet1.getRow(i).createCell(9).setCellValue(date);
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
		

		}


