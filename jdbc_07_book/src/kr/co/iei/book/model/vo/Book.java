package kr.co.iei.book.model.vo;

import java.sql.Date;

public class Book {
	//대여번호\t회원명\t제목\t대여일\t반납일\t상태"
	private int bookNo;
	private String bookGenre;
	private String bookTitle;
	private String bookAuthor;
	private int bookStock;
	private int rental_no;
	private String book_title;
	private Date rental_date;
	private Date rental_return_date;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int bookNo, String bookGenre, String bookTitle, String bookAuthor, int bookStock) {
		super();
		this.bookNo = bookNo;
		this.bookGenre = bookGenre;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookStock = bookStock;
	}
	
	public Book(int bookNo, String bookGenre, String bookTitle, String bookAuthor, int bookStock, int rental_no,
			String book_title, Date rental_date, Date rental_return_date) {
		super();
		this.bookNo = bookNo;
		this.bookGenre = bookGenre;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookStock = bookStock;
		this.rental_no = rental_no;
		this.book_title = book_title;
		this.rental_date = rental_date;
		this.rental_return_date = rental_return_date;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookGenre() {
		return bookGenre;
	}
	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public int getBookStock() {
		return bookStock;
	}
	public void setBookStock(int bookStock) {
		this.bookStock = bookStock;
	}
	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", bookGenre=" + bookGenre + ", bookTitle=" + bookTitle + ", bookAuthor="
				+ bookAuthor + ", bookStock=" + bookStock + "]";
	}
	
	
	
	
}
