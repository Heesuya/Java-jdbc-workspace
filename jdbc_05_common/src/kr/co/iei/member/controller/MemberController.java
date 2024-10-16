package kr.co.iei.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import kr.co.iei.member.model.service.MemberService;
import kr.co.iei.member.model.vo.Member;

public class MemberController {
	private Scanner sc;
	private MemberService memberService;

	public MemberController() {
		super();
		sc = new Scanner(System.in);
		memberService = new MemberService();
	}

	public void main() {
		while (true) {
			System.out.println("\n---------- 회원 관리 프로그램v5 ----------\n");
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
		System.out.print("조회 할 성별을 입력하세요[남/여] >>");
		String memberGender = sc.next();
		ArrayList<Member> list = memberService.selectMemberGender(memberGender);
		if(list.isEmpty()) {
			System.out.println("조회할 회원 정보가 없습니다.");
		}else {
			System.out.println("아이디\t이름\t전화번호\t\t성별");
			for (Member m : list) {
				System.out.println(m.getMemberId() + "\t" + m.getMemberName()
						+ m.getMemberPhone() + "\t" + m.getMemberGender());
			}
		}
	}

	private void deleteMember() {
		System.out.println("\n---------- 회원 삭제 ----------\n");
		System.out.print("삭제 할 회원 아이디 입력 : ");
		String memberId = sc.next();
		int result = memberService.deleteMember(memberId);
		if( result == -1) {
			System.out.println("회원 정보를 조회할 수 없습니다.");
		}else if(result == 0) {
			System.out.println("처리 중 문제 발생했습니다.");
		}else {
			System.out.println("회원이 삭제되었습니다.");
		}
			
	}

	private void insertMember() {
		System.out.println("\n---------- 회원 정보 등록 ----------\n");
		Member m = new Member();
		while (true) {
			System.out.print("가입 할 아이디 입력 : ");
			String memberId = sc.next();
			Member member = memberService.selectMemberId(memberId);
			if(member == null) {
				m.setMemberId(memberId);
				break;
			}else {
				System.out.println("이미 가입된 아이디입니다.");
			}
		}
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next();
		System.out.print("이름 입력 : ");
		String memberName = sc.next();
		System.out.print("전화번호 입력[ex.010-0000-0000] : ");
		String memberPhone = sc.next();
		System.out.print("나이 입력 : ");
		int memberAge = sc.nextInt();
		System.out.print("성별 입력[남/여] : ");
		String memberGender = sc.next();
		m.setMemberAge(memberAge);
		m.setMemberGender(memberGender);
		m.setMemberId(m.getMemberId());
		m.setMemberName(memberName);
		m.setMemberPhone(memberPhone);
		m.setMemberPw(memberPw);
		int result = memberService.insertMember(m);
		if (result > 0) {
			System.out.println("회원 가입 완료!");
		} else {
			System.out.println("회원 가입 실패 ㅠ");
		}
	}

	private void selectAllMember() {
		System.out.println("\n---------- 전체 회원 조회 ----------\n");
		ArrayList<Member> list = memberService.selecetAllMember();
		if (list.isEmpty()) {
			System.out.println("등록된 회원 정보가 없습니다.");
		} else {
			System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t가입일");
			for (Member m : list) {
				System.out.println(
						m.getMemberId() + "\t" + m.getMemberPw() + "\t" + m.getMemberName() + "\t" + m.getMemberPhone()
								+ "\t" + m.getMemberAge() + "\t" + m.getMemberGender() + "\t" + m.getEnrollDate());
			}
		}
	}

	private void updateMember() {
		System.out.println("\n---------- 회원 정보 수정 ----------\n");
		System.out.print("수정 할 회원 아이디 입력 : ");
		String memberId = sc.next();
		Member m = memberService.selectMemberId(memberId);
		if (m == null) {
			System.out.println("회원 정보를 조회할 수 없습니다.");
		} else {
			System.out.print("수정 할 비밀번호 입력 : ");
			String memberPw = sc.next();
			System.out.print("수정 할 이름 입력 : ");
			String memberName = sc.next();
			System.out.print("수정 할 전화번호 입력[ex.010-0000-0000] : ");
			String memberPhone = sc.next();
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberName(memberName);
			member.setMemberPw(memberPw);
			member.setMemberPhone(memberPhone);
			int result = memberService.updateMember(member);
			if (result > 0) {
				System.out.println("수정 완료!");
			} else {
				System.out.println("수정 실패!");
			}
		}
	}

	private void selectMemberId() {
		System.out.println("\n---------- 아이디로 회원 조회 ----------\n");
		System.out.print("조회 할 아이디 입력 : ");
		String memberId = sc.next();
		Member m = memberService.selectMemberId(memberId);
		if (m == null) {
			System.out.println("회원 정보를 조회할 수 없습니다.");
		} else {
			System.out.println("아이디 : " + m.getMemberId());
			System.out.println("비밀번호 : " + m.getMemberPw());
			System.out.println("이름 : " + m.getMemberName());
			System.out.println("전화번호 : " + m.getMemberPhone());
			System.out.println("나이 : " + m.getMemberAge());
			System.out.println("성별 : " + m.getMemberGender());
			System.out.println("가입일 : " + m.getEnrollDate());
		}
	}

	private void selectMemberName() {
		System.out.println("\n---------- 이름으로 회원 조회 ----------\n");
		System.out.print("조회 할 회원 아이디 입력 : ");
		String memberName = sc.next();
		ArrayList<Member> list = memberService.selecetMemberName(memberName);
		if (list.isEmpty()) {
			System.out.println("회원 정보를 조회할 수 없습니다.");
		} else {
			System.out.println("아이디\t비밀번호\t이름\t전화번호\t\t나이\t성별\t가입일");
			for (Member m : list) {
				System.out.println("아이디 : " + m.getMemberId());
				System.out.println("비밀번호 : " + m.getMemberPw());
				System.out.println("이름 : " + m.getMemberName());
				System.out.println("전화번호 : " + m.getMemberPhone());
				System.out.println("나이 : " + m.getMemberAge());
				System.out.println("성별 : " + m.getMemberGender());
				System.out.println("가입일 : " + m.getEnrollDate());
			}
		}
	}

}
