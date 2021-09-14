package shulphLiveCheck;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShulphDataScrap {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
System.setProperty("webdriver.gecko.driver","C:\\Users\\premachandras\\Documents\\Eslipse\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		
		File file=new File("C:\\Users\\premachandras\\Desktop\\New folder (3)\\sulph.xlsx");
		FileInputStream fls=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(2);
		FileOutputStream fileOut = new FileOutputStream("C:\\Users\\premachandras\\Desktop\\New folder (3)\\sulph.xlsx");
		XSSFRow row1;
		WebDriver driver=new FirefoxDriver();
		int numRow=sheet1.getLastRowNum();
		for(int i=0;i<=numRow;i++)
		{
			driver.get(sheet1.getRow(i).getCell(0).toString());
			Thread.sleep(2000);
			try {
				sheet1.getRow(i).createCell(1).setCellValue(driver.findElement(By.xpath("//h1[@class='product-name']")).getText());
				System.out.println("Title Name : ---");
				System.out.println(driver.findElement(By.xpath("//h1[@class='product-name']")).getText());
				System.out.println("Author Name:---");
				sheet1.getRow(i).createCell(2).setCellValue(driver.findElement(By.xpath("//span[@class='authorname']")).getText());
				System.out.println(driver.findElement(By.xpath("//span[@class='authorname']")).getText());
				sheet1.getRow(i).createCell(3).setCellValue(driver.findElement(By.xpath("//span[@class='buttonprice']")).getText());
				System.out.println("Price : ----");
				System.out.println(driver.findElement(By.xpath("//span[@class='buttonprice']")).getText());
				sheet1.getRow(i).createCell(4).setCellValue(driver.findElement(By.xpath("//div[@class='bodycontent']")).getText());
				System.out.println("Body content : ----");
				System.out.println(driver.findElement(By.xpath("//div[@class='bodycontent']")).getText());
				
			} catch (Exception e) {
				driver.navigate().refresh();
				i--;
			}
			
			
			
		}
		wb.write(fileOut);
		 wb.close();
		 driver.quit();
	}

}
