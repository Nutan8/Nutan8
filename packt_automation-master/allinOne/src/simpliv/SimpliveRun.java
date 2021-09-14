package simpliv;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.x2006.encryption.CTKeyEncryptor.Uri;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class SimpliveRun {

	public JFrame frame;
	private JTextField excelPath;
	private JTextField textUserName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpliveRun window = new SimpliveRun();
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
	public SimpliveRun() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 461, 758);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(new Color(0, 51, 204));
		lblUserName.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 15));
		lblUserName.setBounds(38, 72, 121, 35);
		frame.getContentPane().add(lblUserName);
		
		textUserName = new JTextField();
		textUserName.setBounds(140, 78, 230, 35);
		frame.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		
		excelPath = new JTextField();
		excelPath.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		excelPath.setBounds(50, 118, 349, 45);
		frame.getContentPane().add(excelPath);
		excelPath.setColumns(10);
		
		JTextArea ResultDetails = new JTextArea();
		ResultDetails.setBounds(10, 250, 425, 504);
		frame.getContentPane().add(ResultDetails);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBackground(new Color(0, 153, 153));
		btnRun.setForeground(new Color(0, 153, 255));
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			DataExtact de=new DataExtact();
			
			File file=new File(excelPath.getText()+".xlsx");
			try {
				FileInputStream fls=new FileInputStream(file);
				XSSFWorkbook wb=new XSSFWorkbook(fls);

				XSSFSheet sheet1= wb.getSheetAt(0);

				int rownum=sheet1.getLastRowNum();
				DataFormatter formatter = new DataFormatter();
				
				for(int i=1;i<rownum;i++)

				{	
					

					if(wb.getSheet(formatter.formatCellValue(sheet1.getRow(i).getCell(0)))!=null)
					{
						String url=formatter.formatCellValue(sheet1.getRow(i).getCell(1));
						String requiredsheet=formatter.formatCellValue(sheet1.getRow(i).getCell(0));
					XSSFSheet sheet2=wb.getSheet(requiredsheet.trim());
					int titleRowNum=sheet2.getLastRowNum();
					System.out.println(titleRowNum);
					for(int j=1;j<titleRowNum;j++)
					{
					String kk=formatter.formatCellValue(sheet2.getRow(j).getCell(0));// for put coloum 1 value to hash map
				
					String kk1=formatter.formatCellValue(sheet2.getRow(j).getCell(1));		// for puting coloum 2 value to hash map		

					String productId=kk.trim();
					String videoPath=kk1.trim();
					de.uploadSection(j,productId,videoPath,url.trim(),textUserName.getText().toString().trim());
			}
			}
				}
			}
			
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ResultDetails.setText(e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ResultDetails.setText(e.getMessage());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ResultDetails.setText(e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//ResultDetails.setText(e.getMessage());
			}
			}
			
		});
		btnRun.setFont(new Font("MV Boli", Font.BOLD, 17));
		btnRun.setBounds(114, 180, 169, 53);
		frame.getContentPane().add(btnRun);
		
		JLabel lblExcelFilePath = new JLabel("Excel file path with file name");
		lblExcelFilePath.setForeground(new Color(0, 51, 255));
		lblExcelFilePath.setFont(new Font("MV Boli", Font.BOLD, 17));
		lblExcelFilePath.setBounds(87, 11, 288, 40);
		frame.getContentPane().add(lblExcelFilePath);
		
	
	
	}
}
