package jdbc.member.model.service;

import java.sql.Connection;

import jdbc.common.JDBCTemplate;
import jdbc.member.model.dao.MemberDao;
import jdbc.member.model.vo.Member;

public class MemberService {
	private MemberDao dao;

	public MemberService() {

		dao = new MemberDao();

	}

	public Member searchId(String memberId) {

		Connection conn = JDBCTemplate.getConnection();

		Member m = dao.searchId(conn, memberId);

		JDBCTemplate.close(conn);

		return m;

	}

}