import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Window.Type;
import javax.swing.JTextArea;

public class ShowFileText {

	private JFrame frmFileT;
	private String contentsTextO = null;
	private String thisFileWeWorkOnO = null;
	
	public void showThisFileText() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowFileText window = new ShowFileText();
					window.frmFileT.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ShowFileText() {
		initialize(null, null);
	}
	
	public ShowFileText(String contentsText, String thisFileWeWorkOn) {
		initialize(contentsText, thisFileWeWorkOn);
	}
	
	
	private void initialize(String contentsText, String thisFileWeWorkOn) {
		frmFileT = new JFrame();
		frmFileT.setResizable(false);
		frmFileT.setType(Type.POPUP);
		frmFileT.getContentPane().setBackground(new Color(0, 191, 255));
		frmFileT.getContentPane().setLayout(null);
		
		JLabel lblContentsOfThe = new JLabel("Contents of the file:  ");
		lblContentsOfThe.setForeground(new Color(128, 0, 0));
		lblContentsOfThe.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 15));
		lblContentsOfThe.setBounds(52, 11, 220, 55);
		frmFileT.getContentPane().add(lblContentsOfThe);
		
		JTextArea txtarGetTextvalue = new JTextArea();
		txtarGetTextvalue.setForeground(Color.DARK_GRAY);
		txtarGetTextvalue.setFont(new Font("David", Font.BOLD, 15));
		txtarGetTextvalue.setBounds(10, 75, 343, 190);
		txtarGetTextvalue.append("" +contentsText);
		
		frmFileT.getContentPane().add(txtarGetTextvalue);
		frmFileT.setBackground(new Color(0, 191, 255));
		frmFileT.setTitle("File Contents ");
		frmFileT.setBounds(100, 100, 369, 321);
		frmFileT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
