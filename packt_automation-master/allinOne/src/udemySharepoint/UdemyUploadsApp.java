package udemySharepoint;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UdemyUploadsApp {

	private JFrame frame;
	private JTextField prductId;
	private JTextField videoPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UdemyUploadsApp window = new UdemyUploadsApp();
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
	public UdemyUploadsApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 531, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProductId = new JLabel("Product ID");
		lblProductId.setBounds(42, 75, 46, 14);
		frame.getContentPane().add(lblProductId);
		
		JLabel lblVideoPath = new JLabel("Video path");
		lblVideoPath.setBounds(42, 134, 46, 14);
		frame.getContentPane().add(lblVideoPath);
		
		prductId = new JTextField();
		prductId.setBounds(153, 72, 86, 20);
		frame.getContentPane().add(prductId);
		prductId.setColumns(10);
		
		videoPath = new JTextField();
		videoPath.setBounds(153, 131, 86, 20);
		frame.getContentPane().add(videoPath);
		videoPath.setColumns(10);
		
		JButton btnRunScript = new JButton("Run Script");
		btnRunScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnRunScript.setBounds(179, 184, 89, 23);
		frame.getContentPane().add(btnRunScript);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 233, 495, 220);
		frame.getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
	}
}
