package five;

public class MenuDTO {
	String cno,cname;
	int cprice;
	public MenuDTO() {}
	
	public MenuDTO(String cno, String cname, int cprice) {
		this.cno = cno;
		this.cname = cname;
		this.cprice = cprice;
	}
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCprice() {
		return cprice;
	}
	public void setCprice(int cprice) {
		this.cprice = cprice;
	}
	
	
}
