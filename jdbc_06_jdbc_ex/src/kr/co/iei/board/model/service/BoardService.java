package kr.co.iei.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.board.common.JDBCTemplate;
import kr.co.iei.board.model.dao.BoardDao;
import kr.co.iei.board.model.vo.Board;
import kr.co.iei.board.model.vo.Member;

public class BoardService {
	private BoardDao boardDao;

	public BoardService() {
		super();
		boardDao = new BoardDao();
	}

	public int insertContent(Board b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = boardDao.insertContent(conn, b);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Board> contentList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> list = boardDao.contentList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public Board viewContent(int selectContentNum) {
		Connection conn = JDBCTemplate.getConnection();
		Board board = null;
		board = boardDao.viewContent(conn, selectContentNum);
		int result = boardDao.count(conn, board);
		board = boardDao.viewContent(conn, selectContentNum);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return board;
	}
	
	public Board updateContent(int selectContentNum) {
		Connection conn = JDBCTemplate.getConnection();
		Board board = null;
		board = boardDao.viewContent(conn, selectContentNum);	
		JDBCTemplate.close(conn);
		return board;
	}

	public int updateContent(Board board) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0; 
		result= boardDao.updateContent(conn, board);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteContent(Board board) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		result = boardDao.deleteContent(conn, board);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateLoginMember(Member loginMember) {
		Connection conn = JDBCTemplate.getConnection();
		int result = boardDao.updateLoginMember(conn , loginMember);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteLoginMember(Member loginMember) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		result = boardDao.delteLoginMember(conn, loginMember);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	
}
