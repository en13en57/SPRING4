package kr.green.member.service;

import kr.green.member.vo.MemberVO;

public interface MemberService {
	//  1. 로그 인
	MemberVO loginOk(MemberVO memberVO);
	//  2. 로그 아웃
	MemberVO logoutOk(MemberVO memberVO);
	//  3. 회원 가입
	void insert(MemberVO memberVO);
	//  4. 회원 정보 수정
	void update(MemberVO memberVO);
	//  5. 회원 탈퇴
	void delete(MemberVO memberVO);
	//  6. 비번 변경
	void updatePassword(MemberVO memberVO);
	//  7. 인증 완료
	void updateUse(MemberVO memberVO);
	//  8. 아이디 중복확인
	void idCheck(MemberVO memberVO);
	//  9. 아이디 찾기
	void idSearch(MemberVO memberVO);
	// 10. 비번 찾기
	void passwordSearch(MemberVO memberVO);
}
