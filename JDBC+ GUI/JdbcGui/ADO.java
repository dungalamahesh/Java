package JdbcGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ADO {

	Connection myCon;

	public ADO() throws SQLException {

		myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "13245678");
	}

	public List<People> getAllPeople() throws SQLException {

		List<People> peopleList = new ArrayList<>();
		Statement stmPeople = null;
		String query = "SELECT * From login_users";
		ResultSet resultPeople;

		stmPeople = this.myCon.createStatement();
		resultPeople = stmPeople.executeQuery(query);

		while (resultPeople.next()) {

			peopleList.add(getSortPeople(resultPeople));
		}
		return peopleList;
	}

	public List<People> searchForPeople(String howSearch) throws SQLException {

		List<People> peopleList = new ArrayList<>();
		PreparedStatement prStmSearch = null;
		String query = "SELECT * FROM login_users WHERE name LIKE ?";
		ResultSet result = null;

		prStmSearch = this.myCon.prepareStatement(query);
		howSearch += "%";
		prStmSearch.setString(1, howSearch);

		result = prStmSearch.executeQuery();
		while (result.next()) {

			peopleList.add(getSortPeople(result));
		}

		return peopleList;
	}

	public void addPeople(People addThisPeople) {

		PreparedStatement prStm = null;
		try {
			prStm = this.myCon.prepareStatement("INSERT INTO login_users (name, last_name, email) VALUES (?, ?, ?)");
			prStm.setString(1, addThisPeople.getName());
			prStm.setString(2, addThisPeople.getLast_name());
			prStm.setString(3, addThisPeople.getEmail());

			prStm.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Opps some problem: " +e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void updatePeople(People updateThisPeople) throws SQLException {
		
		PreparedStatement prStm = null;
		
		prStm = myCon.prepareStatement("UPDATE login_users SET name= ?, last_name= ?, email= ? WHERE id = ?");

		prStm.setString(1, updateThisPeople.getName());
		prStm.setString(2, updateThisPeople.getLast_name());
		prStm.setString(3, updateThisPeople.getEmail());
		prStm.setString(4, updateThisPeople.getId());
		
		prStm.executeUpdate();
	}
	
	private People getSortPeople(ResultSet result) throws SQLException {

		String id = result.getString("id");
		String name = result.getString("name");
		String lastName = result.getString("last_name");
		String email = result.getString("email");
		People returnThisGay = new People(id, name, lastName, email);

		return returnThisGay;
	}

}
