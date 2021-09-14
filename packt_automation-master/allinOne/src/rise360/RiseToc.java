package rise360;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.commons.math3.stat.regression.RegressionResults;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import rise360.UploadContent;
import javax.swing.JTextArea;
public class RiseToc {

	private JFrame frame;
	private JTextField textUserName;
	private JTextField textProductId;
	private JTextField textVideoPath;
	String result="";
	private JTextField sectionNo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RiseToc window = new RiseToc();
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
	public RiseToc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 627, 614);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setForeground(new Color(0, 51, 204));
		lblUserName.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 15));
		lblUserName.setBounds(38, 30, 121, 35);
		frame.getContentPane().add(lblUserName);
		
		textUserName = new JTextField();
		textUserName.setBounds(191, 36, 230, 35);
		frame.getContentPane().add(textUserName);
		textUserName.setColumns(10);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setForeground(new Color(0, 51, 204));
		lblProductId.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 15));
		lblProductId.setBounds(38, 89, 91, 16);
		frame.getContentPane().add(lblProductId);
		
		textProductId = new JTextField();
		textProductId.setBounds(191, 82, 121, 35);
		frame.getContentPane().add(textProductId);
		textProductId.setColumns(10);
		
		JLabel lblVideoPath = new JLabel("Video Path");
		lblVideoPath.setForeground(new Color(0, 51, 204));
		lblVideoPath.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 15));
		lblVideoPath.setBounds(38, 128, 91, 29);
		frame.getContentPane().add(lblVideoPath);
		
		textVideoPath = new JTextField();
		textVideoPath.setBounds(191, 128, 281, 32);
		frame.getContentPane().add(textVideoPath);
		textVideoPath.setColumns(10);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 254, 601, 310);
		frame.getContentPane().add(textArea);
		
		
		JLabel lblSectionNo = new JLabel("Section No.");
		lblSectionNo.setForeground(new Color(0, 51, 204));
		lblSectionNo.setFont(new Font("Century Schoolbook", Font.BOLD | Font.ITALIC, 15));
		lblSectionNo.setBounds(334, 131, 91, 16);
		frame.getContentPane().add(lblSectionNo);
		
		sectionNo = new JTextField();
		sectionNo.setColumns(10);
		sectionNo.setBounds(437, 82, 121, 35);
		frame.getContentPane().add(sectionNo);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UploadContent run=new UploadContent(); // calling the content class to upload the content
				try {
					int sec=Integer.parseInt(sectionNo.getText());
					 result=run.uplodContentRun(textUserName.getText().trim(),textProductId.getText().trim(),textVideoPath.getText().trim(),sec);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					result=e.getMessage();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					result=e.getMessage();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					result=e.getMessage();
				}
				
				System.out.println(result);
				textArea.setText(result);
			}
			
		});
		btnUpload.setForeground(new Color(0, 153, 204));
		btnUpload.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 16));
		btnUpload.setBounds(219, 182, 189, 44);
		frame.getContentPane().add(btnUpload);
		
		
	
		
		
		
	}
}
