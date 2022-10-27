import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Button;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

public class MainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
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
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 754, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(121, 219, 483, 180);
		frame.getContentPane().add(textArea);
		
		JButton button = new JButton("Choose Java File");
		button.setBounds(256, 123, 201, 41);
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("File Report");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(295, 183, 125, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Please Select a JavaFile to be analyzed");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(142, 38, 462, 41);
		frame.getContentPane().add(lblNewLabel_1);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView());
				j.showSaveDialog(null);
				// Open the save dialog
				
				File f = j.getSelectedFile();
				JavaFile file = new JavaFile(f);
				textArea.setText(file.generateReport());
			}
			
		});
	}
}
