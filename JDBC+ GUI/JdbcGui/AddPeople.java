package JdbcGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.PopupMenuListener;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class AddPeople extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtLastName;
	private JTextField txtEmail;

	private ADO ado;
	private PeopleTable searchPeople;
	private People firstPeople;
	private boolean updateMode = false;

	public AddPeople(PeopleTable searchPeople, ADO ado, People p, boolean updateMode) {
		this();
		this.ado = ado;
		this.searchPeople = searchPeople;
		this.firstPeople = p;
		this.updateMode = updateMode;
		if (updateMode) {
			setTitle("Update panel");
			txtName.setText(firstPeople.getName());
			txtLastName.setText(firstPeople.getLast_name());
			txtEmail.setText(firstPeople.getEmail());
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddPeople() {
		setTitle("Add new People");
		setBounds(100, 100, 450, 195);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel labFirstName = new JLabel("First name:");
			labFirstName.setBounds(11, 14, 80, 21);
			labFirstName.setForeground(Color.BLUE);
			labFirstName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(labFirstName);
		}
		{
			txtName = new JTextField();
			txtName.setBounds(97, 11, 332, 27);
			txtName.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			JLabel labLastName = new JLabel("Last name:");
			labLastName.setBounds(15, 47, 76, 21);
			labLastName.setForeground(Color.BLUE);
			labLastName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(labLastName);
		}
		{
			txtLastName = new JTextField();
			txtLastName.setBounds(97, 44, 332, 27);
			txtLastName.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
			txtLastName.setColumns(10);
			contentPanel.add(txtLastName);
		}
		{
			JLabel labEmail = new JLabel("Email:");
			labEmail.setBounds(49, 80, 42, 21);
			labEmail.setForeground(Color.BLUE);
			labEmail.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
			contentPanel.add(labEmail);
		}
		{
			txtEmail = new JTextField();
			txtEmail.setBounds(97, 77, 332, 27);
			txtEmail.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
			txtEmail.setColumns(10);
			contentPanel.add(txtEmail);
		}

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (updateMode)
						updateSpesificUser();
					else
						saveNewPeople();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setForeground(Color.BLUE);
		btnSave.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSave.setBounds(149, 122, 89, 23);
		contentPanel.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnCancel.setForeground(Color.RED);
		btnCancel.setBounds(248, 122, 89, 23);
		contentPanel.add(btnCancel);

	}

	private void saveNewPeople() throws SQLException {

		String name = txtName.getText();
		String lastName = txtLastName.getText();
		String email = txtEmail.getText();
		People tempPeople = null;
		People addThisPeople = null;

		if (updateMode) {
			txtName.setText(firstPeople.getName());
			txtLastName.setText(firstPeople.getLast_name());
			txtEmail.setText(firstPeople.getEmail());
		} else {
			addThisPeople = new People(null, name, lastName, email);
		}
		if (updateMode) {
			ado.updatePeople(tempPeople);
		} else {
			ado.addPeople(addThisPeople);
			setVisible(false);
			dispose(); // Releases all of the native screen resources used by
						// this
						// Window, its subcomponents, and all of its owned
						// children
		}
		searchPeople.refreshPeopleview();
		JOptionPane.showMessageDialog(null, "this Pople is added", "Add People Message",
				JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}

	private void updateSpesificUser() throws SQLException {

		String id = firstPeople.getId();
		String name = txtName.getText();
		String lastName = txtLastName.getText();
		String email = txtEmail.getText();
		People tempPeople = null;
		People updateThisPeople = null;

		People updateWithNewParameter = new People(id, name, lastName, email);
		ado.updatePeople(updateWithNewParameter);
		searchPeople.refreshPeopleview();
		JOptionPane.showMessageDialog(null, "this Pople is updated", "update People", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}
