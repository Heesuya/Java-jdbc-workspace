package kr.co.iei.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.member.model.model.vo.Member;
import kr.co.iei.member.model.service.MemberService;

//1. 조회결과를 Member타입으로 변환하는 코드   -> 컬럼네임 모두 일치해야함. 
//2. Connection생성, close(), commit(), rollback();... 중복되는 코드
//3. singtone 디자인패턴 (to be continue......)

public class MemberController {
	private Scanner sc = null;
	private MemberService memberService;

	public MemberController() {
		super();
		sc = new Scanner(System.in);
		memberService = new MemberService();
	}

	public void main() {
		while (true) {
			System.out.println("\n---------- 회원 관리 프로그램v4 ----------\n");
			System.out.println("1. 전체 회원 조회");
			System.out.println("2. 아이디로 회원 조회");
			System.out.println("3. 이름으로 회원 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 수정");
			System.out.println("6. 회원 탈퇴");
			System.out.println("7. 성별로 회원 조회");
			System.out.print("선택 >> ");
			int select = sc.nextInt();
			switch (select) {
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
			case 7:
				selectMemberGender();
				break;
			case 0:
				return;
			default:
				break;

			}
		}
	}

	private void selectMemberGender() {
		System.out.println("\n---------- 성별로 회원 조회 ----------\n");
		System.out.print("조회 할 성별을 입력하세요 [남/여] : ");
		String memberGender = sc.next();
		ArrayList<Member> list = memberService.selectMemberGender(memberGender);
		System.out.println("아이디\t이름\t전화번호\t\t성별");
		for (Member m : list) {
			System.out.println(m.getMemberId() + "\t" + m.getMemberName()
					+ m.getMemberPhone() + "\t" + m.getMemberGender());
		}
	}

	private void deleteMember() {
		System.out.println("\n---------- 회원 정보 삭제 ---------\n");
		// select * from member_tbl where member_id=? --1
		// delete from member_tbl where membe_id=? --2
		// insert into del_member_tbl valuse(?,?,?,sysdate); --3
		System.out.print("탈퇴 할 회원 이름 아이디 : ");
		String memberId = sc.next();
		int result = memberService.deleteMember(memberId);
		if(result == -1) {
			System.out.println("회원 정보를 조회할 수 없습니다.");
		}else if(result > 0) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("삭제 실패");
		}
	}

	private void insertMember() {
		System.out.println("\n---------- 회원 정보 등록 ---------\n");
		// 받아야할정보 : 아이디/비밀번호/이름/전화번호/나이/성별
		Member m = new Member();
		while (true) {
			System.out.print("아이디 입력 : ");
			String memberId = sc.next();
			Member member = memberService.selectMemberId(memberId);
			if (member == null) {
				m.setMemberId(memberId);
				break;
			} else {
				System.out.println("이미 사용중인 아이디 입니다.");
			}
		}

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
		int result = memberService.insertMember(m);
		if (result > 0) {
			System.out.println("정보 등록 완료");
		} else {
			System.out.println("정보 등록 실패");
		}
	}

	private void selectMemberName() {
		System.out.println("\n---------- 이름으로 회원 조회 ----------\n");
		System.out.print("조회 할 회원 이름 입력 : ");
		ArrayList<Member> list = memberService.selectMemberName(sc.next());
		if (list.isEmpty()) {
			System.out.println("조회 된 회원 정보가 없습니다");
		} else {
			System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별");
			for (Member m : list) {
				System.out.println(m.getMemberId() + "\t" + m.getMemberPw() + "\t" + m.getMemberName()
						+ m.getMemberPhone() + "\t" + m.getMemberAge() + "\t" + m.getMemberGender());
			}
		}
	}

	private void selectAllMember() {
		System.out.println("\n---------- 회원 전체 조회 ----------\n");
		ArrayList<Member> list = memberService.selectAllMember();
		if (list.isEmpty()) {
			System.out.println("등록된 회원 번호가 없습니다");
		} else {
			System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별");
			for (Member m : list) {
				System.out.println(m.getMemberId() + "\t" + m.getMemberPw() + "\t" + m.getMemberName()
						+ m.getMemberPhone() + "\t" + m.getMemberAge() + "\t" + m.getMemberGender());
			}
		}
	}

	private void selectMemberId() {
		System.out.println("\n---------- 아이디로 회원 조회 ----------\n");
		// select * from member_tbl where member_id=?
		System.out.print("조회 할 회원 아이디 입력 : ");
		String memberId = sc.next();
		// 컨트롤러는 서비스에게 요청
		Member m = memberService.selectMemberId(memberId);
		if (m == null) {
			System.out.println("회원 정보를 조회할 수 없습니다");
		} else {
			System.out.println("아이디 : " + m.getMemberId());
			System.out.println("비밀번호 : " + m.getMemberId());
			System.out.println("이름 : " + m.getMemberName());
			System.out.println("전화번호 : " + m.getMemberPhone());
			System.out.println("나이 : " + m.getMemberAge());
			System.out.println("성별 : " + m.getMemberGender());
			System.out.println("가입일 : " + m.getEnrollDate());
		}
	}// selectMemberId();

	private void updateMember() {
		System.out.println("\n---------- 회원 정보 수정 ----------\n");
		// update member_tbl set member_pw=?,member_name=?,member_phone=? where
		// member_id=?;
		System.out.println("수정 할 회원 아이디 입력 : ");
		String memberId = sc.next();
		Member m = memberService.selectMemberId(memberId);
		if (m == null) {
			System.out.println("회원 정보를 찾을 수 없습니다.");
		} else {
			System.out.print("수정 할 비밀번호 입력 : ");
			String memberPw = sc.next();
			System.out.print("수정 할 이름 입력 : ");
			String memberName = sc.next();
			System.out.print("수정 할 전화번호 입력 [ex.010-0000-0000] : ");
			String memberPhone = sc.next();
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberName(memberName);
			member.setMemberPhone(memberPhone);
			member.setMemberPw(memberPw);
			int result = memberService.updateMember(member);
			if (result > 0) {
				System.out.println("정보 변경 성공");
			} else {
				System.out.println("정보 변경 실패");
			}
		}
	}// updateMember();
}// MemberController();
