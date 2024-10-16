package kr.co.iei.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.midi.Soundbank;

import kr.co.iei.member.model.dao.MemberDao;
import kr.co.iei.member.model.vo.Member;

public class MemberController {
	private Scanner sc;
	private MemberDao memberDao;
	public MemberController() {
		super();
		sc = new Scanner(System.in);
		memberDao = new MemberDao();
	}
	public void main() {
		while(true) {
			System.out.println("\n---------- 회원 관리 프로그램v3 ----------\n");
			System.out.println("1. 전체회원 조회");
			System.out.println("2. 아이디로 회원 조회");
			System.out.println("3. 이름으로 회원 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
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
			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			}
			
		}
	}//main();
	
	//모든 회원 정보 보기 
	public void selectAllMember() {
		System.out.println("\n---------- 전체 회원 정보 조회 ----------\n");
		ArrayList<Member> list = new ArrayList<Member>();
		list = memberDao.selcetAllMember();
		if(list.isEmpty()) {
			System.out.println("회원 정보가 등록되어 있지 않습니다.");
		}else {
			System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t가입일");
			for(Member m : list) {
				System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"+m.getMemberPhone()
							+"\t"+m.getMemberAge()+"\t"+m.getMemberGender()+"\t"+m.getEnrollDate());				
			}
		}
	}
	
	public void selectMemberId() {
		System.out.println("\n---------- 아이디로 회원 정보 조회 ----------\n");
		//select * from member_tbl where member_id='입력한아이디';
		//입력받을 정보 : 아이디 
		System.out.println("조회 할 아이디를 입력하세요 : ");
		String searchId = sc.next();
		Member m = memberDao.selectMemberId(searchId);
		if(m == null) {
			System.out.println("회원 정보를 찾을 수 없습니다");
		}else {
			System.out.println("아이디 : "+m.getMemberId());
			System.out.println("비밀번호 : "+m.getMemberPw());
			System.out.println("이름 : "+m.getMemberName());
			System.out.println("전화번호 : "+m.getMemberPhone());
			System.out.println("나이 : "+m.getMemberAge());
			System.out.println("성별 : "+m.getMemberGender());
			System.out.println("가입일 : "+m.getEnrollDate());
		}
	}//selectMemberId();
	
	//회원가입 
	public void insertMember() {
		System.out.println("\n---------- 회원 가입 ---------\n");
		//받아야할정보 : 아이디/비밀번호/이름/전화번호/나이/성별 
		Member m = new Member();
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		m.setMemberId(memberId);
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		m.setMemberPw(memberPw);
		System.out.print("이름 입력 : ");
		m.setMemberName(sc.next());
		System.out.print("전화번호 입력[010-0000-0000] : ");
		m.setMemberPhone(sc.next());
		System.out.print("나이 입력 : ");
		m.setMemberAge(sc.nextInt());
		System.out.print("성별 입력[남/여] : ");
		m.setMemberGender(sc.next());
		int result = memberDao.insertMember(m);
		if(result > 0) {
			System.out.println("회원 가입 완료");
		}else {
			System.out.println("회원 가입 실패");
		}
	}//insertMember();

	
	public void selectMemberName() {
		System.out.println("\n---------- 이름으로 회원 정보 조회 ----------\n");
		System.out.print("조회 할 회원 이름을 입력하세요 : ");
		String searchName = sc.next();
		
		ArrayList<Member> list = memberDao.selectMemberName(searchName);
		if(list.isEmpty()) {
			System.out.println("회원 정보가 없습니다");
		}else {
			System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t가입일");
			for(Member m : list) {
				System.out.println(m.getMemberId()+"\t"+m.getMemberPw()+"\t"+m.getMemberName()+"\t"+m.getMemberPhone()
							+"\t"+m.getMemberAge()+"\t"+m.getMemberGender()+"\t"+m.getEnrollDate());				
			}
		}
	}//selectMemberName();
	
	public void updateMember() {
		System.out.println("\n---------- 회원 정보 수정 ----------\n");
		System.out.print("수정할 회원 아이디 입력: ");
		String memberId = sc.next();
		Member m = memberDao.selectMemberId(memberId);
		
		if(m == null) {
			System.out.println("회원 정보가 없습니다.");
		}else {
			//비밀번호, 이름, 전화번호 
			System.out.print("수정할 비밀번호 입력 : ");
			String memberPw = sc.next();
			System.out.print("수정할 회원 이름 입력 : ");
			String memberName = sc.next();
			System.out.print("수정할 회원 전화번호[010-0000-0000] : ");
			String memberPhone = sc.next();
			//이름검색한 m 보내면 업데이트가 안됨 
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			member.setMemberPhone(memberPhone);
			member.setMemberName(memberName);
			int result = memberDao.updateMember(member);
			
			if(result > 0) {
				System.out.println("회원 정보 수정 완료!");
			}else {
				System.out.println("회원 정보 수정 실패!");
			}
		}
	}//updateMember();
	
	
	public void deleteMember() {
		System.out.println("--------- 회원 정보 삭제 ----------\n");
		System.out.print("삭제할 회원 아이디입력 : ");
		String deleteId = sc.next();
		int result = memberDao.deleteMember(deleteId);
		if(result > 0) {
			System.out.println("회원 삭제 완료 ");
		}else {
			System.out.println("회원 삭제 실패 ");
		}
	}//deletMember();
}
