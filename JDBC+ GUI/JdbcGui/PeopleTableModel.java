package JdbcGui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PeopleTableModel extends AbstractTableModel {

	private static final int NAME = 0;
	private static final int LAST_NAME = 1;
	private static final int EMAIL = 2;

	private String[] columnNumbers = {"Name", "Last Name", "Email" };
	private List<People> peopleList = null;

	public PeopleTableModel(List<People> getThisList) {

		this.peopleList = getThisList;
	}

	@Override
	public int getColumnCount() {
		return columnNumbers.length;
	}

	@Override
	public int getRowCount() {
		return peopleList.size();
	}

	public String getColumnName(int index) {
		return columnNumbers[index];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		People people = peopleList.get(rowIndex);

		switch (columnIndex) {
		case NAME:
			return people.getName();
		case LAST_NAME:
			return people.getLast_name();
		case EMAIL:
			return people.getEmail();
		default:
			return people.getName();
		}
	}

	public Class getColumnClass(int i) {
		return getValueAt(0, i).getClass();
	}

}
