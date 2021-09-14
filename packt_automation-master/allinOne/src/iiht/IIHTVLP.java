package iiht;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IIHTVLP {

	public static void main(String[] args) throws IOException {
		DataFormatter formatter = new DataFormatter();
		// TODO Auto-generated method stub
		//for the fixted file to read the content
		File file=new File("C:\\automationMetadata\\Automation NecessaryData\\IIHT\\IIHT VLP.xlsx");
		
		FileInputStream fls=new FileInputStream(file);
		
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		
		XSSFSheet sheet1= wb.getSheetAt(0);
		XSSFRow row1;
		int numRow=sheet1.getLastRowNum();
		System.out.println(numRow);
		for(int i=1;i<=numRow;i++)
		{
			File file1=new File("C:\\automationMetadata\\Automation NecessaryData\\IIHT\\Necessary data\\metadata.xlsx");
			FileInputStream fls1=new FileInputStream(file1);
			XSSFWorkbook wb1=new XSSFWorkbook(fls1);
			System.out.println(sheet1.getRow(i).getCell(0).toString());
			XSSFSheet vlpTitle=wb.getSheet(sheet1.getRow(i).getCell(0).toString());
			FileOutputStream fileOut = new FileOutputStream("C:\\automationMetadata\\Automation NecessaryData\\IIHT\\metadata\\"+formatter.formatCellValue(sheet1.getRow(i).getCell(2))+".xlsx");
			XSSFSheet productIDsh =wb1.createSheet("metadata"); 
			HashMap<String, String> tocOut=new HashMap<String, String>();
			int instertRow=0;
			row1=productIDsh.createRow((short) instertRow);
			row1.createCell(0).setCellValue("Master File Name");
			row1.createCell(1).setCellValue("Course Name");
			row1.createCell(2).setCellValue("Section Name");
			row1.createCell(3).setCellValue("Module Name");
			row1.createCell(4).setCellValue("Module Type");
			row1.createCell(5).setCellValue("CategoryName");
			row1.createCell(6).setCellValue("Course Summary");
			row1.createCell(7).setCellValue("Author Name");
			row1.createCell(8).setCellValue("Duration of Each Course");
			row1.createCell(9).setCellValue("Rating");
			instertRow++;
			for(int j=1;j<=vlpTitle.getLastRowNum();j++)
			{
				
				row1=productIDsh.createRow((short) instertRow);
				
				row1.createCell(1).setCellValue(sheet1.getRow(i).getCell(1).toString());
				
				if(vlpTitle.getRow(j).getCell(0)!=null && vlpTitle.getRow(j).getCell(0).toString().length()>1)
				{
					
	tocOut=tocGeneration(vlpTitle.getRow(j).getCell(0).toString());
	
	row1.createCell(2).setCellValue(tocOut.get("Title Name"));
		instertRow+=2;
		
			  for(int section=1;section<=tocOut.size();section++)
				{ 
					if(tocOut.get("Section "+section)==null)
						break;
					else
					{
						//System.out.println(tocOut.get("Section "+section));
						
						row1=productIDsh.createRow((short) instertRow);
						row1.createCell(1).setCellValue(sheet1.getRow(i).getCell(1).toString());
						row1.createCell(2).setCellValue(tocOut.get("Title Name"));
						row1.createCell(3).setCellValue(tocOut.get("Section "+section));
						row1.createCell(4).setCellValue("Video");
						row1.createCell(6).setCellValue(tocOut.get("Inner section "+section+":"+1).toString());
						row1.createCell(7).setCellValue(vlpTitle.getRow(j).getCell(2).toString());
						row1.createCell(8).setCellValue(vlpTitle.getRow(j).getCell(3).toString());
						row1.createCell(5).setCellValue(vlpTitle.getRow(j).getCell(4).toString());
						instertRow++;
					for(int secsec=1;secsec<=tocOut.size();secsec++)
					{
						if(tocOut.get(section+"."+secsec)==null)
						{
						break;
						}
						else
						{
							System.out.println(tocOut.get(section+"."+secsec));
							row1=productIDsh.createRow((short) instertRow);
							
							
							//System.out.println(formatter.formatCellValue(vlpTitle.getRow(j).getCell(1)));
							row1.createCell(0).setCellValue(formatter.formatCellValue(vlpTitle.getRow(j).getCell(1))+"-video"+section+"_"+secsec);
							row1.createCell(1).setCellValue(sheet1.getRow(i).getCell(1).toString());
							row1.createCell(2).setCellValue(tocOut.get("Title Name"));
							row1.createCell(3).setCellValue(tocOut.get(section+"."+secsec));
							row1.createCell(4).setCellValue("Video");
					 	StringBuilder data=new StringBuilder();
						//section desctipiton
					 	
					 	//clip description
					 	
						for(int clip=1;clip<=tocOut.size();clip++)
						{
							
							if(tocOut.get("Clip Section "+section+"."+secsec+":"+clip)==null)
							{
								break;
							}
							else
							{
								if(clip==1)
								{
								data.append(tocOut.get("Clip Section "+section+"."+secsec+":"+clip));
								}
								else
								{
									data.append("\n   •  "+tocOut.get("Clip Section "+section+"."+secsec+":"+clip));
								}
							}
							
						}
						System.out.println(data);
						row1.createCell(6).setCellValue(data.toString());
						row1.createCell(7).setCellValue(vlpTitle.getRow(j).getCell(2).toString());
						row1.createCell(8).setCellValue(vlpTitle.getRow(j).getCell(3).toString());
						row1.createCell(5).setCellValue(vlpTitle.getRow(j).getCell(4).toString());
						instertRow++;
						}
					
				}}}
			  
				}
				else
					break;
				
				row1=productIDsh.createRow((short) instertRow);
				instertRow++;
					
			}
			
			wb1.write(fileOut);
			
		}
		
	}

	public static HashMap<String, String> tocGeneration(String productID) throws IOException
  {
		HashMap<String, String> hm=new HashMap<String, String>();
		File file=new File("C:\\automationMetadata\\excelMetadata\\"+productID+".xlsx");
		FileInputStream fls=new FileInputStream(file);
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
			hm.put(a,b);
	}
	  return hm;
  }
	
}
