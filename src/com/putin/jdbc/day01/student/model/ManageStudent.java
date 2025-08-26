package com.putin.jdbc.day01.student.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class ManageStudent implements ManageInterface{
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USER = "PUTINJDBC";
	private static final String PASSWORD = "PUTINJDBC";
	
	private List<Student> sList;
	
	public ManageStudent() {
		sList = new ArrayList<Student>();
	}
	@Override
	public Student searchOneByName(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Student student = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT STUDENT_ID, FIRST_SCORE, SECOND_SCORE FROM STUDENT_TBL WHERE STUDENT_ID = '" + name + "'";
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				student = new Student();
				student.setName(rset.getString("STUDENT_ID"));
				student.setFirstScore(rset.getInt("FIRST_SCORE"));
				student.setSecondScore(rset.getInt("SECOND_SCORE"));
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
		return student;
	}
	@Override
	public int searchIndexByName(String name) {
		for(int i = 0; i < sList.size(); i++) {
			if(sList.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1; // index가 0이면 첫번째 값이기 때문에 -1로 리턴
	}
	public List<Student> getAllStudents() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM STUDENT_TBL";
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			// 후처리
			// rset에 있는 필드의 값을 Student의 필드에 하나씩 넣어줌
			// Student 객체는 List에 저장해서 보내줌
			// sList를 생성자에서 한번만 초기화하는 것이 아니라
			// getAllStudents() 메소드가 동작할 때마다 초기화해서 값이 누적되지 않도록함.
			sList = new ArrayList<Student>();
			while(rset.next()) {
				Student student = new Student();
				student.setName(rset.getString("STUDENT_ID"));
				student.setFirstScore(rset.getInt("FIRST_SCORE"));
				student.setSecondScore(rset.getInt("SECOND_SCORE"));
				sList.add(student);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return sList;
	}
	@Override
	public void addStudent(Student student) {
		// 정보가 입력된 student객체를 sList에 저장
		//sList.add(student);
		try {
			// 1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "ELONJDBC", "ELONJDBC");
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO STUDENT_TBL VALUES('"+student.getName()
															+"', "+student.getFirstScore()
															+", "+student.getSecondScore()+")";
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("학생정보 추가 완료");
			}else {
				System.out.println("학생정보 추가 실패");
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setStudent(int index, Student student) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "UPDATE STUDENT_TBL SET FIRST_SCORE = " + student.getFirstScore() 
											+ ", SECOND_SCORE = " + student.getSecondScore()
											+ " WHERE STUDENT_ID = '" + student.getName() + "'";
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("학생정보 수정 완료");
			} else {
				System.out.println("학생정보 수정 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void removeStudent(int index) {
		sList.remove(index);
	}
	@Override
	public void removeStudent(String name) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "DELETE FROM STUDENT_TBL WHERE STUDENT_ID = '" + name + "'";
			int result = stmt.executeUpdate(query);
			if(result > 0) {
				System.out.println("학생정보 삭제 완료");
			} else {
				System.out.println("학생정보 삭제 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}   finally {
		      try {
		          stmt.close();
		          conn.close();
		      } catch (SQLException e) {
		          e.printStackTrace();
		      }
		  }
	}
	
	@Override
	public void checkRetakeEligibility(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			String query = "SELECT STUDENT_ID, FIRST_SCORE, SECOND_SCORE FROM STUDENT_TBL WHERE STUDENT_ID = '" + name + "'";
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				String studentName = rset.getString("STUDENT_ID");
				int firstScore = rset.getInt("FIRST_SCORE");
				int secondScore = rset.getInt("SECOND_SCORE");
				
				// 재평가 대상 기준: 1차 또는 2차 점수가 60점 미만
				if(firstScore < 60 || secondScore < 60) {
					System.out.println("====== " + studentName + " 재평가 대상 여부 ======");
					System.out.println(studentName + " 학생은 재평가 대상입니다.");
					System.out.println("1차 점수: " + firstScore + "점, 2차 점수: " + secondScore + "점");
				} else {
					System.out.println("====== " + studentName + " 재평가 대상 여부 ======");
					System.out.println(studentName + " 학생은 재평가 대상이 아닙니다.");
					System.out.println("1차 점수: " + firstScore + "점, 2차 점수: " + secondScore + "점");
				}
			} else {
				System.out.println("해당 학생 정보가 존재하지 않습니다.");
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
	}
}






























