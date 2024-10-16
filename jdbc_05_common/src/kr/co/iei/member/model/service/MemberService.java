package kr.co.iei.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.common.JDBCTemplate;
import kr.co.iei.member.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		super();
		memberDao = new MemberDao();
	}

	public Member selectMemberId(String memberId) {
		Connection conn = JDBCTemplate.getConnction();
		Member m = memberDao.selectMemberId(conn,memberId);
		JDBCTemplate.close(conn);
		return m;
	}

	public int updateMember(Member member) {
		Connection conn = JDBCTemplate.getConnction();
		int result = memberDao.updateMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	 public ArrayList<Member> selecetMemberName(String memberName) {
		 Connection conn = JDBCTemplate.getConnction();
		 ArrayList<Member> list = memberDao.selectMemberName(conn, memberName);
		 JDBCTemplate.close(conn);
		 return list;
	}

	public ArrayList<Member> selecetAllMember() {
		Connection conn = JDBCTemplate.getConnction();
		ArrayList<Member> list = memberDao.selectAllMember(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public int insertMember(Member member) {
		Connection conn = JDBCTemplate.getConnction();
		int result = memberDao.insertMember(conn, member);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteMember(String memberId) {
		Connection conn = null;
		int result = -1;
		Member m = selectMemberId(memberId);
		if(m != null) {
			result = memberDao.deleteMemberId(conn, memberId);
			if(result > 0) {
				result = memberDao.insertDelMember(conn, m);
				if(result > 0) {
					JDBCTemplate.commit(conn);
				}else {
					JDBCTemplate.rollback(conn);
				}
			}
		}else {
			JDBCTemplate.rollback(conn); 
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Member> selectMemberGender(String memberGender) {
		Connection conn = null;
		ArrayList<Member> list = memberDao.selectMemberGender(conn, memberGender);
		JDBCTemplate.close(conn);
		return list;
	}
	
}
