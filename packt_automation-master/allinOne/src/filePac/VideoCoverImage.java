package filePac;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VideoCoverImage {

	public static void getPath(String docLoc) throws ParseException, IOException, InterruptedException
	{
		File fileDocLoc=new File(docLoc);
		FileInputStream fls=new FileInputStream(fileDocLoc);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(0);
		int rownum=sheet1.getLastRowNum();
		
		 String sortJpg="";
		 String	pathJpg="";
		 int max=1586;
		 
		DataFormatter formatter = new DataFormatter();
		String copyLocation="";
		for(int i=1;i<rownum;i++)
		{
			String bundleName=formatter.formatCellValue(sheet1.getRow(i).getCell(0)).trim();
			String tilteName=formatter.formatCellValue(sheet1.getRow(i).getCell(1)).trim();
			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(6)).trim();
			String printIsbn=formatter.formatCellValue(sheet1.getRow(i).getCell(2)).trim();
			String pId=formatter.formatCellValue(sheet1.getRow(i).getCell(0)).trim();
			System.out.println(kk);
			
			 String[] arrOfStr = kk.split("-");
			 System.out.println("Print month: "+arrOfStr[1]);
			 System.out.println("Print Year: "+arrOfStr[2]);
			 Locale locale = new Locale("en", "UK");
			 DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
			 
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MMMMM-yyyy",dateFormatSymbols);
			 Date date1=sdf.parse(kk); 
			 Date requDate=sdf.parse("31-December-2017");
			System.out.println(requDate);
			
			 if(date1.before(requDate))
			 {
				 
				 
					sheet1.getRow(i).createCell(8).setCellValue("old tilte manually search");					
				
				 
			 }
			 else
			 {
				//System.out.println("\\\\192.168.0.200\\eBook Operation\\"+arrOfStr[2]+"\\"+arrOfStr[1]); 
				
				String year=arrOfStr[2];
				String month=arrOfStr[1];
				pathJpg="\\\\192.168.0.200\\eBook Operation\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+"\\"+printIsbn+".jpg";
				
				
			 }
			 
			 String Status=jpgFileCopy(pId);
				Thread.sleep(1000);
				sheet1.getRow(i).createCell(8).setCellValue(Status);
				FileOutputStream frt=new FileOutputStream(docLoc);
				 wb.write(frt);
				 System.out.println(rownum-i);
			 
		}
}
	
	
	static String jpgFileCopy(String pId)
	{
		String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\Transneuron\\Cover\\Prem colllection by script\\2019\\";
		String path="";
		File dest = new File(filePath);
		 try {
			 path="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\Transneuron\\Cover\\Prem colllection by script\\"+pId+".jpg";
			 File source = new File(path);
			FileUtils.copyFileToDirectory(source, dest );
			return "File placed";
		} catch (IOException e) {
			return e.getMessage();
			
		}
		 
	}
	
	
	
	//main method
	
	public static void main(String[] args) throws ParseException, IOException, InterruptedException {
		VideoCoverImage vcd=new VideoCoverImage();
		VideoCoverImage.getPath("C:\\Users\\premachandras\\OneDrive - Packt\\Transneurons\\Transneuron - For Prem.xlsx");
		
	}
		
	}
	
	
	
