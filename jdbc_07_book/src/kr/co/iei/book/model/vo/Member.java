package kr.co.iei.book.model.vo;

import java.sql.Date;

public class Member {
	private int nemberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberGrade;
	private int rental_no;
	private String book_title;
	private Date rental_date;
	private Date rental_return_date;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(int nemberNo, String memberId, String memberPw, String memberName, int memberGrade) {
		super();
		this.nemberNo = nemberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberGrade = memberGrade;
	}

	public Member(int nemberNo, String memberId, String memberPw, String memberName, int memberGrade, int rental_no,
			String book_title, Date rental_date, Date rental_return_date) {
		super();
		this.nemberNo = nemberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberGrade = memberGrade;
		this.rental_no = rental_no;
		this.book_title = book_title;
		this.rental_date = rental_date;
		this.rental_return_date = rental_return_date;
	}

	public int getNemberNo() {
		return nemberNo;
	}

	public void setNemberNo(int nemberNo) {
		this.nemberNo = nemberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(int memberGrade) {
		this.memberGrade = memberGrade;
	}

	public int getRental_no() {
		return rental_no;
	}

	public void setRental_no(int rental_no) {
		this.rental_no = rental_no;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public Date getRental_date() {
		return rental_date;
	}

	public void setRental_date(Date rental_date) {
		this.rental_date = rental_date;
	}

	public Date getRental_return_date() {
		return rental_return_date;
	}

	public void setRental_return_date(Date rental_return_date) {
		this.rental_return_date = rental_return_date;
	}

	@Override
	public String toString() {
		return "Member [nemberNo=" + nemberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberName="
				+ memberName + ", memberGrade=" + memberGrade + "]";
	}

}
