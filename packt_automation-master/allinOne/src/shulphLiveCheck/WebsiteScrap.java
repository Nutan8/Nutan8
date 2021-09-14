package shulphLiveCheck;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Driver;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;

public class WebsiteScrap {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		int count=1;
		System.setProperty("webdriver.gecko.driver","C:\\Users\\premachandras\\Documents\\Eslipse\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		
		File file=new File("C:\\Users\\premachandras\\Desktop\\New folder (3)\\sulph.xlsx");
		FileInputStream fls=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(1);
		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\premachandras\\Desktop\\New folder (3)\\sulph.xlsx");
		XSSFRow row1;
		WebDriver driver=new FirefoxDriver();
		for(int i=1;i<=83;i++)
		{
			driver.get("https://www.shulph.com/books/categories/computers/"+i);
	java.util.List<WebElement> li=driver.findElements(By.xpath("//div[@class='booktile']/a"));
	for (int j = 1; j<li.size(); j++)
		 
	 {
	 
	// System.out.println(li.get(j).getAttribute("href"));
	
	    row1=sheet1.createRow((short) count);
		row1.createCell(0).setCellValue(li.get(j).getAttribute("href").toString());
	 count++;
	 }
	Thread.sleep(2000);
			
		}
		wb.write(fileOut);
		 wb.close();


		
		driver.quit();

	}

}
