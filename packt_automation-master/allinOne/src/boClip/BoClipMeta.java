package boClip;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BoClipMeta {

	static HashMap<String,String> toc=new HashMap<String,String>(); 
	private JFrame frame;
	private JTextField productId;
	private JTextField videoPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoClipMeta window = new BoClipMeta();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 428, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(35, 32, 85, 25);
		frame.getContentPane().add(lblProductId);
		
		productId = new JTextField();
		productId.setBounds(144, 31, 143, 24);
		frame.getContentPane().add(productId);
		productId.setColumns(10);
		
		JLabel lblVideosPath = new JLabel("Videos path");
		lblVideosPath.setBounds(35, 92, 89, 24);
		frame.getContentPane().add(lblVideosPath);
		
		videoPath = new JTextField();
		videoPath.setBounds(144, 92, 143, 25);
		frame.getContentPane().add(videoPath);
		videoPath.setColumns(10);
		
		JButton btnGenerate = new JButton("Generate ");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 if(productId.getText().toString().length()==6)
				 {
			
					try {
						getData(productId.getText());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
				try {
					try {
						createMetadata(productId.getText().toString(),videoPath.getText().toString());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				 else
					{
						JOptionPane.showMessageDialog(null,"Fields are should not blank or enter correct fields");
					}
			}
			
		});
		btnGenerate.setBounds(150, 146, 108, 36);
		frame.getContentPane().add(btnGenerate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				productId.setText("");
				videoPath.setText("");
				
			}
		});
		btnClear.setBounds(150, 218, 108, 36);
		frame.getContentPane().add(btnClear);
	}
	
	
	 protected static void getData(String Title) throws IOException   // getting TOC of the title
	 {
		 File file=new File("C:\\automationMetadata\\excelMetadata\\"+Title+".xlsx");
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
	 
	 
	protected static void copyFileUsing(File source, File dest,String s)
		        throws IOException {
		    //FileUtils.copyFile(source, dest);
		    File from=new File("C:\\automationMetadata\\Automation NecessaryData\\BO clip\\BoCLipSample.xlsx");
		    File to=new File("C:\\automationMetadata\\Automation NecessaryData\\BO clip\\"+s+".xlsx");
		    from.renameTo(to);
		    
		}

	 
	protected static void createMetadata(String productId,String videoPath) throws IOException, InterruptedException
	 {
		File source=new File("C:\\automationMetadata\\Automation NecessaryData\\BO clip\\Bo clip sample metadata\\BoCLipSample.xlsx");
		File dist=new File("C:\\automationMetadata\\Automation NecessaryData\\BO clip\\BoCLipSample.xlsx");
		copyFileUsing(source,dist,productId);
		
		Thread.sleep(6000);
		String docLoc="C:\\automationMetadata\\Automation NecessaryData\\BO clip\\"+productId+".xlsx";
		File file=new File(docLoc);
		FileInputStream fls=new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb=new XSSFWorkbook(fls);
		XSSFSheet sheet1= wb.getSheetAt(0);
		 XSSFRow row1;
		FileOutputStream fileOut = new FileOutputStream(docLoc);
		 int row=1;
		 for(int i=1;i<=100;i++)
			{ 
				if(toc.get("Section "+i)==null)
				{
					break;
				}
				else
				{
					
					row1=sheet1.createRow((short) row);
					row1.createCell(0).setCellValue("Section "+i);
					row1.createCell(1).setCellValue(toc.get("Section "+i));
					row1.createCell(3).setCellValue(toc.get("Inner section "+i+":1"));
				
				for(int j=1;j<=100;j++)
				{
					
					if(toc.get(i+"."+j)==null)
					{
					break;
					}
					else
					{
						row=row+1;
						row1=sheet1.createRow((short) row);
						row1.createCell(0).setCellValue("Lecture "+i+"."+j);
						row1.createCell(1).setCellValue(toc.get(i+"."+j));
						row1.createCell(2).setCellValue("video"+i+"_"+j);
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
						data.append("\n"+toc.get("Inner section "+i+":1"));
						row1.createCell(3).setCellValue(data.toString());
					}
					
					
									
					
					String vPath = videoPath+"video"+i+"_"+j+".mp4";
					/*IContainer container = IContainer.make();
					int result = container.open(vPath, IContainer.Type.READ, null);
					if (result<0)	{					 
						throw new RuntimeException("Failed to open media file");}
					else
					{
					  long duration = container.getDuration();
						row1.createCell(4).setCellValue(duration);
					  }*/
					
					
					
					//row1.createCell(4).setCellValue(vPath);
					}
				
				
				
				}
				
				row++;
			}
				
	 }
		 
		 //FileOutputStream fileOut = new FileOutputStream(docLoc);
		 wb.write(fileOut);
		 wb.close();
		 
		  
	 }
}

