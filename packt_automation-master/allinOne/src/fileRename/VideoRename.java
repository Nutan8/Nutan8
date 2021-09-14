package fileRename;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class VideoRename {

	public static void main(String[] args) throws IOException
	{
		String docLoc="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem\\VideoInfo1.xlsx";
		File fileDocLoc=new File(docLoc);
		FileInputStream fls=new FileInputStream(fileDocLoc);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(0);
		int rownum=sheet1.getLastRowNum();
		DataFormatter formatter = new DataFormatter();
		String printIsbn="";
		String productID="";
		String videoPath="";
		String destDir="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem"+"\\"+productID+"\\";
		for(int i=299;i<=321;i++)
		{
			 productID=formatter.formatCellValue(sheet1.getRow(i).getCell(0)).trim();
			 videoPath=formatter.formatCellValue(sheet1.getRow(i).getCell(3)).trim();
			 printIsbn=formatter.formatCellValue(sheet1.getRow(i).getCell(1)).trim();
			 destDir="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem"+"\\"+productID+"\\";
			 System.out.println(videoRename(destDir,printIsbn));
		}
	}
	
	
	public static String videoRename(String path,String isbn1)
	{
		File titleDest=new File(path);
		File[] filesInDir = titleDest.listFiles();
        int i = 0;
        for(File file:filesInDir) {
            i++;
            String name = file.getName();
            name=name.toLowerCase();
            String newName = isbn1+"-"+name;
            String newPath = titleDest + "\\" + newName;
            file.renameTo(new File(newPath));
	}
        return "video renamed for "+isbn1;
	}
	
}
