package JdbcGui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;


public class ConnectionPanel {

	private Connection managerConect;

	public ConnectionPanel() throws SQLException {
		managerConect = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "13245678");
	}

	public boolean askForConnection(String name, String pass) throws SQLException {

		String query = "SELCT * FROM login WHERE username = ? AND password = ?";
		
		if (name.length() < 0 || pass.length() < 0)
			return false;
		else {
			PreparedStatement prStm = managerConect.prepareStatement(query);
			prStm.setString(1, name);
			prStm.setString(2, pass);
			ResultSet result  = prStm.executeQuery();
			if ( result != null )
				return true;
		}
		return false;
	}
	
}
