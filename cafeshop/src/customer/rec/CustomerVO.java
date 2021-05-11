package customer.rec;

public class CustomerVO {
	private String custName, custId, custPwd, custGender, custEmail,
				   custTel, custAddr, custAccount, custDate;

	
	public CustomerVO(String custName, String custId, String custPwd, String custGender, String custEmail,
			String custTel, String custAddr, String custAccount, String custDate) {
		this.custName = custName;
		this.custId = custId;
		this.custPwd = custPwd;
		this.custGender = custGender;
		this.custEmail = custEmail;
		this.custTel = custTel;
		this.custAddr = custAddr;
		this.custAccount = custAccount;
		this.custDate = custDate;
	}
	
	public CustomerVO() {}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustPwd() {
		return custPwd;
	}

	public void setCustPwd(String custPwd) {
		this.custPwd = custPwd;
	}

	public String getCustGender() {
		return custGender;
	}

	public void setCustGender(String custGender) {
		this.custGender = custGender;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public String getCustAddr() {
		return custAddr;
	}

	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}

	public String getCustAccount() {
		return custAccount;
	}

	public void setCustAccount(String custAccount) {
		this.custAccount = custAccount;
	}

	public String getCustDate() {
		return custDate;
	}

	public void setCustDate(String custDate) {
		this.custDate = custDate;
	}
}
