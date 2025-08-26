package com.putin.jdbc.day02.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.putin.jdbc.day02.stmt.member.model.vo.Member;

public class MemberView {
	private Scanner sc;
	
	public MemberView() {
		sc = new Scanner(System.in);
	}
	
	// Run에서 시작할 메소드 - Controller에서 호출됨
	public void startProgram() {
		// 이 메소드는 Controller에서 메뉴 로직을 처리하므로 비워둡니다.
	}
	// 아이디 검색시 아이디 입력받기
	public String inputMemberId() {
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}
	// 회원 정보 추가시 회원정보 입력받기
	public Member addMember() {
		System.out.println("====== 회원 가입 ======");
		System.out.print("아이디 : ");
		String memberId = sc.next();
		System.out.print("비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별(M/F) : ");
		char gender = sc.next().toUpperCase().charAt(0);
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		sc.nextLine(); // 개행문자 제거
		System.out.print("주소 : ");
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby);
		return member;
	}
	// 회원 정보 수정시 수정정보 입력받기
	public Member modifyMember(String memberId) {
		System.out.println("====== 회원 정보 수정 ======");
		System.out.print("수정할 비밀번호 : ");
		String memberPwd = sc.next();
		System.out.print("수정할 이름 : ");
		String memberName = sc.next();
		System.out.print("수정할 성별(M/F) : ");
		char gender = sc.next().toUpperCase().charAt(0);
		System.out.print("수정할 나이 : ");
		int age = sc.nextInt();
		System.out.print("수정할 이메일 : ");
		String email = sc.next();
		System.out.print("수정할 전화번호 : ");
		String phone = sc.next();
		sc.nextLine(); // 개행문자 제거
		System.out.print("수정할 주소 : ");
		String address = sc.nextLine();
		System.out.print("수정할 취미 : ");
		String hobby = sc.nextLine();
		
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby);
		return member;
	}
	// 회원 1개 정보 출력
	public void printOne(Member member) {
		System.out.println("====== 회원 정보 출력 ======");
		System.out.println("아이디 : " + member.getMemberId());
		System.out.println("이름 : " + member.getMemberName());
		System.out.println("성별 : " + member.getGender());
		System.out.println("나이 : " + member.getAge());
		System.out.println("이메일 : " + member.getEmail());
		System.out.println("전화번호 : " + member.getPhone());
		System.out.println("주소 : " + member.getAddress());
		System.out.println("취미 : " + member.getHobby());
		System.out.println("가입일 : " + member.getEnrollDate());
	}
	// 회원 전체 정보 출력
	public void printAllMember(List<Member>mList) {
		System.out.println("====== 회원 전체 정보 출력 ======");
		for(Member member : mList) {
			System.out.println(member.getMemberId() + "\t" + member.getMemberName() + "\t" 
					+ member.getGender() + "\t" + member.getAge() + "\t" 
					+ member.getEmail() + "\t" + member.getPhone() + "\t" 
					+ member.getAddress() + "\t" + member.getHobby() + "\t" 
					+ member.getEnrollDate());
		}
	}
	// 메시지 출력
	public void printMessage(String message) {
		System.out.println(message);
	}
	// 메뉴 출력
	public int printMenu() {
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색(아이디)");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int menu = sc.nextInt();
		return menu;
	}
}
