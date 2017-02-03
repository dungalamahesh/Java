import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Color;

public class ChooseFile {

	private JFrame SelectFile;

	/**
	 * Launch the application.
	 */
	public void show(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseFile window = new ChooseFile();
					window.SelectFile.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChooseFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		SelectFile = new JFrame();
		SelectFile.getContentPane().setBackground(new Color(30, 144, 255));
		SelectFile.setBackground(new Color(30, 144, 255));
		SelectFile.setType(Type.POPUP);
		SelectFile.setTitle("Choose File");
		SelectFile.setBounds(100, 100, 551, 412);
		SelectFile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
