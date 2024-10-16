package kr.co.iei.book.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.book.common.JDBCTemplate;
import kr.co.iei.book.model.dao.MemberDao;
import kr.co.iei.book.model.vo.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		super();
		memberDao = new MemberDao();
	}

	public int insertMember(Member member) {
		Connection conn = JDBCTemplate.getConnection();
		int result = memberDao.insertMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	
	public Member loginMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		Member memeber = memberDao.loginMember(conn, m);
		JDBCTemplate.close(conn);
		return memeber;
	}

	public ArrayList<Member> selectAllMember() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = memberDao.selectAllMember(conn);
		return null;
	}

}
