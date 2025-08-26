package com.putin.jdbc.day02.stmt.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.putin.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "PUTINJDBC";
	private static final String PASSWORD = "PUTINJDBC";
	
	/**
	 * 회원 전체 조회
	 * @return 회원 전체 리스트
	 */
	public List<Member> selectAllMembers() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Member> mList = new ArrayList<Member>();
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL ORDER BY ENROLL_DATE";
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Member member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER").charAt(0));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
				mList.add(member);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mList;
	}
	
	/**
	 * 아이디로 회원 검색
	 * @param memberId 검색할 회원 아이디
	 * @return 검색된 회원 객체
	 */
	public Member selectMemberById(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Member member = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'";
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				member = new Member();
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberPwd(rset.getString("MEMBER_PWD"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setGender(rset.getString("GENDER").charAt(0));
				member.setAge(rset.getInt("AGE"));
				member.setEmail(rset.getString("EMAIL"));
				member.setPhone(rset.getString("PHONE"));
				member.setAddress(rset.getString("ADDRESS"));
				member.setHobby(rset.getString("HOBBY"));
				member.setEnrollDate(rset.getDate("ENROLL_DATE"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset != null) rset.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	
	/**
	 * 회원 정보 추가
	 * @param member 추가할 회원 정보
	 * @return 추가된 행의 개수
	 */
	public int insertMember(Member member) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "INSERT INTO MEMBER_TBL VALUES('" + member.getMemberId() + "', '"
						+ member.getMemberPwd() + "', '" + member.getMemberName() + "', '"
						+ member.getGender() + "', " + member.getAge() + ", '"
						+ member.getEmail() + "', '" + member.getPhone() + "', '"
						+ member.getAddress() + "', '" + member.getHobby() + "', SYSDATE)";
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 회원 정보 수정
	 * @param member 수정할 회원 정보
	 * @return 수정된 행의 개수
	 */
	public int updateMember(Member member) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "UPDATE MEMBER_TBL SET MEMBER_PWD = '" + member.getMemberPwd()
						+ "', MEMBER_NAME = '" + member.getMemberName() + "', GENDER = '" + member.getGender()
						+ "', AGE = " + member.getAge() + ", EMAIL = '" + member.getEmail()
						+ "', PHONE = '" + member.getPhone() + "', ADDRESS = '" + member.getAddress()
						+ "', HOBBY = '" + member.getHobby() + "' WHERE MEMBER_ID = '" + member.getMemberId() + "'";
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 회원 정보 삭제
	 * @param memberId 삭제할 회원 아이디
	 * @return 삭제된 행의 개수
	 */
	public int deleteMember(String memberId) {
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '" + memberId + "'";
			result = stmt.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}