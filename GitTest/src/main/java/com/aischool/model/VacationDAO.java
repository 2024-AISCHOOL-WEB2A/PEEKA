package com.aischool.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// DAO(Data Access Object)
// 데이터베이스 관련 기능을 관리하는 객체
// 1. Connection객체
// 2. PreparedStatement객체
// 3. ResultSet객체
public class VacationDAO {

   private Connection conn;
   private PreparedStatement pst;
   private ResultSet rs;

   // 데이터베이스 연결기능
   public void connect() {

      try {
         // 1. OracleDriver 동적 로딩
         Class.forName("oracle.jdbc.driver.OracleDriver");

         // 2. Connection객체 생성(DB연결)
         // - url, user, password 필요

         String url = "jdbc:oracle:thin:@project-db-stu3.smhrd.com:1524:xe";
         String id = "Insa5_SpringA_hacksim_1";
         String pw = "aishcool1";

         conn = DriverManager.getConnection(url, id, pw);

         if (conn != null) {
            System.out.println("Connection 성공");
         } else {
            System.out.println("Connection 실패");
         }

      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   // 데이터베이스 연결종료 기능
   public void close() {

      try {
    	  if(rs != null) {
    		  rs.close();
    	  }
    	  
    	 if(pst!=null) {
    		 pst.close();
    	 }
    	 
    	 
         if (conn != null) {
            conn.close();
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }
   
   
   
   
   // 1. 휴가 신청
   public WebMember memberLogin(WebMember member) {
	   WebMember mem = null;
	   
	   connect();
	   
	  
	   
	   try {
		String sql = "select * from MEMBER_TB where EMPID=? and PW=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, member.getEmpid());
		pst.setString(2, member.getPw());
		
		rs = pst.executeQuery();
		
		if(rs.next()) { // 조회된 정보가 있는 상태
			
			String empid = rs.getString(1);
			String pw = rs.getString(2);
			String name = rs.getString("name");
			String email = rs.getString("email");
			String birthday = rs.getString("birthday");
			String hiredate = rs.getString("hiredate");
			String phone = rs.getString("phone");
			String inlinenum = rs.getString("inlinenum");
			String deptidx = rs.getString("deptidx");
			String position = rs.getString("position");
			String joindate = rs.getString("joindate");
			String lastlogin = rs.getString("lastlogin");
			String role = rs.getString("role");
			
			mem = new WebMember(empid, pw, name, email, birthday, hiredate, phone, inlinenum, deptidx, position, joindate, lastlogin, role );
			
		// 얼굴 인식 으로 보내야함 ++	
			
			
		}
		
		
		
	} catch (SQLException e) {

		e.printStackTrace();
	}finally {
		   close();
	   }
	   
	   
	   return mem;
   } 
   
   
   
   
   
   // 2. 휴가 취소
   
   
   
   
   
   // 3. 휴가 결재
   
   
   
   
   
   
}