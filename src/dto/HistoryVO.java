package dto;

import java.sql.Date;

public class HistoryVO {
	String hNo;
	String hID;
	String hMenu;
	String hSize;
	String hOption;
	String hPrice;
	String hDate;
	
	public HistoryVO(String hNo, String hID, String hMenu, String hSize, String hOption, String hPrice) {
		this.hNo = hNo;
		this.hID = hID;
		this.hMenu = hMenu;
		this.hSize = hSize;
		this.hOption = hOption;
		this.hPrice = hPrice;
	}
	
	public HistoryVO( String hID, String hMenu, String hSize, String hOption) {
		
		this.hID = hID;
		this.hMenu = hMenu;
		this.hSize = hSize;
		this.hOption = hOption;
	}
	public HistoryVO() {};
	
	public String gethNo() {
		return hNo;
	}
	public void sethNo(String hNo) {
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
	public String gethPrice() {
		return hPrice;
	}
	public void sethPrice(String hPrice) {
		this.hPrice = hPrice;
	}
	public String gethDate() {
		return hDate;
	}
	public void sethDate(String hDate) {
		this.hDate = hDate;
	}
	@Override
	public String toString() {
		return "DTO_History [hNo=" + hNo + ", hID=" + hID + ", hMenu=" + hMenu + ", hSize=" + hSize + ", hOption="
				+ hOption + ", hPrice=" + hPrice + ", hDate=" + hDate + "]";
	}
	
	
}
