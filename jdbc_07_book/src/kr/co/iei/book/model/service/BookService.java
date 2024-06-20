package kr.co.iei.book.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.co.iei.book.common.JDBCTemplate;
import kr.co.iei.book.model.dao.BookDao;
import kr.co.iei.book.model.vo.Book;

public class BookService {
	private BookDao bookDao;

	public BookService() {
		super();
		bookDao = new BookDao();
	}

	public int insertBook(Book book) {
		Connection conn = JDBCTemplate.getConnection();
		int result = 0;
		result = bookDao.insertBook(conn, book);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Book> viewBooks() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Book> list = bookDao.viewBooks(conn);
		JDBCTemplate.close(conn);;
		return list;
	}
	
}
