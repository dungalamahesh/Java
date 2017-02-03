import java.io.IOException;
import java.sql.SQLException;

public class ConnectionMain {
	
	public static void main (String args[]) throws SQLException, IOException{
		
		ConnectionPanel myCon = new ConnectionPanel("jdbc:mysql://localhost:3306/jdbc", "root", "13245678");
		myCon.display();	
		myCon.WriteBlob();
		//myCon.searchDisplayById();
		//myCon.searchDisplayByName();
		//myCon.deleteByName();
		//myCon.addNewUser();
	}
	
}
