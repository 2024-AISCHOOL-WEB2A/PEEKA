package com.aischool.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aischool.model.MemberDAO;
import com.aischool.model.WebMember;


/**
 * Servlet implementation class AddMember
 */
@WebServlet("/AddMember")
public class AddMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//POST 방식일 경우, 한글데이터 인코딩 설정
	      request.setCharacterEncoding("UTF-8");
	      // index.jsp에서 입력한 회원가입정보를 가져와서 콘솔창에 출력해보기!
	      
	      String empid = request.getParameter("empid");
	      String pw = request.getParameter("pw");
	      String name = request.getParameter("name");
	      String email = request.getParameter("email");
	      String birthday = request.getParameter("birthday");
	      String hiredate = request.getParameter("hiredate");
	      String phone = request.getParameter("phone");
	      String inlinenum = request.getParameter("inlinenum");
	      String deptidx = request.getParameter("deptidx");
	      String position = request.getParameter("position");
	      String joindate = request.getParameter("joindate");
	      String lastlogin = request.getParameter("lastlogin");
	      String role = request.getParameter("role");
	      

	      
	      WebMember member = new WebMember(empid, pw, name, email, birthday, hiredate, phone, inlinenum, deptidx, position, joindate, lastlogin, role );
	      
	      MemberDAO dao = new MemberDAO();
	      
	      //SQL 성공 -> 1
	      //SQL 실패 -> 0
	      int cnt = dao.memberJoin(member);
	      
	      
	      if(cnt >0) { // 회원가입 성공
	    	  
	    	  //회원성공페이지에 이메일을 출력하기 위해서 forward방식으로 이동
	    	  request.setAttribute("email", email);
	    	  
	    	  RequestDispatcher dis = request.getRequestDispatcher("joinSuccess.jsp");
	    	  dis.forward(request, response);
	    	  
	    	  
	      }else { // 회원가입 실패
	    	  
	    	  response.sendRedirect("index.jsp");
	    	  
	      }
	      
	      
	      
	   }
	

}
