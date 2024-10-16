package kr.co.iei.board.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.board.common.JDBCTemplate;
import kr.co.iei.board.model.vo.Board;
import kr.co.iei.board.model.vo.Member;

public class BoardDao {
	
	public Board getBoard(ResultSet rset){
		Board b = null;
		try {
			b = new Board(rset.getInt("board_no"), rset.getString("board_title"), rset.getString("board_content"), 
							     rset.getInt("board_writer"), rset.getInt("read_count"), rset.getDate("write_date"),rset.getString("member_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public Board getBoardNull(ResultSet rset){
		Board b = new Board();
			try {
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardWriterName(rset.getString("member_name"));
				b.setReadCount(rset.getInt("read_count"));
				b.setWriteDate(rset.getDate("write_date"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return b;
	}
	public int insertContent(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into exam_board values(exam_board_seq.nextval,?,?,?,0,sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Board> contentList(Connection conn) {
		ArrayList<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select board_no, board_title, nvl(member_name,'회원탈퇴') as member_name, read_count, write_date from exam_board left join exam_member on (board_writer = member_no)";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = getBoardNull(rset);
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt = conn.prepareStatement(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public Board viewContent(Connection conn, int selectContentNum) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String qeury = "select * from exam_board join exam_member on (board_writer = member_no) where board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(qeury);
			pstmt.setInt(1, selectContentNum);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = getBoard(rset);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	public int count(Connection conn, Board board) {
		PreparedStatement stmt = null;
		int result = 0;
		if(board == null) {
			return result;
		}
		String query = "update exam_board set read_count = 1+ ? where board_no = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, board.getReadCount());
			stmt.setInt(2, board.getBoardNo());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateContent(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update exam_board set board_title =?, board_content=? where board_no =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteContent(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from exam_board where board_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board.getBoardNo());
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateLoginMember(Connection conn, Member loginMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update exam_member set member_pw =?,member_phone=? where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMember.getMemberPw());
			pstmt.setString(2, loginMember.getMemberPhone());
			pstmt.setInt(3, loginMember.getMemberNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int delteLoginMember(Connection conn, Member loginMember) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete exam_member where member_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginMember.getMemberNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}


	
	
	
}
