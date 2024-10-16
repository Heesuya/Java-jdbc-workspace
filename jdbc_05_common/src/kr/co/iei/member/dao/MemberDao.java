package kr.co.iei.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.member.model.vo.Member;

public class MemberDao {
	public Member getMember(ResultSet rset) {
		Member m = new Member();
		try {
				m.setEnrollDate(rset.getDate("enroll_date"));
				m.setMemberAge(rset.getInt("member_age"));
				m.setMemberGender(rset.getString("member_gender"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberPw(rset.getString("member_pw"));				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	public Member selectMemberId(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member m = null;
		String query = "select * from member_tbl where member_id =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = getMember(rset);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member_tbl set member_pw=?,member_name=?,member_phone=? where member_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberPw());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberPhone());
			pstmt.setString(4, member.getMemberId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Member> selectMemberName(Connection conn, String memberName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member_tbl from member_name like %||?||% ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberName);
			rset = pstmt.executeQuery();
			while(rset.next()) {
			Member m = getMember(rset);	
			list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<Member> selectAllMember(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String qeury = "select * from member_tbl";
		try {
			pstmt = conn.prepareStatement(qeury);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = getMember(rset);
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		String qeury = "insert into member_tbl values(?,?,?,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(qeury);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setInt(5, member.getMemberAge());
			pstmt.setString(6, member.getMemberGender());
			result = pstmt.executeUpdate();
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteMemberId(Connection conn,  String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from member_tbl where member_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertDelMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into del_member_tbl values (?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberName());
			pstmt.setString(3, m.getMemberPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}		
		return result;
	}

	public ArrayList<Member> selectMemberGender(Connection conn, String memberGender) {
		PreparedStatement pstmt = null;
		ArrayList<Member> list = new ArrayList<Member>();
		ResultSet rset= null;
		String query = "select member_id,member_name,member_phone,member_gender from member_tbl where member_gender = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberGender);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPhone(rset.getString("member_phone"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberGender(rset.getString("member_gender"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
}
