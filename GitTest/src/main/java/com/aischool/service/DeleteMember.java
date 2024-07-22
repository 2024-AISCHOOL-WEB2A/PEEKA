package com.aischool.service;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aischool.model.MemberDAO;
import com.aischool.model.MemberDAO;

/**
 * Servlet implementation class TestService
 */
@WebServlet("/DeleteMember")
public class DeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		System.out.println("삭제할 이메일 : " + email);
		
		// 1.MemberDAO객체 생성
		MemberDAO dao = new MemberDAO();
		
		// 2. 넘겨받은 이메일을 memberDelete()에 전달
		// 반환 타입: int
		int cnt = dao.memberDelete(email);
		
		// 3. 성공 시, MemberSelectAllService로 이동
		if(cnt>0) {
			response.sendRedirect("// 사내망으로 옮겨야될 사이트 넣어야됨");
		}
		
		
	}

}