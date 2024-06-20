package jdbc.member.controller;

import java.util.Scanner;

import jdbc.member.model.service.MemberService;
import jdbc.member.model.vo.Member;

public class MemberController {
	private Scanner sc;

	private MemberService service;

	public MemberController() {

		super();

		sc = new Scanner(System.in);

		service = new MemberService();

	}

	public void searchId() {

		System.out.print("조회할 회원 아이디 입력 : ");

		String memberId = sc.next();

		Member m = service.searchId(memberId);

		if (m != null) {

			System.out.println("아이디 : " + m.getMemberId());

			System.out.println("비밀번호 : " + m.getMemberPw());

			System.out.println("이름 : " + m.getMemberName());

			System.out.println("나이 : " + m.getMemberAge());

			System.out.println("전화번호 : " + m.getMemberPhone());

		} else {

			System.out.println("회원 정보를 조회할 수 없습니다.");

		}

	}

}