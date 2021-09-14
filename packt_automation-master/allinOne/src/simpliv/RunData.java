package simpliv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RunData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DataExtact de=new DataExtact();
		String[] cars = {"V06294", "V06330", "V06953", "V07149"};
		
		File file=new File("C:\\automationMetadata\\Automation NecessaryData\\simpliv\\SimpliV Automation.xlsx");
		FileInputStream fls=new FileInputStream(file);

		XSSFWorkbook wb=new XSSFWorkbook(fls);

		XSSFSheet sheet1= wb.getSheetAt(0);

		//XSSFSheet sheet1=wb.getSheet("0");
		int rownum=sheet1.getLastRowNum();
		DataFormatter formatter = new DataFormatter();
		HashMap<String,String> hm=new HashMap<String,String>();
		System.out.println(rownum);
		for(int i=1;i<rownum;i++)

		{		
			
			if(wb.getSheet(formatter.formatCellValue(sheet1.getRow(i).getCell(0)))!=null)
			{
				//String url=formatter.formatCellValue(sheet1.getRow(i).getCell(1));
				String requiredsheet=formatter.formatCellValue(sheet1.getRow(i).getCell(0));
			XSSFSheet sheet2=wb.getSheet(requiredsheet.trim());
			int titleRowNum=sheet2.getLastRowNum();
			System.out.println(titleRowNum);
			for(int j=5;j<titleRowNum;j++)
			{
			String kk=formatter.formatCellValue(sheet2.getRow(j).getCell(0));// for put coloum 1 value to hash map
		
			String kk1=formatter.formatCellValue(sheet2.getRow(j).getCell(1));		// for puting coloum 2 value to hash map		

			String productId=kk.trim();
			String videoPath=kk1.trim();
			//System.out.println("product id :"+productId+"  videoPath : "+videoPath+"website link : "+url);
			de.uploadSection(j,productId,videoPath,"https://www.simpliv.com/courses/learning-path-from-python-programming-to-data-science/edit?step=2","premachandras");
			}
			}
			
			//de.uploadSection(i,productId,videoPath,"https://www.simpliv.com/courses/prem-testing-title-demo/edit/?step=2");
	}
		
	}

}
