package pkgStreamBundle;

public class Receipts {
	
	private int custID;
	private String fullName;
	private String email;
	private String recDate;
	private String pack;
	private double payment;
	private String paymentType;
	
	
	public Receipts() {
		super();
		this.custID = 0;
		this.fullName = "";
		this.email = "";
		this.recDate = "";
		this.pack = "";
		this.payment = 0.00;
		this.paymentType = "";
	}
	
	
	public Receipts(int custID, String fullName, String email, String recDate, String pack, double payment, String paymentType) {
		super();
		this.custID = custID;
		this.fullName = fullName;
		this.email = email;
		this.recDate = recDate;
		this.pack = pack;
		this.payment = payment;
		this.paymentType = paymentType;
	}


	public int getCustID() {
		return custID;
	}


	public void setCustID(int custID) {
		this.custID = custID;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRecDate() {
		return recDate;
	}


	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}


	public String getPack() {
		return pack;
	}


	public void setPack(String pack) {
		this.pack = pack;
	}


	public double getPayment() {
		return payment;
	}


	public void setPayment(double payment) {
		this.payment = payment;
	}


	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	
	

}
