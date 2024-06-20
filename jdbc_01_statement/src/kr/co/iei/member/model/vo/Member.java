package kr.co.iei.member.model.vo;

import java.sql.Date;

//한명 저장할 객체 타입을 만듦  데이터베이스와 무조건 일치하지는 않다. 
public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private int memberAge;
	private char memberGender;//String으로 다루는게 편함 
	private Date enrollDate; //date 타입은 sql 과 연결하기에 date.sql 
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Member(String memberId, String memberPw, String memberName, String memberPhone, int memberAge,
			char memberGender, Date enrollDate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberAge = memberAge;
		this.memberGender = memberGender;
		this.enrollDate = enrollDate;
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
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public int getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}
	public char getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}
	public Date getenrollDate() {
		return enrollDate;
	}
	public void setenrollDate(Date date) {
		this.enrollDate = date;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberPhone=" + memberPhone + ", memberAge=" + memberAge + ", memberGender=" + memberGender
				+ ", date=" + enrollDate + "]";
	}
	
}
