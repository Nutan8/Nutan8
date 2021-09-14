package simplivFTM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class FtmRun {

	private JFrame frame;
	private JTextField userName;
	private JTextField productId;
	private JTextField CourseUrl;
	private JTextField videoPath;
	private JTextField sectionId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FtmRun window = new FtmRun();
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
	public FtmRun() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 448, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userName = new JTextField();
		userName.setForeground(new Color(0, 51, 153));
		userName.setFont(new Font("Sitka Small", Font.ITALIC, 12));
		userName.setBounds(133, 26, 181, 30);
		frame.getContentPane().add(userName);
		userName.setColumns(10);
		
		productId = new JTextField();
		productId.setForeground(new Color(0, 51, 153));
		productId.setFont(new Font("Sitka Small", Font.ITALIC, 12));
		productId.setBounds(133, 76, 181, 30);
		frame.getContentPane().add(productId);
		productId.setColumns(10);
		
		CourseUrl = new JTextField();
		CourseUrl.setForeground(new Color(0, 51, 153));
		CourseUrl.setFont(new Font("Sitka Small", Font.ITALIC, 12));
		CourseUrl.setBounds(133, 117, 289, 30);
		frame.getContentPane().add(CourseUrl);
		CourseUrl.setColumns(10);
		
		videoPath = new JTextField();
		videoPath.setForeground(new Color(0, 51, 153));
		videoPath.setFont(new Font("Sitka Small", Font.ITALIC, 12));
		videoPath.setBounds(133, 166, 289, 30);
		frame.getContentPane().add(videoPath);
		videoPath.setColumns(10);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setForeground(new Color(0, 0, 204));
		lblUsername.setFont(new Font("Perpetua", Font.BOLD | Font.ITALIC, 15));
		lblUsername.setBounds(25, 26, 83, 30);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblProductId = new JLabel("Product Id");
		lblProductId.setForeground(new Color(0, 0, 204));
		lblProductId.setFont(new Font("Perpetua", Font.BOLD | Font.ITALIC, 15));
		lblProductId.setBounds(25, 84, 91, 25);
		frame.getContentPane().add(lblProductId);
		
		JLabel lblCourseUrl = new JLabel("Course Url");
		lblCourseUrl.setForeground(new Color(0, 0, 204));
		lblCourseUrl.setFont(new Font("Perpetua", Font.BOLD | Font.ITALIC, 15));
		lblCourseUrl.setBounds(25, 125, 98, 21);
		frame.getContentPane().add(lblCourseUrl);
		
		JLabel lblVideoPath = new JLabel("Video Path");
		lblVideoPath.setForeground(new Color(0, 0, 204));
		lblVideoPath.setFont(new Font("Perpetua", Font.BOLD | Font.ITALIC, 15));
		lblVideoPath.setBounds(25, 169, 92, 24);
		frame.getContentPane().add(lblVideoPath);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setForeground(new Color(0, 0, 255));
		lblSection.setFont(new Font("Lucida Console", Font.BOLD | Font.ITALIC, 14));
		lblSection.setBounds(25, 204, 71, 25);
		frame.getContentPane().add(lblSection);
		
		JTextArea outpurt = new JTextArea();
		outpurt.setBounds(10, 309, 412, 168);
		frame.getContentPane().add(outpurt);
		
		
		sectionId = new JTextField();
		sectionId.setForeground(new Color(0, 51, 153));
		sectionId.setFont(new Font("Sitka Small", Font.ITALIC, 12));
		sectionId.setColumns(10);
		sectionId.setBounds(133, 207, 71, 30);
		frame.getContentPane().add(sectionId);
		
		
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				contentUpload cu=new contentUpload();
				int sectNum=Integer.parseInt(sectionId.getText().toString());
				System.out.println(sectNum);
					try {
						cu.uploadSection(productId.getText().toString().trim(), videoPath.getText().toString().trim(), CourseUrl.getText().toString().trim(),userName.getText().toString().trim(),sectNum);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						outpurt.setText(e1.getMessage());
						System.out.println(e1.getMessage());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						outpurt.setText(e1.getMessage());
						System.out.println(e1.getMessage());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						outpurt.setText(e1.getMessage());
						System.out.println(e1.getMessage());
					}
			
			
			}
		});
		btnUpload.setForeground(new Color(102, 102, 204));
		btnUpload.setFont(new Font("Microsoft Tai Le", Font.BOLD | Font.ITALIC, 16));
		btnUpload.setBounds(169, 261, 133, 37);
		frame.getContentPane().add(btnUpload);
		
		
		
		
		
		
		
	}
}
