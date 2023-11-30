package pkgStreamBundle;

public class CustomerInfo {
	
	private int custID;
	private String fullname;
	private String email;
	private String password;
	
	public CustomerInfo() {
		super();
		this.custID = 0;
		this.fullname ="";
		this.email = "";
		this.password = "";
	}
	

	public CustomerInfo(int custID, String fullname, String email, String password)
	{
		super();
		this.custID = custID;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}
	
	
	
	
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}