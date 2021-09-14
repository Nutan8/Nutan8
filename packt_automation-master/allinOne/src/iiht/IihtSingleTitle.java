package iiht;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IihtSingleTitle {

	public static void main(String[] args) throws IOException {
		DataFormatter formatter = new DataFormatter();
		// TODO Auto-generated method stub
File file=new File("C:\\automationMetadata\\Automation NecessaryData\\IIHT\\Metadata for FTM.xlsx");
		
		FileInputStream fls=new FileInputStream(file);
		
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		
		XSSFSheet sheet1= wb.getSheetAt(0);
		XSSFRow row1;
		
		int numRow=sheet1.getLastRowNum();
		for(int i=1;i<=numRow;i++)
		{
			int addRow=0;
			//FileOutputStream fileOut = new FileOutputStream("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\IIHTFTM\\"+formatter.formatCellValue(sheet1.getRow(i).getCell(1))+".xlsx");
			FileOutputStream fileOut = new FileOutputStream("C:\\automationMetadata\\Automation NecessaryData\\IIHT\\metadataFTM\\"+formatter.formatCellValue(sheet1.getRow(i).getCell(1))+".xlsx");
			File file1=new File("C:\\automationMetadata\\Automation NecessaryData\\IIHT\\Necessary data\\metadata.xlsx");
			FileInputStream fls1=new FileInputStream(file1);
			XSSFWorkbook wb1=new XSSFWorkbook(fls1);
			XSSFSheet BasicDetails= wb1.getSheetAt(0);
			XSSFSheet MetadataDetails= wb1.getSheetAt(1);
			HashMap<String, String> tocOut=tocGeneration(sheet1.getRow(i).getCell(0).toString());
			
			
			row1=BasicDetails.createRow((short) 0);
			row1.createCell(0).setCellValue("Product ID");
			row1.createCell(1).setCellValue("ISBN");
			row1.createCell(2).setCellValue("Title Name");
			row1.createCell(3).setCellValue("Packt Metadescription");
			row1.createCell(4).setCellValue("Audience");
			row1.createCell(5).setCellValue("Requirement");
			row1.createCell(6).setCellValue("What you will learn");
//			row1.createCell(7).setCellValue("Description");
			row1.createCell(8).setCellValue("Clip Count");
			row1=BasicDetails.createRow((short) 1);
			row1.createCell(0).setCellValue(sheet1.getRow(i).getCell(0).toString());
			row1.createCell(1).setCellValue(formatter.formatCellValue(sheet1.getRow(i).getCell(1)));
			row1.createCell(2).setCellValue(sheet1.getRow(i).getCell(2).toString());
			row1.createCell(3).setCellValue(sheet1.getRow(i).getCell(4).toString());
			row1.createCell(4).setCellValue(sheet1.getRow(i).getCell(5).toString());
			row1.createCell(5).setCellValue(sheet1.getRow(i).getCell(6).toString());
			row1.createCell(6).setCellValue(sheet1.getRow(i).getCell(7).toString());
			row1.createCell(7).setCellValue(sheet1.getRow(i).getCell(8).toString());
			//row1.createCell(8).setCellValue(sheet1.getRow(i).getCell(0).toString());
			row1=MetadataDetails.createRow((short) addRow);
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
			addRow++;
			
			for(int section=1;section<=100;section++)
			{
				
				if(tocOut.get("Section "+section)==null)
					break;
				else
				{
				
					for(int subSec=1;subSec<=100;subSec++)
					{
						StringBuilder data=new StringBuilder();
						if(tocOut.get(section+"."+subSec)==null)
						{
						break;
						}						
						else
						{
							
							for(int clipDet=1;clipDet<=100;clipDet++)
							{
								if(tocOut.get("Clip Section "+section+"."+subSec+":"+clipDet)==null)
								{
									break;
								}
								else
								{
									if(clipDet==1)
									{
									data.append(tocOut.get("Clip Section "+section+"."+subSec+":"+clipDet));
									}
									else
									{
										data.append("\n   •  "+tocOut.get("Clip Section "+section+"."+subSec+":"+clipDet));
									}
								}
							
							}
							
						
						}
						
						
						row1=MetadataDetails.createRow((short) addRow);
						row1.createCell(0).setCellValue(formatter.formatCellValue(sheet1.getRow(i).getCell(1))+"-video"+section+"_"+subSec);
						System.out.println(formatter.formatCellValue(sheet1.getRow(i).getCell(1))+"-video"+section+"_"+subSec);
						row1.createCell(1).setCellValue(tocOut.get("Title Name"));
						System.out.println(tocOut.get("Title Name"));
						row1.createCell(2).setCellValue(tocOut.get("Section "+section));
						System.out.println(tocOut.get("Section "+section));
						row1.createCell(3).setCellValue(tocOut.get(section+"."+subSec));
						System.out.println(tocOut.get(section+"."+subSec));
						row1.createCell(4).setCellValue("Video");
						System.out.println();
						row1.createCell(5).setCellValue(sheet1.getRow(i).getCell(9).toString());
						System.out.println(sheet1.getRow(i).getCell(9).toString());
						row1.createCell(6).setCellValue(data.toString());
						System.out.println(data.toString());
						row1.createCell(7).setCellValue(sheet1.getRow(i).getCell(10).toString());
						System.out.println(sheet1.getRow(i).getCell(10).toString());
						row1.createCell(8).setCellValue(sheet1.getRow(i).getCell(11).toString());
						System.out.println(sheet1.getRow(i).getCell(11).toString());
						row1.createCell(9).setCellValue("");
						System.out.println();
						addRow++;
						System.out.println(addRow);
						
					}
				}
			
				
			}
			wb1.write(fileOut);
			
		}

	}
	
	
	
	//Create sheet 1;
	
	//Create Sheet 2
	
	//Create TOC
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
