package kr.co.iei.board.model.service;

import java.sql.Connection;

import kr.co.iei.board.common.JDBCTemplate;
import kr.co.iei.board.model.dao.MemberDao;
import kr.co.iei.board.model.vo.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		super();
		memberDao = new MemberDao();
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = memberDao.insertMember(conn , m);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Member selectMemberNamePhone(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = memberDao.selectMemberNamePhone(conn,m);
		JDBCTemplate.close(conn);
		return member;
	}

	public Member selectMemberSearch(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = memberDao.selectMemberSearch(conn , m);
		JDBCTemplate.close(conn);
		return member;
	}

	public Member login(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = memberDao.login(conn , m);
		JDBCTemplate.close(conn);
		return member;
	}

	public String searchId(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		String searchId = memberDao.searchId(conn , m);
		JDBCTemplate.close(conn);
		return searchId;
	}

//	public int updateMember(Member loginMember) {
//		Connection conn = JDBCTemplate.getConnection();
//		int result = memberDao.updateMember(conn , loginMember);
//		if(result > 0) {
//			JDBCTemplate.commit(conn);
//		}else {
//			JDBCTemplate.rollback(conn);
//		}
//		JDBCTemplate.close(conn);
//		return result;
//	}
	
}
