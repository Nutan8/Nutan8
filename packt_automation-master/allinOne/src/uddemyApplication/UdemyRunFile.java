package uddemyApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import udemyLive.UdemyUploads;

public class UdemyRunFile {
	static HashMap<String,String> titles=new HashMap<String,String>();  
	static HashMap<String,String> dataDetails=new HashMap<String,String>();  
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UdemyRunFile window = new UdemyRunFile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UdemyRunFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 348);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton runScript = new JButton("Run Script");
		runScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 UdemyUploads uu=new UdemyUploads();
				
					try {
						uu.udemyLogin();
						
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						
					}
					try {
						UdemyRunFile.getFileInfo();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						
					}
				 // call method for udemy login 
				
					
				 for(int i=0;i<=titles.size();i++)
				 {
					 if(titles.get("Title "+i)!=null)
					 {
						try {
							uu.uploadsVideo(titles.get("Title "+i));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							
						}
						
						 System.out.println("****************** complete courese :"+ titles.get("Title "+i) +" *****************");
					 }
					 
				 }
				
			}
		});
		runScript.setBackground(Color.GREEN);
		runScript.setBounds(116, 108, 114, 45);
		frame.getContentPane().add(runScript);
	}
	
	//************** file information fetch from excel files ******************************
	
	 protected static void getFileInfo() throws IOException   // getting TOC of the title
	 {
			File file=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\Udemy Automation\\Udemy-Automation-title-list.xlsx");
			FileInputStream fls=new FileInputStream(file);
			@SuppressWarnings("resource")
			XSSFWorkbook wb=new XSSFWorkbook(fls);
			XSSFSheet sheet1= wb.getSheetAt(0);
			int rownum=sheet1.getLastRowNum();
			
			DataFormatter formatter = new DataFormatter();
			for(int i=1;i<=rownum;i++)
			{
				// for put coloum 1 value to hash map
				String kk=formatter.formatCellValue(sheet1.getRow(i).getCell(0));
				// for puting coloum 2 value to hash map
				String kk1=formatter.formatCellValue(sheet1.getRow(i).getCell(1));				
				String a=kk;
				String b=kk1;
				// maping the excel value to hash map
			titles.put(a,b);
		}
				
	 }
	 // *************** for testing generated the TOC of the Titles ***********************************
	 protected static void getFileInfo(String s) throws IOException   // getting TOC of the title
	 {
			File file=new File("\\\\192.168.0.200\\Products\\Work\\Dynamic\\Video Distribution Project\\Udemy Automation\\"+s+".xlsx");
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
				dataDetails.put(a,b);
		}
				
	 }
}
