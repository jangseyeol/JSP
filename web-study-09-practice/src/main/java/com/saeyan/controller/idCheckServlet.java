package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.MemberDAO;


@WebServlet("/idCheck.do")
public class idCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userid = request.getParameter("userid");
		//회원 가입 시 입력한 아이디를 얻어온다.
		
		MemberDAO mDao = MemberDAO.getInstance();
		//DAO객체를 얻어온다.
		
		int result = mDao.confirmID(userid);
		//아이디 중복 쳌를 위한 comfirmID()메소드에 아이디를 전달해 주어 결과값을 얻어온다.
		
		request.setAttribute("userid", userid);
		request.setAttribute("result", result);
		//아이디 중복 체크 후 얻어온 confirmID() 메소드의 결과값을 사용자 아이디와 함께 idCheck.jsp 페이지에 어트리뷰트에 실어 보낸다.
		
		RequestDispatcher dis = request.getRequestDispatcher("member/idCheck.jsp");
		dis.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
