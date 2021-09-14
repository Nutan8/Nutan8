package filePac;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class FileCopy {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		// TODO Auto-generated method stub
		
	String docLoc="C:\\Users\\premachandras\\Documents\\fillecollectionsheet.xlsx";
		File fileDocLoc=new File(docLoc);
		FileInputStream fls=new FileInputStream(fileDocLoc);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(9);
		int rownum=sheet1.getLastRowNum();
		
		 String	path="";
		 String sortPath="";
		 String sortJpg="";
		 String	pathJpg="";
		 String sortEpub="";
		 String pathEpub="";
		 String sortMobi="";
		 String pathMobi="";
		 String sortCode="";
		 String pathCode="";
		DataFormatter formatter = new DataFormatter();
		String copyLocation="";
		for(int i=7;i<=rownum;i++)
		{
			String bundleName=formatter.formatCellValue(sheet1.getRow(i).getCell(0)).trim();
			String tilteName=formatter.formatCellValue(sheet1.getRow(i).getCell(1)).trim();
			String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(3)).trim();
			String printIsbn=formatter.formatCellValue(sheet1.getRow(i).getCell(2)).trim();
			//String eBookIsbn=formatter.formatCellValue(sheet1.getRow(i).getCell(2)).trim();
			System.out.println(kk);
			
			 String[] arrOfStr = kk.split("-");
			 System.out.println("Print month: "+arrOfStr[1]);
			 System.out.println("Print Year: "+arrOfStr[2]);
			 Locale locale = new Locale("en", "UK");
			 DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
			 
			 SimpleDateFormat sdf=new SimpleDateFormat("dd-MMMMM-yyyy",dateFormatSymbols);
			 Date date1=sdf.parse(kk); 
			 Date requDate=sdf.parse("1-September-2018");
			System.out.println(requDate);
			
			 if(date1.before(requDate))
			 {
				 System.out.println("\\\\192.168.0.200\\eBook Operation\\Ebook\\"+arrOfStr[2]+"\\"+arrOfStr[1]);
				 path="\\\\192.168.0.200\\eBook Operation\\Ebook\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+".pdf";
				 sortPath="\\\\192.168.0.200\\eBook Operation\\Ebook\\"+arrOfStr[2]+"\\"+arrOfStr[1].substring(0, 3)+"\\"+printIsbn+".pdf";
				 
				 pathJpg="\\\\192.168.0.200\\eBook Operation\\CoverImage\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+".jpg";
				 sortJpg="\\\\192.168.0.200\\eBook Operation\\CoverImage\\"+arrOfStr[2]+"\\"+arrOfStr[1].substring(0, 3)+"\\"+printIsbn+".jpg";
				 
				 
				 sortEpub="\\\\192.168.0.200\\eBook Operation\\Epubs\\"+arrOfStr[2]+"\\"+arrOfStr[1].substring(0, 3)+"\\"+printIsbn+".epub";
				 pathEpub="\\\\192.168.0.200\\eBook Operation\\Epubs\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+".epub";
				 
				 sortMobi="\\\\192.168.0.200\\eBook Operation\\Mobi\\"+arrOfStr[2]+"\\"+arrOfStr[1].substring(0, 3)+"\\"+printIsbn+".mobi";
				 pathMobi="\\\\192.168.0.200\\eBook Operation\\Mobi\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+".mobi";
				 
				
				 
			 }
			 else
			 {
				System.out.println("\\\\192.168.0.200\\eBook Operation\\"+arrOfStr[2]+"\\"+arrOfStr[1]); 
				path="\\\\192.168.0.200\\eBook Operation\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+"\\"+printIsbn+"_Ebook.pdf";
				pathJpg="\\\\192.168.0.200\\eBook Operation\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+"\\"+printIsbn+".jpg";
				pathEpub="\\\\192.168.0.200\\eBook Operation\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+"\\"+printIsbn+".epub";
				pathMobi="\\\\192.168.0.200\\eBook Operation\\"+arrOfStr[2]+"\\"+arrOfStr[1]+"\\"+printIsbn+"\\"+printIsbn+".mobi";
			 }
			 
			 
			String pdfStatus= pdfFileCopy(path,sortPath,printIsbn,bundleName,tilteName);
			Thread.sleep(10000);
			sheet1.getRow(i).createCell(7).setCellValue(pdfStatus);
			String jpgStatus=jpgFileCopy(pathJpg,sortJpg,printIsbn,bundleName,tilteName);
			Thread.sleep(10000);
			sheet1.getRow(i).createCell(6).setCellValue(jpgStatus);
			String epubStatus=epubFileCopy(pathEpub,sortEpub,printIsbn,bundleName,tilteName);
			Thread.sleep(10000);
			sheet1.getRow(i).createCell(4).setCellValue(epubStatus);
			String mobiStatus=mobiFileCopy(pathMobi,sortMobi,printIsbn,bundleName,tilteName);
			Thread.sleep(10000);
			sheet1.getRow(i).createCell(5).setCellValue(mobiStatus);
			
			String onixepubStatus=epubOnixFileCopy(pathMobi,sortMobi,printIsbn);
			sheet1.getRow(i).createCell(4).setCellValue(mobiStatus);
			Thread.sleep(10000);
				FileOutputStream frt=new FileOutputStream(docLoc);
				wb.write(frt);
			 System.out.println(sdf.format(date1));
			 
		}
		
	
		
	
	  }
	
	//Ebook
	static String pdfFileCopy(String path,String sortPath,String printIsbn,String bundle, String title)
	{
		String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\Humble Bundle\\AWS data\\2021\\June 2021\\"+bundle+"\\"+title+"\\";
		File source = new File(path);
		File sorSource=new File(sortPath);
		File dest = new File(filePath);
		 try {
			FileUtils.copyFileToDirectory(source, dest );
			return "File placed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				FileUtils.copyFileToDirectory(sorSource, dest );
				return "File placed";
				
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
				return e.toString();
			}
		}
		 
	}
	
	//epub for oinxsuite
	static String epubOnixFileCopy(String path,String sortPath,String printIsbn)
	{
		String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\Onixsuite\\Sharepoint\\EBC\\14-07-21\\Prem\\";
		File source = new File(path);
		File sorSource=new File(sortPath);
		File dest = new File(filePath);
		 try {
			FileUtils.copyFileToDirectory(source, dest );
			return "File placed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				FileUtils.copyFileToDirectory(sorSource, dest );
				return "File placed";
				
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				return e.toString();
			}
			
		}
		 
	}
	
	//cover image
	static String jpgFileCopy(String path,String sortPath,String printIsbn,String bundle, String title)
	{
		String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\Humble Bundle\\AWS data\\2021\\June 2021\\"+bundle+"\\"+title+"\\";
		File source = new File(path);
		File sorSource=new File(sortPath);
		File dest = new File(filePath);
		 try {
			FileUtils.copyFileToDirectory(source, dest );
			return "File placed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				FileUtils.copyFileToDirectory(sorSource, dest );
				return "File placed";
				
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				return e.toString();
			}
			
		}
		 
	}
	
	//epub
	static String epubFileCopy(String path,String sortPath,String printIsbn,String bundle, String title)
	{
		String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\Humble Bundle\\AWS data\\2021\\June 2021\\"+bundle+"\\"+title+"\\";
		File source = new File(path);
		File sorSource=new File(sortPath);
		File dest = new File(filePath);
		 try {
			FileUtils.copyFileToDirectory(source, dest );
			return "File placed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				FileUtils.copyFileToDirectory(sorSource, dest );
				return "File placed";
				
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				return e.toString();
			}
			
		}
		 
	}
	
	
	//Mobi
	
	//epub
		static String mobiFileCopy(String path,String sortPath,String printIsbn,String bundle, String title)
		{
			String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\Humble Bundle\\AWS data\\2021\\June 2021\\"+bundle+"\\"+title+"\\";
			File source = new File(path);
			File sorSource=new File(sortPath);
			File dest = new File(filePath);
			 try {
				FileUtils.copyFileToDirectory(source, dest );
				return "File placed";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					FileUtils.copyFileToDirectory(sorSource, dest );
					return "File placed";
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println(e.getMessage());
					return e.toString();
				}
				
			}
			 
		}
	
	}
