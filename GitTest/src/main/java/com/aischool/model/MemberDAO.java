package com.aischool.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// DAO(Data Access Object)
// 데이터베이스 관련 기능을 관리하는 객체
// 1. Connection객체
// 2. PreparedStatement객체
// 3. ResultSet객체
public class MemberDAO {

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

   // 회원가입 기능
   public int memberJoin(WebMember member) {
	    // 1. DB연결
	    connect();
	    int cnt = 0;

	    try {
	        // Empid 중복 체크
	        String checkSql = "SELECT COUNT(*) FROM MEMBER_TB WHERE empid = ?";
	        pst = conn.prepareStatement(checkSql);
	        pst.setString(1, member.getEmpid());
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) > 0) {
	            // Empid가 이미 존재하면 -1 반환
	            return -1;
	        }

	        // 2. 데이터를 저장하는 SQL문 생성 & SQL실행
	        String sql = "INSERT INTO MEMBER_TB(empid, pw, name, email, birthday, hiredate, phone, inlinenum, deptidx, position, joindate, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        pst = conn.prepareStatement(sql);

	        pst.setString(1, member.getEmpid());
	        pst.setString(2, member.getPw());
	        pst.setString(3, member.getName());
	        pst.setString(4, member.getEmail());
	        pst.setString(5, member.getBirthday());
	        pst.setString(6, member.getHiredate());
	        pst.setString(7, member.getPhone());
	        pst.setString(8, member.getInlinenum());
	        pst.setString(9, member.getDeptidx());
	        pst.setString(10, member.getPosition());
	        pst.setString(11, member.getJoindate());
	        pst.setString(12, member.getRole());

	        // insert, update, delete SQL문을 실행할 때 사용하는 메소드
	        cnt = pst.executeUpdate();

	        // 3. SQL실행 결과처리
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 4. DB연결종료
	        close();
	    }

	    return cnt;
	}
   
   //로그인 서비스
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
			
			mem = new WebMember(empid, pw, name, email, birthday, hiredate, phone, inlinenum, deptidx, position, joindate, lastlogin, role);

		    // 현재 시간을 가져옵니다
		    LocalDateTime currentTime = LocalDateTime.now();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formattedDateTime = currentTime.format(formatter);

		    // 로그인 시간을 출력합니다
		    System.out.println("로그인 시간: " + formattedDateTime);

		    // 데이터베이스에 마지막 로그인 시간을 업데이트합니다
		    String updateSql = "UPDATE MEMBER_TB SET LASTLOGIN = ? WHERE EMPID = ?";
		    PreparedStatement updatePst = conn.prepareStatement(updateSql);
		    updatePst.setString(1, formattedDateTime);
		    updatePst.setString(2, empid);
		    updatePst.executeUpdate();
		    updatePst.close();
		    
		    // 얼굴 인식 으로 보내야함 ++	
			
			
		}
		
		
		
	} catch (SQLException e) {

		e.printStackTrace();
	}finally {
		   close();
	   }
	   
	   
	   return mem;
   } 
   
//	회원 정보 수정 나중
//   
//   public int memberUpdate(WebMember member) {
//
//	   connect();
//	   
//	   int cnt = 0;
//	   
//	   try {
//			String sql = "UPDATE WEB_MEMBER SET PW = ?, TEL = ?, ADDRESS = ? WHERE EMAIL = ?";
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, member.getPw());
//			pst.setString(2, member.getTel());
//			pst.setString(3, member.getAddress());
//			pst.setString(4, member.getEmail());
//			
//			cnt = pst.executeUpdate();
//			
//			
//			
//			
//			
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}finally {
//			   close();
//		   }
//		   
//		   
//		   return cnt;
//	   
//   }
   
   
   public int memberSelect(WebMember member) {
	   connect();
	   
	   int cnt = 0;
	   
	   
	   try {
		   while(true) {
			   String sql = "SELECT EMAIL, TEL, ADDRESS FROM MEMBER_TB";
				pst = conn.prepareStatement(sql);
				
				
				cnt = pst.executeUpdate();
		   }
			
			
			
			
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			   close();
		   }
		   
		   
		   return cnt;
	   
	   
   }
   
   
   // 전체회원정보 조회기능 
//   public ArrayList<WebMember> memberSelect(){
//	   ArrayList<WebMember> list = new ArrayList<WebMember>();
//	   
//	   connect();
//	   
//	   try {
//		   String sql = "select email, tel, address from web_member where email not in('admin')";
//		   pst = conn.prepareStatement(sql);
//		   
//		   
//		   //rs 객체에는 eamil, tel, address 3개의 컬럼을 가진 데이터 셋을 보관
//		   rs = pst.executeQuery();
//		   
//		   
//		   //셀렉 구문을 입력했을 때 다음행으로 넘어갈 때 데이터가 조회디지 않을 때까지 반복
//		   // rs.next() -> 데이터가 있으면 true / 없으면 false
//		   while(rs.next()) {
//			   
//			   String email = rs.getString(1);
//			   String tel = rs.getString(2);
//			   String address = rs.getString(3);
//			   
//			   WebMember member = new WebMember();
//			   member.setEmail(email);
//			   member.setTel(tel);
//			   member.setAddress(address);
//			   
//			   list.add(member);
//			   
//		   }
//		   
//		   
//		   
//	   } catch (SQLException e) {
//		
//		   e.printStackTrace();
//			
//			
//	}
//	   
//
//	   
//	   
//	   return list;
//   }

public int memberDelete(String empid) {
	int cnt = 0;
	
	connect();
	
	
	
	try {
		String sql = "DELETE FROM MEMBER_TB WHERE EMAIL=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, empid);
		
		cnt = pst.executeUpdate();
		
	} catch (SQLException e) {
		
		e.printStackTrace();
		
	} finally {
		close();
	}
	
	return cnt;
}
   

}
