package dto;


public class MenuVO {
	String cNo;
	String cName;
	int cPrice;
	
	public MenuVO() {};
	
	public MenuVO(String cNo, String cName, int cPrice) {
		this.cNo = cNo;
		this.cName = cName;
		this.cPrice = cPrice;
	}
	public String getcNo() {
		return cNo;
	}
	public void setcNo(String cNo) {
		this.cNo = cNo;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getcPrice() {
		return cPrice;
	}
	public void setcPrice(int cPrice) {
		this.cPrice = cPrice;
	}
	
	
}
