package kr.co.iei.member.start;

import kr.co.iei.member.controller.MemberController;

public class Start {

	public static void main(String[] args) {
		MemberController mc = new MemberController();
		mc.main();
		//쿼리문 강제로 입력하면 해킹이 가능했었음 (10년전)
	}

}
