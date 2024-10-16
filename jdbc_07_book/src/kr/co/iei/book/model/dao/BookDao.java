package kr.co.iei.book.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.iei.book.common.JDBCTemplate;
import kr.co.iei.book.model.vo.Book;

public class BookDao {
	public Book getBookList(ResultSet rset) {
		Book book = new Book();
		try {
			book.setBookAuthor(rset.getString("book_author"));
			book.setBookGenre(rset.getString("book_genre"));
			book.setBookNo(rset.getInt("book_no"));
			book.setBookStock(rset.getInt("book_stock"));
			book.setBookTitle(rset.getString("book_title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	
	public int insertBook(Connection conn, Book book) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into book_tbl values(book_tbl_seq.nextval,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, book.getBookGenre());
			pstmt.setString(2, book.getBookTitle());
			pstmt.setString(3, book.getBookAuthor());
			pstmt.setInt(4, book.getBookStock());
			result =pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Book> viewBooks(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Book> list = new ArrayList<Book>();
 		String query = "select * from book_tbl";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
			Book book = new Book();
			book = getBookList(rset);
			list.add(book);
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
