package kr.co.iei.book.controller;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.book.model.service.BookService;
import kr.co.iei.book.model.service.MemberService;
import kr.co.iei.book.model.service.RentalService;
import kr.co.iei.book.model.vo.Book;
import kr.co.iei.book.model.vo.Member;

public class Controller {
	private Scanner sc;
	private BookService bookService;
	private MemberService memberService;
	private RentalService rentalService;
	private Member loginMember;
	public Controller() {
		super();
		sc = new Scanner(System.in);
		bookService = new BookService();
		memberService = new MemberService();
		rentalService = new RentalService();
		loginMember = null;
	}
	public void Main() {
		if(loginMember == null) {
			noLogin();
		}else {
			if(loginMember.getMemberGrade() == 1) {
				memberMenu();
			}else {
				employeeMenu();
			}
		}
	}
	
	public void noLogin() {
		while(true) {
			System.out.println("---------- KH 도서관 ----------");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("3. 종료");
			System.out.print("선택 > ");
			switch(sc.nextInt()) {
			case 1:
				loginMember();
				break;
			case 2:
				insertMember();
				break;
			case 0:
				return;
			}
		}
	}
	


	///////////////////////////////////////////////////////////////////
	//로그인
	private void loginMember() {
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		Member m = new Member(0, memberId, memberPw, null, 0);
		Member member = memberService.loginMember(m);
		if(member == null) {
			System.out.println("아이디와 비밀번호를 확인해주세요.");
		}else {
			loginMember = member;			
		}
	}

	
	//회원가입
	private void insertMember() {
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		System.out.print("회원 이름 입력 : ");
		String memberName = sc.next();
		System.out.print("회원 등급 선택[1.고객 / 2.직원] : ");
		int memberGrade = sc.nextInt();
		Member member = new Member(0, memberId, memberPw, memberName, memberGrade);
		int result = memberService.insertMember(member);
		if(result > 0) {
			System.out.println("회원 가입 성공");
		}else {
			System.out.println("회원 가입 실패");
		}
	}
	
	////////////////////////////////////////////////////////////////////
	//고객용메뉴
	public void memberMenu() {
		while(true) {
			System.out.println("------------ KH 도서관 ------------");
			System.out.println("1. 책 빌리기");
			System.out.println("2. 내 대여정보 확인");
			System.out.println("3. 책 반납하기");
			System.out.println("4. 내 정보 보기");
			System.out.println("0. 로그아웃");
			System.out.print("선택 > ");
			int select = sc.nextInt();
			switch(select){
			case 1:
				break;
			case 2:
				break;
			case 4:
				break;
			case 0:
				loginMember = null;
				return;
			default:
				break;
			}			
		}
	}
	
	
	////////////////////////////////////////////////////////////////////
	//직원용 메뉴
	public void employeeMenu() {
		while(true) {
			System.out.println("------------ KH 도서관 관리 메뉴 ------------");
			System.out.println("1. 책 현황 보기");
			System.out.println("2. 신규 책 등록");
			System.out.println("3. 전체 대여 현황");
			System.out.println("4. 전체 회원 정보 조회");
			System.out.println("0. 로그아웃");
			System.out.print("선택 > ");
			int select = sc.nextInt();
			switch(select){
			case 1:
				viewBooks();
				break;
			case 2:
				insertBook();
				break;
			case 3:
				bookStatus();
				break;
			case 4:
				selectAllMember();
				break;
			case 0:
				loginMember = null;
				return;
			default:
				break;
			}			
		}
	}
	
	private void selectAllMember() {
		System.out.println("------------ 전체 회원 현황 ------------");
		
	}

	//책 현황 
	private void viewBooks() {
		System.out.println("------------ KH 도서 현황 ------------");
		ArrayList<Book> list = bookService.viewBooks();
		if(list.isEmpty()) {
			System.out.println("등록된 도서가 없습니다.");
		}else {
			System.out.println("책번호  장르    제목    작가    수량");
			for(Book b : list) {
				System.out.println(b.getBookNo()+"\t"+b.getBookGenre()+"\t"+b.getBookTitle()
									+"\t"+b.getBookAuthor()+"\t"+b.getBookStock());
			}
		}
	}

	//책등록
	private void insertBook() {
		System.out.println("------------ KH 신규 책 등록 ------------");
		System.out.print("장르 : ");
		String genre = sc.next();
		System.out.print("제목 : ");
		sc.nextLine();
		String tilte = sc.nextLine();
		System.out.print("작가 : ");
		String author = sc.next();
		System.out.print("책 재고 입력 : ");
		int stock = sc.nextInt();
		Book book = new Book(0, genre, tilte, author, stock);
		int result = bookService.insertBook(book);
		if(result > 0) {
			System.out.println("등록 완료");
		}else {
			System.out.println("등록 실패");
		}
	}
}
