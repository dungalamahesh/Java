package JdbcGui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PeopleTable extends JFrame {

	private JPanel contentPane;
	private JTextField txtEnterNameField;
	private ADO adoPeople;
	private JTable tlbPeople;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PeopleTable frame = new PeopleTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public PeopleTable() throws SQLException {

		try {
			adoPeople = new ADO();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Some error: " + e.getMessage(), "SQL Error!!",
					JOptionPane.ERROR_MESSAGE);
		}

		setTitle("People search app");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel labEnterName = new JLabel("Enter a name: ");
		labEnterName.setForeground(Color.BLUE);
		labEnterName.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		labEnterName.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labEnterName);

		txtEnterNameField = new JTextField();
		txtEnterNameField.setForeground(SystemColor.textHighlight);
		txtEnterNameField.setFont(new Font("Comic Sans MS", Font.ITALIC, 17));
		panel.add(txtEnterNameField);
		txtEnterNameField.setColumns(20);

		JButton btnEnterName = new JButton("Search");
		btnEnterName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String name = txtEnterNameField.getText();
				List<People> peopleList = null;

				try {
					if (name != null && name.trim().length() > 0) {
						peopleList = adoPeople.searchForPeople(name);
						/*
						 * for ( People tempP : peopleList )
						 * System.out.println(tempP);
						 */
						PeopleTableModel tableModel = new PeopleTableModel(peopleList);
						tlbPeople.setModel(tableModel);
					} else {
						peopleList = adoPeople.getAllPeople();
						PeopleTableModel tableModel = new PeopleTableModel(peopleList);
						tlbPeople.setModel(tableModel);
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Some error: " + e.getMessage(), "SQL Error!!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEnterName.setForeground(new Color(0, 128, 0));
		btnEnterName.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		panel.add(btnEnterName);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPeople addPeople = new AddPeople(PeopleTable.this, adoPeople, null, false);
				addPeople.setVisible(true);
			}
		});
		btnAdd.setForeground(new Color(65, 105, 225));
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		panel.add(btnAdd);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tlbPeople.getSelectedRow();
				int cols = tlbPeople.getSelectedColumn();
				final int ZERO = 0, ONE = 1, TWO = 2;

				if (row < 0) {
					JOptionPane.showMessageDialog(null, "This row isn't available!", "Error Point",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				People selectedPeople = null;
				List<People> tempPeompe = null;
				String value = tlbPeople.getValueAt(row, cols).toString();
				try {
					tempPeompe = adoPeople.searchForPeople(tlbPeople.getValueAt(row, 0).toString());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				selectedPeople = new People(tempPeompe.get(0).getId(), tlbPeople.getValueAt(row, 0).toString(),
						tlbPeople.getValueAt(row, 1).toString(), tlbPeople.getValueAt(row, 2).toString());

				AddPeople addSpecificPeople = new AddPeople(PeopleTable.this, adoPeople, selectedPeople, true);
				addSpecificPeople.setVisible(true);
			}
		});
		btnUpdate.setForeground(new Color(128, 0, 128));
		btnUpdate.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		panel.add(btnUpdate);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tlbPeople = new JTable();
		List<People> lst_People = adoPeople.getAllPeople();
		PeopleTableModel tableModel = new PeopleTableModel(lst_People);
		tlbPeople.setModel(tableModel);
		tlbPeople.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		scrollPane.setViewportView(tlbPeople);
	}

	public void refreshPeopleview() throws SQLException {

		List<People> lstPeople = adoPeople.getAllPeople();
		PeopleTableModel tableModel = new PeopleTableModel(lstPeople);
		tlbPeople.setModel(tableModel);
	}
}
