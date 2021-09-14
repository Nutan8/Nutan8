package rps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtilsCopyDirectoryToDirectoryTestCase;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fileRename.FileReName;

public class FileCollection {

	
	public static void main(String[] args) throws IOException
	{
		String docLoc="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem\\VideoInfo.xlsx";
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
		for(int i=329;i<=rownum;i++)
		{
			 productID=formatter.formatCellValue(sheet1.getRow(i).getCell(0)).trim();
			 videoPath=formatter.formatCellValue(sheet1.getRow(i).getCell(3)).trim();
			 printIsbn=formatter.formatCellValue(sheet1.getRow(i).getCell(1)).trim();
			 File source=new File(videoPath);
			 File dest=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem"+"\\"+productID+"\\");
			//System.out.println(VideoFileCopy(videoPath, printIsbn, productID));
			 destDir="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem"+"\\"+productID+"\\";
		FileCollection fileDemo = new FileCollection();
	           fileDemo.copydir(source, dest);
	            System.out.println("Copied successfully.");
	            
	        // System.out.println(fileRenameing(destDir,printIsbn));
	            System.out.println(i);       
		}
	}
	
	
	
	
	
	static String VideoFileCopy(String path,String printIsbn, String productID) throws IOException
	{
		String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem"+"\\"+productID+"\\";
		File source = new File(path);
		File dest = new File(filePath);
		 //FileUtils.copyFileToDirectory(source, dest );
		return "File placed";
		 
	}
	
	
	
	static String CodeFileCopy(String path,String printIsbn, String productID)
	{
		String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem"+"\\"+productID+"\\";
		File source = new File(path);
		File dest = new File(filePath);
		 try {
			FileUtils.copyFileToDirectory(source, dest );
			return "File placed";
		} catch (IOException e) {
				return e.toString();
			
		}
		 
	}
	
	
	static String AssessmentFileCopy(String path,String printIsbn, String productID)
	{
		String filePath="\\\\192.168.0.200\\Products\\Work\\Dynamic\\Channel\\RPS\\Mar - 2021\\Prem"+"\\"+productID+"\\";
		File source = new File(path);
		File dest = new File(filePath);
		 try {
			FileUtils.copyFileToDirectory(source, dest );
			return "File placed";
		} catch (IOException e) {
				return e.toString();
			
		}
		 
	}
	
	
	
	
	 public void copydir(File src, File dest) throws IOException
	    {

	        if (src.isDirectory())
	        {

	            // if directory not exists, create it
	            if (!dest.exists())
	            {
	                dest.mkdir();
	                System.out.println("Directory copied from " + src + "  to "
	                        + dest);
	            }

	            // list all the directory contents
	            String files[] = src.list();

	            for (String fileName : files)
	            {
	                // construct the src and dest file structure
	                File srcFile = new File(src, fileName);
	                File destFile = new File(dest, fileName);
	                // recursive copy
	                copydir(srcFile, destFile);
	            }

	        }
	        else
	        {
	            // If file, then copy it
	            fileCopy(src, dest);
	        }
	    }

	    private void fileCopy(File src, File dest)
	            throws FileNotFoundException, IOException
	    {

	        InputStream in = null;
	        OutputStream out = null;

	        try
	        {
	            // If file, then copy it
	            in = new FileInputStream(src);
	            out = new FileOutputStream(dest);

	            byte[] buffer = new byte[10240000];

	            int length;
	            // Copy the file content in bytes
	            while ((length = in.read(buffer)) > 0)
	            {
	                out.write(buffer, 0, length);
	            }

	        }
	        finally
	        {
	            if (in != null)
	            {
	                in.close();
	            }
	            if (out != null)
	            {
	                out.close();
	            }
	        }
	        System.out.println("File copied from " + src + " to " + dest);
	    }
	    
	    public static String fileRenameing(String path, String iSBN)
	    {
	    	String absolutePath = path;
	        File dir = new File(absolutePath);
	        File[] filesInDir = dir.listFiles();
	        int i = 0;
	        for(File file:filesInDir) {
	            i++;
	            String name = file.getName();
	            String newName = iSBN+"-"+name;
	            String newPath = absolutePath + "\\" + newName;
	            file.renameTo(new File(newPath));
	            return name + " changed to " + newName;
	    }
	        return "File renamed";
	    }
	
}
