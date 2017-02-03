package JdbcGui;

public class People {

	private String name;
	private String last_name;
	private String email;
	private String id;
	
	public People(String id, String name, String lastName, String email){
		
		this.id = id;
		this.name = name;
		this.last_name= lastName;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return String.format("(" +getId()+ ") " +getName()+ " " +getLast_name()+ ", " +getEmail());
	}
}
