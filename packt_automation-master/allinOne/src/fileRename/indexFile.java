package fileRename;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import fileRename.IndexFileRun;

public class indexFile {

	private JFrame frame;
	private JTextField textPath;
	private JTextField titleName;
	private JTextField fileName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					indexFile window = new indexFile();
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
	public indexFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 464, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDocumentPath = new JLabel("Document Path :");
		lblDocumentPath.setForeground(Color.BLUE);
		lblDocumentPath.setFont(new Font("Calibri Light", Font.BOLD, 17));
		lblDocumentPath.setBounds(10, 28, 144, 35);
		frame.getContentPane().add(lblDocumentPath);
		
		textPath = new JTextField();
		textPath.setBounds(149, 30, 268, 31);
		frame.getContentPane().add(textPath);
		textPath.setColumns(10);
		
		JLabel lblTitleName = new JLabel("Title Name :");
		lblTitleName.setForeground(Color.BLUE);
		lblTitleName.setFont(new Font("Calibri Light", Font.BOLD, 17));
		lblTitleName.setBounds(10, 155, 119, 25);
		frame.getContentPane().add(lblTitleName);
		
		titleName = new JTextField();
		titleName.setBounds(144, 152, 294, 31);
		frame.getContentPane().add(titleName);
		titleName.setColumns(10);
		
		JButton btnGenerate = new JButton("Run");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title=titleName.getText();
				String path=textPath.getText()+"\\"+fileName.getText();
				String path1=textPath.getText();
				System.out.println(path1);
				
				IndexFileRun ifx=new IndexFileRun();
				String output=ifx.runfile(title,path);
				//System.out.println(output);
				try
				{
					File newTextFile = new File(path1+"\\index.html");
					FileWriter fw = new FileWriter(newTextFile);
					fw.write(output);
				    fw.close();
				    JOptionPane.showMessageDialog(null,"File Created sucessfully");
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}
		});
		btnGenerate.setForeground(new Color(0, 100, 0));
		btnGenerate.setFont(new Font("Calibri Light", Font.BOLD, 22));
		btnGenerate.setBounds(55, 249, 122, 43);
		frame.getContentPane().add(btnGenerate);
		
		JLabel lblFileName = new JLabel("   File Name");
		lblFileName.setForeground(new Color(0, 0, 128));
		lblFileName.setFont(new Font("Calibri Light", Font.BOLD, 17));
		lblFileName.setBounds(12, 90, 128, 35);
		frame.getContentPane().add(lblFileName);
		
		fileName = new JTextField();
		fileName.setBounds(144, 90, 278, 34);
		frame.getContentPane().add(fileName);
		fileName.setColumns(10);
	}
}
