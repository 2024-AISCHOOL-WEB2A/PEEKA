package com.aischool.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestService
 */
@WebServlet("/TestService")
public class TestService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("첫번째 코드 생성");
		System.out.println("첫번째 코드 생성");
		
		
		System.out.println("두번째 코드 생성");
		System.out.println("두번째 코드 생성");
		
		
	}

}
