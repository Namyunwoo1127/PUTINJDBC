package com.putin.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.putin.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.putin.jdbc.day02.stmt.member.model.vo.Member;
import com.putin.jdbc.day02.stmt.member.view.MemberView;

public class MemberController {
	private MemberDAO dao;
	private MemberView view;
	
	public MemberController() {
		dao = new MemberDAO();
		view = new MemberView();
	}
	
	public void startProgram() {
		finish:
		while(true) {
			int menu = view.printMenu();
			switch(menu) {
			case 1:
				insertMember();
				break;
			case 2:
				selectAllMembers();
				break;
			case 3:
				selectMemberById();
				break;
			case 4:
				updateMember();
				break;
			case 5:
				deleteMember();
				break;
			case 0:
				view.printMessage("프로그램이 종료되었습니다.");
				break finish;
			default:
				view.printMessage("0 ~ 5 사이의 수를 입력하세요.");
				break;
			}
		}
	}
	
	/**
	 * 회원 전체 조회 요청
	 */
	public void selectAllMembers() {
		List<Member> mList = dao.selectAllMembers();
		if(!mList.isEmpty()) {
			view.printAllMember(mList);
		} else {
			view.printMessage("회원 정보가 존재하지 않습니다.");
		}
	}
	
	/**
	 * 아이디로 회원 검색 요청
	 */
	public void selectMemberById() {
		String memberId = view.inputMemberId();
		Member member = dao.selectMemberById(memberId);
		if(member != null) {
			view.printOne(member);
		} else {
			view.printMessage("해당 아이디의 회원이 존재하지 않습니다.");
		}
	}
	
	/**
	 * 회원 가입 요청
	 */
	public void insertMember() {
		Member member = view.addMember();
		int result = dao.insertMember(member);
		if(result > 0) {
			view.printMessage("회원가입 성공!");
		} else {
			view.printMessage("회원가입 실패!");
		}
	}
	
	/**
	 * 회원 정보 수정 요청
	 */
	public void updateMember() {
		String memberId = view.inputMemberId();
		Member member = dao.selectMemberById(memberId);
		if(member != null) {
			member = view.modifyMember(memberId);
			int result = dao.updateMember(member);
			if(result > 0) {
				view.printMessage("회원 정보 수정 성공!");
			} else {
				view.printMessage("회원 정보 수정 실패!");
			}
		} else {
			view.printMessage("해당 아이디의 회원이 존재하지 않습니다.");
		}
	}
	
	/**
	 * 회원 정보 삭제 요청
	 */
	public void deleteMember() {
		String memberId = view.inputMemberId();
		Member member = dao.selectMemberById(memberId);
		if(member != null) {
			int result = dao.deleteMember(memberId);
			if(result > 0) {
				view.printMessage("회원 정보 삭제 성공!");
			} else {
				view.printMessage("회원 정보 삭제 실패!");
			}
		} else {
			view.printMessage("해당 아이디의 회원이 존재하지 않습니다.");
		}
	}
}