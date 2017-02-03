package JdbcGui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ConnectDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUserName;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnectDialog dialog = new ConnectDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConnectDialog() {
		setTitle("Connection Panel");
		setBounds(100, 100, 450, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labPicture = new JLabel("");
		labPicture.setIcon(new ImageIcon("C:\\Users\\john\\workspace\\JdbcT\\bin\\Groups-Meeting-Dark-icon.png"));
		labPicture.setBounds(27, 23, 73, 61);
		contentPanel.add(labPicture);
		
		JLabel labUserName = new JLabel("User name:");
		labUserName.setForeground(Color.BLUE);
		labUserName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		labUserName.setBounds(121, 23, 107, 28);
		contentPanel.add(labUserName);
		
		JLabel lasPass = new JLabel("Password:");
		lasPass.setForeground(Color.BLUE);
		lasPass.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lasPass.setBounds(121, 56, 107, 28);
		contentPanel.add(lasPass);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		txtUserName.setForeground(Color.DARK_GRAY);
		txtUserName.setBounds(230, 29, 167, 20);
		contentPanel.add(txtUserName);
		txtUserName.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.DARK_GRAY);
		textField_1.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(230, 62, 167, 20);
		contentPanel.add(textField_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOK = new JButton("Connect");
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							ConnectionPanel askPanel = new ConnectionPanel();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				btnOK.setForeground(Color.BLUE);
				btnOK.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			{
				JButton btnExit = new JButton("Exit");
				btnExit.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				btnExit.setForeground(Color.DARK_GRAY);
				btnExit.setActionCommand("Cancel");
				buttonPane.add(btnExit);
			}
		}
	}
}
