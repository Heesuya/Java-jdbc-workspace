package kr.co.iei.book.model.vo;

import java.sql.Date;

public class Rental {
	private int rentalNo;
	private Date rentalDate;
	private Date rentalReturnDate;
	private int rentalStatus;
	private int memberNo;
	private int bookNo;
	public Rental() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rental(int rentalNo, Date rentalDate, Date rentalReturnDate, int rentalStatus, int memberNo, int bookNo) {
		super();
		this.rentalNo = rentalNo;
		this.rentalDate = rentalDate;
		this.rentalReturnDate = rentalReturnDate;
		this.rentalStatus = rentalStatus;
		this.memberNo = memberNo;
		this.bookNo = bookNo;
	}
	public int getRentalNo() {
		return rentalNo;
	}
	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public Date getRentalReturnDate() {
		return rentalReturnDate;
	}
	public void setRentalReturnDate(Date rentalReturnDate) {
		this.rentalReturnDate = rentalReturnDate;
	}
	public int getRentalStatus() {
		return rentalStatus;
	}
	public void setRentalStatus(int rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	@Override
	public String toString() {
		return "Rental [rentalNo=" + rentalNo + ", rentalDate=" + rentalDate + ", rentalReturnDate=" + rentalReturnDate
				+ ", rentalStatus=" + rentalStatus + ", memberNo=" + memberNo + ", bookNo=" + bookNo + "]";
	}
	
}
