package rps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RpsMetadata {
	static HashMap<String,String> toc=new HashMap<String,String>(); 
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		getData("V10348");
		createMetadata("V10348","9781789131550");

	}
	
	
	
	
	 protected static void getData(String Title) throws IOException   // getting TOC of the title
	 {
		// File file=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\Udemy Automation\\"+Title+".xlsx");
		 File file=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\Udemy Automation\\"+Title+".xlsx");
			FileInputStream fls=new FileInputStream(file);
			@SuppressWarnings("resource")
			XSSFWorkbook wb=new XSSFWorkbook(fls);
			XSSFSheet sheet1= wb.getSheetAt(0);
			int rownum=sheet1.getLastRowNum();
			
			DataFormatter formatter = new DataFormatter();
			for(int i=0;i<=rownum;i++)
			{
				// for put coloum 1 value to hash map
				String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));
				// for puting coloum 2 value to hash map
				String kk1=formatter.formatCellValue(sheet1.getRow(i).getCell(1));				
				String a=kk.trim();
				String b=kk1.trim();
				// maping the excel value to hash map
				toc.put(a,b);
		}
	 }
	 
	 
	 
	 protected static void createMetadata(String productId,String isbn) throws IOException, InterruptedException
	 {
		String source="C:\\Users\\premachandras\\Documents\\RPS -1\\Latest\\"+productId+"\\Sample Metadata.xlsx";
		// String source="C:\\Users\\premachandras\\Documents\\RPS\\"+productId+".xlsx";
		File file=new File(source);
		FileInputStream fls=new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(0);
		int rownum=sheet1.getLastRowNum();
		 XSSFRow row1;
		FileOutputStream fileOut = new FileOutputStream(source);
		 int row=1;
		 for(int i=1;i<=100;i++)
			{ 
				if(toc.get("Section "+i)==null)
				{
					break;
				}
				else
				{
					
				for(int j=1;j<=100;j++)
				{
					
					if(toc.get(i+"."+j)==null)
					{
					break;
					}
					else
					{
						
						StringBuilder data=new StringBuilder();
					for(int k=1;k<=100;k++)
					{
						
						if(toc.get("Clip Section "+i+"."+j+":"+k)==null)
						{
							break;
						}
						else
						{
							if(k==1)
							{
							data.append(toc.get("Clip Section "+i+"."+j+":"+k));
							}
							else
							{
								data.append("\n   •  "+toc.get("Clip Section "+i+"."+j+":"+k));
							}
						}					
					}
					row1=sheet1.createRow((short) row);
					row1.createCell(0).setCellValue(isbn+"-Video"+i+"_"+j);
					row1.createCell(1).setCellValue(toc.get("Title Name"));
					row1.createCell(2).setCellValue(toc.get("Section "+i));
					row1.createCell(3).setCellValue(toc.get(i+"."+j));
					row1.createCell(4).setCellValue(data.toString());
					row=row+1;
					
					}
				
				}
				
			}
				
	 }
		 
		 //FileOutputStream fileOut = new FileOutputStream(docLoc);
		 wb.write(fileOut);
		 wb.close();
		 
		  
	 }

}
