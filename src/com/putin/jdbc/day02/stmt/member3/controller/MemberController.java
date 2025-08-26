package com.putin.jdbc.day02.stmt.member3.controller;

import java.util.List;

import com.putin.jdbc.day02.stmt.member3.model.dao.MemberDAO;
import com.putin.jdbc.day02.stmt.member3.model.vo.Member;

public class MemberController {
	private MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	public Member findOneById(String memberId) {
		return mDao.selectOneById(memberId);
	}
	public List<Member> showMemberList() {
		List<Member> mList = mDao.selectList();
		return mList;
	}
	
	public int registerMember(Member member) {
		int result = mDao.insertMember(member);
		return result;
	}
	
	public int updateMember(Member member) {
		int result = mDao.updateMember(member);
		return result;
	}
	
	public int deleteMember(String memberId) {
		return mDao.deleteMember(memberId);
	}
}