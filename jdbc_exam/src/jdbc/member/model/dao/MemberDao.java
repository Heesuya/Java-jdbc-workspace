package jdbc.member.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import jdbc.common.JDBCTemplate;

import jdbc.member.model.vo.Member;

public class MemberDao {

	public Member searchId(Connection conn, String memberId) {

		PreparedStatement pstmt = null;

		ResultSet rset = null;

		Member m = null;

		String query = "select * from member_tbl where member_id = ?";

		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, memberId);

			rset = pstmt.executeQuery();

			if (rset.next()) {

				m = new Member();

				m.setMemberId(rset.getString("member_id"));

				m.setMemberPw(rset.getString("member_pw"));

				m.setMemberName(rset.getString("member_name"));

				m.setMemberAge(rset.getInt("member_age"));

				m.setMemberPhone(rset.getString("member_phone"));

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			JDBCTemplate.close(rset);

			JDBCTemplate.close(pstmt);

		}

		return m;

	}

}