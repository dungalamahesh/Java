import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class ConnectionPanel {

	private Connection connectionLine;

	public ConnectionPanel(String path, String username, String password) throws SQLException {

		try {
			connectionLine = DriverManager.getConnection(path, username, password);
		} catch (SQLException e) {
			System.err.println("this connection isn't oriented, DOS --> " + e.getMessage());
		}

		getMetaDataInfo();
	}

	public Connection connector() {

		return this.connectionLine;
	}

	public void searchDisplayById() throws SQLException {
		PreparedStatement stmSearch = null;
		System.out.println("Please enter id for begin: ");
		String search = new Scanner(System.in).nextLine();
		try {
			stmSearch = this.connectionLine.prepareStatement("SELECT * FROM login_users WHERE id > ? ");
			stmSearch.setString(1, search);
		} catch (SQLException e) {
			System.err.println("Check your Serch value --> " + e.getMessage());
		}

		System.out.println("***********This is the result***************");
		ResultSet results = stmSearch.executeQuery();

		while (results.next()) {

			System.out.println("Name: " + results.getString(4) + " | Last name: " + results.getString(2) + " | @Email: "
					+ results.getString(1));
		}
		System.out.println("***********************************");
	}

	public void searchDisplayByName() throws SQLException {

		PreparedStatement stmSearch = null;
		System.out.println("Please enter name for search: ");
		String search = new Scanner(System.in).nextLine();

		try {
			stmSearch = this.connectionLine.prepareStatement("SELECT * FROM login_users WHERE name = ? ");
			stmSearch.setString(1, search);
		} catch (SQLException e) {
			System.err.println("Check your Serch value --> " + e.getMessage());
		}

		System.out.println("***********This is the result***************");
		ResultSet results = stmSearch.executeQuery();

		while (results.next()) {

			System.out.println("Name: " + results.getString(4) + " | Last name: " + results.getString(2) + " | @Email: "
					+ results.getString(1));
		}
		System.out.println("***********************************");
	}

	public void deleteByName() throws SQLException {
		PreparedStatement stmDelete = null;
		System.out.println("Enter the name you want to delete");
		String name = new Scanner(System.in).nextLine();

		try {
			stmDelete = this.connectionLine.prepareStatement("DELETE FROM login_users WHERE name = ? ");
			stmDelete.setString(1, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int rowsHowDeleted = stmDelete.executeUpdate();
		System.out.println("You delete " + rowsHowDeleted + " rows");
		display();
	}

	public void addNewUser() throws SQLException {

		PreparedStatement stmAddition = null;
		System.out.println("name: ");
		String name = new Scanner(System.in).nextLine();
		System.out.println("last name: ");
		String lastName = new Scanner(System.in).nextLine();
		System.out.println("email adress: ");
		String emailAdress = new Scanner(System.in).nextLine();

		try {
			stmAddition = this.connectionLine
					.prepareStatement("INSERT INTO login_users (email, last_name,  id,  name) VALUES (?, ?, NULL, ?);");
			stmAddition.setString(1, name);
			stmAddition.setString(2, lastName);
			stmAddition.setString(3, emailAdress);
		} catch (SQLException e) {
			System.err.println("Check your Serch value --> " + e.getMessage());
		}

		display();
	}

	private boolean yesOrNo() {
		System.out.println("Do you want to see info about Data Base? --> {y/s}");
		String yesNoQuestion = new Scanner(System.in).nextLine();

		if (yesNoQuestion.equals("n"))
			return false;
		else if (yesNoQuestion.equals("y"))
			return true;
		else
			return false;
	}

	public void getMetaDataInfo() throws SQLException {

		java.sql.DatabaseMetaData metaDataInfo = connectionLine.getMetaData();
		if (yesOrNo() == true) {
			System.out.println("DB name: " + metaDataInfo.getDatabaseProductName());
			System.out.println("DB version: " + metaDataInfo.getDatabaseMajorVersion());
			System.out.println("User name: " + metaDataInfo.getUserName());
			// getTablesInfo(); //--> old way and and cumbersome
			getResultable();
		} else {
			System.out.println("Continue...");
		}

	}

	public void getTablesInfo() throws SQLException {

		String catalog = null, schemaPattern = null, tableNamePattern = null, columnNamePattern = null;
		String[] types = null;
		ResultSet result = null;
		java.sql.DatabaseMetaData metaDataInfo = connectionLine.getMetaData();

		System.out.println("List of Tables: ");
		System.out.println("--------------------");

		result = metaDataInfo.getTables(catalog, schemaPattern, tableNamePattern, types);
		while (result.next()) {

			System.err.print(result.getString("TABLE_NAME") + " | ");
		}
		System.out.println();

		System.out.println("List of Columns: ");
		System.out.println("--------------------");
		result = metaDataInfo.getColumns(catalog, schemaPattern, "login_users", columnNamePattern);

		while (result.next()) {

			System.err.print(result.getString("COLUMN_NAME") + " | ");
		}
		System.out.println();
	}

	public void getResultable() throws SQLException {

		ResultSet result = null;
		Statement stm = null;

		stm = this.connectionLine.createStatement();
		result = stm.executeQuery("SELECT * FROM login_users");

		java.sql.ResultSetMetaData resultMetaData = result.getMetaData();
		int COLUMN = resultMetaData.getColumnCount(); // get the counter of
														// columns

		for (int i = 1; i < COLUMN + 1; i++) {
			System.out.println(
					"column (" + i + ") " + resultMetaData.getColumnName(i) + " , " + resultMetaData.getColumnType(i)
							+ " , " + resultMetaData.isAutoIncrement(i) + " , " + resultMetaData.getCatalogName(i));
		}

	}

	public void WriteBlob() throws SQLException, IOException {

		String updateBlob = "UPDATE login_users SET context= ? WHERE name= ? ";
		PreparedStatement prStm = this.connectionLine.prepareStatement(updateBlob);

		File fileToWrite = new File("sample_resume.pdf");
		FileInputStream streamInput = new FileInputStream(fileToWrite);

		System.out.println("Enter name for update the contents: ");
		String name = new Scanner(System.in).nextLine();
		prStm.setBinaryStream(1, streamInput);
		prStm.setString(2, name);
		prStm.executeUpdate();
		System.out.println("Complete success!!!");
		readBlob();
	}

	public void readBlob() throws SQLException, IOException {

		InputStream input = null;
		/**
		 * A file output stream is an output stream for writing data to a File
		 * or to a FileDescriptor. Whether or not a file is available or may be
		 * created depends upon the underlying platform.
		 **/
		FileOutputStream output = null;

		String readInfo = "SELECT context FROM login_users WHERE name= 'John' ";
		Statement stm = this.connectionLine.createStatement();

		ResultSet result = stm.executeQuery(readInfo);

		File theFile = new File("resume_from_db.pdf");
		output = new FileOutputStream(theFile);

		if (result.next()) {

			input = result.getBinaryStream("context");
			System.out.println("Reading resume from database...");

			byte[] buffer = new byte[1024];
			while (input.read(buffer) > 0) {
				output.write(buffer);
			}

			System.out.println("Completed successfully!");
		}

	}

	public void display() throws SQLException {

		Statement stmConnection = this.connectionLine.createStatement();
		System.out.println("***********************************");
		ResultSet results = stmConnection.executeQuery("SELECT * FROM login_users");

		while (results.next()) {

			System.out.println("Name: " + results.getString(4) + " | Last name: " + results.getString(2) + " | @Email: "
					+ results.getString(1));
		}
		System.out.println("***********************************");
	}

}
