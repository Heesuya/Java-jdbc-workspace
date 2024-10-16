package kr.co.iei.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.book.common.JDBCTemplate;
import kr.co.iei.book.model.vo.Member;

public class MemberDao {

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member_tbl values(member_tbl_seq.nextval,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setInt(4, member.getMemberGrade());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Member loginMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String qeury = "select * from member_tbl where member_id = ? and member_pw=?";
		try {
			pstmt = conn.prepareStatement(qeury);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPw());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				member = new Member();
				member.setMemberGrade(rset.getInt("member_grade"));
				member.setMemberId(rset.getString("member_id"));
				member.setMemberName(rset.getString("member_name"));
				member.setMemberPw(rset.getString("member_pw"));
				member.setNemberNo(rset.getInt("member_no"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return member;
	}

	public ArrayList<Member> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		//memberNo memberId memberPw memberName 
		//대여번호\t회원명\t제목\t대여일\t반납일\t상태"
		String qeury = "select rental_no, member_name, book_title, rental_date, rental_return_date, rental_status from member_tbl left join rental_tbl using (member_no) left join book_tbl using (book_no) where member_grade = 1 ";
		try {
			pstmt = conn.prepareStatement(qeury);
			while(rset.next()) {
				Member member = new Member();
				member.setRental_no(rset.getInt("rental_no"));
				member.setMemberName(rset.getString("member_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
