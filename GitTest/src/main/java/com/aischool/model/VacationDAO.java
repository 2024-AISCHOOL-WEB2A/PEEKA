package com.aischool.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VacationDAO {

   private Connection conn;
   private PreparedStatement pst;
   private ResultSet rs;

   // 데이터베이스 연결기능
   public void connect() {

      try {
         
         Class.forName("oracle.jdbc.driver.OracleDriver");


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
   
   
   
   
   // 1. 휴가 신청자
   public VacationTB EmpOffday (VacationTB Userempid) {
	   VacationTB offday = null;
	   
	   connect();
	   
	  
	   
	   try {
		String sql = "select * from VACATION_TB A left join MEMBER_TB B on A.USER EMPID = B.USER EMPID where EMPID=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, Userempid.getEmpid());
		
		
		rs = pst.executeQuery();
		
		if(rs.next()) { 
			
			int idx = rs.getInt("idx");	// 휴가 일수
			String empid = rs.getString(1);
			String year = rs.getString("year");
			Double Yoffday = rs.getDouble("yYoffday");
			Double Moffday = rs.getDouble("mMoffday");
			
			
			offday = new VacationTB(idx, empid, year, Yoffday, Moffday);
		
		// 얼굴 인식 으로 보내야함 ++	s
			
			
		}
		
		
		
	} catch (SQLException e) {

		e.printStackTrace();
	}finally {
		   close();
	   }
	   
	   
	   return offday;
   } 
   
   
   
   
   
   // 2. 휴가 취소
   
   
   
   
   
   // 3. 휴가 결재
   
   
   
   
   
   
}