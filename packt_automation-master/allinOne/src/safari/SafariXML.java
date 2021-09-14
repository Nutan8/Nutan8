package safari;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SafariXML {

	private JFrame frame;
	private JTextField titleIDTextBox;
	private JTextField userNameTextBox;
	String res="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SafariXML window = new SafariXML();
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
	public SafariXML() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 505,683);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userNameTextBox = new JTextField();
		userNameTextBox.setBounds(115, 15, 133, 36);
		frame.getContentPane().add(userNameTextBox);
		userNameTextBox.setColumns(10);
		
		titleIDTextBox = new JTextField();
		titleIDTextBox.setBounds(115, 76, 133, 36);
		frame.getContentPane().add(titleIDTextBox);
		titleIDTextBox.setColumns(10);
			
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 154, 470, 480);
		frame.getContentPane().add(scrollPane_1);
		
		JTextArea ResultDetails = new JTextArea();
		scrollPane_1.setViewportView(ResultDetails);
		
		
		JLabel username = new JLabel("UserName");
		username.setFont(new Font("Segoe UI Symbol", Font.BOLD, 15));
		username.setBounds(10, 15, 107, 42);
		frame.getContentPane().add(username);
		
		JLabel lblVideoId = new JLabel("Video Id");
		lblVideoId.setFont(new Font("Segoe UI Symbol", Font.BOLD, 25));
		lblVideoId.setBounds(10, 76, 107, 42);
		frame.getContentPane().add(lblVideoId);
		
		JButton btnGenetate = new JButton("Generate");
		btnGenetate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titleId=titleIDTextBox.getText();
				MetaCrea mc=new MetaCrea();
				try {
					res=mc.Meta(titleId,userNameTextBox.getText());
					
					ResultDetails.setText(res);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnGenetate.setFont(new Font("Kokila", Font.PLAIN, 14));
		btnGenetate.setBounds(266, 33, 101, 43);
		frame.getContentPane().add(btnGenetate);
		
		JButton ClearTextAreA = new JButton("Clear");
		ClearTextAreA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultDetails.setText("");
				titleIDTextBox.setText("");
			}
		});
		ClearTextAreA.setBounds(377, 33, 106, 42);
		frame.getContentPane().add(ClearTextAreA);
		
		
		
		
		
		
		
		
		
		
		

		
	}
}
