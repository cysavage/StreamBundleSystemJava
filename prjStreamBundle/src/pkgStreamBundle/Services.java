package pkgStreamBundle;

public class Services {
	
	private int sId;
	private String sName;
	private float price;
	private String desc;
	
	public Services() {
		super();
		this.sId = 0;
		this.sName = "";
		this.price = 0;
		this.desc = "";
	}
	
	public Services(int sId, String sName, float price, String desc) {
		super();
		this.sId = 0;
		this.sName = "";
		this.price = 0;
		this.desc = "";;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setfName(String sName) {
		this.sName = sName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
