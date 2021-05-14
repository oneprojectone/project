package one;

import java.sql.Date;

public class DTO_History {
	int hNo;
	String hID;
	String hMenu;
	String hSize;
	String hOption;
	int hPrice;
	Date hDate;
	
	public DTO_History(int hNo, String hID, String hMenu, String hSize, String hOption, int hPrice) {
		this.hNo = hNo;
		this.hID = hID;
		this.hMenu = hMenu;
		this.hSize = hSize;
		this.hOption = hOption;
		this.hPrice = hPrice;
	}
	public int gethNo() {
		return hNo;
	}
	public void sethNo(int hNo) {
		this.hNo = hNo;
	}
	public String gethID() {
		return hID;
	}
	public void sethID(String hID) {
		this.hID = hID;
	}
	public String gethMenu() {
		return hMenu;
	}
	public void sethMenu(String hMenu) {
		this.hMenu = hMenu;
	}
	public String gethSize() {
		return hSize;
	}
	public void sethSize(String hSize) {
		this.hSize = hSize;
	}
	public String gethOption() {
		return hOption;
	}
	public void sethOption(String hOption) {
		this.hOption = hOption;
	}
	public int gethPrice() {
		return hPrice;
	}
	public void sethPrice(int hPrice) {
		this.hPrice = hPrice;
	}
	public Date gethDate() {
		return hDate;
	}
	public void sethDate(Date hDate) {
		this.hDate = hDate;
	}
	@Override
	public String toString() {
		return "DTO_History [hNo=" + hNo + ", hID=" + hID + ", hMenu=" + hMenu + ", hSize=" + hSize + ", hOption="
				+ hOption + ", hPrice=" + hPrice + ", hDate=" + hDate + "]";
	}
	
	
}
