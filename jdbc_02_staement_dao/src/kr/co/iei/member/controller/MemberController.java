package kr.co.iei.member.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

//사용자 입력, 데이터운영, 데이터베이스 처리 
public class MemberController {
	private Scanner sc;
	private MemberDao memberDao;
	public MemberController() {
		super();
		sc = new Scanner(System.in);
		memberDao = new MemberDao();
	}
	
	//전체 메뉴를 출력하는 메소드
	public void main() {
		while(true) {
			System.out.println("\n---------- 회원 관리 프로그램v2 ----------\n");
			System.out.println("1. 전체 회원 조회");
			System.out.println("2. 아이디로 회원 조회");
			System.out.println("3. 이름으로 회원 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 정보 삭제");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch(select) {
			case 1:
				selectAllMember();
				break;
			case 2:
				selectMemberId();
				break;
			case 3:
				selectMemberName();
				break;
			case 4:
				insertMember();
				break;
			case 5:
				updateMember();
				break;
			case 6:
				deleteMember();
				break;
			case 0:
				return;
			default :
				System.out.println("잘못입력하셨습니다.");
				break;
			}
		}
	}//main() 종료

	public void selectMemberId() {
		//DB작업 필요한지? O
		//query : select * from member_tbl where member_id ='아이디';
		//입력받아야 하는 정보 : 아이디
		System.out.println("\n---------- 아이디로 회원 조회 ----------\n");
		System.out.print("조회 할 회원 아이디 입력 : ");
		String searchId = sc.next();
		
		//데이터베이스 작업은 dao의 메소드를 통해서 수행
		// dao메소드의 역할 : 쿼리문을 수행하고 수행결과를 되돌려줌
		// controller -> dao : 쿼리문 수행에 필요한 데이터를 전송(매개변수) 
		// dao -> controller : 쿼리문 수행 결과를 되돌려줘야 함(리턴)
		// 쿼리문 완성에 필요한 데이터는 입력받은 회원 아이디 
		// 쿼리문 수행 결과는 회원 1명정보 또는 null -> Member
		Member m = memberDao.selectMemberId(searchId);
		if(m == null) {
			System.out.println("회원 정보를 찾을 수 없습니다.");
		}else {
			System.out.println("아아디 : "+m.getMemberId());
			System.out.println("비밀번호 : "+m.getMemberPw());
			System.out.println("이름 : "+m.getMemberName());
			System.out.println("전화번호 :"+m.getMemberPhone());
			System.out.println("나이 : "+m.getMemberAge());
			System.out.println("성별 : "+m.getMemberGender());
			System.out.println("가입일 : "+m.getEnrollDate());
		}
	}//selectMemberId() 종료
	
	public void selectAllMember() {
		//jdbc흐름 어려운것보다 자바 문법 ㅋ.ㅎ.
		System.out.println("\n---------- 전체 회원 조회 ----------\n");
		ArrayList<Member> list = memberDao.selectAllMember();
		System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t가입일");
		System.out.println("-----------------------------------------------------------");
		if(list.isEmpty()) {
			System.out.println("조회 할 회원 정보가 없습니다.");
		}else {
			for(Member m : list) {
				System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"
						+m.getMemberPhone()+"\t"+m.getMemberAge()+"\t"+m.getMemberGender()+"\t"+m.getEnrollDate());
			}
		}
	}//selectAllMember() 종료
		
	public void selectMemberName() {
		System.out.println("\n---------- 이름으로 회원 조회 ------------\n");
		System.out.print("조회 할 회원 이름 입력 : ");
		String searchName = sc.next();
		ArrayList<Member> list = new ArrayList<Member>();
		list = MemberDao.memberSearchName(searchName);
		if(list.isEmpty()) {
			System.out.println("조회 정보를 조회할 수 없습니다.");
		}else {
			System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t가입일");
			for(Member m : list) {
				System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"
						+m.getMemberPhone()+"\t"+m.getMemberAge()+"\t"+m.getMemberGender()+"\t"+m.getEnrollDate());
			}
		}
	}//selecetMemberName() 종료
	
	public void insertMember() {
		System.out.println("\n---------- 회원 정보 등록 ----------\n");
		System.out.print("아이디 입력 : ");
		String insertId = sc.next();
		System.out.print("비밀번호 입력 : ");
		String insertPw = sc.next();
		System.out.print("이름 입력 : ");
		String insertName = sc.next();
		System.out.print("전화번호 입력[010-0000-0000] :");
		String insertPhone = sc.next();
		System.out.print("나이 입력 : ");
		int insertAge = sc.nextInt();
		System.out.print("성별 입력[남/여] : ");
		String insertGender = sc.next();
		
		Member m = new Member(insertId, insertPw, insertName, insertPhone, insertAge, insertGender, null);
		//매개변수를 만들때는 개수 제한이 없어서 6개를 다 보낼수도 있당~ 그런데 객체로 묶어 쓰는거 권고~
		int result = memberDao.insertMember(m);
		
		if(result > 0) {
			System.out.println("회원 등록 성공!");
		}else {
			System.out.println("회원 등록 실패 ㅠ! ");
		}
	}//insertMember() 종료 
	
	public void updateMember() {
		System.out.println("\n--------- 회원 정보 수정 -----------\n");
		System.out.print("수정 할 아이디 입력 : ");
		String memberId = sc.next();
		Member m = memberDao.selectMemberId(memberId);
		//int result = memberDao.updateSearchMember(memberId);
		if(m != null) {
			System.out.print("수정 할 비밀번호 입력 : ");
			String memberPw = sc.next();
			System.out.print("수정 할 이름 입력 : ");
			String memberName = sc.next();
			System.out.print("수정 할 전화번호 입력[010-0000-0000] : ");
			String memberPhone = sc.next();
			m = new Member(memberId, memberPw, memberName, memberPhone, 0, null, null);
			/*
			받은 정보로만 입력해도 됨 
			Member member = new Member();
			member.setMemberId(memberId);
			int result = memberDao.updateMember(member);
			*/
			//result = memberDao.updateMember(memberId,memberPw,memberName,memberPhone);
			int result = 0;
			result = memberDao.updateMember2(m);
			
			if(result > 0) {
				System.out.println("회원 정보 수정완료!");
			}else {
				System.out.println("회원 정보 실패!");
			}
		}else {
			System.out.println("회원 정보가 없습니다.");
		}
	}
	
	public void deleteMember() {
		System.out.println("\n--------- 회원 정보 삭제 ----------\n");
		System.out.print("삭제할 회원 아이디 : ");
		String memberId = sc.next();
		
		int result = memberDao.deleteMember(memberId);
		if(result > 0) {
			System.out.println("삭제 완료!");
		}else {
			System.out.println("삭제 실패!");
		}
	}
}
